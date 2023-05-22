package userinterface;

import java.math.BigDecimal;
import java.util.Scanner;

import calculator.Calculator;

public class CalculatorUI {
	private final Scanner scanner;
	private final Calculator calculator;

	public CalculatorUI(Calculator calculator) {
		this.scanner = new Scanner(System.in);
		this.calculator = calculator;
	}

	public void runProgram() {
		System.out.println("Welcome to Calculator!");
		boolean running = true;

		while (running) {
			System.out.print("Enter a command (add, sub, mul, div) or 'exit' to quit: ");
			String command = scanner.nextLine().trim();

			switch (command) {
			case "add":
				performAddition();
				break;
			case "sub":
				performSubtraction();
				break;
			case "mul":
				performMultiplication();
				break;
			case "div":
				performDivision();
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

	void performAddition() {
		BigDecimal a = readNumber("Enter the first number: ");
		BigDecimal b = readNumber("Enter the second number: ");
		BigDecimal result = calculator.add(a, b);
		displayResult(result);
	}

	void performSubtraction() {
		BigDecimal a = readNumber("Enter the first number: ");
		BigDecimal b = readNumber("Enter the second number: ");
		BigDecimal result = calculator.sub(a, b);
		displayResult(result);
	}

	void performMultiplication() {
		BigDecimal a = readNumber("Enter the first number: ");
		BigDecimal b = readNumber("Enter the second number: ");
		BigDecimal result = calculator.mul(a, b);
		displayResult(result);
	}

	void performDivision() {
		BigDecimal dividend = readNumber("Enter the dividend: ");
		BigDecimal divisor = readNumber("Enter the divisor: ");

		try {
			BigDecimal result = calculator.div(dividend, divisor);
			displayResult(result);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
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
