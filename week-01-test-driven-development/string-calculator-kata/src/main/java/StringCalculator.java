import static java.util.Arrays.stream;

public class StringCalculator {

    private static final int MINIMUM_ALLOWED_NUMBER = 0;
    private static final int MAXIMUM_ALLOWED_NUMBER = 1000;
    private static final char DEFAULT_SEPARATOR = ',';
    private static final String CUSTOM_SEPARATOR_INDICATOR = "//";

    public int add(String numbers) {
        if (numbers.equals("")) return MINIMUM_ALLOWED_NUMBER;

        int sum = 0;
        int[] parsedNumbers = parseNumbers(numbers);
        for (int number : parsedNumbers) {
            if (number < MINIMUM_ALLOWED_NUMBER) throw new InvalidNumberException();

            if (number <= MAXIMUM_ALLOWED_NUMBER) sum += number;
        }

        return sum;
    }

    private int[] parseNumbers(String numbers) {
        if (numbers.startsWith(CUSTOM_SEPARATOR_INDICATOR)) {
            int customSeparatorLength = CUSTOM_SEPARATOR_INDICATOR.length();
            String updatedNumbers = numbers.substring(customSeparatorLength + 1);
            char separator = numbers.charAt(customSeparatorLength);

            return findNumbers(updatedNumbers, separator);
        }

        return findNumbers(numbers, DEFAULT_SEPARATOR);
    }

    private int[] findNumbers(String numbers, char separator) {
        String search = String.format("\\n|%c", separator);
        String[] result = numbers.trim().split(search);

        return stream(result)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
