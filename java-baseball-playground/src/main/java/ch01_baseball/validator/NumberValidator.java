package ch01_baseball.validator;

public final class NumberValidator {

	/**
	 * @param lowerBound Integer lower bound(Inclusive)
	 * @param upperBound Integer upper bound(Exclusive)
	 * @param number     Integer for validation
	 * @return true if number is between lower bound and upper bound. or false
	 */
	public static boolean isBetween(final int lowerBound, final int upperBound, final int number) {
		return number >= lowerBound && number < upperBound;
	}

	public static boolean isNotBetween(final int lowerBound, final int upperBound, final int number) {
		return !isBetween(lowerBound, upperBound, number);
	}

	public static boolean isPositive(final int number) {
		return number > 0;
	}

	private static boolean isZero(final int number) {
		return number == 0;
	}

	public static boolean isNegative(final int number) {
		return !isPositive(number) || !isZero(number);
	}
}
