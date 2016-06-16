package com.epam.courses.jf.intro.homework.t02;

import org.hamcrest.core.Is;
import org.junit.Test;

import static com.epam.courses.jf.intro.homework.t02.MinFinder.findMin;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MinFinderTest {

    @Test
    public void testFindMin() throws Exception {

        double min = findMin(0.1);
        out.println("Минимальный индекс: " + min);

        assertThat(min, Is.is(3.0));
    }
}
