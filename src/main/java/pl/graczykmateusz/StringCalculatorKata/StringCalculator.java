package pl.graczykmateusz.StringCalculatorKata;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator implements IStringCalculator {

    @Override
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        if (numbers.length() == 1) {
            return Integer.parseInt(numbers);
        }
        return calculateNumbersLongerThenOne(numbers);
    }

    int calculateNumbersLongerThenOne(String numbers) {
        List<String> splitNumbers;
        if (checkIfIsDifferentSeparator(numbers)) {
            String separatorRegex;
            if (checkIfIsDifferentManySeparators(numbers)) {
                String[] separators = getAllSeparators(numbers);
                separatorRegex = createSeparatorRegex(separators);
            } else {
                separatorRegex = createSeparatorRegex(numbers);
            }
            String numbersWithoutInitialSeparator = removePartOfSeparatorsFromNumbersString(numbers);
            splitNumbers = splitNumbers(numbersWithoutInitialSeparator, separatorRegex);
        } else {
            splitNumbers = splitNumbers(numbers);
        }
        checkNegativeNumbers(splitNumbers);
        return sumNumbers(splitNumbers);
    }

    boolean checkIfIsDifferentSeparator(String numbers) {
        return numbers.matches("^(?s)//.*\\n.*");
    }

    boolean checkIfIsDifferentManySeparators(String numbers) {
        return numbers.matches("^(?s)//\\[.*]\\n.*$");
    }

    String[] getAllSeparators(String numbers) {
        return StringUtils.substringsBetween(numbers, "[", "]");
    }

    String createSeparatorRegex(String[] separators) {
        StringBuilder builder = new StringBuilder();
        for (String s : separators) {
            builder.append(s).append("|");
        }
        return builder.toString();
    }

    String createSeparatorRegex(String numbers) {
        return StringUtils.substringBetween(numbers, "//", "\n");
    }

    String removePartOfSeparatorsFromNumbersString(String numbers) {
        return StringUtils.substringAfter(numbers, "\n");
    }

    List<String> splitNumbers(String numbersWithoutInitialSeparator, String separatorRegex) {
        return Arrays.stream(
                numbersWithoutInitialSeparator.split("(?s)[,\\n{" + separatorRegex + "}]"))
                .filter(e -> e.trim().length() > 0).toList();
    }

    List<String> splitNumbers(String numbers) {
        return Arrays.asList(numbers.split("(?s)[,\\n]"));
    }

    void checkNegativeNumbers(List<String> splitNumbers) {
        List<String> negativeNumbers = splitNumbers.stream()
                .filter(s -> s.startsWith("-"))
                .collect(Collectors.toList());
        if (!negativeNumbers.isEmpty())
            throw new RuntimeException("negatives not allowed " + negativeNumbers);
    }

    int sumNumbers(List<String> splitNumbers) {
        final int maxNumberValue = 1001;
        return splitNumbers.stream()
                .map(Integer::parseInt)
                .sorted(Comparator.naturalOrder())
                .filter(integer -> integer < maxNumberValue)
                .reduce(0, Integer::sum);
    }
}
