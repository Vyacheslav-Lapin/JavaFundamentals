package com.epam.courses.jf.common;

public interface Reflect {

    static void loadClass(String className) {
        loadClass(className, "Can't find class " + className);
    }

    static void loadClass(String className, String message) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(message, e);
        }
    }
}
