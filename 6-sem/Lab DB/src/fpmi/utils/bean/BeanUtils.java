package fpmi.utils.bean;

import com.sun.istack.NotNull;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Bean utils to call methods using reflection.<br>
 * To use this, object should be instance of class, that is valid bean: class, with declared getters and setters
 *
 * @author Ihar Zharykau
 */
public class BeanUtils {
    /**
     * Create new instance for class
     *
     * @param clazz Class of object
     * @param <T>   Class of object
     * @return created object, or null if errors occurred
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Call getter
     *
     * @param object    object, in which call getter
     * @param fieldName field name of getter
     * @param <T>       class of returned field
     * @return field value, if it's exists. null - otherwise
     */
    @SuppressWarnings("unchecked")
    public static <T> T callGet(@NotNull Object object, String fieldName) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
            Method getter = pd.getReadMethod();
            Object value = getter.invoke(object);
            return (T) value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Call setter
     *
     * @param object    object, in which call setter
     * @param fieldName field name
     * @param value     value to set
     */
    public static void callSet(Object object, String fieldName, Object value) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
            Method setter = pd.getWriteMethod();
            setter.invoke(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
