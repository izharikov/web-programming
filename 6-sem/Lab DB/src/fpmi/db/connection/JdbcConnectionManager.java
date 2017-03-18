package fpmi.db.connection;

import fpmi.db.exception.JdbcException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Connection manager for creating {@link JdbcConnection}
 *
 * @author Ihar Zharykau
 */
public class JdbcConnectionManager {

    private static JdbcConnectionManager instance = new JdbcConnectionManager();

    private static final String CONF_FILE_NAME = "database";
    private static final String URL_PROPERTY_NAME = "url";
    private static final String DRIVER_PROPERTY_NAME = "driver";
    private static final String PASSWORD_PROPERTY_NAME = "password";
    private static final String USER_PROPERTY_NAME = "user";

    /**
     * creating new connection to database
     *
     * @return created connection
     * @throws JdbcException if troubles with establishing connection
     */
    public JdbcConnection getConnection() throws JdbcException {
        ResourceBundle resource = ResourceBundle.getBundle(CONF_FILE_NAME);
        String url = resource.getString(URL_PROPERTY_NAME);
        String driver = resource.getString(DRIVER_PROPERTY_NAME);
        String user = resource.getString(USER_PROPERTY_NAME);
        String pass = resource.getString(PASSWORD_PROPERTY_NAME);
        try {
            Class.forName(driver).newInstance();
            return new JdbcConnection(DriverManager.getConnection(url, user, pass));
        } catch (ClassNotFoundException e) {
            throw new JdbcException("Driver Class Not Found", e);
        } catch (InstantiationException | IllegalAccessException | SQLException e) {
            throw new JdbcException(e);
        }
    }

    public static JdbcConnectionManager getInstance() {
        return instance;
    }

}
