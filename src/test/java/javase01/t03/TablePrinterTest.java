package javase01.t03;

import org.junit.Test;

/**
 * @author Vyacheslav_Lapin
 */
public class TablePrinterTest {

    @Test
    public void testGetTable() throws Exception {
        new TablePrinter(0, 10, 0.5).printTable(10);
    }
}
