package fpmi.business.repository;

import fpmi.business.exception.RepositoryException;
import fpmi.db.connection.JdbcConnection;
import fpmi.db.connection.JdbcConnectionManager;
import fpmi.db.dao.JdbcDaoFactory;
import fpmi.db.dao.crud.JdbcCrudRepository;
import fpmi.db.entities.*;
import fpmi.db.exception.DaoException;
import fpmi.db.exception.JdbcException;
import fpmi.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static fpmi.business.exception.ExceptionCode.*;

/**
 * @author Ihar Zharykau
 */
public class LibraryRepositoryImpl implements LibraryRepository{

    private static Logger LOGGER = LogManager.getLogger(LibraryRepositoryImpl.class);

    private static final int MAX_MONTH = 2;

    private static final JdbcConnectionManager CONNECTION_MANAGER = new JdbcConnectionManager();

    private JdbcCrudRepository<Book> bookCRUDRepository = JdbcDaoFactory.getBookJdbcCrudRepository();
    private JdbcCrudRepository<Catalog> catalogCRUDRepository = JdbcDaoFactory.getCatalogJdbcCrudRepository();
    private JdbcCrudRepository<User> userCRUDRepository = JdbcDaoFactory.getUserJdbcCrudRepository();
    private JdbcCrudRepository<Form> formCRUDRepository = JdbcDaoFactory.getFormJdbcCrudRepository();
    private JdbcCrudRepository<Librarian> librarianCRUDRepository = JdbcDaoFactory.getLibrarianJdbcCrudRepository();
    private JdbcCrudRepository<Author> authorCRUDRepository = JdbcDaoFactory.getAuthorJdbcCrudRepository();

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void createNewUser(String userName, String password) throws RepositoryException {
        if (validateUsernameAnsPassword(userName, password)) {
            try {
                User user = new User();
                user.setName(userName);
                user.setPassword(StringUtils.cryptWithMD5(password));
                userCRUDRepository.create(user);
                LOGGER.debug("New user created");
            } catch (DaoException e) {
                throw new RepositoryException(e, DB_SYSTEM_ERROR);
            }
        } else {
            throw new RepositoryException(USER_INPUT_ERROR);
        }
    }

    protected static boolean validateUsernameAnsPassword(String userName, String password) {
        return StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password);
    }

    @Override
    public boolean loginUser(String userName, String password) throws RepositoryException {
        boolean successLogin = false;
        if (validateUsernameAnsPassword(userName, password)) {
            User user = new User();
            user.setName(userName);
            user.setPassword(StringUtils.cryptWithMD5(password));
            try {
                successLogin = userCRUDRepository.checkExist(user);
                if (successLogin) {
                    LOGGER.info("User success logged in " + userName);
                } else {
                    LOGGER.debug("User invalid log in " + userName);
                }
            } catch (DaoException e) {
                throw new RepositoryException(e, DB_SYSTEM_ERROR);
            }
        }
        return successLogin;
    }

    @Override
    public boolean loginUser(String userName, String password, Role role) throws RepositoryException {
        return false;
    }

    @Override
    public int getUserId(String login) {
        return 0;
    }

    @Override
    public List<User> deptUsers(int days) throws RepositoryException {
        List<User> deptsUsers = new ArrayList<>();
        JdbcConnection connection = new JdbcConnection();
        try {
            connection = CONNECTION_MANAGER.getConnection();
            PreparedStatement statement = connection.getConnection().prepareStatement(
                    "SELECT id as user_id, name as user_name\n" +
                    "FROM user_tbl\n" +
                    "WHERE id IN (SELECT user_id\n" +
                    "             FROM form_tbl\n" +
                    "             WHERE end_date < (CURRENT_DATE - INTERVAL ? DAY));");
            statement.setInt(1, days);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_name"));
                deptsUsers.add(user);
            }
        } catch (JdbcException | SQLException e) {
            throw new RepositoryException(e, DB_SYSTEM_ERROR);
        } finally {
            connection.close();
        }
        return deptsUsers;
    }

    @Override
    public void giveBookToUser(Book book, User user, Librarian librarian) throws RepositoryException {
        Calendar cal = Calendar.getInstance();
        Date startDate = cal.getTime();
        cal.add(Calendar.MONTH, MAX_MONTH);
        Date endDate = cal.getTime();
        JdbcConnection connection = new JdbcConnection();
        Form form = new Form();
        form.setUser(user);
        form.setLibrarian(librarian);
        form.setBook(book);
        form.setStartDate(startDate);
        form.setEndDate(endDate);
        try {
            formCRUDRepository.create(connection, form);
        } catch (DaoException e) {
            throw new RepositoryException(e, DB_SYSTEM_ERROR);
        } finally {
            connection.close();
        }
    }

    @Override
    public void giveBookToUser(Book book, User user, User librarian) throws RepositoryException {

    }

    @Override
    public void giveBookToUser(int bookId, int userId, int libId) throws RepositoryException {
        Calendar cal = Calendar.getInstance();
        Date startDate = cal.getTime();
        cal.add(Calendar.MONTH, MAX_MONTH);
        Date endDate = cal.getTime();
        JdbcConnection connection = new JdbcConnection();
        Form form = new Form();
        try {
            Book book = bookCRUDRepository.read(connection, bookId);
            if ( book == null || book.getCountOfBooks() < 1){
                throw new RepositoryException(USER_INPUT_ERROR);
            }
            form.setUser(userCRUDRepository.read(connection, userId));
            form.setLibrarian(librarianCRUDRepository.read(connection, libId));
            form.setBook(book);
            form.setStartDate(startDate);
            form.setEndDate(endDate);
            formCRUDRepository.create(form);
        } catch (DaoException e) {
            throw new RepositoryException(e, DB_SYSTEM_ERROR);
        } finally {
            connection.close();
        }
    }

    @Override
    public Collection<Book> getAllBooks() throws RepositoryException {
        try {
            return bookCRUDRepository.queryAll();
        } catch (DaoException e) {
            throw new RepositoryException(e, DB_SYSTEM_ERROR);
        }
    }

    @Override
    public Collection<Book> getBooksByAuthor(int id) throws RepositoryException{
        if ( id < 0){
            throw new RepositoryException(USER_INPUT_ERROR);
        }
        try {
            Author author = authorCRUDRepository.read(id);
            if ( author != null){
                return author.getBooks();
            }
            throw new RepositoryException(SYSTEM_ERROR);

        } catch (DaoException e) {
            throw new RepositoryException(e, DB_SYSTEM_ERROR);
        }
    }

    @Override
    public Collection<Author> getAllAuthors() throws RepositoryException {
        try {
            return authorCRUDRepository.queryAll();
        } catch (DaoException e) {
            throw new RepositoryException(e, DB_SYSTEM_ERROR);
        }
    }

    @Override
    public Collection<User> getAllUsers() throws RepositoryException {
        try {
            return userCRUDRepository.queryAll();
        } catch (DaoException e) {
            throw new RepositoryException(e, DB_SYSTEM_ERROR);
        }
    }

    @Override
    public Collection<Form> getAllForms() throws RepositoryException{
        try {
            return formCRUDRepository.queryAll();
        } catch (DaoException e) {
            throw new RepositoryException(e, DB_SYSTEM_ERROR);
        }
    }
}
