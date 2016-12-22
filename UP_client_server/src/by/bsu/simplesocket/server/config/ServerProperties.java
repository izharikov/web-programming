package by.bsu.simplesocket.server.config;

import java.util.ResourceBundle;

public class ServerProperties {

    public static final String PROPERTIES_FILE = "server_config";
    private static final ResourceBundle BUNDLE;

    static {
        BUNDLE = ResourceBundle.getBundle(PROPERTIES_FILE);
    }

    public ServerProperties() {
    }

    public static String getProperty(Property property, String defaultValue) {
        String key = property.getKey();
        return BUNDLE.containsKey(key) ? BUNDLE.getString(key) : defaultValue;
    }

    public static Integer getPort() {
        String portString = getProperty(Property.PORT, "8080");
        return Integer.valueOf(portString);
    }

    public static enum Property {

        PORT("port");

        private final String key;

        private Property(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        @Override
        public String toString() {
            return key;
        }
    }
}
