package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {

    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encryptPassword(String password) {

        md5.reset();

        byte[] bs = md5.digest(password.getBytes());

        StringBuilder stringBuilder = new StringBuilder();

        //hex encode the digest
        for (int i = 0; i < bs.length; i++) {
            String hexVal = Integer.toHexString(0xFF & bs[i]);
            if (hexVal.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(hexVal);
        }

        return stringBuilder.toString();
    }
}
