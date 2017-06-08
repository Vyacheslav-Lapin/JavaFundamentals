package com.epam.courses.jf.oop.demo;

import java.util.Comparator;

public class ComparatorUtils {

    public static <T extends Comparable<T>> T compareChange(T comparable, Comparator<T> comparator) {
        return comparable;
    }
}
