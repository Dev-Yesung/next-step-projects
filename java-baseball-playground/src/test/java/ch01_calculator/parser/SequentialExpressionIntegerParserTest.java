package ch01_calculator.parser;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import ch01_calculator.dto.ParsedExpressions;

class SequentialExpressionIntegerParserTest {

	private ExpressionParser expressionParser;

	@BeforeEach
	void setUp() {
		NumberParser<Integer> numberParser = new IntegerParser();
		expressionParser = new SequentialExpressionIntegerParser(numberParser);
	}

	@DisplayName("형식에 맞는 올바른 연산식이 들어왔을 경우 순차적인 파싱에 성공한다.")
	@MethodSource("provideExpressionAndExpectedValue")
	@ParameterizedTest
	void success_parse(String expression, ParsedExpressions expected) {
		// when
		ParsedExpressions actual = expressionParser.parse(expression);

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("형식이 올바르지 않은 연산식이 들어올 경우 예외가 발생한다.")
	@ValueSource(strings = {
		"3", "5", "33", "455",
		"+", "-", "*", "/",
		"/ / / /", "+ + + +", "- - - -", "* * * *",
		"$#%^", "abcdefg", "가나다라마바사",
		"345++432", "342-532 * 243",
		"3+", "+3", "-3", "3-",
		"3 +", "3 -", "3 *", "3 /",
		"+ 3", "- 3", "* 3", "/ 3",
		"3+4", "3 +4", "3+ 4",
		"3-4", "3- 4", "3 -4",
		"3*4", "3* 4", "3 *4",
		"3/4", "3 /4", "3/ 4",
		"345 / 432*345", "345 / 432-345", "345 * 432+345",
	})
	@ParameterizedTest
	void fail_if_expression_is_invalid_format(String expression) {
		// when -> then
		assertThatThrownBy(() -> expressionParser.parse(expression))
			.isInstanceOf(RuntimeException.class);
	}

	private static Stream<Arguments> provideExpressionAndExpectedValue() {
		return Stream.of(
			Arguments.of("3 + 4", new ParsedExpressions(List.of("+"), List.of(3, 4))),
			Arguments.of("3 + 4 + 5", new ParsedExpressions(List.of("+", "+"), List.of(3, 4, 5))),
			Arguments.of("30 + 4 + -5", new ParsedExpressions(List.of("+", "+"), List.of(30, 4, -5))),
			Arguments.of("30 - 4 + -5", new ParsedExpressions(List.of("-", "+"), List.of(30, 4, -5))),
			Arguments.of("30 - 4 + -5 * 0", new ParsedExpressions(List.of("-", "+", "*"), List.of(30, 4, -5, 0))),
			Arguments.of("30 - 4 + -5 * 0 / 20",
				new ParsedExpressions(List.of("-", "+", "*", "/"), List.of(30, 4, -5, 0, 20))),
			Arguments.of("30 - 4 + -5 * 0 / 20 / -20",
				new ParsedExpressions(List.of("-", "+", "*", "/", "/"), List.of(30, 4, -5, 0, 20, -20))),
			Arguments.of("30 - 4 + -5 * 0 / 20 / -20",
				new ParsedExpressions(List.of("-", "+", "*", "/", "/"), List.of(30, 4, -5, 0, 20, -20))),
			Arguments.of("30 - 4 + -5 * 0 / 20 / -20 * -20",
				new ParsedExpressions(List.of("-", "+", "*", "/", "/", "*"), List.of(30, 4, -5, 0, 20, -20, -20)))
		);
	}
}
