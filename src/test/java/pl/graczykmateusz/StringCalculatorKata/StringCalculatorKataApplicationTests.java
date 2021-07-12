package pl.graczykmateusz.StringCalculatorKata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorKataApplicationTests {
	private StringCalculator stringCalculator;

	@BeforeEach
	void setUp() {
		stringCalculator = new StringCalculator();
	}

	@Test
	void addEmptyStringShouldReturnZero() {
		var excepted = 0;
		var actual = stringCalculator.add("");

		Assertions.assertEquals(excepted, actual);
	}

}
