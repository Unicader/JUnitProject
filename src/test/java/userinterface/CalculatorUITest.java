package userinterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CalculatorUITest {
	CalculatorUI calculatorUI;

	@BeforeEach
	void setUp() {
		calculatorUI = Mockito.spy(CalculatorUI.class);
	}

	@Test
	void testPerformAddition() {
		// Arrange
		mockingReadNumbers("Enter the first addend: ", 1, "Enter the second addend: ", 10);
		// Act
		BigDecimal result = calculatorUI.performAddition();
		// Assert
		assertEquals(BigDecimal.valueOf(11), result);
	}

	@Test
	void testPerformSubtraction() {
		// Arrange
		mockingReadNumbers("Enter the minuend: ", 1, "Enter the subtrahend: ", 10);
		// Act
		BigDecimal result = calculatorUI.performSubtraction();
		// Assert
		assertEquals(BigDecimal.valueOf(-9), result);
	}

	@Test
	void testPerformMultiplication() {
		// Arrange
		mockingReadNumbers("Enter the first factor: ", 1, "Enter the second factor: ", 10);
		// Act
		BigDecimal result = calculatorUI.performMultiplication();
		// Assert
		assertEquals(BigDecimal.valueOf(10), result);
	}

	@Test
	void testPerformDivision() {
		// Arrange
		mockingReadNumbers("Enter the dividend: ", 1, "Enter the divisor: ", 10);
		// Act
		BigDecimal result = calculatorUI.performDivision();
		// Assert
		assertEquals(BigDecimal.valueOf(0.1), result);
	}

	@Test
	void testPerformDivisionWithZeroAsDivisor() throws InterruptedException {
		// Arrange
		mockingReadNumbers("Enter the dividend: ", 1, "Enter the divisor: ", 0);
		// Act
		doAnswer(invocation -> { // Change the behavior to return one after a delay
			Thread.sleep(20);
			return BigDecimal.ONE;
		}).when(calculatorUI).readNumber("Enter the divisor: ");
		BigDecimal performDivision = calculatorUI.performDivision();
		// Assert
		assertEquals(BigDecimal.ONE, performDivision);
	}

	@Test
	void testReadNumber() {
		// Arrange
		Mockito.doReturn("1").when(calculatorUI).readInputLine();
		// Act
		BigDecimal readNumber = calculatorUI.readNumber("");
		// Assert
		assertEquals(BigDecimal.ONE, readNumber);
	}

	@Test
	void testDisplayResult() {
		// Arrange
		ByteArrayOutputStream outputStream = setUpOUtputStream();
		BigDecimal result = new BigDecimal("10.5");
		// Act
		calculatorUI.displayResult(result);
		// Assert
		String consoleOutput = outputStream.toString().trim();
		assertEquals("Result: 10.5", consoleOutput);
	}
	
	private void mockingReadNumbers(String first, int firstValue, String second, int secondValue) {
		Mockito.doReturn(BigDecimal.valueOf(firstValue)).when(calculatorUI).readNumber(first);
		Mockito.doReturn(BigDecimal.valueOf(secondValue)).when(calculatorUI).readNumber(second);
	}

	private ByteArrayOutputStream setUpOUtputStream() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		return outputStream;
	}
}