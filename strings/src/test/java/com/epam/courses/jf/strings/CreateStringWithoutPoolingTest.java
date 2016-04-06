package com.epam.courses.jf.strings;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateStringWithoutPoolingTest {

    private static final String STRING = "Hello";

    @Test
    public void stringOutOfTheStringPool() {
        String nonePooledString = CreateStringWithoutPooling.toNonePooledString(STRING);
        assertFalse(nonePooledString == STRING);
        assertTrue(nonePooledString.intern() == STRING);
    }

}
