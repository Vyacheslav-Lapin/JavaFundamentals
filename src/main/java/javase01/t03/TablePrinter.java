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

//    private static final Logger logger = LogManager.getLogger(TablePrinter.class);

    double[][] table;

    void printTable(int quantity) {
        printTable(quantity, out);
    }

    void printTable(int quantity, PrintStream out) {

        printTop(quantity, out);

        for (double[] functionResultPair: table)
            out.printf("|% .9f|% .9f|\n", functionResultPair[0], functionResultPair[1]);

        printBottom(out);
    }

    private void printBottom(PrintStream out) {
        out.print("+-------------------------+");
    }

    private void printTop(int quantity, PrintStream out) {
        out.println("+-------------------------+"); // TODO: Сделать ширину каждого заголовка равной quantity
        out.println("|     x     |     f(x)    |");
        out.println("+-------------------------+");
    }

    public TablePrinter(double start, double end, double step) {

        assert end > start;

        table = getTable(start, end, step);
    }

    static double[][] getTable(double start, double end, double step) {

        int length = (int) floor((end - start) / step) + 1;
        double result[][] = new double[length][],
               x = start;

        for (int index = 0; index < length; index++, x += step) {
            result[index] = getDataAndResultPair(x);
        }

        return result;
    }

    static double[] getDataAndResultPair(double x) {
        return new double[]{x, function(x)};
    }
    
    static double function(double x) {
        return tan(2 * x) - 3;
    }
}
