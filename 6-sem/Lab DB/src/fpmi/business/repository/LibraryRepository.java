package fpmi.business.repository;

import fpmi.business.exception.RepositoryException;
import fpmi.db.entities.*;

import java.util.Collection;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
public interface LibraryRepository {
    void start();
    void end();
    void createNewUser(String userName, String password) throws RepositoryException;

    boolean loginUser(String userName, String password) throws RepositoryException;
    boolean loginUser(String userName, String password, Role role) throws RepositoryException;
    int getUserId(String login);
    List<User> deptUsers(int days) throws RepositoryException;

    @Deprecated
    void giveBookToUser(Book book, User user, Librarian librarian) throws RepositoryException;

    void giveBookToUser(Book book, User user, User librarian) throws RepositoryException;

    void giveBookToUser(int bookId, int userId, int libId) throws RepositoryException;

    Collection<Book> getAllBooks() throws RepositoryException;

    Collection<Book> getBooksByAuthor(int id) throws RepositoryException;

    Collection<Author> getAllAuthors() throws RepositoryException;

    Collection<User> getAllUsers() throws RepositoryException;

    Collection<Form> getAllForms() throws RepositoryException;
}
