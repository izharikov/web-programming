package fpmi.business.repository.jpa;

import fpmi.business.exception.RepositoryException;
import fpmi.business.repository.LibraryRepository;
import fpmi.db.dao.jpa.JpaDaoBase;
import fpmi.db.entities.*;
import fpmi.utils.StringUtils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class JpaLibraryRepository implements LibraryRepository {

    public JpaLibraryRepository() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Catalog");
        daoBase = new JpaDaoBase(emfactory.createEntityManager());
    }

    protected JpaDaoBase daoBase;

    @Override
    public void start() {
        daoBase.start();
    }

    @Override
    public void end() {
        daoBase.end();
    }

    @Override
    public void createNewUser(String userName, String password) throws RepositoryException {
        User user = new User();
        user.setName(userName);
        user.setPassword(StringUtils.cryptWithMD5(password));
        user.setRole(Role.USUAL);
        daoBase.create(user);
    }

    @Override
    public boolean loginUser(String userName, String password) throws RepositoryException {
        return loginUser(userName, password, Role.USUAL);
    }

    @Override
    public boolean loginUser(String userName, String password, Role role) throws RepositoryException {
        User user = new User();
        user.setName(userName);
        user.setPassword(StringUtils.cryptWithMD5(password));
        user.setRole(role);
        return daoBase.checkUserExist(user);
    }

    @Override
    public int getUserId(String login) {
        return daoBase.getUserId(login);
    }

    @Override
    public List<User> deptUsers(int days) throws RepositoryException {
        return daoBase.deptsUsers(days);
    }

    @Override
    @Deprecated
    public void giveBookToUser(Book book, User user, Librarian librarian) throws RepositoryException {

    }

    @Override
    public void giveBookToUser(Book book, User user, User librarian) throws RepositoryException {
        daoBase.giveBookToUser(book, user, librarian);
    }

    @Override
    public void giveBookToUser(int bookId, int userId, int libId) throws RepositoryException {
        Book book = daoBase.read(bookId, Book.class);
        User user = daoBase.read(userId, User.class);
        User librarian = daoBase.read(libId, User.class);
        daoBase.giveBookToUser(book, user, librarian);
    }

    @Override
    public Collection<Book> getAllBooks() throws RepositoryException {
        return daoBase.queryAll(Book.class);
    }

    @Override
    public Collection<Book> getBooksByAuthor(int id) throws RepositoryException {
        return daoBase.booksByAuthor(id);
    }

    @Override
    public Collection<Author> getAllAuthors() throws RepositoryException {
        return daoBase.queryAll(Author.class);
    }

    @Override
    public Collection<User> getAllUsers() throws RepositoryException {
        return daoBase.queryUsersByRole(Role.USUAL);
    }

    @Override
    public Collection<Form> getAllForms() throws RepositoryException {
        return daoBase.queryAll(Form.class);
    }
}
