package pl.graczykmateusz.StringCalculatorKata;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
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
                boolean isDifferentManySeparators = numbers.matches("^(?s)//\\[.*]\\n.*$");
                if (isDifferentManySeparators) {
                    String[] separators = StringUtils.substringsBetween(numbers, "[", "]");
                    String numbersWithoutInitialSeparators = StringUtils.substringAfter(numbers, "\n");

                    StringBuilder builder = new StringBuilder();
                    for (String s : separators) {
                        builder.append(s + "|");
                    }
                    String separator = builder.toString();

                    splitNumbers = Arrays.stream(
                            numbersWithoutInitialSeparators.split("(?s)[,\\n{" + separator + "}]"))
                            .filter(e -> e.trim().length() > 0).toList();
                } else {
                    String separator = StringUtils.substringBetween(numbers, "//", "\n");
                    String numbersWithoutInitialSeparator = StringUtils.substringAfter(numbers, "\n");
                    splitNumbers = Arrays.stream(
                            numbersWithoutInitialSeparator.split("(?s)[,\\n{" + separator + "}]"))
                            .filter(e -> e.trim().length() > 0).toList();
                }
            } else {
                splitNumbers = Arrays.asList(numbers.split("(?s)[,\\n]"));
            }
            List<String> negativeNumbers = splitNumbers.stream()
                    .filter(s -> s.startsWith("-"))
                    .collect(Collectors.toList());
            if (!negativeNumbers.isEmpty())
                throw new RuntimeException("negatives not allowed " + negativeNumbers);
            final int maxNumberValue = 1001;
            return splitNumbers.stream()
                    .map(Integer::parseInt)
                    .sorted(Comparator.naturalOrder())
                    .filter(integer -> integer < maxNumberValue)
                    .reduce(0, Integer::sum);
        }
        return Integer.parseInt(numbers);
    }
}
