package com.epam.courses.jf.jdbc.cp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public interface PooledConnection extends ConnectionProxy {

    BlockingQueue<Connection> getFreeConnections();
    BlockingQueue<Connection> getReservedConnections();

    static PooledConnection wrap(Connection connection, BlockingQueue<Connection> freeConnections, BlockingQueue<Connection> reservedConnections) {
        return new PooledConnection() {
            @Override
            public BlockingQueue<Connection> getFreeConnections() {
                return freeConnections;
            }

            @Override
            public BlockingQueue<Connection> getReservedConnections() {
                return reservedConnections;
            }

            @Override
            public Connection toSrc() {
                return connection;
            }
        };
    }

    default void reallyClose() throws SQLException {
        toSrc().close();
    }

    @Override
    default void close() throws SQLException {
        if (toSrc().isClosed()) {
            throw new SQLException("Attempting to close closed connection.");
        }
        if (toSrc().isReadOnly()) {
            toSrc().setReadOnly(false);
        }
        if (!getReservedConnections().remove(this.toSrc())) {
            throw new SQLException("Error deleting connection from the given away connections pool.");
        }
        if (!getFreeConnections().offer(this.toSrc())) {
            throw new SQLException("Error allocating connection in the pool.");
        }
    }
}
