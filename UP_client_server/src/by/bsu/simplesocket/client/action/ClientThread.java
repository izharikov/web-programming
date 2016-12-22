package by.bsu.simplesocket.client.action;

import by.bsu.simplesocket.common.entity.DisconnectMessageResponse;
import by.bsu.simplesocket.common.entity.MessageResponse;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientThread implements Runnable{
    private Thread thread;
    private ObjectInputStream is;
    private boolean search = true;
    private JPanel panel;

    public ClientThread(ObjectInputStream is, JPanel panel) {
        this.is = is;
        this.panel = panel;
        thread = new Thread(this);
        thread.start();
    }

    public void setSearch(boolean search) {
        this.search = search;
    }
    public boolean getSearch() {
        return  this.search;
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        MessageResponse response;
        try {
            while (search) {
                if ( (response = (MessageResponse) is.readObject()) != null) {
                    if ( response instanceof DisconnectMessageResponse && response.getUserName().length() == 0){
                        continue;
                    }
                    panel.add(new JLabel(response.toStringForClient().toString()));
                    panel.revalidate();
                }
            }
        }
        catch (IOException | ClassNotFoundException ex){
            //ex.printStackTrace();
        }
    }
}
