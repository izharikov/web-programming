package fpmi.db.adapter;

import fpmi.db.entities.*;

/**
 * @author Ihar Zharykau
 */
public class EntityAdapterFactory {
    private static final EntityAdapter<Book> BOOK_ENTITY_ADAPTER = new BookEntityAdapter();
    private static final EntityAdapter<Catalog> CATALOG_ENTITY_ADAPTER = new CatalogEntityAdapter();
    private static final EntityAdapter<Form> FORM_ENTITY_ADAPTER = new FormEntityAdapter();
    private static final EntityAdapter<User> USER_ENTITY_ADAPTER = new UserEntityAdapter();
    private static final EntityAdapter<Librarian> LIBRARIAN_ENTITY_ADAPTER = new LibrarianEntityAdapter();
    private static final EntityAdapter<Author> AUTHOR_ENTITY_ADAPTER = new AuthorEntityAdapter();

    public static EntityAdapter<Book> bookEntityAdapter(){
        return BOOK_ENTITY_ADAPTER;
    }

    public static EntityAdapter<Catalog> catalogEntityAdapter(){
        return CATALOG_ENTITY_ADAPTER;
    }

    public static EntityAdapter<Form> formEntityAdapter(){
        return FORM_ENTITY_ADAPTER;
    }

    public static EntityAdapter<User> userEntityAdapter(){
        return USER_ENTITY_ADAPTER;
    }

    public static EntityAdapter<Librarian> librarianEntityAdapter(){
        return LIBRARIAN_ENTITY_ADAPTER;
    }

    public static EntityAdapter<Author> getAuthorEntityAdapter() {
        return AUTHOR_ENTITY_ADAPTER;
    }
}
