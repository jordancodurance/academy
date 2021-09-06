import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorShould {

    private StringCalculator target;

    @BeforeEach
    void setUp() {
        target = new StringCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "'', 0",
            "4, 4",
            "6, 6",
            "'1,2', 3",
            "'1,2,3,4,5,6,7,8,9', 45",
            "'1\n2,3', 6",
            "'1\n2,3\n3\n1', 10",
            "'//;\n1;2', 3",
            "'1000', 1000",
            "'1001', 0"
    })
    void add_numbers(String input, int expected) {
        assertEquals(target.add(input), expected);
    }

    @ParameterizedTest
    @CsvSource({
           "'-1'",
            "'1,-2'",
            "'1,2,-3'",
            "'1,-2,-3'"
    })
    void prevent_negative_numbers_being_added_together(String input) {
        assertThrows(InvalidNumberException.class, () -> target.add(input));
    }
}
