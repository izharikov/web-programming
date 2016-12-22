package by.bsu.simplesocket.client.main;

import java.util.Scanner;

/**
 * @author Ihar Zharykau
 */
public class ClientConsole {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

    }

    private static Command readCommand(){
        String strCommand = sc.nextLine();
        String commandParams[] = strCommand.split(" ");
        return Command.parse(commandParams[0]);
    }

    enum Command{
        SEND_MESSAGE, EXIT, HELP, UNKNOWN, LIST_CLIENTS;

        public static Command parse(String strCommand){
            switch (strCommand){
                case "-s":
                    return SEND_MESSAGE;
                case "-q":
                    return EXIT;
                case "-h":
                    return HELP;
                case "-l":
                    return LIST_CLIENTS;
                default:
                    return UNKNOWN;
            }
        }
    }
}
