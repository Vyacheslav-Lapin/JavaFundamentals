package listeners;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolDistributor implements ServletContextListener {

    public static final String POOL_NAME = "connectionPool";
    private ServletContext servletContext;
    private ConnectionPool connectionPool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.setAttribute(POOL_NAME, connectionPool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.removeAttribute(POOL_NAME);
        try {
            connectionPool.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
