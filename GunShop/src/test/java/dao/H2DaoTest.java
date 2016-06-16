package dao;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.h2.H2GunDao;
import dao.h2.H2InstanceDao;
import dao.h2.H2PersonDao;
import dao.interfaces.GunDao;
import dao.interfaces.InstanceDao;
import dao.interfaces.PersonDao;
import model.Gun;
import model.Instance;
import model.Person;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class H2DaoTest {

    private static final String RESOURCES_FILE_PATH = "src/test/resources/";
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";
    private static final String DB_PREPARE_FILE_NAME = "h2.sql";
    private static PersonDao personDao;
    private static GunDao gunDao;
    private static InstanceDao instanceDao;

    @BeforeClass
    public static void prepare() throws Exception {
        ConnectionPool connectionPool =
                ConnectionPool.create(RESOURCES_FILE_PATH + DB_PROPERTIES_FILE_NAME);
        connectionPool.executeScript(RESOURCES_FILE_PATH + DB_PREPARE_FILE_NAME);

        personDao = new H2PersonDao(connectionPool);
        gunDao = new H2GunDao(connectionPool);
        instanceDao = new H2InstanceDao(connectionPool);
    }

    @Test
    public void getPersonById() throws Exception {
        final Person person = personDao.getPersonById(1)
                .orElseThrow(() -> new RuntimeException("There is no such Person with id = " + 1));

        assertThat(person.getEmail(), is("Jose_Eglesias@mail.es"));
    }

    @Test
    public void getGunById() {
        final Gun gun = gunDao.getGunById(1)
                .orElseThrow(() -> new RuntimeException("There is no such Gun with id = " + 1));

        assertThat(gun.getName(), is("Kolt"));
    }

    @Test
    public void getInstanceById() {
        final Instance instance = instanceDao.getInstanceById(1)
                .orElseThrow(() -> new RuntimeException("There is no such Instance with id = " + 1));

        assertThat(instance.getModel().getName(), is("Kolt"));
    }
}
