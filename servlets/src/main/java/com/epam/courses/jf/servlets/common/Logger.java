package com.epam.courses.jf.servlets.common;

import java.time.Instant;

@FunctionalInterface
public interface Logger {

    void log(String s);

    default void logByLevel(String level, String message) {
        log(Instant.now() + " " + level + " [" + Thread.currentThread().getName() + "] " + message);
    };

    default void info(String message) {
        logByLevel("INFO", message);
    }

    default void warn(String message) {
        logByLevel("WARNING", message);
    }

    default void error(String message) {
        logByLevel("ERROR", message);
    }

    default void fatal(String message) {
        logByLevel("FATAL", message);
    }
}
