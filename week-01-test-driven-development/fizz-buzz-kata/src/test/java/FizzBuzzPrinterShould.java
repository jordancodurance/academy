import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FizzBuzzPrinterShould {

    private FizzBuzzPrinter target;

    @BeforeEach
    void setUp() {
        target = new FizzBuzzPrinter();
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "3, Fizz",
            "4, 4",
            "5, Buzz",
            "6, Fizz",
            "8, 8",
            "9, Fizz",
            "10, Buzz",
            "15, FizzBuzz",
            "30, FizzBuzz",
            "100, Buzz"
    })
    void print_out_the_expected_output(int input, String output) {
        assertEquals(target.print(input), output);
    }

    @ParameterizedTest
    @CsvSource({
            "-1", "0", "101"
    })
    void prevent_printing_numbers_outside_required_range(int input) {
        assertThrows(InvalidNumberException.class, () -> target.print(input));
    }
}
