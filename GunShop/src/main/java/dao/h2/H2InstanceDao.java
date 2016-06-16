package dao.h2;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.interfaces.InstanceDao;
import lombok.Value;
import model.Gun;
import model.Instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Value
public class H2InstanceDao implements InstanceDao {

    private ConnectionPool connectionPool;

    @Override
    public Optional<Instance> getInstanceById(int id) {
        return executeQuery(
                "SELECT g.id, name, caliber FROM Gun g, Instance i WHERE model_id = g.id AND  i.id = " + id,
                rs -> !rs.next() ? null :
                        new Instance(id, new Gun(rs.getInt("id"), rs.getString("name"), rs.getDouble("caliber")))
        ).toOptional();
    }
}
