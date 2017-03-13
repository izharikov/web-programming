package fpmi.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ihar Zharykau
 */
public class StringUtils {
    public static boolean isEmpty(String string){
        return string == null || string.trim().length() == 0;
    }

    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }

    public static String cryptWithMD5(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {

        }
        return null;
    }
}
