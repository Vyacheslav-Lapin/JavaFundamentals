package javase01.t05;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Vyacheslav_Lapin
 */
public class CrossMatrixTest {

    @Test
    public void testGet() throws Exception {
        CrossMatrix crossMatrix = new CrossMatrix(6);

        System.out.println(crossMatrix);

        assertTrue(crossMatrix.get(1, 1));
        assertTrue(crossMatrix.get(5, 5));
        assertTrue(crossMatrix.get(0, 5));
        assertFalse(crossMatrix.get(4, 5));
    }
}
