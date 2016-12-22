package server.keeper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.config.ServerProperties;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Class, that keeps all connections <br>
 * Singleton (see: http://habrahabr.ru/post/129494/)
 */
public enum ConnectionsKeeper {
    INSTANCE;
    Logger log;
    private final Map<String, Socket> connectionsMap;
    ObjectOutputStream os;
    ObjectInputStream is;
    private static File CLIENTS_FILE;

    ConnectionsKeeper() {
        init();
        connectionsMap = new HashMap<>();
        writeCollection();
    }

    /**
     * init saving connections to file
     */
    public void init() {
        try {
            CLIENTS_FILE = new File(ServerProperties.getProperty(ServerProperties.Property.CLIENTS_FILE, "o.bin"));
            if (!CLIENTS_FILE.exists()) {
                CLIENTS_FILE.createNewFile();
            }
            os = new ObjectOutputStream(new FileOutputStream(CLIENTS_FILE));
            os.flush();
            log = LogManager.getLogger(ConnectionsKeeper.class.getName());
        } catch (IOException e) {

        }
    }

    /**
     * write client's names to file
     */
    private void writeCollection() {
        try {
            os = new ObjectOutputStream(new FileOutputStream(CLIENTS_FILE));
            HashSet set = new HashSet();
            set.addAll(connectionsMap.keySet());
            os.writeObject(set);
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error("ERROR", e);
        }
    }

    /**
     * push new user to map
     *
     * @param userName name of user
     * @param socket   socket
     */
    public void addServerSocketForUser(String userName, Socket socket) {
        connectionsMap.put(userName, socket);
        writeCollection();
    }

    /**
     * retrieve socket by user name
     *
     * @param userName name of user
     * @return socket of particular user
     */
    public Socket getServerSocketByUsername(String userName) {
        return connectionsMap.get(userName);
    }

    /**
     * remove user from map
     *
     * @param userName usrr name
     */
    public void removeUser(String userName) {
        connectionsMap.remove(userName);
        writeCollection();
    }

    /**
     * determine, is user connected
     *
     * @param userName name of user
     * @return true, if connected, false - otherwise
     */
    public boolean userAlreadyConnected(String userName) {
        return connectionsMap.containsKey(userName);
    }

    public Set<String> getConnectedUsers() {
        return Collections.unmodifiableSet(connectionsMap.keySet());
    }


}
