package ch01_calculator.parser;

import ch01_calculator.executor.IntegerExecutor;

@FunctionalInterface
public interface ExpressionParser {

	IntegerExecutor parse(String expression);
}
