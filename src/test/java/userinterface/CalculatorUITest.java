package userinterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
		mockingReadNumbers("Enter the first addend:", 1, "Enter the second addend:", 10);
		// Act
		BigDecimal result = calculatorUI.performAddition();
		// Assert
		assertEquals(BigDecimal.valueOf(11), result);
	}

	@Test
	void testPerformSubtraction() {
		// Arrange
		mockingReadNumbers("Enter the minuend:", 1, "Enter the subtrahend:", 10);
		// Act
		BigDecimal result = calculatorUI.performSubtraction();
		// Assert
		assertEquals(BigDecimal.valueOf(-9), result);
	}

	@Test
	void testPerformMultiplication() {
		// Arrange
		mockingReadNumbers("Enter the first factor:", 1, "Enter the second factor:", 10);
		// Act
		BigDecimal result = calculatorUI.performMultiplication();
		// Assert
		assertEquals(BigDecimal.valueOf(10), result);
	}

	@Test
	void testPerformDivision() {
		// Arrange
		mockingReadNumbers("Enter the dividend:", 1, "Enter the divisor:", 10);
		// Act
		BigDecimal result = calculatorUI.performDivision();
		// Assert
		assertEquals(BigDecimal.valueOf(0.1), result);
	}

	@Test
	void testPerformDivisionWithZeroAsDivisor() throws InterruptedException {
		// Arrange
		ByteArrayOutputStream outputStream = setUpOutputStream();
		Mockito.doReturn(BigDecimal.valueOf(1)).when(calculatorUI).readNumber("Enter the dividend:");
		Mockito.doAnswer(invocation -> {
	        if (outputStream.toString().contains("Divisor cant be zero!")) {
	            return "1";
	        }
	        return "0";
	    }).when(calculatorUI).readInputLine();
		// Act
		BigDecimal performDivision = calculatorUI.performDivision();
		// Assert
		assertEquals(BigDecimal.ONE, performDivision);
	}

	@Test
	void testReadNumberWithNumber() {
		// Arrange
		Mockito.doReturn("1").when(calculatorUI).readInputLine();
		// Act
		BigDecimal readNumber = calculatorUI.readNumber("");
		// Assert
		assertEquals(BigDecimal.ONE, readNumber);
	}
	
	@Test
	void testReadNumberWithInvalidNumber() {
		// Arrange
		ByteArrayOutputStream outputStream = setUpOutputStream();
		Mockito.doAnswer(invocation -> {
			if (invocation.getMethod().getName().equals("readInputLine")
					&& outputStream.toString().contains("Not a number!")) {
				return "1";
			}
			return "number";
		}).when(calculatorUI).readInputLine();
		// Act
		BigDecimal readNumber = calculatorUI.readNumber("");
		// Assert
		assertEquals(BigDecimal.ONE, readNumber);
		assertTrue(outputStream.toString().contains("Not a number!"));
	}

	@Test
	void testDisplayResult() {
		// Arrange
		ByteArrayOutputStream outputStream = setUpOutputStream();
		BigDecimal result = new BigDecimal("10.5");
		// Act
		calculatorUI.displayResult(result);
		// Assert
		String consoleOutput = outputStream.toString().trim();
		assertEquals("Result: 10.5", consoleOutput);
	}

	@ParameterizedTest(name = "{0} {2} and {4} is {5}")
	@CsvSource({ //
			"add, Enter the first addend:, 1,   Enter the second addend:, 10, 11", //
			"sub, Enter the minuend:,      1,   Enter the subtrahend:,    10, -9", //
			"mul, Enter the first factor:, 1,   Enter the second factor:, 10, 10", //
			"div, Enter the dividend:,     10,  Enter the divisor:,       10, 1", //
	})
	void testRunProgramAndThenExit(String command, String firstText, int firstNumber, String secondText,
			int secondNumber, int result) {
		// Arrange
		ByteArrayOutputStream outputStream = setUpOutputStream();
		mockingReadNumbers(firstText, firstNumber, secondText, secondNumber);
		Mockito.doAnswer(invocation -> {
			if (invocation.getMethod().getName().equals("readInputLine")
					&& outputStream.toString().contains("Result: " + result)) {
				return "exit";
			}
			return command;
		}).when(calculatorUI).readInputLine();
		// Act
		calculatorUI.runProgram();
		// Assert
		String consoleOutput = outputStream.toString().trim();
		assertTrue(consoleOutput.contains("Result: " + result));
		assertTrue(consoleOutput.contains("Calculator is now closed."));
	}
	
	@Test
	void testRunProgramAndImediatlyExit() {
		// Arrange
		ByteArrayOutputStream outputStream = setUpOutputStream();
		Mockito.doReturn("exit").when(calculatorUI).readInputLine();
		// Act
		calculatorUI.runProgram();
		// Assert
		String consoleOutput = outputStream.toString().trim();
		assertTrue(consoleOutput.contains("Calculator is now closed."));
	}

	@Test
	void testRunProgramAndTypeInvalidComandThenExit() {
		// Arrange
		ByteArrayOutputStream outputStream = setUpOutputStream();
		Mockito.doAnswer(invocation -> {
			if (invocation.getMethod().getName().equals("readInputLine")
					&& outputStream.toString().contains("Invalid command. Please try again.")) {
				return "exit";
			}
			return "comand";
		}).when(calculatorUI).readInputLine();
		// Act
		calculatorUI.runProgram();
		// Assert
		String consoleOutput = outputStream.toString().trim();
		assertTrue(consoleOutput.contains("Calculator is now closed."));
	}

	private void mockingReadNumbers(String first, int firstValue, String second, int secondValue) {
		Mockito.doReturn(BigDecimal.valueOf(firstValue)).when(calculatorUI).readNumber(first);
		Mockito.doReturn(BigDecimal.valueOf(secondValue)).when(calculatorUI).readNumber(second);
	}

	private ByteArrayOutputStream setUpOutputStream() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		return outputStream;
	}
}