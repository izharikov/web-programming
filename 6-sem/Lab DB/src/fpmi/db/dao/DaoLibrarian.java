package fpmi.db.dao;

import fpmi.db.adapter.EntityAdapter;
import fpmi.db.adapter.EntityAdapterFactory;
import fpmi.db.connection.JdbcConnection;
import fpmi.db.entities.Librarian;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ihar Zharykau
 */
class DaoLibrarian extends DaoBase<Librarian> {

    private static final EntityAdapter<Librarian> LIBRARIAN_ENTITY_ADAPTER = EntityAdapterFactory.librarianEntityAdapter();

    private static final String SELECT_STATEMENT = "select id as lib_id, name as lib_name from lib_tbl where id = ?;";
    private static final String UPDATE_STATEMENT = "update lib_tbl set name = ? where id = ?;";
    private static final String INSERT_STATEMENT = "insert into lib_tbl (name) values (?);";
    private static final String DELETE_STATEMENT = "delete from lib_tbl where id = ?;";
    private static final String SELECT_ALL_STATEMENT = "select * from lib_tbl";

    DaoLibrarian() {
        super(LIBRARIAN_ENTITY_ADAPTER);
    }

    @Override
    protected PreparedStatement selectSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement existSqlStatement(JdbcConnection connection, Librarian object) throws SQLException {
        return selectSqlStatement(connection, object.getId());
    }

    @Override
    protected PreparedStatement queryAllStatement(JdbcConnection connection) throws SQLException {
        return connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
    }

    @Override
    protected PreparedStatement insertSqlStatement(JdbcConnection connection, Librarian entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement updateSqlStatement(JdbcConnection connection, Librarian entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, Librarian entity) throws SQLException {
        return deleteSqlStatement(connection, entity.getId());
    }
}
