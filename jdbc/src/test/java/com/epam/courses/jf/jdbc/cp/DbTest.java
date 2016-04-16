package com.epam.courses.jf.jdbc.cp;

import com.epam.courses.jf.common.functions.ExceptionalConsumer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DbTest {

    @SuppressWarnings("InjectedReferences")
    private static final String RESOURCES_FILE_PATH = "src/test/resources/";
    @SuppressWarnings("InjectedReferences")
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";
    private static final String DB_PREPARE_FILE_NAME = "h2.sql";

    @BeforeClass
    public static void init() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.h2.Driver");
        final Path path = Paths.get(RESOURCES_FILE_PATH, DB_PREPARE_FILE_NAME);
        final String[] sqls = Files.lines(path)
                .collect(Collectors.joining()).split(";");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
             Statement statement = connection.createStatement()) {
            Arrays.stream(sqls).forEach((ExceptionalConsumer<String, ?>) statement::addBatch);
            statement.executeBatch();
        }
    }

    @Test
    public void simpleTestDB() throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
             Statement statement = connection.createStatement()) {
            testDb(statement);
        }
    }

    @Test
    public void cpTest() throws Exception {
        try (final ConnectionPool connectionPool = ConnectionPool.create(RESOURCES_FILE_PATH + DB_PROPERTIES_FILE_NAME);
             final Connection connection = connectionPool.getConnection();
             final Statement statement = connection.createStatement()) {
            testDb(statement);
        }
    }

    private void testDb(Statement statement) throws SQLException {
        try (ResultSet rs = statement.executeQuery("SELECT id, first_name FROM Person")) {
            while (rs.next())
                System.out.println(rs.getInt("id") + " " + rs.getString("first_name"));
        }
    }
}
