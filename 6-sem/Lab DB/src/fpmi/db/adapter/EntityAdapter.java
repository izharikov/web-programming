package fpmi.db.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author Ihar Zharykau
 */
public interface EntityAdapter<T> {
    T convert(ResultSet resultSet) throws SQLException;
    Collection<T> convertToCollection(ResultSet resultSet) throws SQLException;
}
