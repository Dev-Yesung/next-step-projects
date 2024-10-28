package ch01_calculator.processor;

import static ch01_calculator.text.InfoText.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Mode {

	SIMPLE_CALCULATOR(1, SIMPLE_CALCULATOR_MODE_SELECT_MESSAGE, true),
	END(0, END_MODE_SELECT_MESSAGE, false);

	private static final Map<Integer, Mode> valueToEnum = Arrays.stream(values())
		.collect(Collectors.toUnmodifiableMap(Mode::getValue, type -> type));
	private final int value;
	private final String message;
	private final boolean isRunning;

	Mode(int value, String message, boolean isRunning) {
		this.value = value;
		this.message = message;
		this.isRunning = isRunning;
	}

	public static Mode resolve(final String command) {
		final Integer number = Integer.valueOf(command);
		return Optional.ofNullable(valueToEnum.get(number))
			.orElseThrow(() -> new RuntimeException("지원하지 않는 명령입니다."));
	}

	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public boolean isRunning() {
		return isRunning;
	}
}
