package test.jpa;

import fpmi.db.entities.Author;
import fpmi.db.entities.Book;

import javax.ejb.Remote;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
@Remote
public interface JpaRepositoryRemote {
    List<Book> allBooks();
    List<Author> allAuthors();
}
