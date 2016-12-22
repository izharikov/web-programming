package by.bsu.simplesocket.server.main;

import by.bsu.simplesocket.common.exception.SimpleSocketException;
import by.bsu.simplesocket.server.action.ServerThread;
import by.bsu.simplesocket.server.config.ServerProperties;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {

    public static void main(String[] args) {
        try (ServerSocket serv = new ServerSocket(ServerProperties.getPort())) {
            System.out.println("Server is initialized successfully.");
            while (true) {
                System.out.println("Listening...");
                Socket socket = serv.accept();
                System.out.println(socket.getInetAddress().getHostName() + " connected");
                ServerThread connectionThread = new ServerThread(socket);
                connectionThread.start();
            }
        } catch (SimpleSocketException | IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
