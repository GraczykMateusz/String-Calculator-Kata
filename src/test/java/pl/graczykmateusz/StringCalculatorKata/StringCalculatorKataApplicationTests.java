package pl.graczykmateusz.StringCalculatorKata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringCalculatorKataApplicationTests {
	private StringCalculator stringCalculator;

	@BeforeEach
	void setUp() {
		stringCalculator = new StringCalculator();
	}

	@Test
	void shouldReturnZeroWhenAddEmptyString() {
		var excepted = 0;
		var actual = stringCalculator.add("");

		Assertions.assertEquals(excepted, actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"})
	void shouldReturnThatNumberWhenAddOneNumber(String number) {
		var excepted = Integer.parseInt(number);
		var actual = stringCalculator.add(number);

		Assertions.assertEquals(excepted, actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {"0,0", "1,2", "2,3", "3,3", "4,5", "6,3", "11,23"})
	void shouldReturnSumWhenAddTwoNumbers(String numbers) {
		var excepted = Integer.parseInt(numbers);
		var actual = stringCalculator.add(numbers);

		Assertions.assertEquals(excepted, actual);
	}
}
