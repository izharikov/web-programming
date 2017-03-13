package fpmi.db.dao;

import fpmi.db.adapter.EntityAdapter;
import fpmi.db.adapter.EntityAdapterFactory;
import fpmi.db.connection.JdbcConnection;
import fpmi.db.entities.Catalog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ihar Zharykau
 */
class DaoCatalog extends DaoBase<Catalog> {
    private static final EntityAdapter<Catalog> CATALOG_ENTITY_ADAPTER = EntityAdapterFactory.catalogEntityAdapter();

    DaoCatalog() {
        super(CATALOG_ENTITY_ADAPTER);
    }

    private static final String SELECT_SQL_STATEMENT =
            "select b.name as book_name, b.id as book_id, b.count_of_books, c.id as catalog_id, " +
                    "c.name as catalog_name from " +
                    "catalog_tbl c, book_tbl b where c.id = ? and c.id = b.catalog_id;";
    private static final String SELECT_ALL_SQL_STATEMENT =
            "select b.name as book_name, b.id as book_id, b.count_of_books, c.id as catalog_id, " +
                    "c.name as catalog_name from " +
                    "catalog_tbl c, book_tbl b where c.id = b.catalog_id;";
    private static final String INSERT_SQL_STATEMENT =
            "insert into catalog_tbl (name) values(?);";

    private static final String DELETE_SQL_STATEMENT =
            "delete from catalog_tbl where id = ?;";

    private static final String UPDATE_SQL_STATEMENT =
            "update catalog_tbl set name = ? where id = ?;";

    private static final String EXIST_STATEMENT = "select * from catalog_tbl where id = ?;";

    @Override
    protected PreparedStatement selectSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_SQL_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement existSqlStatement(JdbcConnection connection, Catalog object) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(EXIST_STATEMENT);
        preparedStatement.setInt(1, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement queryAllStatement(JdbcConnection connection) throws SQLException {
        return connection.getConnection().prepareStatement(SELECT_ALL_SQL_STATEMENT);
    }

    @Override
    protected PreparedStatement insertSqlStatement(JdbcConnection connection, Catalog entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(INSERT_SQL_STATEMENT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement updateSqlStatement(JdbcConnection connection, Catalog entity) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(UPDATE_SQL_STATEMENT);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(DELETE_SQL_STATEMENT);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteSqlStatement(JdbcConnection connection, Catalog entity) throws SQLException {
        return deleteSqlStatement(connection, entity.getId());
    }
}
