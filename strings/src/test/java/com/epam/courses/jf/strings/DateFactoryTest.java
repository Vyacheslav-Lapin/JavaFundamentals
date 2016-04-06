package com.epam.courses.jf.strings;

import org.junit.Test;

import static com.epam.courses.jf.strings.DateFactory.getDate;
import static junit.framework.Assert.assertEquals;

public class DateFactoryTest {

    @Test
    public void getDateTest() {
        long t = System.currentTimeMillis();
        assertEquals(t, getDate(t).getTime());
    }
}
