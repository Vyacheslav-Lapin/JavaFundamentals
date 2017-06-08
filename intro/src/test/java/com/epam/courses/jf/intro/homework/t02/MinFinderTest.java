package com.epam.courses.jf.intro.homework.t02;

import org.junit.jupiter.api.Test;

import static com.epam.courses.jf.intro.homework.t02.MinFinder.findMin;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MinFinderTest {

    @Test
    void testFindMin() throws Exception {

        double min = findMin(0.1);
        out.println("Минимальный индекс: " + min);

        assertEquals(3.0, min);
    }
}
