package com.epam.courses.jf.jdbc.cp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.epam.courses.jf.common.functions.ExceptionalRunnable.run;

/**
 * Default ConnectionPool, instantiated by {@link ConnectionPool#create(String)} static method
 */
class SimpleConnectionPool implements ConnectionPool {

    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> reservedConnections;
    private volatile boolean isClosing;

    SimpleConnectionPool(int size, Supplier<Connection> connectionSupplier) {
        freeConnections = new ArrayBlockingQueue<>(size);
        reservedConnections = new ArrayBlockingQueue<>(size);

        IntStream.range(0, size)
                .mapToObj(index -> connectionSupplier.get())
                .forEach(this::add);
    }

    private void add(Connection connection) {
        freeConnections.add(wrap(connection));
    }

    @Override
    public Connection getConnection() {

        if (isClosing) throw new RuntimeException("Trying to get connection from closed pool!");

        try {
            final Connection connection = freeConnections.take();
            reservedConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        isClosing = true;
        freeConnections.stream().forEach(connection -> run(connection::close, RuntimeException::new));
    }

    private ConnectionProxy wrap(Connection connection) {
        return new ConnectionProxy() {
            @Override
            public Connection toSrc() {
                return connection;
            }

            @Override
            public void close() throws SQLException {
                if (connection.isClosed())
                    throw new SQLException("Attempting to close closed connection.");

                if (connection.isReadOnly())
                    toSrc().setReadOnly(false);

                if (reservedConnections.contains(this) && !reservedConnections.remove(this))
                    throw new RuntimeException("Error deleting connection from the given away connections pool.");

                if (isClosing)
                    toSrc().close();
                else if (!freeConnections.offer(toSrc()))
                        throw new RuntimeException("Error allocating connection in the pool.");
            }
        };
    }
}
