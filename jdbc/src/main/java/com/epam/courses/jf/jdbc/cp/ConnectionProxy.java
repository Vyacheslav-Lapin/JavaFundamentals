package com.epam.courses.jf.jdbc.cp;

import com.hegel.core.Wrapper;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
/*
 * 1. Для методов, возвращающих значения:
 * "^\s{4}([^v@\s][\w<,\s?>]+)\s(\w+)\(([^\)]*)\)\s([^;]*);$"
 * "    default $1 $2($3) $4 {\n        return get().$2($3);\n    }"
 *
 * 2. Для методов, не возвращающих значения:
 * "^\s{4}void\s(\w+)\(([^\)]*)\)\s([^;]*);$"
 * "    default void $1($2) $3 {\n        get().$1($2);\n    }"
 *
 * 3. Убираем тип у параметров при вызове - для методов с одним параметром:
 * "\(([\w\<\>]+\s(\w+)(,\s)?)+\);"
 * "($2);"
 *
 * 4. (Типы у методов с больше чем одним параметром убираем вручную)
 */
@FunctionalInterface
public interface ConnectionProxy extends Connection, Wrapper<Connection> {

    @Override
    default Statement createStatement() throws SQLException {
        return get().createStatement();
    }

    @Override
    default PreparedStatement prepareStatement(String sql) throws SQLException {
        return get().prepareStatement(sql);
    }

    @Override
    default CallableStatement prepareCall(String sql) throws SQLException {
        return get().prepareCall(sql);
    }

    @Override
    default String nativeSQL(String sql) throws SQLException {
        return get().nativeSQL(sql);
    }

    @Override
    default void setAutoCommit(boolean autoCommit) throws SQLException{
        get().setAutoCommit(autoCommit);
    }

    @Override
    default boolean getAutoCommit() throws SQLException {
        return get().getAutoCommit();
    }

    @Override
    default void commit() throws SQLException{
        get().commit();
    }

    @Override
    default void rollback() throws SQLException{
        get().rollback();
    }

    @Override
    default void close() throws SQLException{
        get().close();
    }

    @Override
    default boolean isClosed() throws SQLException {
        return get().isClosed();
    }

    @Override
    default DatabaseMetaData getMetaData() throws SQLException {
        return get().getMetaData();
    }

    @Override
    default void setReadOnly(boolean readOnly) throws SQLException{
        get().setReadOnly(readOnly);
    }

    @Override
    default boolean isReadOnly() throws SQLException {
        return get().isReadOnly();
    }

    @Override
    default void setCatalog(String catalog) throws SQLException{
        get().setCatalog(catalog);
    }

    @Override
    default String getCatalog() throws SQLException {
        return get().getCatalog();
    }

    @Override
    default void setTransactionIsolation(int level) throws SQLException{
        get().setTransactionIsolation(level);
    }

    @Override
    default int getTransactionIsolation() throws SQLException {
        return get().getTransactionIsolation();
    }

    @Override
    default SQLWarning getWarnings() throws SQLException {
        return get().getWarnings();
    }

    @Override
    default void clearWarnings() throws SQLException{
        get().clearWarnings();
    }

    @Override
    default Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return get().createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return get().prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    default CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return get().prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    default Map<String, Class<?>> getTypeMap() throws SQLException {
        return get().getTypeMap();
    }

    @Override
    default void setTypeMap(Map<String, Class<?>> map) throws SQLException{
        get().setTypeMap(map);
    }

    @Override
    default void setHoldability(int holdability) throws SQLException{
        get().setHoldability(holdability);
    }

    @Override
    default int getHoldability() throws SQLException {
        return get().getHoldability();
    }

    @Override
    default Savepoint setSavepoint() throws SQLException {
        return get().setSavepoint();
    }

    @Override
    default Savepoint setSavepoint(String name) throws SQLException {
        return get().setSavepoint(name);
    }

    @Override
    default void rollback(Savepoint savepoint) throws SQLException{
        get().rollback(savepoint);
    }

    @Override
    default void releaseSavepoint(Savepoint savepoint) throws SQLException{
        get().releaseSavepoint(savepoint);
    }

    @Override
    default Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return get().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return get().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    default CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return get().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return get().prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return get().prepareStatement(sql, columnIndexes);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return get().prepareStatement(sql, columnNames);
    }

    @Override
    default Clob createClob() throws SQLException {
        return get().createClob();
    }

    @Override
    default Blob createBlob() throws SQLException {
        return get().createBlob();
    }

    @Override
    default NClob createNClob() throws SQLException {
        return get().createNClob();
    }

    @Override
    default SQLXML createSQLXML() throws SQLException {
        return get().createSQLXML();
    }

    @Override
    default boolean isValid(int timeout) throws SQLException {
        return get().isValid(timeout);
    }

    @Override
    default void setClientInfo(String name, String value) throws SQLClientInfoException{
        get().setClientInfo(name, value);
    }

    @Override
    default void setClientInfo(Properties properties) throws SQLClientInfoException{
        get().setClientInfo(properties);
    }

    @Override
    default String getClientInfo(String name) throws SQLException {
        return get().getClientInfo(name);
    }

    @Override
    default Properties getClientInfo() throws SQLException {
        return get().getClientInfo();
    }

    @Override
    default Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return get().createArrayOf(typeName, elements);
    }

    @Override
    default Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return get().createStruct(typeName, attributes);
    }

    @Override
    default void setSchema(String schema) throws SQLException{
        get().setSchema(schema);
    }

    @Override
    default String getSchema() throws SQLException {
        return get().getSchema();
    }

    @Override
    default void abort(Executor executor) throws SQLException{
        get().abort(executor);
    }

    @Override
    default void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException{
        get().setNetworkTimeout(executor, milliseconds);
    }

    @Override
    default int getNetworkTimeout() throws SQLException {
        return get().getNetworkTimeout();
    }

    @Override
    default <T> T unwrap(Class<T> iface) throws SQLException {
        return get().unwrap(iface);
    }

    @Override
    default boolean isWrapperFor(Class<?> iface) throws SQLException {
        return get().isWrapperFor(iface);
    }
}
