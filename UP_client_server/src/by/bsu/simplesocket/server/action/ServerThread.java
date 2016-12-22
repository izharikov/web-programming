package by.bsu.simplesocket.server.action;

import by.bsu.simplesocket.common.entity.*;
import by.bsu.simplesocket.common.enums.ResponseInfo;
import by.bsu.simplesocket.common.exception.SimpleSocketException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(ServerThread.class.getName());

    private ObjectOutputStream os;
    private ObjectInputStream is;
    private InetAddress address;
    private String userName;
    private static Set<Socket> users;
    private static List<ObjectInputStream> inputStreams = new ArrayList<>();
    private static List<ObjectOutputStream> outputStreams = new ArrayList<>();

    public ServerThread(Socket socket) throws SimpleSocketException {
        try {
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
            inputStreams.add(is);
            outputStreams.add(os);
            address = socket.getInetAddress();
        } catch (IOException ex) {
            throw new SimpleSocketException(ex);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                MessageRequest request = (MessageRequest) is.readObject();
                boolean haveToDisconnect = false;
                switch (request.getMessageType()) {
                    case CONNECT_REQUEST:
                        boolean connected = tryConnectUser((ConnectMessageRequest) request);
                        haveToDisconnect = !connected;
                        break;
                    case DISCONNECT_REQUEST:
                        haveToDisconnect = true;
                        break;
                    case MESSAGE_REQUEST:
                        prepareAndSendResponseToClients((ChatMessageRequest) request);
                        break;
                }
                if (haveToDisconnect) {
                    disconnect();
                    return;
                }
            }
        } catch (SimpleSocketException ex) {
            LOGGER.log(Level.SEVERE, "Disconnect...", ex);
        } catch (ClassNotFoundException | IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            // disconnect();
        }
    }

    private static void sendResponseForParticularClients(ChatMessageResponse response) throws SimpleSocketException {

    }

    private static void sendResponseForAllClients(MessageResponse response) throws SimpleSocketException {
        try {
            //os.writeObject(response);
            for (ObjectOutputStream os : outputStreams) {
                os.writeObject(response);
            }
        } catch (IOException ex) {
            throw new SimpleSocketException(ex);
        }
    }

    private void sendResponseToClient(MessageResponse response) throws SimpleSocketException {
        try {
            os.writeObject(response);
        } catch (IOException ex) {
            throw new SimpleSocketException(ex);
        }
    }

    private boolean tryConnectUser(ConnectMessageRequest request) throws SimpleSocketException {
        boolean result = false;
        String userName = request.getUserName();
        this.userName = "";
        ConnectMessageResponse response;
        synchronized (ConnectionsKeeper.INSTANCE) {
            boolean alreadyConnected = ConnectionsKeeper.INSTANCE.userAlreadyConnected(userName);
            if (alreadyConnected) {
                response = new ConnectMessageResponse(ResponseInfo.USER_ALREADY_CONNECTED, userName);
            } else {
                this.userName = userName;
                ConnectionsKeeper.INSTANCE.addServerThreadForUser(userName, this);
                response = new ConnectMessageResponse(ResponseInfo.OK, userName);
                LOGGER.log(Level.INFO, "User \"{0}\" is connected [host = {1}]", new Object[]{userName, address.getHostName()});
                result = true;
                sendResponseForAllClients(response);
            }
        }
        sendResponseToClient(response);
        return result;
    }

    private void disconnect() throws SimpleSocketException {
        try {
            synchronized (ConnectionsKeeper.INSTANCE) {
                if (userName != null) {
                    ConnectionsKeeper.INSTANCE.removeUser(userName);
                }
            }
            //os.writeObject(new DisconnectMessageResponse(ResponseInfo.OK, userName));
            sendResponseForAllClients(new DisconnectMessageResponse(ResponseInfo.OK, userName));
            os.close();
            is.close();
            outputStreams.remove(os);
            inputStreams.remove(is);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Something wrong with streams closing", ex);
        } finally {
            LOGGER.log(Level.INFO, "\"{0}\" is disconnected", address.getHostName());
            this.interrupt();
        }
    }

    private void prepareAndSendResponseToClients(ChatMessageRequest request) throws SimpleSocketException {
        String clientText = request.getHelloText();
        String userName = request.getUserName();
        MessageResponse response;
        if (clientText == null || clientText.isEmpty()) {
            LOGGER.log(Level.INFO, "\"{0}\" send empty hello text", request.getUserName());
            response = new ErrorMessage(ResponseInfo.EMPTY_HELLO_TEXT_ERROR);
        } else {
            LOGGER.log(Level.INFO, "\"{0}\" send: \"{1}\"", new Object[]{request.getUserName(), clientText});
            //String serverText = String.format(">>> Server received from you: %s", clientText);
            String serverText = clientText;
            response = new ChatMessageResponse(ResponseInfo.OK, serverText, userName);
        }
        sendResponseForAllClients(response);
    }

    public static void addClient(Socket socket) {
        users.add(socket);
    }

}
