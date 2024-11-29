package ch01_calculator.operand;

public class IntegerOperand extends Operand<Integer> {

	public IntegerOperand(String number) {
		super(number);
	}

	public IntegerOperand(int number) {
		this(String.valueOf(number));
	}

	@Override
	protected Integer parse(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			throw new RuntimeException("[ERROR] 정수로 지원되지 않는 값입니다.");
		}
	}

	@Override
	public String toString() {
		return super.value.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		return ((IntegerOperand)obj).getValue().equals(this.getValue());
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
