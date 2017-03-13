package fpmi.db.adapter;

import fpmi.db.entities.Book;
import fpmi.db.entities.Catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Ihar Zharykau
 */
class BookEntityAdapter implements EntityAdapter<Book> {

    @Override
    public Book convert(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        if ( resultSet.next()) {
            book.setId(resultSet.getInt("book_id"));
            book.setName(resultSet.getString("book_name"));
            book.setCountOfBooks(resultSet.getInt("count_of_books"));
            Catalog catalog = new Catalog();
            catalog.setId(resultSet.getInt("catalog_id"));
            catalog.setName(resultSet.getString("catalog_name"));
            book.setCatalog(catalog);
        }
        return book;
    }

    @Override
    public Collection<Book> convertToCollection(ResultSet resultSet) throws SQLException {
        Collection<Book> result = new LinkedList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("book_id"));
            book.setName(resultSet.getString("book_name"));
            book.setCountOfBooks(resultSet.getInt("count_of_books"));
            Catalog catalog = new Catalog();
            catalog.setName(resultSet.getString("catalog_name"));
            catalog.setId(resultSet.getInt("catalog_id"));
            book.setCatalog(catalog);
            result.add(book);
        }
        return result;
    }
}
