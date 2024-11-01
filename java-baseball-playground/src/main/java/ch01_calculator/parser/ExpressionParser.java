package ch01_calculator.parser;

import ch01_calculator.dto.ParsedExpressions;

@FunctionalInterface
public interface ExpressionParser {

	ParsedExpressions parse(String expression);
}
