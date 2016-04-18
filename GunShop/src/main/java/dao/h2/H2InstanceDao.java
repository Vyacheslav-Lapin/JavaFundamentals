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
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(
                     "SELECT gun.id, name, caliber " +
                             "FROM Instance inst, Gun gun WHERE gun.id = inst.model_id AND inst.id=" + id)) {
            return rs.next()
                    ? Optional.of(new Instance(id,
                        new Gun(rs.getInt(id), rs.getString("name"), rs.getDouble("caliber"))))
                    : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
