package com.epam.courses.jf.javase01.mypackage;

import java.util.concurrent.Callable;

public class CallableThread implements Callable<Integer> {
    public int count = 0;
    public Integer call() {
        for (int i = 0; i < 1000000; i++) {
            count++;
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }
}
