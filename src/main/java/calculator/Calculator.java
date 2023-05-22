package calculator;

public class Calculator {

	double add(double a, double b) {
		return a + b;
	}

	double sub(double a, double b) {
		return a - b;
	}

	double mul(double a, double b) { 
		return a * b;
	}

	double div(double dividend, double divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException("Divisor cannot be zero");
		}
		return dividend / divisor;
	}

}
