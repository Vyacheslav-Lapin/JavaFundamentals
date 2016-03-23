package com.epam.courses.jf.javase01.t02;

import static java.lang.Math.pow;
import static java.lang.System.out;

public class MinFinder {

    static double findMin(double e) {
        return new MinFinder(e).findMin();
    }

    double e;

    MinFinder(double e) {
        this.e = e;
    }

    static double getA(int n) {
        return 1 / pow(n + 1, 2);
    }

    boolean filter(double a) {
        return a < e;
    }

    double findMin() {
        double a;

        for (int index = 1;; index++) {
            a = getA(index);
            if (filter(a)) {
                return a;
            } else {
                out.println(a);
            }
        }
    }

    public static void main(String[] args) {
        double min = findMin(0.1);
        out.println("Минимальное значение: " + min);
    }
}
