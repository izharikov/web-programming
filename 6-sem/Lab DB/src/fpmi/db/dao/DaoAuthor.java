package fpmi.db.dao;

import fpmi.db.adapter.EntityAdapter;
import fpmi.db.adapter.EntityAdapterFactory;
import fpmi.db.connection.JdbcConnection;
import fpmi.db.entities.Author;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ihar Zharykau
 */
class DaoAuthor extends DaoBase<Author> {

    private static EntityAdapter<Author> authorEntityAdapter = EntityAdapterFactory.getAuthorEntityAdapter();

    DaoAuthor() {
        super(authorEntityAdapter);
    }

    private static final String SELECT_STATEMENT = "SELECT \n" +
            "  b.name AS book_name, \n" +
            "  b.id   AS book_id, \n" +
            "  b.count_of_books, \n" +
            "  c.id   AS catalog_id, \n" +
            "  c.name AS catalog_name, \n" +
            "  a.name AS author_name, \n" +
            "  a.id   AS author_id \n" +
            "FROM catalog_tbl c INNER JOIN book_tbl b ON c.id = b.catalog_id \n" +
            "  INNER JOIN author_tbl a ON a.id = b.autor_id WHERE a.id = ? " +
            "ORDER BY author_id;";

    private static final String SELECT_ALL_STATEMENT = "SELECT\n" +
            "  b.name AS book_name,\n" +
            "  b.id   AS book_id,\n" +
            "  b.count_of_books,\n" +
            "  c.id   AS catalog_id,\n" +
            "  c.name AS catalog_name,\n" +
            "  a.name AS author_name,\n" +
            "  a.id   AS author_id\n" +
            "FROM catalog_tbl c INNER JOIN book_tbl b ON c.id = b.catalog_id\n" +
            "  INNER JOIN author_tbl a ON a.id = b.autor_id\n" +
            "ORDER BY author_id;";

    private static final String INSERT_STATEMENT = "insert into author_tbl (name) values ?;";

    private static final String UPDATE_STATEMENT = "update author_tbl set name = ? where id = ?;";

    private static final String DELETE_STATEMENT = "delete from author_tbl where id = ?;";

    private static final String EXISTS_STATEMENT = "select * from author_tbl where id = ?;";


    @Override
    protected PreparedStatement selectSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement existSqlStatement(JdbcConnection connection, Author object) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(EXISTS_STATEMENT);
        preparedStatement.setInt(1, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement queryAllStatement(JdbcConnection connection) throws SQLException {
        return connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
    }

    @Override
    protected PreparedStatement insertSqlStatement(JdbcConnection connection, Author entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement updateSqlStatement(JdbcConnection connection, Author entity) throws SQLException {
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
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, Author entity) throws SQLException {
        return deleteSqlStatement(connection, entity.getId());
    }
}
