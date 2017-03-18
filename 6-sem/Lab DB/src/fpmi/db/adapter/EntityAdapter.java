package fpmi.db.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Class to convert {@link ResultSet} to specified entity object
 *
 * @author Ihar Zharykau
 */
public interface EntityAdapter<T> {
    /**
     * Convert {@link ResultSet} to object
     *
     * @param resultSet queried result set
     * @return converted object
     * @throws SQLException when parsing result set goes wrong
     */
    T convert(ResultSet resultSet) throws SQLException;

    /**
     * Convert result set to collection of objects
     *
     * @param resultSet queried result set
     * @return converted collection of objects
     * @throws SQLException when parsing result set goes wrong
     */
    Collection<T> convertToCollection(ResultSet resultSet) throws SQLException;
}
