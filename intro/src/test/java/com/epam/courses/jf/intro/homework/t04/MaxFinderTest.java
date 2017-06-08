package com.epam.courses.jf.intro.homework.t04;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxFinderTest {

    @Test
//    @Ignore
    public void testGetMaximum() throws Exception {
        assertEquals(4,
                new MaxFinder(1, 2, 3, -1, 0)
                        .getMaximum(), 0.1);
    }
}
