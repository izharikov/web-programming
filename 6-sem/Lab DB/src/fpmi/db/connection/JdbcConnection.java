package fpmi.db.connection;

import fpmi.db.exception.DaoException;
import fpmi.db.exception.JdbcException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ihar Zharykau
 */
public class JdbcConnection {
    private Connection connection;

    public JdbcConnection() {
    }

    public JdbcConnection(Connection connection) {
        this.connection = connection;
    }

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
