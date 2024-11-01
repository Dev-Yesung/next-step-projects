package ch01_calculator.processor;

import static ch01_calculator.text.InfoText.*;

import ch01_calculator.dto.ParsedExpressions;
import ch01_calculator.executor.CalculationExecutor;
import ch01_calculator.hanlder.input.InputHandler;
import ch01_calculator.hanlder.output.OutputHandler;
import ch01_calculator.parser.ExpressionParser;

public class Processor {

	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final ExpressionParser expressionParser;
	private final CalculationExecutor calculationExecutor;

	public Processor(InputHandler inputHandler, OutputHandler outputHandler, ExpressionParser expressionParser,
		CalculationExecutor calculationExecutor) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.expressionParser = expressionParser;
		this.calculationExecutor = calculationExecutor;
	}

	public void run() {
		outputHandler.displayMessage(APPLICATION_START_UP_MESSAGE);

		while (true) {
			try {
				final Mode mode = selectMode();
				outputHandler.displayMessage(mode.getMessage());

				switch (mode) {
					case Mode.SIMPLE_CALCULATOR -> {
						outputHandler.displayMessage(EXPRESSION_ENTER_MESSAGE);
						final String expression = inputHandler.readInput();
						final ParsedExpressions parsedExpressions = expressionParser.parse(expression);
						final String output = calculationExecutor.calculate(parsedExpressions);
						outputHandler.displayOutput(expression, output);
					}
				}

				if (!mode.isRunning()) {
					break;
				}
			} catch (RuntimeException e) {
				outputHandler.displayError(e.getMessage());
			}
		}
	}

	private Mode selectMode() {
		outputHandler.displayMessage(COMMAND_EXPLAIN_MESSAGE);
		final String command = inputHandler.readInput();

		return Mode.resolve(command);
	}
}
