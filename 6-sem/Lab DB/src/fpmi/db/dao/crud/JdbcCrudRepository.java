package fpmi.db.dao.crud;

import fpmi.db.connection.JdbcConnection;
import fpmi.db.exception.DaoException;

import java.util.Collection;

/**
 * @author Ihar Zharykau
 */
public interface JdbcCrudRepository<T> extends CRUDRepository<T> {
    T read(JdbcConnection connection, int id) throws DaoException;
    int create(JdbcConnection connection, T object) throws DaoException;
    void update(JdbcConnection connection, T object) throws DaoException;
    void delete(JdbcConnection connection, T object) throws DaoException;
    void delete(JdbcConnection connection, int id) throws DaoException;
    boolean checkExist(JdbcConnection connection, T object) throws DaoException;
    Collection<T> queryAll(JdbcConnection connection) throws DaoException;
}
