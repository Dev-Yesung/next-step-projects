package ch01_calculator.operator;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ch01_calculator.operand.IntegerOperand;

public enum IntegerOperator implements Calculator<IntegerOperand> {

	PLUS("+") {
		@Override
		public IntegerOperand calculate(IntegerOperand leftOperand, IntegerOperand rightOperand) {
			final int result = Math.addExact(leftOperand.getValue(), rightOperand.getValue());
			return new IntegerOperand(result);
		}
	},
	MINUS("-") {
		@Override
		public IntegerOperand calculate(IntegerOperand leftOperand, IntegerOperand rightOperand) {
			final int result = Math.subtractExact(leftOperand.getValue(), rightOperand.getValue());
			return new IntegerOperand(result);
		}
	},
	MULTIPLIER("*") {
		@Override
		public IntegerOperand calculate(IntegerOperand leftOperand, IntegerOperand rightOperand) {
			final int result = Math.multiplyExact(leftOperand.getValue(), rightOperand.getValue());
			return new IntegerOperand(result);
		}
	},
	DIVIDER("/") {
		@Override
		public IntegerOperand calculate(IntegerOperand leftOperand, IntegerOperand rightOperand) {
			if (rightOperand.getValue() == 0) {
				throw new ArithmeticException("분모는 0이 될 수 없습니다.");
			}

			final int result = leftOperand.getValue() / rightOperand.getValue();
			return new IntegerOperand(result);
		}
	};

	private static final Map<String, IntegerOperator> stringToEnumMap = Arrays.stream(values())
		.collect(Collectors.toUnmodifiableMap(IntegerOperator::getSymbol, (value) -> value));
	private final String symbol;

	IntegerOperator(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public static IntegerOperator resolve(String symbol) {
		return Optional.ofNullable(stringToEnumMap.get(symbol))
			.orElseThrow(() -> new RuntimeException("지원하지 않는 연산자입니다."));
	}
}
