import java.util.TreeMap;

public class RomanNumeralsConverter {

    private final TreeMap<Integer, String> SPECIAL_NUMERAL = new TreeMap<>() {{
        put(1, "I");
        put(4, "IV");
        put(5, "V");
        put(9, "IX");
        put(10, "X");
        put(40, "XL");
        put(50, "L");
        put(90, "XC");
        put(100, "C");
        put(400, "CD");
        put(500, "D");
        put(900, "CM");
        put(1000, "M");
    }};

    public String convert(int amount) {
        StringBuilder numeral = new StringBuilder();
        while (amount != 0) {
            Integer nearestAmount = SPECIAL_NUMERAL.floorKey(amount);
            String nearestNumeral = SPECIAL_NUMERAL.get(nearestAmount);
            numeral.append(nearestNumeral);

            amount -= nearestAmount;
        }

        return numeral.toString();
    }

}
