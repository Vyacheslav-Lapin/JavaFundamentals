package com.epam.courses.jf.oop.homework.t2;

import com.epam.courses.jf.oop.homework.t1.Color;
import com.epam.courses.jf.oop.homework.t1.Pen;
import com.epam.courses.jf.oop.homework.t1.Stationery;
import lombok.Value;

@Value
public class Employee {

    private final String name;

    private Stationery[] stationers = {
            new Pen(Color.BLUE, "Parker"),
            new Pen(Color.BLACK, "NoName")
    };

    public long getStationeryPrise() {
        long result = 0;
        for (Stationery stationery: stationers)
            result += stationery.getPrice();
        return result;
    }
}
