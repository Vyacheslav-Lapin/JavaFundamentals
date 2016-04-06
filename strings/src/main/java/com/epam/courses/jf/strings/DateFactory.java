package com.epam.courses.jf.strings;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFactory {

    public static Date getDate(long t) {
        return new java.util.Date(t);
    }

    public static Calendar getCalendar() {
        return new GregorianCalendar();
    }
}
