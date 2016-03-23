package com.epam.courses.jf.javase01.mypackage;

@FunctionalInterface
public interface Sortable {

    double PI = 3.1414926535;

    long m1(double x);

    default double m2() {
        return 0;
    }
}
