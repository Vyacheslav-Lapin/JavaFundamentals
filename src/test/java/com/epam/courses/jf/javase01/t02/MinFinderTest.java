package com.epam.courses.jf.javase01.t02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.epam.courses.jf.javase01.t02.MinFinder.findMin;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

public class MinFinderTest {

    @Before
    public void kuhadfg(){
        System.out.println("");
    }

    @Test
    public void testFindMin() throws Exception {

        double min = findMin(0.1);
        out.println("Минимальное значение: " + min);

        assertEquals(min, 0.06, 0.01);
    }

    @After
    public void kjhdfg(){

    }
}
