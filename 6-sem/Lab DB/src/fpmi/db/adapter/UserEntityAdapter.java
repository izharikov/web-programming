package fpmi.db.adapter;

import fpmi.db.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Ihar Zharykau
 */
class UserEntityAdapter implements EntityAdapter<User> {
    @Override
    public User convert(ResultSet resultSet) throws SQLException {
        User user = new User();
        if ( resultSet.next()) {
            user.setId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("user_name"));
        }
        return user;
    }

    @Override
    public Collection<User> convertToCollection(ResultSet resultSet) throws SQLException {
        Collection<User> result = new LinkedList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString("user_name"));
            user.setId(resultSet.getInt("user_id"));
            result.add(user);
        }
        return result;
    }
}
