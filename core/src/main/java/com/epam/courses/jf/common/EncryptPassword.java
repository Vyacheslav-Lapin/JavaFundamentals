package com.epam.courses.jf.common;

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

        String hexVal;

        //hex encode the digest
        for (byte b : bs) {
            hexVal = Integer.toHexString(0xFF & b);
            if (hexVal.length() == 1)
                stringBuilder.append("0");
            stringBuilder.append(hexVal);
        }

        return stringBuilder.toString();
    }
}
