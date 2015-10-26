package javase01.t02;

import static javase01.t02.MinFinder.findMin;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vyacheslav_Lapin
 */
public class MinFinderTest {

    @Test
    public void testFindMin() throws Exception {

        double min = findMin(2, 1);

        System.out.println("Минимальное значение: " + min);

        Assert.assertTrue(min > 1.0 / 10);
        Assert.assertTrue(min < 1.0 / 8);
    }
}
