package com.epam.courses.jf.jdbc.common;

import com.epam.courses.jf.common.functions.Exceptional;
import com.epam.courses.jf.common.functions.ExceptionalFunction;
import com.epam.courses.jf.jdbc.cp.ConnectionPool;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

import static java.lang.Character.toUpperCase;

public interface Dao {

    ConnectionPool getConnectionPool();

    default <T> Exceptional<T, SQLException> withConnection(
            ExceptionalFunction<Connection, T, SQLException> jdbcTemplate) {
        try (final Connection connection = getConnectionPool().getConnection()) {
            return jdbcTemplate.apply(connection);
        } catch (SQLException e) {
            return Exceptional.withException(e);
        }
    }

    default <T> Exceptional<T, SQLException> withStatement(
            ExceptionalFunction<Statement, T, SQLException> jdbcTemplate) {
        return withConnection(connection -> {
            try (final Statement statement = connection.createStatement()) {
                return jdbcTemplate.get(statement);
            }
        });
    }

    default <T> Exceptional<T, SQLException> withPreparedStatement(
            ExceptionalFunction<PreparedStatement, T, SQLException> jdbcTemplate, String sql) {
        return withConnection(connection -> {
            try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                return jdbcTemplate.get(statement);
            }
        });
    }

    default <T> Exceptional<T, SQLException> withCallableStatement(
            ExceptionalFunction<CallableStatement, T, SQLException> jdbcTemplate, String call) {
        return withConnection(connection -> {
            try (final CallableStatement callableStatement = connection.prepareCall(call)) {
                return jdbcTemplate.get(callableStatement);
            }
        });
    }

    default <T> Exceptional<T, SQLException> executeQuery(String sql, ExceptionalFunction<ResultSet, T, SQLException> template) {
        return withStatement(statement -> {
            try (final ResultSet rs = statement.executeQuery(sql)) {
                return template.get(rs);
            }
        });
    }

    default <T> Exceptional<Collection<T>, SQLException> queryCollection(String sql, ExceptionalFunction<ResultSet, T, SQLException> template) {
        return withStatement(statement -> {
            Collection<T> result;
            try (final ResultSet rs = statement.executeQuery(sql)) {
                result = new HashSet<>(rs.getFetchSize());
                while (rs.next())
                    result.add(template.get(rs));
                return result;
            }
        });
    }

    static String toSqlName(String name) {
        return name.replaceAll("([A-Z])", "_$1").toLowerCase();
    }

    static String toCamelCaseName(String name) {
        boolean isFirstString = true;
        StringBuilder result = new StringBuilder();
        for (String string: name.split("_"))
            if (isFirstString) {
                result.append(string);
                isFirstString = false;
            } else
                result.append(toUpperCase(string.charAt(0)))
                    .append(string.substring(1));

        return result.toString();
    }
}