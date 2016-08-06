package com.epam.courses.jf.jdbc.common;

import com.epam.courses.jf.common.functions.Exceptional;
import com.epam.courses.jf.common.functions.ExceptionalFunction;
import com.epam.courses.jf.common.functions.VarFunction;
import com.epam.courses.jf.jdbc.cp.ConnectionPool;

import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static java.lang.Character.toUpperCase;

@FunctionalInterface
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
        return toCamelCaseName(name, true);
    }

    static String toCamelCaseName(String name, boolean isFirstCharLower) {
        StringBuilder result = new StringBuilder();
        for (String string : name.split("_"))
            if (isFirstCharLower) {
                result.append(string);
                isFirstCharLower = false;
            } else
                result.append(toUpperCase(string.charAt(0)))
                        .append(string.substring(1));

        return result.toString();
    }

    static  <T> T getById(Class<T> aClass, int id) {
        return null;
    }

    static <C> String toSelect(Class<C> aClass) {
        return toSelect(aClass.getConstructors()[0]); // TODO: 30/06/16 get max-args constructor!
    }

    static String toSelect(Executable executable) {
        StringBuilder result = new StringBuilder("select ");

        result.append(
                Arrays.stream(executable.getParameters())
                        .filter(parameter -> !parameter.isSynthetic())
                        .map(Parameter::getName)
                        .map(Dao::toSqlName)
                        .collect(Collectors.joining(", ")));

        result.append(" from ")
                .append(executable.getDeclaringClass().getSimpleName());

        return result.toString();
    }

    static <T> T toSelect(Class<T> aClass, int id) {
        return null;
    }

    static <R, T> R toSelect(VarFunction<R, T> generator) {
        return null;
    }
}