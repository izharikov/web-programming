package fpmi.db.dao.crud;

import fpmi.db.exception.DaoException;

import java.util.Collection;

/**
 * Interface with base operations (CRUD : Create, Read, Update, Delete)
 *
 * @author Ihar Zharykau
 */
public interface CRUDRepository<T> {
    /**
     * Read object by id
     *
     * @param id id of object
     * @return object
     * @throws DaoException error occurred
     */
    T read(int id) throws DaoException;

    /**
     * Persists object in database
     *
     * @param object object to persist
     * @return id of created object
     * @throws DaoException error occurred
     */
    int create(T object) throws DaoException;

    /**
     * Update object in database
     *
     * @param object object to update
     * @throws DaoException error occurred
     */
    void update(T object) throws DaoException;

    /**
     * Delete object from database
     *
     * @param object object to delete
     * @throws DaoException error occurred
     */
    void delete(T object) throws DaoException;

    /**
     * Delete object from database by it's id
     *
     * @param id id of object to delete
     * @throws DaoException error occurred
     */
    void delete(int id) throws DaoException;

    /**
     * Extension: check object exists in database
     *
     * @param object checked object
     * @return true - if exists, false - otherwise
     * @throws DaoException error occurred
     */
    boolean checkExist(T object) throws DaoException;

    /**
     * Extension: querying all objects from database
     *
     * @return collection of all objects
     * @throws DaoException error occurred
     */
    Collection<T> queryAll() throws DaoException;
}
