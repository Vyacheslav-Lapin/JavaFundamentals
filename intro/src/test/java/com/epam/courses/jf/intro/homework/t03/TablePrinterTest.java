package com.epam.courses.jf.intro.homework.t03;

import org.junit.jupiter.api.Test;

public class TablePrinterTest {

    @Test
    public void testGetTable() throws Exception {
        new TablePrinter(0, 10.1, 0.5).printTable(10);
    }
}
