package ch01_calculator.mode;

import static ch01_calculator.text.InfoText.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ch01_calculator.mode.executor.Executor;
import ch01_calculator.operand.Operand;
import ch01_calculator.parser.ExpressionParser;
import ch01_calculator.processor.InputHandler;
import ch01_calculator.processor.OutputHandler;

public enum Mode {

	SIMPLE_CALCULATOR(1, CALCULATOR_MODE_SELECT_MESSAGE) {
		@Override
		public boolean execute(InputHandler inputHandler, OutputHandler outputHandler,
			ExpressionParser expressionParser) {
			outputHandler.displayMessage(EXPRESSION_ENTER_MESSAGE);
			final String expression = inputHandler.readInput();
			final Executor executor = expressionParser.parse(expression);
			final Operand<?> result = executor.execute();
			outputHandler.displayOutput(expression, result.toString());

			return APPLICATION_ACTIVE;
		}
	},
	END(0, END_MODE_SELECT_MESSAGE) {
		@Override
		public boolean execute(InputHandler inputHandler, OutputHandler outputHandler,
			ExpressionParser expressionParser) {
			outputHandler.displayMessage(this.getMessage());

			return !APPLICATION_ACTIVE;
		}
	},
	;

	private static final Map<Integer, Mode> valueToEnum = Arrays.stream(values())
		.collect(Collectors.toUnmodifiableMap(Mode::getValue, type -> type));
	public static final boolean APPLICATION_ACTIVE = true;
	private final int value;
	private final String message;

	Mode(int value, String message) {
		this.value = value;
		this.message = message;
	}

	public static Mode resolve(final String command) {
		final Integer number = Integer.valueOf(command);
		return Optional.ofNullable(valueToEnum.get(number))
			.orElseThrow(() -> new RuntimeException("[ERROR] 지원하지 않는 명령입니다."));
	}

	public abstract boolean execute(InputHandler inputHandler, OutputHandler outputHandler,
		ExpressionParser expressionParser);

	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}
}
