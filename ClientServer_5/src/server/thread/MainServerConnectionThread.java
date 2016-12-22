package server.thread;

import common.Request;
import common.Response;
import common.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.config.ServerProperties;
import server.keeper.ConnectionsKeeper;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Class, that monitors client's connections
 *
 * @author Ihar Zharykau
 */
public class MainServerConnectionThread extends Thread {
    private static Logger log = LogManager.getLogger(MainServerConnectionThread.class.getName());

    @Override
    public void run() {
        try (ServerSocket serv = new ServerSocket(ServerProperties.getPort())) {
            log.info("Server is initialized successfully.");
            while (true) {
                log.info("Listening...");
                Socket socket = serv.accept();
                log.info(socket.getInetAddress().getHostName() + " connected");
                new ConnectClient(socket).start();
            }
        } catch (Exception ex) {
            log.error("Error", ex);
        }
    }

    /**
     * Thread, that used to connect/disconnect particular client
     */
    class ConnectClient extends Thread {
        private Socket socket;
        private ObjectInputStream is;
        private ObjectOutputStream os;

        public ConnectClient(Socket socket) throws IOException {
            this.socket = socket;
            is = new ObjectInputStream(socket.getInputStream());
            os = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            log.debug("Run ConnectClient");
            while (true) {
                try {
                    Request request = (Request) is.readObject();
                    if (request != null) {
                        if (tryConnectUser(request, socket)) {
                            os.writeObject(new Response(Status.OK));
                            break;
                        } else {
                            os.writeObject(new Response(Status.USER_ALREADY_CONNECTED));
                        }
                    }
                } catch (Exception e) {
                    log.error(e);
                }
            }
            log.info("Client connected");
            while (true) {
                log.debug("waiting for disconnect request");
                try {
                    is = new ObjectInputStream(socket.getInputStream());
                    Request request = (Request) is.readObject();
                    if (request != null && request.getType() == Request.Type.DISCONNECT) {
                        log.info("Client disconnected");
                        ConnectionsKeeper.INSTANCE.removeUser(request.getUserName());
                        break;
                    }
                } catch (Exception e) {
                    log.error("ERROR", e);
                }
            }
            log.info("exiting ConnectServer thread");
        }

        /**
         * Trying to connect user
         *
         * @param request request from user
         * @param socket  socket between client and server
         * @return true, if connected, false otherwise
         */
        public boolean tryConnectUser(Request request, Socket socket) {
            String userName = request.getUserName();
            boolean enable = !ConnectionsKeeper.INSTANCE.userAlreadyConnected(userName);
            if (enable) {
                ConnectionsKeeper.INSTANCE.addServerSocketForUser(userName, socket);
            }
            return enable;
        }
    }

}
