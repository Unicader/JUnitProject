package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {
	Calculator calculator;
	
	@BeforeEach
	void setup() {
		this.calculator = new Calculator();
	}
	
	@Test
	void onePlusOneIsTwo() {
		double result = calculator.add(1, 1);
		assertEquals(2, result);
	}
	
	@DisplayName("1 - 1 = 0")
    @Tag("subtract")
	@Test
	void oneMinusOneIsZero() {
		double result = calculator.sub(1, 1);
		assertEquals(0, result);
	}
	
    @Tag("divide")
	@ParameterizedTest(name = "{0} / {1} = {2}")
	@CsvSource({ //
			"1,1,1", //
			"7,2,3.5", //
			"3.9,3,1.3", //
			"3.9,1.3,3", //
	})
	void testDiv(double a, double b, double expectedResult) {
		double result = calculator.div(a, b);
		assertEquals(expectedResult, result);
	}
    
    @Test
    void divisorZeroThrowsException() {
    	assertThrows(IllegalArgumentException.class, () -> {
            calculator.div(10, 0);
        });
    }

}
