package test.jpa;

import fpmi.db.entities.Author;
import fpmi.db.entities.Book;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
@Local
public interface JpaRepositoryLocal {
    List<Book> allBooks();
    List<Author> allAuthors();
}
