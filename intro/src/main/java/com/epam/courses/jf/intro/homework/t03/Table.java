package com.epam.courses.jf.intro.homework.t03;

import lombok.experimental.var;
import lombok.val;

import static java.lang.Math.floor;
import static java.lang.Math.tan;

public class Table {

    private double start;
    private double end;
    private double step;

    public Table(double start, double end, double step) {

        assert end > start;

        this.start = start;
        this.end = end;
        this.step = step;
    }

    public double[][] getTable() {

        val length = (int) floor((end - start) / step) + 1;
        val result = new double[length][];
        var x = start;

        for (var index = 0; index < length; index++, x += step)
            result[index] = getDataAndResultPair(x);

        return result;
    }

    static double[] getDataAndResultPair(double x) {
        return new double[]{x, function(x)};
    }

    static double function(double x) {
        return tan(2 * x) - 3;
    }
}
