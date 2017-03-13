package fpmi.db.adapter;

import fpmi.db.entities.Book;
import fpmi.db.entities.Catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author Ihar Zharykau
 */
class CatalogEntityAdapter implements EntityAdapter<Catalog> {

    private EntityAdapter<Book> bookEntityAdapter = EntityAdapterFactory.bookEntityAdapter();

    @Override
    public Catalog convert(ResultSet resultSet) throws SQLException {
        Catalog catalog = new Catalog();
        while (resultSet.next()){
            if ( catalog.getName() == null){
                catalog.setName(resultSet.getString("catalog_name"));
                catalog.setId(resultSet.getInt("catalog_id"));
                catalog.setBooks(new HashSet<>());
            }
            Book book = bookEntityAdapter.convert(resultSet);
            catalog.getBooks().add(book);
        }
        return catalog;
    }

    @Override
    public Collection<Catalog> convertToCollection(ResultSet resultSet) throws SQLException {
        Collection<Catalog> catalogs = new LinkedList<>();
        while (resultSet.next()){
            Catalog catalog = new Catalog();
            if ( catalog.getName() == null){
                catalog.setName(resultSet.getString("catalog_name"));
                catalog.setId(resultSet.getInt("catalog_id"));
                catalog.setBooks(new HashSet<>());
            }
            Book book = bookEntityAdapter.convert(resultSet);
            catalog.getBooks().add(book);
            catalogs.add(catalog);
        }
        return catalogs;
    }
}
