package pl.graczykmateusz.StringCalculatorKata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator implements IStringCalculator {

    @Override
    public int add(String numbers) {
        if (numbers.isEmpty())
            return 0;
        if(numbers.length() != 1) {
            List<String> splitedNumbers = Arrays.asList(numbers.split("\\s*,\\s*"));
            var sum = splitedNumbers.stream()
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
            return sum;
        }
        return Integer.parseInt(numbers);
    }
}
