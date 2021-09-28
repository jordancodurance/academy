package time;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;

import static java.lang.String.format;
import static time.TimeUnit.DAY;
import static time.TimeUnit.HOUR;
import static time.TimeUnit.MINUTE;
import static time.TimeUnit.MONTH;
import static time.TimeUnit.SECOND;
import static time.TimeUnit.YEAR;

public class DurationFormatter {

    private static final int DAYS_IN_A_YEAR = 365;
    private static final int AVERAGE_DAYS_IN_MONTH = 30;

    private final TimestampProvider timestampProvider;

    public DurationFormatter(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    public String fromNow(Instant duration) {
        Instant now = timestampProvider.now();
        Duration durationBetween = Duration.between(duration, now);

        int yearsBetween = (int) durationBetween.toDays() / DAYS_IN_A_YEAR;
        if (yearsBetween > 0) return toFormattedCollectiveDuration(yearsBetween, YEAR);

        int monthsBetween = (int) durationBetween.toDays() / AVERAGE_DAYS_IN_MONTH;
        if (monthsBetween > 0) return toFormattedCollectiveDuration(monthsBetween, MONTH);

        int daysBetween = (int) durationBetween.toDays();
        if (daysBetween > 0) return toFormattedCollectiveDuration(daysBetween, DAY);

        int hoursBetween = (int) durationBetween.toHours();
        if (hoursBetween > 0) return toFormattedCollectiveDuration(hoursBetween, HOUR);

        int minutesBetween = (int) durationBetween.toMinutes();
        if (minutesBetween > 0) return toFormattedCollectiveDuration(minutesBetween, MINUTE);

        int secondsBetween = (int) durationBetween.getSeconds();
        return toFormattedCollectiveDuration(secondsBetween, SECOND);
    }

    private String toFormattedCollectiveDuration(int duration, TimeUnit timeUnit) {
        if (duration == 1) return format("%d %s ago", duration, timeUnit.unit);

        return format("%d %ss ago", duration, timeUnit.unit);
    }

}
