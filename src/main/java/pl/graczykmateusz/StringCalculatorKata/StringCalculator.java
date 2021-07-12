package pl.graczykmateusz.StringCalculatorKata;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator implements IStringCalculator {

    @Override
    public int add(String numbers) {
        if (numbers.isEmpty())
            return 0;
        if (numbers.length() != 1) {
            List<String> splitNumbers;

            boolean isDifferentSeparator = numbers.matches("^//.*[\\n].*");
            if (isDifferentSeparator) {
                List<String> specialSeparators = Arrays.asList(".", "+", "*", "?", "^", "$", "(", ")", "[", "]", "{", "}", "|", "\\");
                String separator = StringUtils.substringBetween(numbers, "//", "\n");
                String numbersWithoutInitialSeparator = StringUtils.substringAfterLast(numbers, "\n");
                if (specialSeparators.contains(separator))
                    splitNumbers = Arrays.asList(numbersWithoutInitialSeparator.split("[,\\n{[\\]" + separator + "}]"));
                else
                    splitNumbers = Arrays.asList(numbersWithoutInitialSeparator.split("[,\\n{" + separator + "}]"));
            } else {
                splitNumbers = Arrays.asList(numbers.split("[,\\n]"));
            }
            Integer sum = splitNumbers.stream()
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
            return sum;
        }
        return Integer.parseInt(numbers);
    }
}
