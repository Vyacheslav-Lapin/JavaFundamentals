package com.epam.courses.jf.intro.homework.t04;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxFinderTest {

    @Test
    @Ignore
    public void testGetMaximum() throws Exception {
        assertEquals(4, new MaxFinder(new double[] {1, 2, 3, -1, 0}).getMaximum(), 0.1);
    }
}
