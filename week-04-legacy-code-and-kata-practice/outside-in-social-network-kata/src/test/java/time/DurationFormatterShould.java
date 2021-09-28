package time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DurationFormatterShould {

    private final Instant NOW = toInstant("2021-09-23T10:15:00");

    @Mock
    private TimestampProvider timestampProvider;

    private DurationFormatter target;

    @BeforeEach
    void setUp() {
        target = new DurationFormatter(timestampProvider);
    }

    @ParameterizedTest
    @CsvSource({
            "'2019-06-12T09:04:35', '2 years ago'",
            "'2020-03-21T13:27:09', '1 year ago'",
            "'2021-02-23T10:04:35', '7 months ago'",
            "'2021-08-21T19:11:01', '1 month ago'",
            "'2021-08-29T16:45:12', '24 days ago'",
            "'2021-09-21T22:10:20', '1 day ago'",
            "'2021-09-23T04:09:00', '6 hours ago'",
            "'2021-09-23T09:11:13', '1 hour ago'",
            "'2021-09-23T09:42:18', '32 minutes ago'",
            "'2021-09-23T10:13:52', '1 minute ago'",
            "'2021-09-23T10:14:38', '22 seconds ago'",
            "'2021-09-23T10:14:59', '1 second ago'",
            "'2021-09-23T10:15:00', '0 seconds ago'"
    })
    void format_for_duration_from_now(String dateTime, String expectedDuration) {
        Instant instant = toInstant(dateTime);
        given(timestampProvider.now()).willReturn(NOW);

        assertEquals(expectedDuration, target.fromNow(instant));
    }

    private Instant toInstant(String dateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime);
        return parsedDateTime.toInstant(UTC);
    }

}