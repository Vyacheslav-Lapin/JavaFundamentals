package com.epam.courses.jf.javase01.t03;

import org.junit.Test;

public class TablePrinterTest {

    @Test
    public void testGetTable() throws Exception {
        new TablePrinter(0, 10.1, 0.5).printTable(10);
    }
}
