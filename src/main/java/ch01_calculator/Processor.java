package ch01_calculator;

import ch01_calculator.hanlder.input.InputHandler;
import ch01_calculator.hanlder.output.OutputHandler;
import ch01_calculator.operation.Operation;
import ch01_calculator.parser.ExpressionParser;
import ch01_calculator.parser.NumberParser;

public class Processor {

	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final Operation<?, ?> operation;
	private final ExpressionParser expressionParser;
	private final NumberParser<?> numberParser;

	public Processor(InputHandler inputHandler, OutputHandler outputHandler, Operation<?, ?> operation,
		ExpressionParser expressionParser, NumberParser<?> numberParser) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.operation = operation;
		this.expressionParser = expressionParser;
		this.numberParser = numberParser;
	}

	public void run() {

	}
}
