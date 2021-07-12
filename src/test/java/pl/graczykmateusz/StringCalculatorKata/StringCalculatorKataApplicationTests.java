package pl.graczykmateusz.StringCalculatorKata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
		var excepted = 0;
		var actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"})
	void shouldReturnThatNumberWhenAddOneNumber(String numbers) {
		var excepted = Integer.parseInt(numbers);
		var actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	@ParameterizedTest
	@MethodSource("addTwoNumbersArguments")
	void shouldReturnSumWhenAddTwoNumbers(String numbers, int excepted) {
		var actual = stringCalculator.add(numbers);
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
	@MethodSource("addUnknownAmountOfNumbersArguments")
	void shouldReturnSumWhenUnknownAmountOfNumbers(String numbers, int excepted) {
		var actual = stringCalculator.add(numbers);
		Assertions.assertEquals(excepted, actual);
	}

	private static Stream<Arguments> addUnknownAmountOfNumbersArguments() {
		return Stream.of(
				Arguments.of("0,0,0", 0),
				Arguments.of("1,1,5", 7),
				Arguments.of("2,4,4,5", 15),
				Arguments.of("10,10,10,10", 40),
				Arguments.of("10,20,30,40,50", 150),
				Arguments.of("1,1,1,1,1,1,1,1,1,1", 10));
	}
}
