package com.epam.courses.jf.jdbc.cp;

import com.epam.courses.jf.common.Private;
import com.epam.courses.jf.common.functions.ExceptionalConsumer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.epam.courses.jf.common.PropertyUtils.getAndRemove;
import static com.epam.courses.jf.common.Reflect.loadClass;
import static java.lang.Integer.parseInt;

public interface ConnectionPool extends AutoCloseable {

    Connection getConnection();

    default void executeScript(String prepareFilePath) {
        executeScript(Paths.get(prepareFilePath));
    }

    default void executeScript(Path path) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            final String[] sqls = Files.lines(path)
                    .collect(Collectors.joining()).split(";");
            Arrays.stream(sqls).forEach(ExceptionalConsumer.toUncheckedConsumer(statement::addBatch));
            statement.executeBatch();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    static ConnectionPool create(String propertyFileName) {
        try (InputStream propertyFileInputStream = new FileInputStream(propertyFileName)) {
            return create(propertyFileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Private
    static ConnectionPool create(InputStream propertyFileInputStream) {
        final Properties properties = new Properties();
        try {
            properties.load(propertyFileInputStream);
            return create(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Private
    static ConnectionPool create(Properties properties) {
        assert properties.containsKey("url");
        assert properties.containsKey("poolSize");
        return create(getAndRemove(properties, "url"),
                parseInt(getAndRemove(properties, "poolSize")),
                properties);
    }

    @Private
    static ConnectionPool create(String url, int poolSize, Properties properties) {
        assert properties.containsKey("user");
        assert properties.containsKey("password");
        assert properties.containsKey("driver");

        loadClass(getAndRemove(properties, "driver"), "Can't find database driver class");
        return new SimpleConnectionPool(poolSize, () -> createConnection(url, properties));
    }

    @Private
    static Connection createConnection(String url, Properties properties) {
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException in ConnectionPoolFactory", e);
        }
    }
}
