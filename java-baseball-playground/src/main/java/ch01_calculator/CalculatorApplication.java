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
import ch01_calculator.parser.SequentialExpressionIntegerParser;

public class CalculatorApplication {

	public static void main(String[] args) {
		InputHandler inputHandler = new ConsoleInputHandler();
		OutputHandler outputHandler = new ConsoleOutputHandler();
		Operation<Integer, Integer> operation = new IntegerOperation();
		NumberParser<Integer> numberParser = new IntegerParser();
		ExpressionParser expressionParser = new SequentialExpressionIntegerParser(numberParser);
		Processor processor = new Processor(inputHandler, outputHandler, operation, expressionParser);

		processor.run();
	}
}
