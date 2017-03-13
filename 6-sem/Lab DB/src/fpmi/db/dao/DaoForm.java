package fpmi.db.dao;

import fpmi.db.adapter.EntityAdapter;
import fpmi.db.adapter.EntityAdapterFactory;
import fpmi.db.connection.JdbcConnection;
import fpmi.db.entities.Form;

import java.sql.*;

/**
 * @author Ihar Zharykau
 */
class DaoForm extends DaoBase<Form> {
    private static final EntityAdapter<Form> FORM_ENTITY_ADAPTER = EntityAdapterFactory.formEntityAdapter();

    DaoForm() {
        super(FORM_ENTITY_ADAPTER);
    }


    private static final String SELECT_STATEMENT =
            "SELECT\n" +
                    "  f.id         AS form_id,\n" +
                    "  f.start_date AS start_date,\n" +
                    "  f.end_date   AS end_date,\n" +
                    "  l.name       AS lib_name,\n" +
                    "  u.name       AS user_name,\n" +
                    "  f.lib_id     AS lib_id,\n" +
                    "  f.user_id    AS user_id,\n" +
                    "  b.name       AS book_name,\n" +
                    "  f.book_id    AS book_id,\n" +
                    "  b.count_of_books,\n" +
                    "  c.id         AS catalog_id,\n" +
                    "  c.name       AS catalog_name\n" +
                    "FROM form_tbl f INNER JOIN lib_tbl l ON f.lib_id = l.id\n" +
                    "  INNER JOIN user_tbl u ON u.id = f.user_id\n" +
                    "  INNER JOIN book_tbl b ON b.id = f.book_id\n" +
                    "  INNER JOIN catalog_tbl c ON b.catalog_id = c.id WHERE f.id = ?;";

    private static final String UPDATE_STATEMENT = "UPDATE form_tbl SET start_date = ?, end_date = ?, lib_id = ?, user_id = ?, book_id = ? WHERE id = ?;";
    private static final String INSERT_STATEMENT = "INSERT INTO form_tbl (start_date, end_date, lib_id, user_id, book_id) VALUES (?,?,?,?,?);";
    private static final String DELETE_STATEMENT = "DELETE FROM librarian_tbl WHERE id = ?;";

    private static final String SELECT_ALL_STATEMENT = "SELECT\n" +
            "  f.id         AS form_id,\n" +
            "  f.start_date AS start_date,\n" +
            "  f.end_date   AS end_date,\n" +
            "  l.name       AS lib_name,\n" +
            "  u.name       AS user_name,\n" +
            "  f.lib_id     AS lib_id,\n" +
            "  f.user_id    AS user_id,\n" +
            "  b.name       AS book_name,\n" +
            "  f.book_id    AS book_id,\n" +
            "  b.count_of_books,\n" +
            "  c.id         AS catalog_id,\n" +
            "  c.name       AS catalog_name\n" +
            "FROM form_tbl f INNER JOIN lib_tbl l ON f.lib_id = l.id\n" +
            "  INNER JOIN user_tbl u ON u.id = f.user_id\n" +
            "  INNER JOIN book_tbl b ON b.id = f.book_id\n" +
            "  INNER JOIN catalog_tbl c ON b.catalog_id = c.id;";

    @Override
    protected PreparedStatement selectSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement existSqlStatement(JdbcConnection connection, Form object) throws SQLException {
        return selectSqlStatement(connection, object.getId());
    }

    @Override
    protected PreparedStatement queryAllStatement(JdbcConnection connection) throws SQLException {
        return connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
    }

    @Override
    protected PreparedStatement insertSqlStatement(JdbcConnection connection, Form entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
        fillStatementWithData(preparedStatement, entity);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement updateSqlStatement(JdbcConnection connection, Form entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        fillStatementWithData(preparedStatement, entity);
        preparedStatement.setInt(6, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, Form entity) throws SQLException {
        return deleteSqlStatement(connection, entity.getId());
    }

    private static void fillStatementWithData(PreparedStatement statement, Form entity) throws SQLException {
        statement.setDate(1, new Date(entity.getStartDate().getTime()));
        statement.setDate(2, new Date(entity.getEndDate().getTime()));
        if (entity.getLibrarian() != null) {
            statement.setInt(3, entity.getLibrarian().getId());
        } else {
            statement.setNull(3, Types.INTEGER);
        }
        if (entity.getUser() != null) {
            statement.setInt(4, entity.getUser().getId());
        } else {
            statement.setNull(4, Types.INTEGER);
        }
        if (entity.getBook() != null) {
            statement.setInt(5, entity.getBook().getId());
        } else {
            statement.setNull(5, Types.INTEGER);
        }
    }
}
