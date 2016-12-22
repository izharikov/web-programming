package server.config;

import java.util.ResourceBundle;

/**
 * Class, that keeps server's properties
 */
public class ServerProperties {

    /**
     * properties file name
     */
    public static final String PROPERTIES_FILE = "server_config";
    private static final ResourceBundle BUNDLE;

    /**
     * loading resources
     */
    static {
        BUNDLE = ResourceBundle.getBundle(PROPERTIES_FILE);
    }

    public ServerProperties() {
    }

    /**
     * get property value
     * @param property {@link Property} object
     * @param defaultValue default value of property
     * @return value
     */
    public static String getProperty(Property property, String defaultValue) {
        String key = property.getKey();
        return BUNDLE.containsKey(key) ? BUNDLE.getString(key) : defaultValue;
    }

    /**
     * get server port
     * @return port
     */
    public static Integer getPort() {
        String portString = getProperty(Property.PORT, "8080");
        return Integer.valueOf(portString);
    }

    /**
     * properties of server
     */
    public enum Property {

        PORT("port"), CLIENTS_FILE("clientsFileOutput");

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
