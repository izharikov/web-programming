package by.bsu.simplesocket.server.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Singleton (see: http://habrahabr.ru/post/129494/)
 */
public enum ConnectionsKeeper {

    INSTANCE;
    private final Map<String, ServerThread> connectionsMap;

    private ConnectionsKeeper() {
        connectionsMap = new HashMap<>();
    }

    public void addServerThreadForUser(String userName, ServerThread serverThread) {
        connectionsMap.put(userName, serverThread);
    }

    public ServerThread getServerThreadByUserName(String userName) {
        return connectionsMap.get(userName);
    }

    public void removeUser(String userName) {
        connectionsMap.remove(userName);
    }

    public boolean userAlreadyConnected(String userName) {
        return connectionsMap.containsKey(userName);
    }

    public Set<String> getConnectedUsers() {
        return Collections.unmodifiableSet(connectionsMap.keySet());
    }

}
