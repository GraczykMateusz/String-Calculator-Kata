package pl.graczykmateusz.StringCalculatorKata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;
import java.util.stream.Stream;

class StringCalculatorKataApplicationTests {
	private StringCalculator stringCalculator;

	@BeforeEach
	void setUp() {
		stringCalculator = new StringCalculator();
	}

	@ParameterizedTest
	@EmptySource
	void shouldReturnZeroWhenAddEmptyString(String numbers) {
		int excepted = 0;
		int actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"})
	void shouldReturnThatNumberWhenAddOneNumber(String numbers) {
		int excepted = Integer.parseInt(numbers);
		int actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	@ParameterizedTest
	@MethodSource("addTwoNumbersArguments")
	void shouldReturnSumWhenAddTwoNumbers(String numbers, int excepted) {
		int actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	private static Stream<Arguments> addTwoNumbersArguments() {
		return Stream.of(
				Arguments.of("0,0", 0),
				Arguments.of("1,1", 2),
				Arguments.of("2,3", 5),
				Arguments.of("10,10", 20),
				Arguments.of("12,44", 56),
				Arguments.of("999,2", 1001));
	}

	@ParameterizedTest
	@MethodSource("addUnknownAmountOfNumbersAndCommaSeparatorArguments")
	void shouldReturnSumWhenUnknownAmountOfNumbersAndCommaSeparator(String numbers, int excepted) {
		int actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	private static Stream<Arguments> addUnknownAmountOfNumbersAndCommaSeparatorArguments() {
		return Stream.of(
				Arguments.of("0,0,0", 0),
				Arguments.of("1,1,5", 7),
				Arguments.of("2,4,4,5", 15),
				Arguments.of("10,10,10,10", 40),
				Arguments.of("10,20,30,40,50", 150),
				Arguments.of("1,1,1,1,1,1,1,1,1,1", 10));
	}

	@ParameterizedTest
	@MethodSource("addUnknownAmountOfNumbersAndNewLineSeparatorArguments")
	void shouldReturnSumWhenUnknownAmountOfNumbersAndNewLineSeparator(String numbers, int excepted) {
		int actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	private static Stream<Arguments> addUnknownAmountOfNumbersAndNewLineSeparatorArguments() {
		return Stream.of(
				Arguments.of("0\n0,0", 0),
				Arguments.of("1,1\n5", 7),
				Arguments.of("2,4,4\n5", 15),
				Arguments.of("10\n10,10,10", 40),
				Arguments.of("10\n20\n30\n40\n50", 150),
				Arguments.of("1,1\n1,1\n1,1\n1,1\n1,1", 10));
	}

	@ParameterizedTest
	@MethodSource("addUnknownAmountOfNumbersAndUnknownSeparatorArguments")
	void shouldReturnSumWhenUnknownAmountOfNumbersAndUnknownSeparator(String numbers, int excepted) {
		int actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	private static Stream<Arguments> addUnknownAmountOfNumbersAndUnknownSeparatorArguments() {
		return Stream.of(
				Arguments.of("//;\n0,0;0", 0),
				Arguments.of("//.\n1,2.3", 6),
				Arguments.of("//+\n1,2+3", 6),
				Arguments.of("//*\n1,2*3", 6),
				Arguments.of("//?\n1,2?3", 6),
				Arguments.of("//^\n1,2^3", 6),
				Arguments.of("//$\n1,2$3", 6),
				Arguments.of("//(\n1,2(3", 6),
				Arguments.of("//)\n1,2)3", 6),
				Arguments.of("//{\n1,2{3", 6),
				Arguments.of("//}\n1,2}3", 6),
				Arguments.of("//|\n1,2|3", 6),
				Arguments.of("//a\n1a1a5", 7),
				Arguments.of("//z\n10,10z10z10", 40),
				Arguments.of("//!\n10!20,30!40,50", 150),
				Arguments.of("//s\n1,1s1,1,1s1s1s1s1,1", 10));
	}

	@ParameterizedTest
	@MethodSource("addNegativeNumbersArguments")
	void shouldReturnExceptionWhenOneNegativeNumber(String numbers) {
		String excepted = "negatives not allowed";
		Optional<Integer> nonExceptedInt = Optional.empty();
		try {
			int actual = stringCalculator.add(numbers);
			nonExceptedInt = Optional.of(actual);
		} catch (RuntimeException e) {
			Assertions.assertTrue(e.getMessage().contains(excepted));
		}
		Assertions.assertTrue(nonExceptedInt.isEmpty());
	}

	private static Stream<Arguments> addNegativeNumbersArguments() {
		return Stream.of(
				Arguments.of("-1,1\n5"),
				Arguments.of("2,-4,4\n5"),
				Arguments.of("//{\n10\n2{-3", 6),
				Arguments.of("//{\n10\n10{-10,10"),
				Arguments.of("10\n20\n30\n-40\n50"),
				Arguments.of("//;\n01;1\n1;1\n1;1\n1;1\n1,-1"));
	}
}
