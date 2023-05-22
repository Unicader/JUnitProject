package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
	
	public Calculator() {}

	public BigDecimal add(BigDecimal a, BigDecimal b) {
		return a.add(b);
	}

	public BigDecimal sub(BigDecimal a, BigDecimal b) {
		return a.subtract(b);
	}

	public BigDecimal mul(BigDecimal a, BigDecimal b) { 
		return a.multiply(b);
	}

	public BigDecimal div(BigDecimal dividend, BigDecimal divisor) {
		if (divisor.equals(BigDecimal.ZERO)) {
			throw new IllegalArgumentException("Divisor cannot be zero");
		}
		return dividend.divide(divisor, 10, RoundingMode.HALF_UP).stripTrailingZeros();
	}

}
