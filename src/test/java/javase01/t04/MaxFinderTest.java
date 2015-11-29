package javase01.t04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxFinderTest {

    @Test
    public void testGetMaximum() throws Exception {
        assertEquals(4, new MaxFinder(new double[] {1, 2, 3, -1, 0}).getMaximum(), 0.1);
    }
}
