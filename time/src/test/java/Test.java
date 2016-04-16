import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Test {

    @org.junit.Test
    public void test() {

        int year = 2007;
        Month month = Month.DECEMBER;
        int dayOfMonth = 1;
        LocalDate today = LocalDate.of(year, month, dayOfMonth).with(
                TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));

//        TemporalAdjuster NEXT_WORKDAY = w -> {
//            LocalDate result = (LocalDate) w;
//            do {
//                result = result.plusDays(1);
//            } while (result.getDayOfWeek().getValue() >= 6);
//            return result;
//        };
//        LocalDate backToWork = today.with(NEXT_WORKDAY);

        TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w; // No cast
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= 6);
            return result;
        });

        LocalTime rightNow = LocalTime.now();
        LocalTime bedtime = LocalTime.of(22, 30); // or LocalTime.of(22, 30, 0)

        LocalTime wakeup = bedtime.plusHours(8); // wakeup is 6:30:00


        QuarterOfYearQuery q = new QuarterOfYearQuery();
        // Direct
        Quarter quarter = q.queryFrom(LocalDate.now());
        System.out.println(quarter);
        // Indirect
        quarter = LocalDate.now().query(q);
        System.out.println(quarter);

        // 1969-07-16T09:32-04:00[America/New_York]
        ZonedDateTime apollo11launch = ZonedDateTime.of(
                1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));

        String formatted = DateTimeFormatter.ISO_DATE_TIME.format(apollo11launch);
        // 1969-07-16T09:32:00-05:00[America/New_York]
    }
}
