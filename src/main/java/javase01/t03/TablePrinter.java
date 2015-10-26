package javase01.t03;

import static java.lang.Math.floor;
import static java.lang.Math.tan;
import static java.lang.System.out;

import java.io.PrintStream;

/**
 * Table printer.
 *
 * @author Vyacheslav_Lapin
 */
public class TablePrinter {

    double[][] table;

    void printTable(int quantity) {
        printTable(quantity, out);
    }

    void printTable(int quantity, PrintStream out) {

        out.println("+-------------------------+");
        out.println("|     x     |     f(x)    |");
        out.println("+-------------------------+");

        for (double[] functionResultPair: table)
            out.printf("|% .9f|% .9f|\n", functionResultPair[0], functionResultPair[1]);

        out.print("+-------------------------+");
    }

    public TablePrinter(double start, double end, double step) {

        assert end > start;

        table = getTable(start, end, step);
    }

    static double[][] getTable(double start, double end, double step) {

        int length = (int) floor((end - start) / step);
        double result[][] = new double[length][],
               x = start;

        for (int index = 0; index < length; index++, x += step)
            result[index] = getFunctionResultPair(x);

        return result;
    }

    static double[] getFunctionResultPair(double x) {
        return new double[]{x, function(x)};
    }

    static double function(double x) {
        return tan(2 * x) - 3;
    }
}
