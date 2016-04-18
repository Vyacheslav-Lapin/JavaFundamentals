package dao;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import model.Gun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
