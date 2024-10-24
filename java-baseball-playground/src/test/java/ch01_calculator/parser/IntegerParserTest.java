package ch01_calculator.parser;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class IntegerParserTest {

	private final NumberParser<Integer> numberParser = new IntegerParser();

	@DisplayName("문자열을 정수로 변환하는데 성공")
	@MethodSource("provideStringNumberAndIntegerNumber")
	@ParameterizedTest
	void success_parse_integer(String number, Integer expected) {
		// when
		Integer actual = numberParser.parseNumber(number);

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("정수 범위를 넘으면 변환에 실패한다.")
	@ValueSource(strings = {"-9999999999", "-2147483649", "2147483648", "9999999999"})
	@ParameterizedTest
	void fail_if_number_exceed_integer_range(String number) {
		// when -> then
		assertThatThrownBy(() -> numberParser.parseNumber(number))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("문자가 숫자의 형태가 아닌 경우 변환에 실패한다.")
	@ValueSource(strings = {"", "      ", "\n\n\n\n", "\r\n\r\n",
		"!@@$#!   $@^", "abcdefghijk", "가나다라마바사", "{}|';./.,~+_)(*"})
	@NullAndEmptySource
	@ParameterizedTest
	void fail_if_string_is_not_number(String number) {
		// when -> then
		assertThatThrownBy(() -> numberParser.parseNumber(number))
			.isInstanceOf(IllegalArgumentException.class);
	}

	private static Stream<Arguments> provideStringNumberAndIntegerNumber() {
		return Stream.of(
			Arguments.of("-2147483648", Integer.MIN_VALUE),
			Arguments.of("-100000", -100_000),
			Arguments.of("-10000", -10_000),
			Arguments.of("-1000", -1_000),
			Arguments.of("-100", -100),
			Arguments.of("-10", -10),
			Arguments.of("0", 0),
			Arguments.of("10", 10),
			Arguments.of("100", 100),
			Arguments.of("1000", 1_000),
			Arguments.of("10000", 10_000),
			Arguments.of("100000", 100_000),
			Arguments.of("2147483647", Integer.MAX_VALUE)
		);
	}
}
