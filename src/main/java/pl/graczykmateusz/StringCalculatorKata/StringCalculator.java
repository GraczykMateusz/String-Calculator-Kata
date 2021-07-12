package pl.graczykmateusz.StringCalculatorKata;

public class StringCalculator implements IStringCalculator {

    @Override
    public int add(String numbers) {
        if (numbers.isEmpty())
            return 0;
        return Integer.parseInt(numbers);
    }
}
