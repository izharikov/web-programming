package fpmi.db.dao;

import fpmi.db.adapter.EntityAdapter;
import fpmi.db.adapter.EntityAdapterFactory;
import fpmi.db.connection.JdbcConnection;
import fpmi.db.entities.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ihar Zharykau
 */
class DaoBook extends DaoBase<Book> {

    private static final EntityAdapter<Book> BOOK_ENTITY_ADAPTER = EntityAdapterFactory.bookEntityAdapter();

    DaoBook() {
        super(BOOK_ENTITY_ADAPTER);
    }

    private static final String SELECT_STATEMENT =
            "SELECT b.id AS book_id, b.name AS book_name, b.count_of_books, c.name AS catalog_name, c.id AS catalog_id FROM " +
                    "book_tbl b, catalog_tbl c WHERE b.id = ? AND c.id = b.catalog_id;";
    private static final String INSERT_STATEMENT = "INSERT INTO book_tbl (name, catalog_id, count_of_books) " +
            "VALUES (?, ?, ?);";
    private static final String UPDATE_STATEMENT = "UPDATE book_tbl SET name = ?, " +
            "catalog_id = ?, count_of_books = ? WHERE id = ?;";
    private static final String DELETE_STATEMENT = "DELETE FROM book_tbl WHERE id = ?;";

    private static final String EXIST_STATEMENT = "select * from book_tbl where id = ?;";
    private static final String QUERY_ALL_STATEMENT = "SELECT\n" +
            "  b.id   AS book_id,\n" +
            "  b.name AS book_name,\n" +
            "  b.count_of_books,\n" +
            "  c.name AS catalog_name,\n" +
            "  c.id   AS catalog_id\n" +
            "FROM\n" +
            "  book_tbl b INNER JOIN catalog_tbl c\n" +
            "    ON c.id = b.catalog_id;";

    @Override
    protected PreparedStatement selectSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement existSqlStatement(JdbcConnection connection, Book object) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(EXIST_STATEMENT);
        preparedStatement.setInt(1, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement queryAllStatement(JdbcConnection connection) throws SQLException {
        return connection.getConnection().
                prepareStatement(QUERY_ALL_STATEMENT);
    }

    @Override
    protected PreparedStatement insertSqlStatement(JdbcConnection connection, Book entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().
                prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getName());
        if (entity.getCatalog() != null) {
            preparedStatement.setInt(2, entity.getCatalog().getId());
        } else {
            preparedStatement.setNull(2, java.sql.Types.INTEGER);
        }
        preparedStatement.setInt(3, entity.getCountOfBooks());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement updateSqlStatement(JdbcConnection connection, Book entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        preparedStatement.setString(1, entity.getName());
        if (entity.getCatalog() != null) {
            preparedStatement.setInt(2, entity.getCatalog().getId());
        } else {
            preparedStatement.setNull(2, java.sql.Types.INTEGER);
        }
        preparedStatement.setInt(3, entity.getCountOfBooks());
        preparedStatement.setInt(4, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, Book entity) throws SQLException {
        return deleteSqlStatement(connection, entity.getId());
    }
}
