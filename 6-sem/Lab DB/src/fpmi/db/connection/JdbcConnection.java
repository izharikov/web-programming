package fpmi.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Jdbc Connection - wrapped {@link Connection} for easy managing it
 *
 * @author Ihar Zharykau
 */
public class JdbcConnection {
    private Connection connection;

    public JdbcConnection() {
    }

    public JdbcConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * close the connection
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
