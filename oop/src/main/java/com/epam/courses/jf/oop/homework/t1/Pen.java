package com.epam.courses.jf.oop.homework.t1;

import lombok.Value;

@Value
public class Pen implements Stationery {
    private final Color color;
    private final String tm;

    @Override
    public int getPrice() {
        return tm.equals("Parker") ? 100: 20;
    }
}
