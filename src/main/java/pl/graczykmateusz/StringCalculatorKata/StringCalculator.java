package pl.graczykmateusz.StringCalculatorKata;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StringCalculator implements IStringCalculator {

    @Override
    public int add(String numbers) {
        if (numbers.isEmpty())
            return 0;
        if (numbers.length() != 1) {
            List<String> splitNumbers;
            boolean isDifferentSeparator = numbers.matches("^(?s)//.*\\n.*");
            if (isDifferentSeparator) {
                String separator = StringUtils.substringBetween(numbers, "//", "\n");
                String numbersWithoutInitialSeparator = StringUtils.substringAfterLast(numbers, "\n");
                splitNumbers = Arrays.asList(numbersWithoutInitialSeparator.split("(?s)[,\\n{" + separator + "}]"));
            } else {
                splitNumbers = Arrays.asList(numbers.split("(?s)[,\\n]"));
            }
            Optional<String> negativeNumber = splitNumbers.stream()
                    .filter(s -> s.startsWith("-"))
                    .findAny();
            if(!negativeNumber.isEmpty())
                throw new RuntimeException("negatives not allowed " + negativeNumber.get());
            Integer sum = splitNumbers.stream()
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
            return sum;
        }
        return Integer.parseInt(numbers);
    }
}
