package userinterface;

import java.math.BigDecimal;
import java.util.Scanner;

import calculator.Calculator;

public class CalculatorUI {
	final Scanner scanner;
	private final Calculator calculator;

	public CalculatorUI() {
		this.scanner = new Scanner(System.in);
		this.calculator = new Calculator();
	}

	public void runProgram() {
		System.out.println("Welcome to Calculator!");
		boolean running = true;

		while (running) {
			System.out.print("Enter a command (add, sub, mul, div) or 'exit' to quit: ");
			String command = scanner.nextLine().trim();

			switch (command) {
			case "add":
				displayResult(performAddition());
				break;
			case "sub":
				displayResult(performSubtraction());
				break;
			case "mul":
				displayResult(performMultiplication());
				break;
			case "div":
				displayResult(performDivision());
				break;
			case "exit":
				running = false;
				break;
			default:
				System.out.println("Invalid command. Please try again.");
				break;
			}
		}

		System.out.println("Calculator is now closed.");
	}

	BigDecimal performAddition() {
		BigDecimal a = readNumber("Enter the first number: ");
		BigDecimal b = readNumber("Enter the second number: ");
		return calculator.add(a, b);
	}

	
	BigDecimal performSubtraction() {
		BigDecimal a = readNumber("Enter the first number: ");
		BigDecimal b = readNumber("Enter the second number: ");
		return calculator.sub(a, b);
	}

	BigDecimal performMultiplication() {
		BigDecimal a = readNumber("Enter the first number: ");
		BigDecimal b = readNumber("Enter the second number: ");
		return calculator.mul(a, b);
	}

	BigDecimal performDivision() {
		BigDecimal dividend = readNumber("Enter the dividend: ");
		BigDecimal divisor = readNumber("Enter the divisor: ");

		try {
			return calculator.div(dividend, divisor);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
	}

	BigDecimal readNumber(String message) {
		System.out.print(message);
		String input = scanner.nextLine().trim();
		return new BigDecimal(input);
	}

	void displayResult(BigDecimal result) {
		System.out.println("Result: " + result);
	}
}
