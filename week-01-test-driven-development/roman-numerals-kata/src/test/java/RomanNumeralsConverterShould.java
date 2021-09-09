import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralsConverterShould {

    private RomanNumeralsConverter target;

    @BeforeEach
    void setUp() {
        target = new RomanNumeralsConverter();
    }

    @ParameterizedTest
    @CsvSource({
            "1, I",
            "2, II",
            "3, III",
            "4, IV",
            "5, V",
            "6, VI",
            "7, VII",
            "9, IX",
            "10, X",
            "20, XX",
            "30, XXX",
            "40, XL",
            "50, L",
            "60, LX",
            "70, LXX",
            "80, LXXX",
            "90, XC",
            "100, C",
            "200, CC",
            "300, CCC",
            "400, CD",
            "500, D",
            "600, DC",
            "700, DCC",
            "800, DCCC",
            "900, CM",
            "1000, M"
    })
    void convert_arabic_numbers_to_roman_numerals(int amount, String numeral) {
        assertEquals(target.convert(amount), numeral);
    }

}