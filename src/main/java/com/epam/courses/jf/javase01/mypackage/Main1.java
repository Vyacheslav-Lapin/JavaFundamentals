package com.epam.courses.jf.javase01.mypackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main1 {

    public static void main(String... args) throws ClassNotFoundException, SQLException {

//        Class.forName("org.h2.Driver");
//        try (Connection connection =
//                     DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
//             Statement statement = connection.createStatement()) {
//            statement.addBatch("CREATE TABLE Person (id INT PRIMARY KEY, name VARCHAR(255))");
//            statement.addBatch("INSERT INTO Person (id, name) VALUES (1,'Jose')");
//            statement.addBatch("INSERT INTO Person (id, name) VALUES (2,'John')");
//            statement.addBatch("INSERT INTO Person (id, name) VALUES (3,'Pit')");
//            statement.addBatch("INSERT INTO Person (id, name) VALUES (4,'Asha')");
//            statement.executeBatch();
//
//            try (ResultSet rs = statement.executeQuery("SELECT * FROM Person")) {
//                while (rs.next()) {
//                    System.out.println(rs.getInt(1) + " " + rs.getString(2));
//                }
//            }
//        }

        ConnectionPool connectionPool = new ConnectionPool();

        try (Connection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {
            statement.addBatch("CREATE TABLE Person (id INT PRIMARY KEY, name VARCHAR(255))");
            statement.addBatch("INSERT INTO Person (id, name) VALUES (1,'Jose')");
            statement.addBatch("INSERT INTO Person (id, name) VALUES (2,'John')");
            statement.addBatch("INSERT INTO Person (id, name) VALUES (3,'Pit')");
            statement.addBatch("INSERT INTO Person (id, name) VALUES (4,'Asha')");
            statement.executeBatch();

            try (ResultSet rs = statement.executeQuery("SELECT * FROM Person")) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2));
                }
            }
        }





//        String s1 = new StringBuffer("He").append("llo").toString();
//        String s2 = "Hello";
//        System.out.println(s2 == s1);

    }
}
