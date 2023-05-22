package userinterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import calculator.Calculator;

public class CalculatorUITest {
	
	@Test
	public void testPerformAddition() {
		// Arrange
		CalculatorUI calculatorUI = Mockito.spy(new CalculatorUI());
		Mockito.doReturn(BigDecimal.ONE).when(calculatorUI).readNumber("Enter the first number: ");
		Mockito.doReturn(BigDecimal.TEN).when(calculatorUI).readNumber("Enter the second number: ");
		// Act
		BigDecimal result = calculatorUI.performAddition();
		// Assert
		assertEquals(BigDecimal.valueOf(11), result);
	}
	
	@Test
	public void testPerformSubtraction() {
		// Arrange
		CalculatorUI calculatorUI = Mockito.spy(new CalculatorUI());
		Mockito.doReturn(BigDecimal.ONE).when(calculatorUI).readNumber("Enter the first number: ");
		Mockito.doReturn(BigDecimal.TEN).when(calculatorUI).readNumber("Enter the second number: ");
		// Act
		BigDecimal result = calculatorUI.performSubtraction();
		// Assert
		assertEquals(BigDecimal.valueOf(-9), result);
	}
	
	@Test
	public void testPerformMultiplication() {
		// Arrange
		CalculatorUI calculatorUI = Mockito.spy(new CalculatorUI());
		Mockito.doReturn(BigDecimal.ONE).when(calculatorUI).readNumber("Enter the first number: ");
		Mockito.doReturn(BigDecimal.TEN).when(calculatorUI).readNumber("Enter the second number: ");
		// Act
		BigDecimal result = calculatorUI.performMultiplication();
		// Assert
		assertEquals(BigDecimal.valueOf(10), result);
	}
	
	@Test
	public void testPerformDivision() {
		// Arrange
		CalculatorUI calculatorUI = Mockito.spy(new CalculatorUI());
		Mockito.doReturn(BigDecimal.ONE).when(calculatorUI).readNumber("Enter the dividend: ");
		Mockito.doReturn(BigDecimal.TEN).when(calculatorUI).readNumber("Enter the divisor: ");
		// Act
		BigDecimal result = calculatorUI.performDivision();
		// Assert
		assertEquals(BigDecimal.valueOf(0.1), result);
	}
	
	@Test
	public void testPerformDivisionWithZeroAsDivisor() {
		// Arrange
		CalculatorUI calculatorUI = Mockito.spy(new CalculatorUI());
		Mockito.doReturn(BigDecimal.ONE).when(calculatorUI).readNumber("Enter the dividend: ");
		Mockito.doReturn(BigDecimal.ZERO).when(calculatorUI).readNumber("Enter the divisor: ");
		// Assert
		assertThrows(IllegalArgumentException.class, () -> {
			calculatorUI.performDivision();
		});
	}
	
	@Disabled
	@Test
	public void testReadNumber() {
		// Arrange
		CalculatorUI calculatorUI = Mockito.spy(new CalculatorUI());
		Mockito.doReturn(BigDecimal.ONE).when(calculatorUI).scanner.nextLine().trim();
		//Act
		BigDecimal readNumber = calculatorUI.readNumber("");
		// Assert
		assertEquals(BigDecimal.ONE, readNumber);
	}
}