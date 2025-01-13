package ch01_baseball.number;

import java.util.Objects;

import ch01_baseball.validator.NumberValidator;

public class DefaultBaseBallNumber implements BaseBallNumber {

	private final int index;
	private final int value;

	public DefaultBaseBallNumber(final int index, final int value) {
		validate(index, value);
		this.index = index;
		this.value = value;
	}

	private void validate(final int index, final int value) {
		if (NumberValidator.isNegative(index)) {
			throw new RuntimeException("[ERROR] 인덱스는 0이상의 정수만 허용됩니다.");
		}
		if (NumberValidator.isNotBetween(LOWER_BOUND_INCLUSIVE, UPPER_BOUND_EXCLUSIVE, value)) {
			throw new RuntimeException("[ERROR] %s는 허용되지 않은 입력입니다.".formatted(value));
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DefaultBaseBallNumber that = (DefaultBaseBallNumber)o;
		return index == that.index && value == that.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(index, value);
	}
}
