package main;

import calculator.Calculator;
import userinterface.CalculatorUI;

public class Main {

	public static void main(String[] args) {
		new CalculatorUI(new Calculator()).runProgram();
	}

}
