package ch01_calculator.operation;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import ch01_calculator.domain.operation.Operation;
import ch01_calculator.domain.operation.IntegerOperation;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class IntegerOperandOperationTest {

	private final Operation<Integer, Integer> integerOperation = new IntegerOperation();

	@DisplayName("정수 덧셈 성공 테스트")
	@MethodSource("provideNumbersAndExpectedNumberForPlus")
	@ParameterizedTest
	void success_plus(Integer leftNumber, Integer rightNumber, Integer expected) {
		// when
		var output = integerOperation.plus(leftNumber, rightNumber);

		// then
		assertThat(output).isEqualTo(expected);
	}

	@DisplayName("정수 덧셈 실패: 산술 오버플로우가 일어나는 경우")
	@MethodSource("provideNumbersOverIntegerRangeForPlus")
	@ParameterizedTest
	void fail_plus_if_arithmetic_overflow_occur(Integer leftNumber, Integer rightNumber) {
		// when -> then
		assertThatThrownBy(() -> integerOperation.plus(leftNumber, rightNumber))
			.isInstanceOf(ArithmeticException.class);
	}

	@DisplayName("정수 뺄셈 성공 테스트")
	@MethodSource("provideNumbersAndExpectedNumberForMinus")
	@ParameterizedTest
	void success_minus(Integer leftNumber, Integer rightNumber, Integer expected) {
		// when
		var output = integerOperation.minus(leftNumber, rightNumber);

		// then
		assertThat(output).isEqualTo(expected);
	}

	@DisplayName("정수 뺄셈 실패: 산술 오버플로우가 일어나는 경우")
	@MethodSource("provideNumbersOverIntegerRangeForMinus")
	@ParameterizedTest
	void fail_minus_if_arithmetic_overflow_occur(Integer leftNumber, Integer rightNumber) {
		// when -> then
		assertThatThrownBy(() -> integerOperation.minus(leftNumber, rightNumber))
			.isInstanceOf(ArithmeticException.class);
	}

	@DisplayName("정수 곱셈 성공 테스트")
	@MethodSource("provideNumbersAndExpectedNumberForMultiply")
	@ParameterizedTest
	void success_multiply(Integer leftNumber, Integer rightNumber, Integer expected) {
		// when
		var output = integerOperation.multiply(leftNumber, rightNumber);

		// then
		assertThat(output).isEqualTo(expected);
	}

	@DisplayName("정수 곱셈 실패: 산술 오버플로우가 일어나는 경우")
	@MethodSource("provideNumbersOverIntegerRangeForMultiply")
	@ParameterizedTest
	void fail_multiply_if_arithmetic_overflow_occur(Integer leftNumber, Integer rightNumber) {
		// when -> then
		assertThatThrownBy(() -> integerOperation.multiply(leftNumber, rightNumber))
			.isInstanceOf(ArithmeticException.class);
	}

	/**
	 * INFO : 나눗셈의 결과는 소수점 둘째 자리까지 표시. 즉, 셋째자리에서 반올림한 결과를 표시
	 */
	@DisplayName("정수 나눗셈 성공 테스트")
	@MethodSource("provideNumbersAndExpectedNumberForDivide")
	@ParameterizedTest
	void success_divide(Integer leftNumber, Integer rightNumber, Integer expected) {
		// when
		var output = integerOperation.divide(leftNumber, rightNumber);

		// then
		assertThat(output).isEqualTo(expected);
	}

	@DisplayName("정수 곱셈 실패: 분모가 0인 경우")
	@CsvSource(value = {"-147:0", "-14:0", "-1:0", "0:0", "3:0", "44:0", "200:0"}, delimiter = ':')
	@ParameterizedTest
	void fail_divide_if_divider_is_zero(String val1, String val2) {
		// given
		Integer leftNumber = Integer.valueOf(val1);
		Integer rightNumber = Integer.valueOf(val2);

		// when -> then
		assertThatThrownBy(() -> integerOperation.divide(leftNumber, rightNumber))
			.isInstanceOf(ArithmeticException.class);
	}

	private static Stream<Arguments> provideNumbersAndExpectedNumberForPlus() {
		return Stream.of(
			Arguments.of(-20, -10, -30),
			Arguments.of(-10, 10, 0),
			Arguments.of(0, 10, 10),
			Arguments.of(10, 20, 30));
	}

	private static Stream<Arguments> provideNumbersAndExpectedNumberForMinus() {
		return Stream.of(
			Arguments.of(-20, -10, -10),
			Arguments.of(-10, 10, -20),
			Arguments.of(10, -50, 60),
			Arguments.of(0, 10, -10),
			Arguments.of(10, 20, -10),
			Arguments.of(100, 20, 80));
	}

	private static Stream<Arguments> provideNumbersAndExpectedNumberForMultiply() {
		return Stream.of(
			Arguments.of(-20, 100, -2000),
			Arguments.of(-20, -100, 2000),
			Arguments.of(100, 0, 0),
			Arguments.of(0, 100, 0),
			Arguments.of(-100, 0, 0),
			Arguments.of(0, -100, 0),
			Arguments.of(20, -100, -2000),
			Arguments.of(20, 100, 2000));
	}

	private static Stream<Arguments> provideNumbersAndExpectedNumberForDivide() {
		return Stream.of(
			Arguments.of(0, 10, 0),
			Arguments.of(0, 100, 0),
			Arguments.of(100, 5, 20),
			Arguments.of(100, 3, 33),
			Arguments.of(100, 6, 16),
			Arguments.of(131, 3, 43),
			Arguments.of(133, 3, 44),
			Arguments.of(-133, 3, -44),
			Arguments.of(133, -3, -44),
			Arguments.of(-133, -3, 44));
	}

	private static Stream<Arguments> provideNumbersOverIntegerRangeForPlus() {
		return Stream.of(
			Arguments.of(Integer.MAX_VALUE, 2),
			Arguments.of(Integer.MIN_VALUE, -2),
			Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE),
			Arguments.of(Integer.MIN_VALUE, Integer.MIN_VALUE));
	}

	private static Stream<Arguments> provideNumbersOverIntegerRangeForMinus() {
		return Stream.of(
			Arguments.of(-2, Integer.MAX_VALUE),
			Arguments.of(Integer.MIN_VALUE, 1),
			Arguments.of(Integer.MAX_VALUE, -1),
			Arguments.of(1, Integer.MIN_VALUE));
	}

	private static Stream<Arguments> provideNumbersOverIntegerRangeForMultiply() {
		return Stream.of(
			Arguments.of(Integer.MAX_VALUE, 2),
			Arguments.of(2, Integer.MAX_VALUE),
			Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE),
			Arguments.of(Integer.MIN_VALUE, Integer.MAX_VALUE),
			Arguments.of(Integer.MAX_VALUE, Integer.MIN_VALUE),
			Arguments.of(Integer.MIN_VALUE, 2),
			Arguments.of(2, Integer.MIN_VALUE),
			Arguments.of(Integer.MIN_VALUE, Integer.MIN_VALUE));
	}
}
