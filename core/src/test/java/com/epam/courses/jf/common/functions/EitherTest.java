package com.epam.courses.jf.common.functions;

import org.junit.Test;

import java.util.Random;

public class EitherTest {
    @SuppressWarnings("unchecked")
    @Test
    public void left() throws Exception {
        new Random().ints(20, 0, 2)
                .mapToObj(i -> i == 0
                        ? Either.<String, Integer>left("left value (String)")
                        : Either.<String, Integer>right(42))
                .forEach(either -> either.apply(
                        left -> System.out.println("received left value: " + left.substring(11)),
                        right -> System.out.println("received right value: 0x" + Integer.toHexString(right))
                ))
        ;
    }

}
