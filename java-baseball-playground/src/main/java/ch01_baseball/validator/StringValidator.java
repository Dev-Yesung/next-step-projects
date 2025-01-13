package ch01_baseball.validator;

import java.util.Objects;

public final class StringValidator {

	public static boolean isNullOrBlank(final String value) {
		return isNull(value) || value.isBlank();
	}

	public static boolean isNull(final String value) {
		return Objects.isNull(value);
	}
}
