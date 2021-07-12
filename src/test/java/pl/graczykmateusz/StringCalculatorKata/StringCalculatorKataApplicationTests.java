package pl.graczykmateusz.StringCalculatorKata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

	@Test
	void shouldReturnThatNumberWhenAddOneNumber() {
		var excepted = 1;
		var actual = stringCalculator.add("1");

		Assertions.assertEquals(excepted, actual);
	}
}
