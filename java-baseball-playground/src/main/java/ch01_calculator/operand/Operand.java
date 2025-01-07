package ch01_calculator.operand;

import java.util.StringJoiner;

public abstract class Operand<T> {

	protected final T value;

	protected Operand(final String input) {
		this.value = parse(input);
	}

	protected abstract T parse(String input);

	@Override
	public String toString() {
		return new StringJoiner(", ", Operand.class.getSimpleName() + "[", "]")
			.add("value=" + value)
			.toString();
	}

	public T getValue() {
		return value;
	}
}
