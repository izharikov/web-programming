package fpmi.utils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author Ihar Zharykau
 */
public class ReflectUtils {

    private static final Class[] PRIMITIVE_CLASSES =
            new Class[]{Integer.class, String.class, Boolean.class, Date.class};

    public static boolean isFieldPrimitive(Field field) {
        Class fieldType = field.getType();
        boolean primitive = false;
        for ( Class cl : PRIMITIVE_CLASSES){
            primitive = cl.equals(fieldType);
            if ( primitive){
                break;
            }
        }
        return primitive;
    }
}
