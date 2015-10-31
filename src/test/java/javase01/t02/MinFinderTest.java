package javase01.t02;

import static javase01.t02.MinFinder.findMin;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Vyacheslav_Lapin
 */
public class MinFinderTest {

    @Test
    public void testFindMin() throws Exception {

        double min = findMin(0.1);

        System.out.println("Минимальное значение: " + min);

        assertTrue(min > 0.06);
        assertTrue(min < 0.07);
    }
}
