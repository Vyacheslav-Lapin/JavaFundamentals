package dao.h2;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.interfaces.GunDao;
import model.Gun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class H2GunDao implements GunDao {

    private ConnectionPool connectionPool;

    public H2GunDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<Gun> getGunById(int id) {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT name, caliber FROM Gun WHERE id=" + id)) {
            return rs.next()
                    ? Optional.of(new Gun(id, rs.getString("name"), rs.getDouble("caliber")))
                    : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Gun> getAll() {

        Collection<Gun> guns = new HashSet<>();

        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT id, name, caliber FROM Gun")) {
            while (rs.next())
                    guns.add(new Gun(rs.getInt("id"), rs.getString("name"), rs.getDouble("caliber")));
            return guns;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
