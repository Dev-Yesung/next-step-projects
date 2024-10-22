package ch01_calculator;

import ch01_calculator.hanlder.InputHandler;
import ch01_calculator.hanlder.OutputHandler;
import ch01_calculator.operation.Operation;
import ch01_calculator.parser.ExpressionParser;
import ch01_calculator.parser.NumberParser;
import ch01_calculator.validator.ExpressionValidator;
import ch01_calculator.validator.NumberValidator;

public class Processor {

	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final Operation<?, ?> operation;
	private final ExpressionParser expressionParser;
	private final ExpressionValidator expressionValidator;
	private final NumberParser<?> numberParser;
	private final NumberValidator<?, ?> numberValidator;

	public Processor(InputHandler inputHandler, OutputHandler outputHandler, Operation<?, ?> operation,
		ExpressionParser expressionParser, ExpressionValidator expressionValidator, NumberParser<?> numberParser,
		NumberValidator<?, ?> numberValidator) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.operation = operation;
		this.expressionParser = expressionParser;
		this.expressionValidator = expressionValidator;
		this.numberParser = numberParser;
		this.numberValidator = numberValidator;
	}


}
