package by.bsu.simplesocket.client.main;

import by.bsu.simplesocket.client.action.ClientThread;
import by.bsu.simplesocket.client.config.ClientProperties;
import by.bsu.simplesocket.common.entity.*;
import by.bsu.simplesocket.common.enums.ResponseInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMain {
    private static final String QUIT_COMMAND = ".quit";

    public static void main(String[] args) {

        JFrame startFrame = new JFrame("Chat");
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startFrame.setBounds(100, 100, 300, 200);
        startFrame.getContentPane().setLayout(null);

        JTextField usernameText = new JTextField();
        usernameText.setBounds(80, 80, 130, 20);
        usernameText.setVisible(true);
        startFrame.add(usernameText);

        JLabel entUsernameLabel = new JLabel("Enter username:");
        entUsernameLabel.setBounds(98, 50, 130, 20);
        startFrame.add(entUsernameLabel);

        JButton startButton = new JButton("Start!");
        startButton.setBounds(105, 110, 80, 20);
        startFrame.add(startButton);
        String serverHost = ClientProperties.getServerHost();
        int serverPort = ClientProperties.getServerPort();
        class CustomActionListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                if (username.equals("")) {
                    entUsernameLabel.setForeground(Color.red);
                } else {
                    try {
                        Socket socket = new Socket(serverHost, serverPort);

                        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                        os.writeObject(new ConnectMessageRequest(username));
                        MessageResponse response = (MessageResponse) is.readObject();
                        if ( response.getInfo().equals(ResponseInfo.USER_ALREADY_CONNECTED)){
                            JOptionPane.showMessageDialog(startFrame,
                                    "User with this name already exists.\nPlease, choose another name.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JFrame mainFrame = new Frame(username, is, os);
                            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            startFrame.dispose();
                        }
                    }
                    catch (IOException | ClassNotFoundException ex){
                        System.out.println("exception in action perfomed:");
                        ex.printStackTrace();
                    }
                }
            }
        }
        startButton.addActionListener(new CustomActionListener());
        usernameText.addActionListener(new CustomActionListener());
        startFrame.setVisible(true);

    }
}

