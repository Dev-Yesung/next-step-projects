package ch01_calculator.processor;

import static ch01_calculator.text.InfoText.*;

import ch01_calculator.executor.IntegerExecutor;
import ch01_calculator.hanlder.input.InputHandler;
import ch01_calculator.hanlder.output.OutputHandler;
import ch01_calculator.operand.IntegerOperand;
import ch01_calculator.parser.ExpressionParser;

public class Processor {

	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final ExpressionParser expressionParser;
	private boolean isRunning = true;

	public Processor(
		InputHandler inputHandler,
		OutputHandler outputHandler,
		ExpressionParser expressionParser
	) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.expressionParser = expressionParser;
	}

	public void run() {
		outputHandler.displayMessage(APPLICATION_START_UP_MESSAGE);
		do {
			outputHandler.displayMessage(COMMAND_EXPLAIN_MESSAGE);
			final String command = inputHandler.readInput();
			final Mode mode = Mode.resolve(command);
			outputHandler.displayMessage(mode.getMessage());
			switch (mode) {
				case Mode.SIMPLE_CALCULATOR -> calculateSimple();
				case Mode.END -> terminateProcessor();
			}
		} while (isRunning);
	}

	private void calculateSimple() {
		outputHandler.displayMessage(EXPRESSION_ENTER_MESSAGE);
		try {
			final String expression = inputHandler.readInput();
			final IntegerExecutor executor = expressionParser.parse(expression);
			final IntegerOperand result = executor.execute();

			outputHandler.displayOutput(expression, result.toString());
		} catch (RuntimeException e) {
			outputHandler.displayError(e.getMessage());
		}
	}

	private void terminateProcessor() {
		outputHandler.displayMessage(Mode.END.getMessage());
		isRunning = false;
	}
}
