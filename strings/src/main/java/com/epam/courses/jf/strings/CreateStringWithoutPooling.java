package com.epam.courses.jf.strings;

import java.sql.SQLException;

public class CreateStringWithoutPooling {

    public static void main(String... args) throws ClassNotFoundException, SQLException {

        String s1 = new StringBuffer("He").append("llo").toString();
        String s2 = "Hello";
        System.out.println(s2 == s1);
        System.out.println(s2 == s1.intern());

    }
}
