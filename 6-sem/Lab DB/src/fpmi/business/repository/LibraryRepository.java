package fpmi.business.repository;

import fpmi.business.exception.RepositoryException;
import fpmi.db.entities.*;

import java.util.Collection;
import java.util.List;

/**
 * Base interface with all needed operations in it. <br>Business level of application. <br><br>
 * Many methods throws {@link RepositoryException} when smth goes wrong in database or
 * problems with user input data detected
 *
 * @author Ihar Zharykau
 */
public interface LibraryRepository {
    /**
     * To start using this interface call this method
     */
    void start();

    /**
     * When all operations with this object done, call this method
     * to destroy (close) some dependencies like closing {@link javax.persistence.EntityManager}
     */
    void end();

    /**
     * Create new user with role {@link Role#USUAL}
     *
     * @param userName user name
     * @param password user password
     * @throws RepositoryException if something goes wrong, like empty userName or password, or
     *                             problems with storing user in database
     */
    void createNewUser(String userName, String password) throws RepositoryException;

    /**
     * System is trying to login user with {@link Role#USUAL}
     *
     * @param userName user name
     * @param password user password
     * @return true - if success login (user with this name and password already exists), false - otherwise
     * @throws RepositoryException if something goes wrong in database
     */
    boolean loginUser(String userName, String password) throws RepositoryException;

    /**
     * System is trying to login user
     *
     * @param userName user name
     * @param password user password
     * @param role     user role
     * @return true - if success login (user with this name, password and role already exists), false - otherwise
     * @throws RepositoryException if something goes wrong in database
     */
    boolean loginUser(String userName, String password, Role role) throws RepositoryException;

    /**
     * Find user by login and returns id
     *
     * @param login user login
     * @return id of user
     */
    int getUserId(String login);

    /**
     * Calculate all users with dept in get back books
     *
     * @param days period of dept
     * @return list of dept users
     * @throws RepositoryException if something goes wrong in database
     */
    List<User> deptUsers(int days) throws RepositoryException;

    /**
     * This method is deprecated, user {@link #giveBookToUser(Book, User, User)} or {@link #giveBookToUser(int, int, int)}
     * instead of this
     *
     * @param book      book
     * @param user      user
     * @param librarian librarian
     * @throws RepositoryException
     */
    @Deprecated
    void giveBookToUser(Book book, User user, Librarian librarian) throws RepositoryException;

    /**
     * Gives specified book to user
     *
     * @param book      book
     * @param user      user with role {@link Role#USUAL}
     * @param librarian user with role {@link Role#ADMIN}
     * @throws RepositoryException
     */
    void giveBookToUser(Book book, User user, User librarian) throws RepositoryException;

    /**
     * Gives specified book to user
     *
     * @param bookId book id
     * @param userId user id with role {@link Role#USUAL}
     * @param libId  user id with role {@link Role#ADMIN}
     * @throws RepositoryException
     */
    void giveBookToUser(int bookId, int userId, int libId) throws RepositoryException;

    /**
     * @return all books
     * @throws RepositoryException
     */
    Collection<Book> getAllBooks() throws RepositoryException;

    /**
     * @param id id of author
     * @return all books of specified author
     * @throws RepositoryException
     */
    Collection<Book> getBooksByAuthor(int id) throws RepositoryException;

    /**
     * @return all authors
     * @throws RepositoryException
     */
    Collection<Author> getAllAuthors() throws RepositoryException;

    /**
     * @return all users
     * @throws RepositoryException
     */
    Collection<User> getAllUsers() throws RepositoryException;

    /**
     * @return all forms
     * @throws RepositoryException
     */
    Collection<Form> getAllForms() throws RepositoryException;
}
