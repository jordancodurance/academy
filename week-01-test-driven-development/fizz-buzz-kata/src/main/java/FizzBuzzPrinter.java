import static java.lang.String.valueOf;

public class FizzBuzzPrinter {

    public String print(int number) {
        if (number < 1 || number > 100) throw new InvalidNumberException();

        if (number % 3 == 0 && number % 5 == 0) return "FizzBuzz";
        if (number % 3 == 0) return "Fizz";
        if (number % 5 == 0) return "Buzz";

        return valueOf(number);
    }

}
