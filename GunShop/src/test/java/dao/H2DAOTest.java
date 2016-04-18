package dao;

import com.epam.courses.jf.common.functions.ExceptionalConsumer;
import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import model.Gun;
import model.Person;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class H2DaoTest {

    private static final String RESOURCES_FILE_PATH = "src/test/resources/";
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";
    private static final String DB_PREPARE_FILE_NAME = "h2.sql";
    private static ConnectionPool connectionPool;

    private static PersonDAO personDAO;
    private static GunDao gunDao;


    @BeforeClass
    public static void prepare() throws Exception {

        final Path path = Paths.get(RESOURCES_FILE_PATH, DB_PREPARE_FILE_NAME);
        final String[] sqls = Files.lines(path)
                .collect(Collectors.joining()).split(";");

        connectionPool = ConnectionPool.create(RESOURCES_FILE_PATH + DB_PROPERTIES_FILE_NAME);
        personDAO = new H2PersonDao(connectionPool);
        gunDao = new H2GunDao(connectionPool);

        try (final Connection connection = connectionPool.getConnection();
             final Statement statement = connection.createStatement()) {
            Arrays.stream(sqls).forEach((ExceptionalConsumer<String, ?>) statement::addBatch);
            statement.executeBatch();
        }
    }

    @Test
    public void getPersonById() throws Exception {
        final Person person = personDAO.getPersonById(1)
                .orElseThrow(() -> new AssertionError("Person with id 1 not finded!"));
        assertThat(person.getEmail(), is("Jose_Eglesias@mail.es"));
        System.out.println(person);
    }

    @Test
    public void getGunById() throws Exception {
        final Gun gun = gunDao.getGunById(1)
                .orElseThrow(() -> new AssertionError("Gun with id 1 not found!"));
        assertThat(gun.getName(), is("Kolt"));
        System.out.println(gun);
    }

}
