package server.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.keeper.ConnectionsKeeper;
import server.thread.MainServerConnectionThread;
import server.thread.SendMessageThread;

import java.net.Socket;
import java.util.Scanner;
import java.util.Set;

/**
 * Main class of Sever
 *
 * @author Ihar Zharykau
 */
public class ServerMain {
    public static Socket DISCONNECT_SOCKET;
    private static Logger log = LogManager.getLogger(MainServerConnectionThread.class.getName());

    // list if commands:
    private static final String LIST_CONNECTIONS = "-l";
    private static final String PRINT_HELP = "-h";
    private static final String SEND_MESSAGES = "-s";
    private static final String EXIT_PROGRAM = "-q";

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String... args) {
        MainServerConnectionThread connectionThread = new MainServerConnectionThread();
        connectionThread.start();
        while (true) {
            System.out.println("Choose command (use -h for help):");
            readAndExecute();
        }
    }

    /**
     * read command and execute it
     */
    private static void readAndExecute() {
        String strCommandWithParams = sc.nextLine();
        log.info("User inputs command : " + strCommandWithParams);
        if (strCommandWithParams.length() >= 2) {
            String cmd = strCommandWithParams.substring(0, 2);
            switch (cmd) {
                case LIST_CONNECTIONS:
                    printClients();
                    break;
                case PRINT_HELP:
                    System.out.println(HELP_MESSAGE);
                    break;
                case SEND_MESSAGES:
                    sendMessages(strCommandWithParams);
                    break;
                case EXIT_PROGRAM:
                    System.exit(0);
                default:
                    System.out.println(UNKNOWN);
                    System.out.println(HELP_MESSAGE);
            }
        }
    }

    /**
     * send message to clients
     *
     * @param strCommand user's input
     */
    private static void sendMessages(String strCommand) {
        String[] params = strCommand.substring(2).trim().split("-m|-u");
        String message = params[1].trim();
        String[] users = params[2].trim().split(" +");
        for (String userName : users) {
            sendMessageToUser(userName, message);
        }
    }

    /**
     * send message to particular client
     *
     * @param userName client name
     * @param message  message to send
     */
    private static void sendMessageToUser(String userName, String message) {
        Socket socket = ConnectionsKeeper.INSTANCE.getServerSocketByUsername(userName);
        SendMessageThread sendMessageThread = new SendMessageThread(socket, message);
        sendMessageThread.start();
    }

    /**
     * print all online clients
     */
    private static void printClients() {
        Set<String> users = ConnectionsKeeper.INSTANCE.getConnectedUsers();
        for (String user : users) {
            System.out.println(user);
        }
        if (users.size() == 0) {
            System.out.println("Empty user list");
        }
    }

    //  messages:
    private static final String UNKNOWN = "You entered unknown command. See available commands:";
    private static final String HELP_MESSAGE = "List of available commands:\n" +
            "\t-l : list available clients\n" +
            "\t-h : print this message\n" +
            "\t-s -m [message] -u [names of clients : send [message] to clients";
}
