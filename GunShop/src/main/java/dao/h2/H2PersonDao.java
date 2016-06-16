package dao.h2;

import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.interfaces.PersonDao;
import lombok.Value;
import model.Person;

import java.util.Optional;

@Value
public class H2PersonDao implements PersonDao {

    private final ConnectionPool connectionPool;

    @Override
    public Optional<Person> getPersonById(int id) {
        return executeQuery(
                "SELECT first_name, last_name, permission, dob, email, password, address, telephone" +
                        " FROM Person WHERE id = " + id,
                rs -> !rs.next() ? null :
                        new Person(id,
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getBoolean("permission"),
                                rs.getDate("dob").toLocalDate(),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("address"),
                                rs.getString("telephone"))
        ).toOptional();
    }

    @Override
    public Optional<String> getPasswordByEmail(String email) {
        return executeQuery("SELECT password FROM Person WHERE email = '" + email + "'",
                rs -> rs.next() ? rs.getString("password") : null
        ).toOptional();
    }

    @Override
    public boolean setPasswordByEmail(String email, String password) {
        return withStatement(statement ->
                1 <= statement.executeUpdate(
                        "UPDATE Person SET password = '" + password + "' WHERE email = '" + email + "'")
        ).toOptional().orElse(false);
    }

    @Override
    public Optional<Person> getPersonByEmail(String email) {
        return executeQuery(
                "SELECT id, first_name, last_name, permission, dob, password, address, telephone" +
                        " FROM Person WHERE email = '" + email + "'",
                rs -> !rs.next() ? null :
                        new Person(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getBoolean("permission"),
                                rs.getDate("dob").toLocalDate(),
                                email,
                                rs.getString("password"),
                                rs.getString("address"),
                                rs.getString("telephone"))
        ).toOptional();
    }
}
