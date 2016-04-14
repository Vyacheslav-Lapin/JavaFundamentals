package com.epam.courses.jf.strings;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTest {

    @Test
    public void newDateTest() {

        Date date = new Date(); // Represents present moment

        Date date1 = new Date(0); // Jan 01 03:00:00 MSK 1970

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date2 = null;
        try {
            date2 = sdf.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date2); //Tue Aug 31 10:20:56 SGT 1982

    }

    @Test
    public void newCalendarTest() {
        Date date = new Date(0);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

    }

    @Test
    public void exampleFromJavaOne2008() {
        // Example from JavaOne 2008
        Date date = new Date(107, 11, 13, 16, 40);
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Hong_Kong");
        Calendar calendar = new GregorianCalendar(timeZone);
        calendar.setTime(date);
        DateFormat dateFormat = new SimpleDateFormat("HH:MM Z");
        String string = dateFormat.format(calendar);
        System.out.println(string);
    }
}
