package client;

import client.config.ClientProperties;
import common.Request;
import common.Response;
import common.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Main class for client
 *
 * @author Ihar Zharykau
 */
public class ClientMain {
    private static String name;
    private static Logger log = LogManager.getLogger(ClientMain.class.getName());

    private static final Scanner sc = new Scanner(System.in);
    private static Socket socket;

    public static void main(String... args) {
        try {
            tryConnect();
            new DisconnectMonitorThread().start();
            while (true) {
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                Object obj = is.readObject();
                if (obj != null) {
                    System.out.println(obj);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error("Error", e);
        }
    }

    /**
     * Method, that tries to connect to server
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void tryConnect() throws IOException, ClassNotFoundException {
        System.out.println("Enter your name: ");
        name = sc.nextLine();
        if (name != null && name.trim().length() > 0) {
            int serverPort = ClientProperties.getServerPort();
            String serverHost = ClientProperties.getServerHost();
            socket = new Socket(serverHost, serverPort);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.flush();
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            os.writeObject(new Request(name, Request.Type.CONNECT));
            Response response = (Response) is.readObject();
            while (response == null) {

            }
            if (response.getStatus().equals(Status.USER_ALREADY_CONNECTED)) {
                System.out.println("User with the same name already exists. Please, enter another name");
                tryConnect();
            }
            if (response.getStatus().equals(Status.OK)) {
                System.out.println("Success connection with server");
            }
        } else {
            System.out.println("Name couldn't be empty. Please, reenter.");
            tryConnect();
        }
    }

    /**
     * Class, that monitors user's input, and if input string <br>
     * equals '-quit', disconnect from server and exit app
     */
    private static class DisconnectMonitorThread extends Thread {
        static final Scanner sc = new Scanner(System.in);

        @Override
        public void run() {
            try {
                while (true) {
                    String inputString = sc.nextLine();
                    if (inputString != null && inputString.equals("-quit")) {
                        ObjectOutputStream is = new ObjectOutputStream(socket.getOutputStream());
                        is.writeObject(new Request(name, Request.Type.DISCONNECT));
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                log.error("ERROR", e);
            }
        }
    }
}
