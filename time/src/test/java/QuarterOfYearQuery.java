import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

import static java.time.Month.*;

public class QuarterOfYearQuery implements TemporalQuery<Quarter> {
    @Override
    public Quarter queryFrom(TemporalAccessor temporal) {
        LocalDate now = LocalDate.from(temporal);
        return now.isBefore(now.with(APRIL).withDayOfMonth(1)) ? Quarter.FIRST
                : now.isBefore(now.with(JULY).withDayOfMonth(1)) ? Quarter.SECOND
                : now.isBefore(now.with(NOVEMBER).withDayOfMonth(1)) ? Quarter.THIRD
                : Quarter.FOURTH;
    }
}
