package fpmi.db.adapter;

import fpmi.db.entities.Author;
import fpmi.db.entities.Book;
import fpmi.db.entities.Catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Ihar Zharykau
 */
class AuthorEntityAdapter implements EntityAdapter<Author> {
    @Override
    public Author convert(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setBooks(new LinkedList<>());
        while (resultSet.next()){
            convertAuthor(resultSet, author);
        }
        return author;
    }

    @Override
    public Collection<Author> convertToCollection(ResultSet resultSet) throws SQLException {
        Collection<Author> result = new LinkedList<>();
        int id = -1;
        int prevId = -1;
        Author author = new Author();
        while (resultSet.next()) {
            id = resultSet.getInt("author_id");
            if (id == prevId) {
                convertAuthor(resultSet, author);
            } else {
                if (prevId != -1) {
                    result.add(author);
                }
                author = new Author();
                author.setBooks(new LinkedList<>());
                author.setId(id);
                author.setName(resultSet.getString("author_name"));
                convertAuthor(resultSet, author);
            }
            prevId = id;
        }
        result.add(author);
        return result;
    }

    private void convertAuthor(ResultSet resultSet, Author author) throws SQLException {
        Book b = new Book();
        b.setId(resultSet.getInt("book_id"));
        b.setName(resultSet.getString("book_name"));
        Catalog catalog = new Catalog();
        catalog.setId(resultSet.getInt("catalog_id"));
        catalog.setName(resultSet.getString("catalog_name"));
        b.setCatalog(catalog);
        b.setCountOfBooks(resultSet.getInt("count_of_books"));
        author.getBooks().add(b);
    }
}
