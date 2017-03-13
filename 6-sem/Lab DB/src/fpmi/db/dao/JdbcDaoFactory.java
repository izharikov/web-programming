package fpmi.db.dao;

import fpmi.db.dao.crud.JdbcCrudRepository;
import fpmi.db.entities.*;

/**
 * @author Ihar Zharykau
 */
public class JdbcDaoFactory {
    private static final JdbcCrudRepository<Book> BOOK_CRUD_REPOSITORY = new DaoBook();
    private static final JdbcCrudRepository<Catalog> CATALOG_CRUD_REPOSITORY = new DaoCatalog();
    private static final JdbcCrudRepository<User> USER_CRUD_REPOSITORY = new DaoUser();
    private static final JdbcCrudRepository<Form> FORM_CRUD_REPOSITORY = new DaoForm();
    private static final JdbcCrudRepository<Librarian> LIBRARIAN_CRUD_REPOSITORY = new DaoLibrarian();
    private static final JdbcCrudRepository<Author> AUTHOR_CRUD_REPOSITORY = new DaoAuthor();

    public static JdbcCrudRepository<Book> getBookJdbcCrudRepository() {
        return BOOK_CRUD_REPOSITORY;
    }

    public static JdbcCrudRepository<Catalog> getCatalogJdbcCrudRepository() {
        return CATALOG_CRUD_REPOSITORY;
    }

    public static JdbcCrudRepository<User> getUserJdbcCrudRepository() {
        return USER_CRUD_REPOSITORY;
    }

    public static JdbcCrudRepository<Form> getFormJdbcCrudRepository() {
        return FORM_CRUD_REPOSITORY;
    }

    public static JdbcCrudRepository<Librarian> getLibrarianJdbcCrudRepository() {
        return LIBRARIAN_CRUD_REPOSITORY;
    }

    public static JdbcCrudRepository<Author> getAuthorJdbcCrudRepository() {
        return AUTHOR_CRUD_REPOSITORY;
    }
}
