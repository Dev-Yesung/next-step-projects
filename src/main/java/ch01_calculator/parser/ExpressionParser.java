package ch01_calculator.parser;

import java.util.List;

public interface ExpressionParser {

	List<String> parseExpression(String expression);
}
