package com.epam.courses.jf.common;

import java.util.Properties;

public interface PropertyUtils {
    static String getAndRemove(Properties properties, String key) {
        return (String) properties.remove(key);
    }
}
