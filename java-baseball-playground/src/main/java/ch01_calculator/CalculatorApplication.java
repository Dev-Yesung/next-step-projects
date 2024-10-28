package ch01_calculator;

import ch01_calculator.executor.CalculationExecutor;
import ch01_calculator.executor.SimpleCalculationExecutor;
import ch01_calculator.hanlder.input.ConsoleInputHandler;
import ch01_calculator.hanlder.input.InputHandler;
import ch01_calculator.hanlder.output.ConsoleOutputHandler;
import ch01_calculator.hanlder.output.OutputHandler;
import ch01_calculator.operation.IntegerOperation;
import ch01_calculator.operation.Operation;
import ch01_calculator.parser.ExpressionParser;
import ch01_calculator.parser.SequentialExpressionParser;
import ch01_calculator.processor.Processor;

public class CalculatorApplication {

	public static void main(String[] args) {
		InputHandler inputHandler = new ConsoleInputHandler();
		OutputHandler outputHandler = new ConsoleOutputHandler();
		ExpressionParser expressionParser = new SequentialExpressionParser();
		Operation<Integer, Integer> operation = new IntegerOperation();
		CalculationExecutor calculationExecutor = new SimpleCalculationExecutor(operation);
		Processor processor = new Processor(inputHandler, outputHandler, expressionParser, calculationExecutor);
		processor.run();
	}
}
