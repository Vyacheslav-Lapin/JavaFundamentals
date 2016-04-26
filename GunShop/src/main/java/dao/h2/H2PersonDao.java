package dao.h2;

import com.epam.courses.jf.common.functions.Exceptional;
import com.epam.courses.jf.common.functions.ExceptionalFunction;
import com.epam.courses.jf.jdbc.cp.ConnectionPool;
import dao.interfaces.PersonDao;
import model.Person;

import java.sql.*;
import java.util.Optional;

public class H2PersonDao implements PersonDao {

    private ConnectionPool connectionPool;

    public H2PersonDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

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
                                rs.getString("telephone"))).toOptional();
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

    private <T> Exceptional<T, SQLException> withConnection(
            ExceptionalFunction<Connection, T, SQLException> jdbcTemplate) {
        try (final Connection connection = connectionPool.getConnection()) {
            return jdbcTemplate.apply(connection);
        } catch (SQLException e) {
            return Exceptional.withException(e);
        }
    }

    private <T> Exceptional<T, SQLException> withStatement(
            ExceptionalFunction<Statement, T, SQLException> jdbcTemplate) {
        return withConnection(connection -> {
            try (final Statement statement = connection.createStatement()) {
                return jdbcTemplate.get(statement);
            }
        });
    }

    private <T> Exceptional<T, SQLException> withPreparedStatement(
            ExceptionalFunction<PreparedStatement, T, SQLException> jdbcTemplate, String sql) {
        return withConnection(connection -> {
            try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                return jdbcTemplate.get(statement);
            }
        });
    }

    private <T> Exceptional<T, SQLException> withCallableStatement(
            ExceptionalFunction<CallableStatement, T, SQLException> jdbcTemplate, String call) {
        return withConnection(connection -> {
            try (final CallableStatement callableStatement = connection.prepareCall(call)) {
                return jdbcTemplate.get(callableStatement);
            }
        });
    }

    private <T> Exceptional<T, SQLException> executeQuery(String sql, ExceptionalFunction<ResultSet, T, SQLException> template) {
        return withStatement(statement -> {
            try (final ResultSet rs = statement.executeQuery(sql)) {
                return template.get(rs);
            }
        });
    }
}
