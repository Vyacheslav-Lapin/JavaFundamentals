package com.epam.courses.jf.intro.homework.t01.main;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Test
    void main() throws Exception {
        assertEquals("I am string in Logic." + LINE_SEPARATOR,
                fromSystemOut(Main::main));
    }

    @SneakyThrows
    private static String fromSystemOut(Runnable runnable) {

        PrintStream realOut = System.out;

        try (val out = new ByteArrayOutputStream();
             val printStream = new PrintStream(out)) {

            System.setOut(printStream);
            runnable.run();

            return new String(out.toByteArray());

        } finally {
            System.setOut(realOut);
        }
    }
}
