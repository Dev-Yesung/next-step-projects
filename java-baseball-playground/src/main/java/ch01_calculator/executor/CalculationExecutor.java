package ch01_calculator.executor;

import ch01_calculator.dto.ParsedExpressions;

public interface CalculationExecutor {

	String calculate(ParsedExpressions parsedExpressions);
}
