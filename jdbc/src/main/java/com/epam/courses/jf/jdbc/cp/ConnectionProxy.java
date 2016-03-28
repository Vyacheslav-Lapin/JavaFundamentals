package com.epam.courses.jf.jdbc.cp;

import com.epam.courses.jf.common.Proxy;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
//^\s{4}([^v@][\w<,\s>?]+)\s(\w+)\(([^\)]*)\)(\sthrows\s\w+)?;$
//    default $1 $2($3)$4{\n        return toSrc().$2($3);\n    }

//^\s{4}void\s(\w+)\(([^\)]*)\)(\sthrows\s\w+)?;$
//    default void $1($2)$3{\n        toSrc().$1($2);\n    }
@FunctionalInterface
public interface ConnectionProxy extends Connection, Proxy<Connection> {

    @Override
    default Statement createStatement() throws SQLException {
        return toSrc().createStatement();
    }

    @Override
    default PreparedStatement prepareStatement(String sql) throws SQLException {
        return toSrc().prepareStatement(sql);
    }

    @Override
    default CallableStatement prepareCall(String sql) throws SQLException {
        return toSrc().prepareCall(sql);
    }

    @Override
    default String nativeSQL(String sql) throws SQLException {
        return toSrc().nativeSQL(sql);
    }

    @Override
    default void setAutoCommit(boolean autoCommit) throws SQLException{
        toSrc().setAutoCommit(autoCommit);
    }

    @Override
    default boolean getAutoCommit() throws SQLException {
        return toSrc().getAutoCommit();
    }

    @Override
    default void commit() throws SQLException{
        toSrc().commit();
    }

    @Override
    default void rollback() throws SQLException{
        toSrc().rollback();
    }

    @Override
    default void close() throws SQLException{
        toSrc().close();
    }

    @Override
    default boolean isClosed() throws SQLException {
        return toSrc().isClosed();
    }

    @Override
    default DatabaseMetaData getMetaData() throws SQLException {
        return toSrc().getMetaData();
    }

    @Override
    default void setReadOnly(boolean readOnly) throws SQLException{
        toSrc().setReadOnly(readOnly);
    }

    @Override
    default boolean isReadOnly() throws SQLException {
        return toSrc().isReadOnly();
    }

    @Override
    default void setCatalog(String catalog) throws SQLException{
        toSrc().setCatalog(catalog);
    }

    @Override
    default String getCatalog() throws SQLException {
        return toSrc().getCatalog();
    }

    @Override
    default void setTransactionIsolation(int level) throws SQLException{
        toSrc().setTransactionIsolation(level);
    }

    @Override
    default int getTransactionIsolation() throws SQLException {
        return toSrc().getTransactionIsolation();
    }

    @Override
    default SQLWarning getWarnings() throws SQLException {
        return toSrc().getWarnings();
    }

    @Override
    default void clearWarnings() throws SQLException{
        toSrc().clearWarnings();
    }

    @Override
    default Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return toSrc().createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return toSrc().prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    default CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return toSrc().prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    default Map<String, Class<?>> getTypeMap() throws SQLException {
        return toSrc().getTypeMap();
    }

    @Override
    default void setTypeMap(Map<String, Class<?>> map) throws SQLException{
        toSrc().setTypeMap(map);
    }

    @Override
    default void setHoldability(int holdability) throws SQLException{
        toSrc().setHoldability(holdability);
    }

    @Override
    default int getHoldability() throws SQLException {
        return toSrc().getHoldability();
    }

    @Override
    default Savepoint setSavepoint() throws SQLException {
        return toSrc().setSavepoint();
    }

    @Override
    default Savepoint setSavepoint(String name) throws SQLException {
        return toSrc().setSavepoint(name);
    }

    @Override
    default void rollback(Savepoint savepoint) throws SQLException{
        toSrc().rollback(savepoint);
    }

    @Override
    default void releaseSavepoint(Savepoint savepoint) throws SQLException{
        toSrc().releaseSavepoint(savepoint);
    }

    @Override
    default Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return toSrc().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return toSrc().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    default CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return toSrc().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return toSrc().prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return toSrc().prepareStatement(sql, columnIndexes);
    }

    @Override
    default PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return toSrc().prepareStatement(sql, columnNames);
    }

    @Override
    default Clob createClob() throws SQLException {
        return toSrc().createClob();
    }

    @Override
    default Blob createBlob() throws SQLException {
        return toSrc().createBlob();
    }

    @Override
    default NClob createNClob() throws SQLException {
        return toSrc().createNClob();
    }

    @Override
    default SQLXML createSQLXML() throws SQLException {
        return toSrc().createSQLXML();
    }

    @Override
    default boolean isValid(int timeout) throws SQLException {
        return toSrc().isValid(timeout);
    }

    @Override
    default void setClientInfo(String name, String value) throws SQLClientInfoException{
        toSrc().setClientInfo(name, value);
    }

    @Override
    default void setClientInfo(Properties properties) throws SQLClientInfoException{
        toSrc().setClientInfo(properties);
    }

    @Override
    default String getClientInfo(String name) throws SQLException {
        return toSrc().getClientInfo(name);
    }

    @Override
    default Properties getClientInfo() throws SQLException {
        return toSrc().getClientInfo();
    }

    @Override
    default Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return toSrc().createArrayOf(typeName, elements);
    }

    @Override
    default Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return toSrc().createStruct(typeName, attributes);
    }

    @Override
    default void setSchema(String schema) throws SQLException{
        toSrc().setSchema(schema);
    }

    @Override
    default String getSchema() throws SQLException {
        return toSrc().getSchema();
    }

    @Override
    default void abort(Executor executor) throws SQLException{
        toSrc().abort(executor);
    }

    @Override
    default void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException{
        toSrc().setNetworkTimeout(executor, milliseconds);
    }

    @Override
    default int getNetworkTimeout() throws SQLException {
        return toSrc().getNetworkTimeout();
    }

    @Override
    default <T> T unwrap(Class<T> iface) throws SQLException {
        return toSrc().unwrap(iface);
    }

    @Override
    default boolean isWrapperFor(Class<?> iface) throws SQLException {
        return toSrc().isWrapperFor(iface);
    }
}
