package ch01_calculator.operation;

public class IntegerOperation implements Operation<Integer, Integer> {

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
	public Integer divide(Integer leftNumber, Integer rightNumber) {
		if (rightNumber == 0) {
			throw new ArithmeticException("분모는 0이 될 수 없습니다.");
		}

		return leftNumber / rightNumber;
	}
}
