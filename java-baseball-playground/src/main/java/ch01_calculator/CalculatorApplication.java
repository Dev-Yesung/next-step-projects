package ch01_calculator;

import java.util.Scanner;

import ch01_calculator.parser.ExpressionParser;
import ch01_calculator.parser.SequentialIntegerExpressionParser;
import ch01_calculator.processor.Processor;

public class CalculatorApplication {

	private final Processor processor;

	public CalculatorApplication(Processor processor) {
		this.processor = processor;
	}

	public void run() {
		boolean isApplicationActive;
		do {
			isApplicationActive = processor.run();
		} while (isApplicationActive);
	}

	public static void main(String[] args) {
		Processor processor = createProcessor();
		new CalculatorApplication(processor).run();
	}

	private static Processor createProcessor() {
		ExpressionParser expressionParser = new SequentialIntegerExpressionParser();
		Scanner scanner = new Scanner(System.in);
		return new Processor(expressionParser, scanner);
	}
}
