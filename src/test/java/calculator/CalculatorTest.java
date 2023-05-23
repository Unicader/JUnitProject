package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {
	Calculator calculator;

	@BeforeEach
	void setup() {
		this.calculator = new Calculator();
	}

	@Test
	void onePlusOneIsTwo() {
		// Act
		BigDecimal result = calculator.add(BigDecimal.ONE, BigDecimal.ONE);
		// Assert
		assertEquals(BigDecimal.valueOf(2), result);
	}

	@Tag("sub")
	@DisplayName("1 - 1 = 0")
	@Test
	void oneMinusOneIsZero() {
		// Act
		BigDecimal result = calculator.sub(BigDecimal.ONE, BigDecimal.ONE);
		// Assert
		assertEquals(BigDecimal.ZERO, result);
	}

	@Tag("div")
	@Test
	void divisorZeroThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> calculator.div(BigDecimal.valueOf(10), BigDecimal.ZERO));
	}

	@Tag("div")
	@ParameterizedTest(name = "{0} / {1} = {2}")
	@CsvSource({ //
			"6,3,2", // two positive numbers
			"6,-3,-2", // positive and negative number
			"-4,-8,0.5", // two negative numbers
			"3.9,1.3,3", // two doubles
			"1.123,2.1223,0.529142911", // division that would throws ArithmeticException: no exact representable
										// decimal result.
	})
	void allPosibilitiesDiv(BigDecimal a, BigDecimal b, BigDecimal expectedResult) {
		// Act
		BigDecimal result = calculator.div(a, b);
		// Assert
		assertEquals(expectedResult, result);
	}

	// remaining tests to cover all possibilities

	@Tag("add")
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvFileSource(resources = "/testDataAdd.csv")
	void allPosibilitiesAdd(BigDecimal a, BigDecimal b, BigDecimal expectedResult) {
		// Act
		BigDecimal result = calculator.add(a, b);
		// Assert
		assertEquals(expectedResult, result);
	}

	@Tag("sub")
	@ParameterizedTest(name = "{0} - {1} = {2}")
	@CsvSource({ //
			"2,3,-1", // two positive numbers
			"2,-7,9", // positive and negative number
			"-4,-8,4", // two negative numbers
			"3.9,1.3,2.6", // two doubles
	})
	void allPosibilitiesSub(BigDecimal a, BigDecimal b, BigDecimal expectedResult) {
		// Act
		BigDecimal result = calculator.sub(a, b);
		// Assert
		assertEquals(expectedResult, result);
	}

	@Tag("mul")
	@ParameterizedTest(name = "{0} - {1} = {2}")
	@CsvSource({ //
			"2,3,6", // two positive numbers
			"2,-7,-14", // positive and negative number
			"-4,-8,32", // two negative numbers
			"3.9,1.3,5.07", // two doubles
	})
	void allPosibilitiesMul(BigDecimal a, BigDecimal b, BigDecimal expectedResult) {
		// Act
		BigDecimal result = calculator.mul(a, b);
		// Assert
		assertEquals(expectedResult, result);
	}

}
