package com.epam.courses.jf.intro.homework.t03;

import java.io.PrintStream;

import static java.lang.System.out;

public class TablePrinter {

    private final Table table;

    public TablePrinter(double start, double end, double step) {
        table = new Table(start, end, step);
    }

    public void printTable(int quantity) {
        printTable(quantity, out);
    }

    private void printTable(int quantity, PrintStream out) {

        printTop(quantity, out);

        for (double[] functionResultPair: table.getTable())
            out.printf("|% 3.9f|% 3.9f|\n", functionResultPair[0], functionResultPair[1]);

        printBottom(quantity, out);
    }

    private void printBottom(int quantity, PrintStream out) {
        out.print("+-------------------------+");
    }

    private void printTop(int quantity, PrintStream out) {
        out.println("+-------------------------+"); // TODO: Сделать ширину каждого заголовка равной quantity
        out.println("|     x     |     f(x)    |");
        out.println("+-------------------------+");
    }
}
