package ch01_calculator.parser;

import ch01_calculator.parser.executor.Executor;

@FunctionalInterface
public interface ExpressionParser {

	Executor parse(String expression);
}
