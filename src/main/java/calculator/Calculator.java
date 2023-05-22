package calculator;

import java.math.BigDecimal;

public class Calculator {

	BigDecimal add(BigDecimal a, BigDecimal b) {
		return a.add(b);
	}

	BigDecimal sub(BigDecimal a, BigDecimal b) {
		return a.subtract(b);
	}

	BigDecimal mul(BigDecimal a, BigDecimal b) { 
		return a.multiply(b);
	}

	BigDecimal div(BigDecimal dividend, BigDecimal divisor) {
		if (divisor.equals(BigDecimal.ZERO)) {
			throw new IllegalArgumentException("Divisor cannot be zero");
		}
		return dividend.divide(divisor);
	}

}
