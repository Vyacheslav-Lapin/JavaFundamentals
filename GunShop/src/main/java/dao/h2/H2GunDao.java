package dao.h2;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.interfaces.GunDao;
import lombok.Value;
import model.Gun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Value
public class H2GunDao implements GunDao {

    ConnectionPool connectionPool;

    @Override
    public Optional<Gun> getGunById(int id) {
        return executeQuery(
                "SELECT name, caliber FROM Gun WHERE id = " + id,
                rs -> !rs.next() ? null : new Gun(id, rs.getString("name"), rs.getDouble("caliber"))
        ).toOptional();
    }

    @Override
    public Collection<Gun> getAll() {
        return queryCollection(
                "SELECT id, name, caliber FROM Gun",
                rs -> new Gun(rs.getInt("id"), rs.getString("name"), rs.getDouble("caliber"))
        ).toOptional()
                .orElse(Collections.emptySet());
    }
}
