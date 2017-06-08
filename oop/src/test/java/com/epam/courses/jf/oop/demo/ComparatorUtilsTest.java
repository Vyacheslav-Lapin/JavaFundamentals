package com.epam.courses.jf.oop.demo;

import lombok.Value;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

public class ComparatorUtilsTest {

    @Value
    class TestComparableClass implements Comparable<TestComparableClass> {
        private final int i;
        @Override
        public int compareTo(@NotNull ComparatorUtilsTest.TestComparableClass o) {
            return i - o.i;
        }
    }

    @Test
    public void compareChange() {
        val testComparableClass0 = new TestComparableClass(0);
        val testComparableClass1 = new TestComparableClass(1);
        val testComparableClass2 = new TestComparableClass(2);
        val testComparableClasses = new TreeSet<TestComparableClass>();
        testComparableClasses.forEach(System.out::println);
    }
}