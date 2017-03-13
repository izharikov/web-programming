package fpmi.db.dao;

import fpmi.db.adapter.EntityAdapter;
import fpmi.db.connection.JdbcConnection;
import fpmi.db.connection.JdbcConnectionManager;
import fpmi.db.dao.crud.JdbcCrudRepository;
import fpmi.db.exception.DaoException;
import fpmi.db.exception.JdbcException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Ihar Zharykau
 */
abstract class DaoBase<T> implements JdbcCrudRepository<T> {

    protected abstract PreparedStatement selectSqlStatement(JdbcConnection connection, int id) throws SQLException;

    protected abstract PreparedStatement existSqlStatement(JdbcConnection connection, T object) throws SQLException;

    protected abstract PreparedStatement queryAllStatement(JdbcConnection connection) throws SQLException;

    protected abstract PreparedStatement insertSqlStatement(JdbcConnection connection, T entity) throws SQLException;

    protected abstract PreparedStatement updateSqlStatement(JdbcConnection connection, T entity) throws SQLException;

    protected abstract PreparedStatement deleteSqlStatement(JdbcConnection connection, int id) throws SQLException;

    protected abstract PreparedStatement deleteSqlStatement(JdbcConnection connection, T entity) throws SQLException;

    protected JdbcConnectionManager connectionManager;
    private EntityAdapter<T> entityAdapter;

    protected DaoBase(EntityAdapter<T> entityAdapter) {
        connectionManager = new JdbcConnectionManager();
        this.entityAdapter = entityAdapter;
    }

    @Override
    public final T read(int id) throws DaoException {
        JdbcConnection connection = new JdbcConnection();
        try {
            connection = connectionManager.getConnection();
            return read(connection, id);
        } catch (JdbcException e) {
            throw new DaoException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public T read(JdbcConnection connection, int id) throws DaoException {
        try {
            connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = selectSqlStatement(connection, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return entityAdapter.convert(resultSet);
        } catch (JdbcException | SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public final void delete(int id) throws DaoException {
        JdbcConnection connection = new JdbcConnection();
        try {
            connection = connectionManager.getConnection();
            delete(connection, id);
        } catch (JdbcException e) {
            throw new DaoException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public void delete(JdbcConnection connection, int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = deleteSqlStatement(connection, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean checkExist(T object) throws DaoException {
        boolean exist = false;
        JdbcConnection connection = new JdbcConnection();
        try {
            connection = connectionManager.getConnection();
            exist = checkExist(connection, object);
        } catch (JdbcException e) {
            throw new DaoException(e);
        }
        return exist;
    }

    @Override
    public Collection<T> queryAll() throws DaoException {
        JdbcConnection connection = new JdbcConnection();
        Collection<T> result = new ArrayList<T>();
        try {
            connection = connectionManager.getConnection();
            result = queryAll(connection);
        } catch (JdbcException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Collection<T> queryAll(JdbcConnection connection) throws DaoException {
        try {
            PreparedStatement preparedStatement = queryAllStatement(connection);
            ResultSet resultSet = preparedStatement.executeQuery();
            return entityAdapter.convertToCollection(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean checkExist(JdbcConnection connection, T object) throws DaoException {
        boolean exist = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = existSqlStatement(connection, object);
            ResultSet resultSet = preparedStatement.executeQuery();
            if ( resultSet.next()){
                exist = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return exist;
    }

    @Override
    public final int create(T object) throws DaoException {
        JdbcConnection connection = new JdbcConnection();
        try {
            connection = connectionManager.getConnection();
            return create(connection, object);
        } catch (JdbcException e) {
            throw new DaoException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public int create(JdbcConnection connection, T object) throws DaoException {
        try {
            PreparedStatement preparedStatement = insertSqlStatement(connection, object);
            preparedStatement.execute();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return -1;
    }

    @Override
    public final void update(T object) throws DaoException {
        JdbcConnection connection = new JdbcConnection();
        try {
            connection = connectionManager.getConnection();
            update(connection, object);
        } catch (JdbcException e) {
            throw new DaoException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(JdbcConnection connection, T object) throws DaoException {
        try {
            PreparedStatement preparedStatement = updateSqlStatement(connection, object);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void delete(T object) throws DaoException {
        JdbcConnection connection = new JdbcConnection();
        try {
            connection = connectionManager.getConnection();
            delete(connection, object);
        } catch (JdbcException e) {
            throw new DaoException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public void delete(JdbcConnection connection, T object) throws DaoException {
        try {
            PreparedStatement preparedStatement = deleteSqlStatement(connection, object);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
