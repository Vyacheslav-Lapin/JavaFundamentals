package com.epam.courses.jf.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringWithoutPoolingCreatorTest {

    private static final String STRING = "Hello";

    @Test
    void stringOutOfTheStringPool() {
        String nonePooledString = StringWithoutPoolingCreator.toNonePooledString(STRING);
        assertFalse(nonePooledString == STRING);
        assertTrue(nonePooledString.intern() == STRING);
    }

}
