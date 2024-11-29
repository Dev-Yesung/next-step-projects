package ch01_calculator;

import java.util.Scanner;

import ch01_calculator.parser.ExpressionParser;
import ch01_calculator.parser.SequentialIntegerExpressionParser;
import ch01_calculator.processor.Processor;

public class CalculatorApplication {

	public static void main(String[] args) {
		ExpressionParser expressionParser = new SequentialIntegerExpressionParser();
		Scanner scanner = new Scanner(System.in);
		Processor processor = new Processor(expressionParser, scanner);

		boolean isApplicationActive;
		do {
			isApplicationActive = processor.run();
		} while (isApplicationActive);
	}
}
