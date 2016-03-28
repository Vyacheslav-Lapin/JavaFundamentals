package com.epam.courses.jf.intro.homework.t01.main;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class MainTest {

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Test
    public void main() throws Exception {
        assertEquals("I am string in Logic." + LINE_SEPARATOR, fromSystemOut(() -> Main.main(new String[0])));
    }

    private static String fromSystemOut(Runnable runnable) {

        PrintStream realOut = System.out;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)) {

            System.setOut(printStream);
            runnable.run();

            return new String(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(realOut);
        }
    }
}
