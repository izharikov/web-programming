package server.thread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Thread, that send message to client
 *
 * @author Ihar Zharykau
 */
public class SendMessageThread extends Thread {
    private Socket socket;
    private String message;

    public SendMessageThread(Socket socket, String message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
            objOut.writeObject(message);
        } catch (IOException e) {

        }
    }
}
