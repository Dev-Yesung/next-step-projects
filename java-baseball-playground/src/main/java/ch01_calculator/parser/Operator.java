package ch01_calculator.parser;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Operator {
	PLUS("+"),
	MINUS("-"),
	MULTIPLIER("*"),
	DIVIDER("/");

	private static final Map<String, Operator> stringToEnumMap = Arrays.stream(values())
		.collect(Collectors.toUnmodifiableMap(Operator::getSymbol, (value) -> value));
	private final String symbol;

	Operator(String symbol) {
		this.symbol = symbol;
	}

	public static void validate(String symbol) {
		resolve(symbol);
	}

	public static Operator resolve(String symbol) {
		return Optional.ofNullable(stringToEnumMap.get(symbol))
			.orElseThrow(() -> new RuntimeException("지원하지 않는 연산자입니다."));
	}

	public String getSymbol() {
		return symbol;
	}
}
