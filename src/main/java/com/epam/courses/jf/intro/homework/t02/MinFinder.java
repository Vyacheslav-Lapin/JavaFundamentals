package com.epam.courses.jf.intro.homework.t02;

import static java.lang.Math.pow;
import static java.lang.System.out;

public class MinFinder {

    public static double findMin(double e) {
        return new MinFinder(e).findMin();
    }

    private double e;

    private MinFinder(double e) {
        this.e = e;
    }

    private static double getA(int n) {
        return 1 / pow(n + 1, 2);
    }

    private boolean filter(double a) {
        return a < e;
    }

    private double findMin() {
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
        out.println("Минимальное значение: " + findMin(0.1));
    }
}
