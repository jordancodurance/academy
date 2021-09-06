import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeapYearValidatorShould {

    private LeapYearValidator target;

    @BeforeEach
    void setUp() {
        target = new LeapYearValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "1988, true",
            "1900, false",
            "1996, true",
            "2000, true",
            "2001, false",
            "2004, true",
            "2016, true",
            "2020, true",
            "2100, false",
            "2200, false",
            "2400, true"
    })
    void validate_leap_year(int input, boolean expected) {
        assertEquals(target.isLeapYear(input), expected);
    }
}
