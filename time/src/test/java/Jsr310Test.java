import com.epam.courses.jf.time.Quarter;
import com.epam.courses.jf.time.QuarterOfYearQuery;
import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.Format;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Jsr310Test {

    @Test
    public void adjusterTest() {
        final LocalDate localDate = LocalDate.of(2016, Month.APRIL, 16);
        assertThat(localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)),
                is(LocalDate.of(2016, Month.APRIL, 19)));
    }

    @Test
    public void instant2DateTransform() {
        final Instant now = Instant.now();
        final Date date = Date.from(now);
        assertThat(date.toInstant(), is(now));
    }

    @Test
    public void instant2TimestampTransform () {
        final Instant now = Instant.now();
        final Timestamp timestamp = Timestamp.from(now);
        assertThat(timestamp.toInstant(), is(now));
    }

    @Test
    public void zonedDateTime2CalendarTransform() {
        final ZonedDateTime now = ZonedDateTime.now();
        final GregorianCalendar calendar = GregorianCalendar.from(now);
        assertThat(calendar.toZonedDateTime(), is(now));
    }

    @Test
    public void localDateTime2TimestampTransform() {
        final LocalDateTime now = LocalDateTime.now();
        final Timestamp timestamp = Timestamp.valueOf(now);
        assertThat(timestamp.toLocalDateTime(), is(now));
    }

    @Test
    public void localDate2SqlDateTransform() {
        final LocalDate now = LocalDate.now();
        final java.sql.Date date = java.sql.Date.valueOf(now);
        assertThat(date.toLocalDate(), is(now));
    }

    @Test
    public void localTime2SqlTimeTransform() {
        final LocalTime now = LocalTime.now();
        final Time time = Time.valueOf(now);
        assertThat(time.toLocalTime(), is(now));
    }

    @Test
    public void localTime2FormatTransform() {
        LocalDateTime dateTime = LocalDateTime.now();
        final DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        final Format legacyFormatter = formatter.toFormat();

//        final LocalTime now = LocalTime.now();
//        final Time time = Time.valueOf(now);
//        assertThat(time.toLocalTime(), is(now));
    }

    @Test
    public void timeZone2ZoneIdTransform() {
        final ZoneId zoneId = ZoneId.of("America/New_York");
        final TimeZone timeZone = TimeZone.getTimeZone(zoneId);
        assertThat(timeZone.toZoneId(), is(zoneId));
    }

    @Test
    public void test() {

//        LocalDate backToWork = today.with(NEXT_WORKDAY);

        TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w; // No cast!
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
        ZonedDateTime apollo11launch1 = ZonedDateTime.of(
                1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));

        String formatted1 = DateTimeFormatter.ISO_DATE_TIME.format(apollo11launch1);
        // 1969-07-16T09:32:00-05:00[America/New_York]

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String formatted = formatter.format(apollo11launch1); // July 16, 1969 9:32:00 AM EDT
        formatted = formatter.withLocale(Locale.FRENCH).format(apollo11launch1);
        // 16 juillet 1969 09:32:00 EDT

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");

        LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
        ZonedDateTime apollo11launch =
                ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
    }
}
