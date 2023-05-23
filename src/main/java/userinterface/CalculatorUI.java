package userinterface;

import java.math.BigDecimal;
import java.util.Scanner;

import calculator.Calculator;

public class CalculatorUI {
	private final Calculator calculator;

	public CalculatorUI() {
		this.calculator = new Calculator();
	}

	public void runProgram() {
		System.out.println("Welcome to Calculator!");
		boolean running = true;

		while (running) {
			System.out.print("\nEnter a command (add, sub, mul, div) or 'exit' to quit: ");
			String command = readInputLine();

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
				System.err.println("Invalid command. Please try again.");
				break;
			}
		}

		System.out.println("Calculator is now closed.");
	}

	BigDecimal performAddition() {
		BigDecimal a = readNumber("Enter the first addend: ");
		BigDecimal b = readNumber("Enter the second addend: ");
		return calculator.add(a, b);
	}

	BigDecimal performSubtraction() {
		BigDecimal a = readNumber("Enter the minuend: ");
		BigDecimal b = readNumber("Enter the subtrahend: ");
		return calculator.sub(a, b);
	}

	BigDecimal performMultiplication() {
		BigDecimal a = readNumber("Enter the first factor: ");
		BigDecimal b = readNumber("Enter the second factor: ");
		return calculator.mul(a, b);
	}

	BigDecimal performDivision() {
		BigDecimal dividend = readNumber("Enter the dividend: ");
		while (true) {
			BigDecimal divisor = readNumber("Enter the divisor: ");
			try {
				return calculator.div(dividend, divisor);
			} catch (IllegalArgumentException e) {
				System.err.println("Divisor cant be zero!\n");
			}
		}
	}

	BigDecimal readNumber(String message) {
		while (true) {
			System.out.print(message);
			String input = readInputLine();
			try {
				return new BigDecimal(input);
			} catch (NumberFormatException e) {
				System.err.println("Not a number!\n");
			}
		}
	}

	void displayResult(BigDecimal result) {
		System.out.println("Result: " + result);
	}

	@SuppressWarnings("resource")
	String readInputLine() {
		return new Scanner(System.in).nextLine().trim();
	}
}
