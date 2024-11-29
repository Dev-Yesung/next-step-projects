package ch01_calculator.operand;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class IntegerOperandTest {

	@DisplayName("정수로 IntegerOperand 객체를 만드는데 성공한다.")
	@ValueSource(ints = {Integer.MIN_VALUE, -10_000, -1_000, -100, -10, -1, 0, 1, 10, 100, 1_000, 10_000,
		Integer.MAX_VALUE})
	@ParameterizedTest
	public void successToCreateIntegerOperand(int number) {
		// when
		Operand<?> integerOperand = new IntegerOperand(number);

		// then
		assertThat(integerOperand.getValue())
			.isEqualTo(number);
	}

	@DisplayName("숫자인 문자열로 IntegerOperand 객체를 만드는데 성공한다.")
	@ValueSource(strings = {"-10000", "-1000", "-100", "-10", "-1", "0", "1", "10", "100", "1000", "10000"})
	@ParameterizedTest
	public void successToCreateIntegerOperand(String number) {
		// when
		Operand<?> integerOperand = new IntegerOperand(number);

		// then
		assertThat(integerOperand.getValue())
			.isEqualTo(Integer.valueOf(number));
	}

	@DisplayName("숫자가 아닌 문자열은 IntegerOperand 객체를 만드는데 실패한다.")
	@NullAndEmptySource
	@ValueSource(strings = {" ", "    ", "q", "(", "#$","[{)", "_+))(", "?><<Bcs", "가", "나다", "라마바사"})
	@ParameterizedTest
	public void failToCreateIntegerOperand_INVALID_STRING_VALUE(String number) {
		// when -> then
		assertThatThrownBy(() -> new IntegerOperand(number))
			.isInstanceOf(RuntimeException.class);
	}

}
