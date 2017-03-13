package fpmi.db.adapter;

import fpmi.db.entities.Librarian;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Ihar Zharykau
 */
class LibrarianEntityAdapter implements EntityAdapter<Librarian> {
    @Override
    public Librarian convert(ResultSet resultSet) throws SQLException {
        Librarian librarian = new Librarian();
        if ( resultSet.next()) {
            String libName = resultSet.getString("lib_name");
            int libId = resultSet.getInt("lib_id");
            librarian.setId(libId);
            librarian.setName(libName);
        }
        return librarian;
    }

    @Override
    public Collection<Librarian> convertToCollection(ResultSet resultSet) throws SQLException {
        Collection<Librarian> librarians = new LinkedList<>();
        if ( resultSet.next()) {
            Librarian librarian = new Librarian();
            String libName = resultSet.getString("lib_name");
            int libId = resultSet.getInt("lib_id");
            librarian.setId(libId);
            librarian.setName(libName);
            librarians.add(librarian);
        }
        return librarians;
    }
}
