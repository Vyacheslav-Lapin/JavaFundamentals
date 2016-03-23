package com.epam.courses.jf.javase01.mypackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> reservedConnections;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    public ConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/db.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driverName = properties.getProperty("driver", "org.h2.Driver");
        url = properties.getProperty("url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        user = properties.getProperty("user", "");
        password = properties.getProperty("password", "");
        poolSize = Integer.parseInt(properties.getProperty("poolsize", "5"));

        initPoolData();
    }

    public void initPoolData() {
        Locale.setDefault(Locale.ENGLISH);

        try {
            Class.forName(driverName);
            reservedConnections = new ArrayBlockingQueue<>(poolSize);
            freeConnections = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection =
                        PooledConnection.wrap(connection, freeConnections, reservedConnections);
                freeConnections.add(pooledConnection);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find database driver class", e);
        }
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
            reservedConnections.add(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error connecting to the data source.", e);
        }
        return connection;
    }
}
