package com.epam.courses.jf.jdbc.cp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class ConnectionPool {

    private String url;
    private Properties properties;
    private int poolSize;

    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> reservedConnections;

    public ConnectionPool() {
        this("src/main/resources/db.properties");
    }

    public ConnectionPool(String propertyFileName) {
        this(new Properties() {
            private Properties load() {
                try (InputStream inputStream = new FileInputStream(propertyFileName)) {
                    load(inputStream);
                    return this;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.load());
    }

    public ConnectionPool(Properties properties) {
        this((String) properties.remove("url"),
                parseInt((String) properties.remove("poolSize")),
                properties);
    }

    public ConnectionPool(String url, int poolSize, Properties properties) {
        assert properties.containsKey("user");
        assert properties.containsKey("password");

        try {
            Class.forName(properties.remove("driver").toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find database driver class", e);
        }

        this.url = url;
        this.properties = properties;
        this.poolSize = poolSize;
        reservedConnections = new ArrayBlockingQueue<>(poolSize);
        freeConnections = new ArrayBlockingQueue<>(poolSize);

        IntStream.range(0, poolSize)
                .mapToObj(value -> {
                    try {
                        return DriverManager.getConnection(url, properties);
                    } catch (SQLException e) {
                        throw new RuntimeException("SQLException in ConnectionPool", e);
                    }
                })
                .map(connection -> PooledConnection.wrap(connection, freeConnections, reservedConnections))
                .forEach(freeConnections::add);

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
