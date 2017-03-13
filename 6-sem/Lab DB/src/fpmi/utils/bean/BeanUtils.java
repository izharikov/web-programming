package fpmi.utils.bean;

import com.sun.istack.NotNull;
import fpmi.db.entities.Book;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author Ihar Zharykau
 */
public class BeanUtils {
    public static <T> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T callGet(@NotNull Object object, String fieldName){
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
            Method getter = pd.getReadMethod();
            Object value = getter.invoke(object);
            return (T) value;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void callSet(Object object, String fieldName, Object value){
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
            Method setter = pd.getWriteMethod();
            setter.invoke(object, value);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String... args){
        Book book = newInstance(Book.class);
        if ( book != null){
            callSet(book, "name", "Test Name");
            callSet(book, "id", "book12345");
            System.out.println((String) callGet(book, "name"));
            System.out.println((String) callGet(book, "id"));
            System.out.println(book.getName());
            System.out.println(book.getId());
        }
    }
}
