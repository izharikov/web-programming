package fpmi.db.dao.crud;

import fpmi.db.connection.JdbcConnection;
import fpmi.db.exception.DaoException;

import java.util.Collection;

/**
 * @author Ihar Zharykau
 */
public interface CRUDRepository<T> {
    T read(int id) throws DaoException;
    int create(T object) throws DaoException;
    void update(T object) throws DaoException;
    void delete(T object) throws DaoException;
    void delete(int id) throws DaoException;
    boolean checkExist(T object) throws DaoException;
    Collection<T> queryAll() throws DaoException;
}
