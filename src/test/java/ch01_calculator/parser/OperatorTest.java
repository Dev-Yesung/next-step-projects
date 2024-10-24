package ch01_calculator.parser;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class OperatorTest {

	@DisplayName("지원하는 연산자일 경우 예외가 발생하지 않는다.")
	@ValueSource(strings = {"+", "-", "*", "-"})
	@ParameterizedTest
	void success_if_operator_is_valid(String operator) {
		// when -> then
		assertThatNoException()
			.isThrownBy(() -> Operator.validate(operator));
	}

	@DisplayName("지원하지 않는 연산자일 경우 예외가 발생한다.")
	@ValueSource(strings = {"", "  ", "!", "%", "^", "&", "|", "!=", ">", "<", "<=", ">=",
		"(", ")", "_", "~", "qwer", "@#$%", "_)(*&^", "=="})
	@NullAndEmptySource
	@ParameterizedTest
	void fail_if_operator_is_not_valid(String operator) {
		// when -> then
		assertThatThrownBy(() -> Operator.validate(operator))
			.isInstanceOf(RuntimeException.class);
	}
}
