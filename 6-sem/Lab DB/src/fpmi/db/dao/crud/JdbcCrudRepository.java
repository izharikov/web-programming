package fpmi.db.dao.crud;

import fpmi.db.connection.JdbcConnection;
import fpmi.db.exception.DaoException;

import java.util.Collection;

/**
 * Jdbc extension of CRUD repository.<br>
 * Added methods to user them with opened {@link JdbcConnection}
 *
 * @author Ihar Zharykau
 */
public interface JdbcCrudRepository<T> extends CRUDRepository<T> {
    /**
     * See {@link #read(int)}
     *
     * @param connection connection object
     * @param id         id of entity
     * @return entity object
     * @throws DaoException error occurred
     * @see #read(int)
     */
    T read(JdbcConnection connection, int id) throws DaoException;

    /**
     * See {@link #create(Object)}
     *
     * @param connection connection object
     * @param object     object to persist
     * @return id of created object
     * @throws DaoException error occurred
     * @see #create(Object)
     */
    int create(JdbcConnection connection, T object) throws DaoException;

    /**
     * See {@link #update(Object)}
     *
     * @param connection connection object
     * @param object     object to update
     * @throws DaoException error occurred
     * @see #update(Object)
     */
    void update(JdbcConnection connection, T object) throws DaoException;

    /**
     * See {@link #delete(Object)}
     *
     * @param connection connection object
     * @param object     object to delete
     * @throws DaoException error occurred
     * @see #delete(Object)
     */
    void delete(JdbcConnection connection, T object) throws DaoException;

    /**
     * See {@link #delete(int)}
     *
     * @param connection connection object
     * @param id         id of object to delete
     * @throws DaoException error occurred
     * @see #delete(int)
     */
    void delete(JdbcConnection connection, int id) throws DaoException;

    /**
     * See {@link #checkExist(Object)}
     *
     * @param connection connection object
     * @param object     object to check existing
     * @return true - if exists, else - otherwise
     * @throws DaoException error occured
     * @see #checkExist(Object)
     */
    boolean checkExist(JdbcConnection connection, T object) throws DaoException;

    /**
     * See {@link #queryAll()}
     *
     * @param connection connection object
     * @return collection of objects
     * @throws DaoException error occurred
     * @see #queryAll()
     */
    Collection<T> queryAll(JdbcConnection connection) throws DaoException;
}
