package javacode.servlets.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ww on 26.05.2016.
 *
 * Class to encode password to md5
 */
public class EncodingPassword {
    private static final MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gives hash of password
     * @param string password
     * @return password hash
     */
    public static String hash(String string) {
        final StringBuilder result = new StringBuilder();
        md5.reset();
        byte[] bytes = md5.digest(string.getBytes());
        for (byte b: bytes) {
            String hexVal = Integer.toHexString(0xFF & b);
            if (hexVal.length() == 1)
                result.append("0");
            result.append(hexVal);
        }
        return result.toString();
    }
}
