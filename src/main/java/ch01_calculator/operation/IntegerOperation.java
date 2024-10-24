package ch01_calculator.operation;

public class IntegerOperation implements Operation<Integer, Integer> {

	private static final int PRECISION_FACTOR = 100;

	@Override
	public Integer plus(final Integer leftNumber, final Integer rightNumber) {
		return Math.addExact(leftNumber, rightNumber);
	}

	@Override
	public Integer minus(final Integer leftNumber, final Integer rightNumber) {
		return Math.subtractExact(leftNumber, rightNumber);
	}

	@Override
	public Integer multiply(Integer leftNumber, Integer rightNumber) {
		return Math.multiplyExact(leftNumber, rightNumber);
	}

	@Override
	public Double divide(Integer leftNumber, Integer rightNumber) {
		if (rightNumber == 0) {
			throw new ArithmeticException("[ERROR] 분모는 0이 될 수 없습니다.");
		}

		return (double)Math.round((leftNumber.doubleValue() / rightNumber) * PRECISION_FACTOR)
			   / PRECISION_FACTOR;
	}
}
