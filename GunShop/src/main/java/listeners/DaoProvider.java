package listeners;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.h2.H2GunDao;
import dao.h2.H2InstanceDao;
import dao.h2.H2PersonDao;
import service.PersonService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DaoProvider implements ServletContextListener {

    private static final String RESOURCES_FILE_PATH = "/WEB-INF/classes/";
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";
    private static final String DB_PREPARE_FILE_NAME = "h2.sql";

    public static final String PERSON_DAO = "personDao";
    public static final String GUN_DAO = "gunDao";
    public static final String INSTANCE_DAO = "instanceDao";
    public static final String PERSON_SERVICE = "personService";

    private static ConnectionPool connectionPool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        final ServletContext servletContext = sce.getServletContext();

        final String dbPropertiesFilePath = servletContext.getRealPath(RESOURCES_FILE_PATH + DB_PROPERTIES_FILE_NAME);
        final String dbPrepareFilePath = servletContext.getRealPath(RESOURCES_FILE_PATH + DB_PREPARE_FILE_NAME);

        connectionPool = ConnectionPool.create(dbPropertiesFilePath);
        connectionPool.executeScript(dbPrepareFilePath);

        final H2PersonDao personDao = new H2PersonDao(connectionPool);
        servletContext.setAttribute(PERSON_DAO, personDao);
        servletContext.setAttribute(PERSON_SERVICE, new PersonService(personDao));
        servletContext.setAttribute(GUN_DAO, new H2GunDao(connectionPool));
        servletContext.setAttribute(INSTANCE_DAO, new H2InstanceDao(connectionPool));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connectionPool.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
