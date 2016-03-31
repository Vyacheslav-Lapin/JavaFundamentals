package com.epam.courses.jf.jdbc;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CPDemo {
    public static void main(String[] args) throws SQLException {

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
    }
}
