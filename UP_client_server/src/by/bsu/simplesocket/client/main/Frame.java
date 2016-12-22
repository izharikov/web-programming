package by.bsu.simplesocket.client.main;

import by.bsu.simplesocket.client.action.ClientThread;
import by.bsu.simplesocket.client.config.ClientProperties;
import by.bsu.simplesocket.common.entity.ChatMessageRequest;
import by.bsu.simplesocket.common.entity.ConnectMessageRequest;
import by.bsu.simplesocket.common.entity.DisconnectMessageRequest;
import by.bsu.simplesocket.common.entity.MessageResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class Frame extends JFrame{

    private static final String QUIT_COMMAND = ".quit";
    public JPanel mainTextArea;

    public Frame(String username, ObjectInputStream is, ObjectOutputStream os){
        super("Chat");
        try {
            setBounds(50, 50, 500, 415);

            mainTextArea = new JPanel();
            mainTextArea.setLayout(new BoxLayout(mainTextArea, BoxLayout.Y_AXIS));
            JScrollPane sp = new JScrollPane(mainTextArea);
            sp.setBounds(20, 20, 445, 260);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            add(sp);

            JTextField mesgTextField = new JTextField();
            mesgTextField.setBounds(20, 320, 360, 20);

            JLabel mesgLabel = new JLabel("Your message: ");
            mesgLabel.setBounds(20, 290, 120, 20);
            add(mesgLabel);

            //os.writeObject(new ConnectMessageRequest(username));
            //MessageResponse response = (MessageResponse) is.readObject();
            //mainTextArea.setText(mainTextArea.getText() + "\n" + response);
            ClientThread clientThread = new ClientThread(is, mainTextArea);

            JButton sendButton = new JButton("Send for All");
            sendButton.setBounds(390, 320, 70, 20);
            class CustomActionListener implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
                    String message = mesgTextField.getText();

                    if (message.equals("")) {
                        mesgLabel.setForeground(Color.RED);
                    } else {
                        mesgLabel.setForeground(Color.BLACK);
                        try {
                            os.writeObject(new ChatMessageRequest(username, message));
                            mesgTextField.setText("");
                        } catch (IOException ex) {
                            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            sendButton.addActionListener(new CustomActionListener());
            add(sendButton);
            add(mesgTextField);
            mesgTextField.addActionListener(new CustomActionListener());

            JButton quitButton = new JButton("Exit");
            quitButton.setBounds(360, 350, 100, 20);
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clientThread.setSearch(false);
                    try {
                        os.writeObject(new DisconnectMessageRequest(username));
                        System.exit(0);
                    } catch (IOException ex) {
                        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            add(quitButton);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    if (clientThread.getSearch()) {
                        clientThread.setSearch(false);
                        try {
                            os.writeObject(new DisconnectMessageRequest(username));
                        } catch (IOException ex) {
                            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            getContentPane().setLayout(null);
            setVisible(true);
        }
        catch (Exception  ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
