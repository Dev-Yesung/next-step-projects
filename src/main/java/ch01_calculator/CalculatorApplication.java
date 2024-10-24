package ch01_calculator;

import ch01_calculator.hanlder.input.ConsoleInputHandler;
import ch01_calculator.hanlder.input.InputHandler;
import ch01_calculator.hanlder.output.ConsoleOutputHandler;
import ch01_calculator.hanlder.output.OutputHandler;
import ch01_calculator.operation.IntegerOperation;
import ch01_calculator.operation.Operation;
import ch01_calculator.parser.ExpressionParser;
import ch01_calculator.parser.IntegerParser;
import ch01_calculator.parser.NumberParser;
import ch01_calculator.parser.SequentialExpressionParser;

public class CalculatorApplication {

	public static void main(String[] args) {
		InputHandler inputHandler = new ConsoleInputHandler();
		OutputHandler outputHandler = new ConsoleOutputHandler();
		Operation<Integer, Integer> operation = new IntegerOperation();
		ExpressionParser expressionParser = new SequentialExpressionParser();
		NumberParser<Integer> numberParser = new IntegerParser();
		Processor processor = new Processor(inputHandler, outputHandler, operation, expressionParser, numberParser);

		processor.run();
	}
}
