package dao.h2;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.interfaces.PersonDao;
import model.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class H2PersonDao implements PersonDao {

    private ConnectionPool connectionPool;

    public H2PersonDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<Person> getPersonById(int id) {

        try (final Connection connection = connectionPool.getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet rs = statement.executeQuery(
                     "SELECT first_name, last_name, permission, dob, email, password, address, telephone" +
                             " FROM Person WHERE id = " + id)) {
            return rs.next()
                    ? Optional.of(new Person(id,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("permission"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("telephone")))
                    : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
