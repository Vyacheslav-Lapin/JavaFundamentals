package com.epam.courses.jf.common;

import com.epam.courses.jf.common.functions.ExceptionalFunction;

import java.security.MessageDigest;

public class StringEncryptUtil {

    private static final String ALGORITHM = "MD5";
    private static final MessageDigest ENCRYPTOR =
            ExceptionalFunction.getOrThrowUnchecked(MessageDigest::getInstance, ALGORITHM);

    public static String encrypt(String s) {

        ENCRYPTOR.reset();

        byte[] bs = ENCRYPTOR.digest(s.getBytes());

        StringBuilder stringBuilder = new StringBuilder();

        //hex encode the digest
        for (byte b : bs) {
            String hexVal = Integer.toHexString(0xFF & b);
            if (hexVal.length() == 1)
                stringBuilder.append("0");
            stringBuilder.append(hexVal);
        }

        return stringBuilder.toString();
    }
}
