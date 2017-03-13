package fpmi.db.adapter;

import fpmi.db.entities.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
class FormEntityAdapter implements EntityAdapter<Form> {

    @Override
    public Form convert(ResultSet resultSet) throws SQLException {
        Form form = new Form();
        if (resultSet.next()) {
            fillForm(form, resultSet);
        }
        return form;
    }

    @Override
    public Collection<Form> convertToCollection(ResultSet resultSet) throws SQLException {
        Collection<Form> result = new LinkedList<>();
        while ( resultSet.next()){
            Form form = new Form();
            fillForm(form, resultSet);
            result.add(form);
        }
        return result;
    }

    private static void fillForm(Form form, ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("form_id");
        Date startDate = resultSet.getDate("start_date");
        Date endDate = resultSet.getDate("end_date");
//        User
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setName(resultSet.getString("user_name"));
//        Librarian
        Librarian librarian = new Librarian();
        String libName = resultSet.getString("lib_name");
        int libId = resultSet.getInt("lib_id");
        librarian.setId(libId);
        librarian.setName(libName);
//        Book
        Book book = new Book();
        book.setId(resultSet.getInt("book_id"));
        book.setName(resultSet.getString("book_name"));
        book.setCountOfBooks(resultSet.getInt("count_of_books"));
        Catalog catalog = new Catalog();
        catalog.setId(resultSet.getInt("catalog_id"));
        catalog.setName(resultSet.getString("catalog_name"));
        book.setCatalog(catalog);
//        Form
        form.setId(id);
        form.setBook(book);
        form.setStartDate(new java.util.Date(startDate.getTime()));
        form.setEndDate(new java.util.Date(endDate.getTime()));
        form.setLibrarian(librarian);
        form.setUser(user);
    }
}
