package com.epam.courses.jf.jdbc.cp;

import com.hegel.core.Private;
import com.hegel.core.functions.ExceptionalBiFunction;
import com.hegel.core.functions.ExceptionalConsumer;
import com.hegel.properties.PropertyMap;
import com.hegel.reflect.Reflect;
import javaslang.CheckedFunction1;
import javaslang.Function1;
import lombok.SneakyThrows;
import lombok.val;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public interface ConnectionPool extends Supplier<Connection>, AutoCloseable {

    default void executeScripts(String... prepareFilePaths) {
        executeScripts(Arrays.stream(prepareFilePaths).map(Paths::get));
    }

    default void executeScripts(Path... paths) {
        executeScripts(Arrays.stream(paths));
    }

    @Private
    Function1<Path, Stream<String>> UNCHECKED_FILES_LINES =
            CheckedFunction1.<Path, Stream<String>>lift(Files::lines)
                    .andThen(streams -> streams.getOrElseThrow(RuntimeException::new));

    @SneakyThrows
    default void executeScripts(Stream<Path> paths) {
        try (val connection = get();
             val statement = connection.createStatement()) {

//            Runnable runnable = () -> System.out.println("kjhs");

//            Function0.of((Function0<Void>) runnable::run);

//          Function1<String, Try<Void>> addBatch1 = CheckedFunction1.<String, Void>liftTry(statement::addBatch);
//          Function1<String, Void> addBatch2 = addBatch1.andThen(voids -> voids.getOrElseThrow(RuntimeException::new));
            Consumer<String> addBatch =
                    ExceptionalConsumer.toUncheckedConsumer(statement::addBatch);

            paths.flatMap(path -> Arrays.stream(UNCHECKED_FILES_LINES.apply(path)
                    .collect(Collectors.joining())
                    .split(";"))
            ).forEach(addBatch);

            statement.executeBatch();
        }
    }

    @SneakyThrows
    static ConnectionPool create(String propertyFileName) {

        val propertyMap = PropertyMap.fromFile(propertyFileName);

        assert propertyMap.containsKey("url");
        assert propertyMap.containsKey("poolSize");
        assert propertyMap.containsKey("driver");

        Reflect.loadClass(propertyMap.remove("driver"), "Can't find database driver class");

        return create(propertyMap.remove("url"),
                parseInt(propertyMap.remove("poolSize")),
                propertyMap.toProperties());
    }

    @Private
    static ConnectionPool create(String url, int poolSize, Properties properties) {
        assert properties.containsKey("user");
        assert properties.containsKey("password");
        assert properties.size() == 2;

        return new SimpleConnectionPool(poolSize,
                ExceptionalBiFunction.supplyUnchecked(DriverManager::getConnection, url, properties));
    }
}
