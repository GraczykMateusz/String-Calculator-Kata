package pl.graczykmateusz.StringCalculatorKata;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.OrderComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator implements IStringCalculator {

    @Override
    public int add(String numbers) {
        if (numbers.isEmpty())
            return 0;
        if (numbers.length() != 1) {
            List<String> splitNumbers;
            boolean isDifferentSeparator = numbers.matches("^(?s)//.*\\n.*");
            if (isDifferentSeparator) {
                boolean isDifferentManySeparators = numbers.matches("^(?s)//\\[.*\\]\\n.*$");
                String separator;
                if (isDifferentManySeparators)
                    separator = StringUtils.substringBetween(numbers, "//[", "]\n");
                else
                    separator = StringUtils.substringBetween(numbers, "//", "\n");
                String numbersWithoutInitialSeparator = StringUtils.substringAfter(numbers, "\n");
                splitNumbers = Arrays.stream(
                        numbersWithoutInitialSeparator.split("(?s)[,\\n{" + separator + "}]"))
                        .filter(e -> e.trim().length() > 0).toList();
            } else {
                splitNumbers = Arrays.asList(numbers.split("(?s)[,\\n]"));
            }
            List<String> negativeNumbers = splitNumbers.stream()
                    .filter(s -> s.startsWith("-"))
                    .collect(Collectors.toList());
            if (!negativeNumbers.isEmpty())
                throw new RuntimeException("negatives not allowed " + negativeNumbers);
            final int maxNumberValue = 1001;
            Integer sum = splitNumbers.stream()
                    .map(Integer::parseInt)
                    .sorted(Comparator.naturalOrder())
                    .filter(integer -> integer < maxNumberValue)
                    .reduce(0, Integer::sum);
            return sum;
        }
        return Integer.parseInt(numbers);
    }
}
