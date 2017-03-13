package fpmi.db.dao;

import fpmi.db.adapter.EntityAdapter;
import fpmi.db.adapter.EntityAdapterFactory;
import fpmi.db.connection.JdbcConnection;
import fpmi.db.entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ihar Zharykau
 */
class DaoUser extends DaoBase<User> {
    private static final EntityAdapter<User> USER_ENTITY_ADAPTER = EntityAdapterFactory.userEntityAdapter();

    DaoUser() {
        super(USER_ENTITY_ADAPTER);
    }

    private static final String SELECT_STATEMENT = "SELECT id AS user_id, name AS user_name FROM user_tbl WHERE id = ?;";
    private static final String INSERT_STATEMENT = "INSERT INTO user_tbl (name, password) VALUES (?,?);";
    private static final String UPDATE_STATEMENT = "UPDATE user_tbl SET name = ?, password = ? WHERE id = ?;";
    private static final String DELETE_STATEMENT = "DELETE FROM user_tbl WHERE id = ?;";
    private static final String EXISTS_STATEMENT = "SELECT id FROM user_tbl WHERE name = ? AND password = ?;";
    private static final String QUERY_ALL_STATEMENT = "SELECT id AS user_id, name AS user_name FROM user_tbl;";

    @Override
    protected PreparedStatement selectSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement existSqlStatement(JdbcConnection connection, User object) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(EXISTS_STATEMENT);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getPassword());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement queryAllStatement(JdbcConnection connection) throws SQLException {
        return connection.getConnection().prepareStatement(QUERY_ALL_STATEMENT);
    }

    @Override
    protected PreparedStatement insertSqlStatement(JdbcConnection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getPassword());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement updateSqlStatement(JdbcConnection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setInt(3, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, User entity) throws SQLException {
        return deleteSqlStatement(connection, entity.getId());
    }
}
