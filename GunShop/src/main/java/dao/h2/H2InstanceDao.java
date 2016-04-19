package dao.h2;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.interfaces.InstanceDao;
import model.Gun;
import model.Instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class H2InstanceDao implements InstanceDao {

    private ConnectionPool connectionPool;

    public H2InstanceDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<Instance> getInstanceById(int id) {
        try (final Connection connection = connectionPool.getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet rs = statement.executeQuery(
                     "SELECT g.id, name, caliber FROM Gun g, Instance i WHERE model_id = g.id AND  i.id = " + id)) {

            return rs.next()
                    ? Optional.of(new Instance(id,
                        new Gun(rs.getInt("id"), rs.getString("name"), rs.getDouble("caliber"))))
                    : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
