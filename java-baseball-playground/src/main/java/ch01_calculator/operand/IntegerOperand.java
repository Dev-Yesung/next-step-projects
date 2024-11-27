package ch01_calculator.operand;

public class IntegerOperand extends Operand<Integer> {

	public IntegerOperand(String input) {
		super(input);
	}

	public IntegerOperand(int input) {
		this(String.valueOf(input));
	}

	@Override
	protected Integer parse(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new RuntimeException("[ERROR] 정수로 지원되지 않는 값입니다.");
		}
	}

	@Override
	public String toString() {
		return super.value.toString();
	}
}
