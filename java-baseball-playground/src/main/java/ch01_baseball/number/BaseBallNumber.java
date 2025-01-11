package ch01_baseball.number;

import java.util.Objects;
import java.util.StringJoiner;

public class BaseBallNumber {

	private static final int NUMBER_LOWER_BOUND_INCLUSIVE = 1;
	private static final int NUMBER_UPPER_BOUND_EXCLUSIVE = 10;
	private final int number;

	public BaseBallNumber(final int number) {
		validate(number);
		this.number = number;
	}

	private void validate(final int number) {
		if (number < NUMBER_LOWER_BOUND_INCLUSIVE || number >= NUMBER_UPPER_BOUND_EXCLUSIVE) {
			throw new RuntimeException("[ERROR] 허용된 숫자 범위는 1에서 9 사이입니다.");
		}
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", BaseBallNumber.class.getSimpleName() + "[", "]")
			.add("number=" + number)
			.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseBallNumber that = (BaseBallNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(number);
	}
}
