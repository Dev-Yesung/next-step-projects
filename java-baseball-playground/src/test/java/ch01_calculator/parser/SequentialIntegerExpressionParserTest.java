package ch01_calculator.parser;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import ch01_calculator.parser.executor.Executor;
import ch01_calculator.parser.executor.IntegerExecutor;
import ch01_calculator.operand.IntegerOperand;
import ch01_calculator.operator.IntegerOperator;

class SequentialIntegerExpressionParserTest {

	private final ExpressionParser expressionParser = new SequentialIntegerExpressionParser();

	@DisplayName("형식에 맞는 올바른 연산식이 들어왔을 경우 순차적인 파싱에 성공한다.")
	@MethodSource("provideExpressionAndExpectedValue")
	@ParameterizedTest
	void success_parse(String expression, Executor expected) {
		// when
		Executor actual = expressionParser.parse(expression);

		// then
		assertThat(actual.execute()).isEqualTo(expected.execute());
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
			Arguments.of("3 + 4",
				new IntegerExecutor(
					new LinkedList<>(List.of(new IntegerOperand(3), new IntegerOperand(4))),
					new LinkedList<>(List.of(IntegerOperator.PLUS))
				)
			),
			Arguments.of("3 + 4 + 5",
				new IntegerExecutor(
					new LinkedList<>(List.of(new IntegerOperand(3), new IntegerOperand(4), new IntegerOperand(5))),
					new LinkedList<>(List.of(IntegerOperator.PLUS, IntegerOperator.PLUS))
				)
			),
			Arguments.of("30 + 4 + -5",
				new IntegerExecutor(
					new LinkedList<>(List.of(new IntegerOperand(30), new IntegerOperand(4), new IntegerOperand(-5))),
					new LinkedList<>(List.of(IntegerOperator.PLUS, IntegerOperator.PLUS))
				)
			),
			Arguments.of("30 - 4 + -5",
				new IntegerExecutor(
					new LinkedList<>(List.of(new IntegerOperand(30), new IntegerOperand(4), new IntegerOperand(-5))),
					new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS))
				)
			),
			Arguments.of("30 - 4 + -5 * 0",
				new IntegerExecutor(
					new LinkedList<>(List.of(new IntegerOperand(30), new IntegerOperand(4), new IntegerOperand(-5),
						new IntegerOperand(0))),
					new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS, IntegerOperator.MULTIPLIER))
				)
			),
			Arguments.of("30 - 4 + -5 * 0 / 20",
				new IntegerExecutor(
					new LinkedList<>(List.of(new IntegerOperand(30), new IntegerOperand(4), new IntegerOperand(-5),
						new IntegerOperand(0), new IntegerOperand(20))),
					new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS, IntegerOperator.MULTIPLIER,
						IntegerOperator.DIVIDER))
				)
			),
			Arguments.of("30 - 4 + -5 * 0 / 20 / -20",
				new IntegerExecutor(
					new LinkedList<>(List.of(new IntegerOperand(30), new IntegerOperand(4), new IntegerOperand(-5),
						new IntegerOperand(0), new IntegerOperand(20), new IntegerOperand(-20))),
					new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS, IntegerOperator.MULTIPLIER,
						IntegerOperator.DIVIDER, IntegerOperator.DIVIDER))
				)
			),
			Arguments.of("30 - 4 + -5 * 0 / 20 / -20 * -20",
				new IntegerExecutor(
					new LinkedList<>(List.of(new IntegerOperand(30), new IntegerOperand(4), new IntegerOperand(-5),
						new IntegerOperand(0), new IntegerOperand(20), new IntegerOperand(-20),
						new IntegerOperand(-20))),
					new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS, IntegerOperator.MULTIPLIER,
						IntegerOperator.DIVIDER, IntegerOperator.DIVIDER, IntegerOperator.MULTIPLIER))
				)
			)
		);
	}
}
