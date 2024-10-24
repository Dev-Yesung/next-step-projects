package ch01_calculator;

import ch01_calculator.hanlder.input.InputHandler;
import ch01_calculator.hanlder.output.OutputHandler;
import ch01_calculator.operation.Operation;
import ch01_calculator.parser.ExpressionParser;

public class Processor {

	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final Operation operation;
	private final ExpressionParser expressionParser;

	public Processor(InputHandler inputHandler, OutputHandler outputHandler, Operation operation,
		ExpressionParser expressionParser) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.operation = operation;
		this.expressionParser = expressionParser;
	}

	public void run() {

	}
}
