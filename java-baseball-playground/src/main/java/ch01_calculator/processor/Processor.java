package ch01_calculator.processor;

import static ch01_calculator.text.InfoText.*;

import java.util.Scanner;

import ch01_calculator.mode.Mode;
import ch01_calculator.parser.ExpressionParser;

public class Processor implements InputHandler, OutputHandler {

	private final ExpressionParser expressionParser;
	private final Scanner scanner;

	public Processor(ExpressionParser expressionParser, Scanner scanner) {
		this.expressionParser = expressionParser;
		this.scanner = scanner;
	}

	public boolean run() {
		displayMessage(APPLICATION_START_UP_MESSAGE);
		try {
			displayMessage(COMMAND_EXPLAIN_MESSAGE);
			final String command = readInput();
			final Mode mode = Mode.resolve(command);

			return mode.execute(this, this, expressionParser);
		} catch (RuntimeException e) {
			displayError(e.getMessage());
		}

		return Mode.APPLICATION_ACTIVE;
	}

	@Override
	public String readInput() {
		final String input = scanner.nextLine();
		if (input == null || input.isEmpty() || input.isBlank()) {
			throw new RuntimeException("[ERROR] 식은 비어있을 수 없습니다. 올바르게 입력해주세요.");
		}
		return input;
	}

	@Override
	public void displayMessage(String message) {
		System.out.printf("%s%n", message);
	}

	@Override
	public void displayOutput(String expression, String output) {
		System.out.printf("Output : %s = %s%n", expression, output);
	}

	@Override
	public void displayError(String errorMessage) {
		System.out.printf("%s%n", errorMessage);
	}
}
