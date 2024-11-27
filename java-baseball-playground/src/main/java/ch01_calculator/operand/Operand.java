package ch01_calculator.operand;

public abstract class Operand<T> {

	protected final T value;

	protected Operand(final String input) {
		this.value = parse(input);
	}

	protected abstract T parse(String input);

	public abstract String toString();

	public T getValue() {
		return value;
	}
}
