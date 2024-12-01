package ch01_calculator.parser.executor;

import static org.assertj.core.api.Assertions.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch01_calculator.operand.IntegerOperand;
import ch01_calculator.operand.Operand;
import ch01_calculator.operator.IntegerOperator;

class IntegerExecutorTest {

	@DisplayName("올바르게 파싱된 연산자와 연산식에 의해 연산 결과가 나온다.")
	@MethodSource("getValidParsedExpressions")
	@ParameterizedTest
	void success_execute(Deque<IntegerOperand> operands, Queue<IntegerOperator> operators, IntegerOperand EXPECTED) {
		// given
		Executor executor = new IntegerExecutor(operands, operators);

		// when
		Operand<?> ACTUAL = executor.execute();

		// then
		assertThat(ACTUAL).isEqualTo(EXPECTED);
	}

	@DisplayName("계산에 필요한 숫자가 없으면 생성 실패")
	@MethodSource("getShortOfNumbersForCalculation")
	@ParameterizedTest
	void fail_ifShortOfNumbers(Deque<IntegerOperand> operands, Queue<IntegerOperator> operators) {
		// when -> then
		assertThatThrownBy(() -> new IntegerExecutor(operands, operators))
			.isInstanceOf(RuntimeException.class);
	}

	@DisplayName("연산자의 개수가 맞지 않으면 생성 실패")
	@MethodSource("getInvalidOperatorSize")
	@ParameterizedTest
	void fail_ifShortOfOperators(Deque<IntegerOperand> operands, Queue<IntegerOperator> operators) {
		// when -> then
		assertThatThrownBy(() -> new IntegerExecutor(operands, operators))
			.isInstanceOf(RuntimeException.class);
	}

	private static Stream<Arguments> getValidParsedExpressions() {
		return Stream.of(
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(5), new IntegerOperand(30))),
				new LinkedList<>(List.of(IntegerOperator.PLUS)),
				new IntegerOperand(35)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-5), new IntegerOperand(30))),
				new LinkedList<>(List.of(IntegerOperator.PLUS)),
				new IntegerOperand(25)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(5), new IntegerOperand(-30))),
				new LinkedList<>(List.of(IntegerOperator.PLUS)),
				new IntegerOperand(-25)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-5), new IntegerOperand(-30))),
				new LinkedList<>(List.of(IntegerOperator.PLUS)),
				new IntegerOperand(-35)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(5), new IntegerOperand(30))),
				new LinkedList<>(List.of(IntegerOperator.MINUS)),
				new IntegerOperand(-25)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-5), new IntegerOperand(30))),
				new LinkedList<>(List.of(IntegerOperator.MINUS)),
				new IntegerOperand(-35)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(5), new IntegerOperand(-30))),
				new LinkedList<>(List.of(IntegerOperator.MINUS)),
				new IntegerOperand(35)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-5), new IntegerOperand(-30))),
				new LinkedList<>(List.of(IntegerOperator.MINUS)),
				new IntegerOperand(25)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(5), new IntegerOperand(30))),
				new LinkedList<>(List.of(IntegerOperator.MULTIPLIER)),
				new IntegerOperand(150)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-5), new IntegerOperand(30))),
				new LinkedList<>(List.of(IntegerOperator.MULTIPLIER)),
				new IntegerOperand(-150)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(5), new IntegerOperand(-30))),
				new LinkedList<>(List.of(IntegerOperator.MULTIPLIER)),
				new IntegerOperand(-150)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-5), new IntegerOperand(-30))),
				new LinkedList<>(List.of(IntegerOperator.MULTIPLIER)),
				new IntegerOperand(150)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(5), new IntegerOperand(30))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER)),
				new IntegerOperand(0)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-5), new IntegerOperand(30))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER)),
				new IntegerOperand(0)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(5), new IntegerOperand(-30))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER)),
				new IntegerOperand(0)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-5), new IntegerOperand(-30))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER)),
				new IntegerOperand(0)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(30), new IntegerOperand(5))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER)),
				new IntegerOperand(6)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-30), new IntegerOperand(5))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER)),
				new IntegerOperand(-6)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(30), new IntegerOperand(-5))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER)),
				new IntegerOperand(-6)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-30), new IntegerOperand(-5))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER)),
				new IntegerOperand(6)
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(-30), new IntegerOperand(-5), new IntegerOperand(3),
					new IntegerOperand(-3), new IntegerOperand(12))),
				new LinkedList<>(List.of(IntegerOperator.DIVIDER, IntegerOperator.MULTIPLIER, IntegerOperator.PLUS,
					IntegerOperator.MINUS)),
				new IntegerOperand(3)
			)
		);
	}

	private static Stream<Arguments> getShortOfNumbersForCalculation() {
		return Stream.of(
			Arguments.of(
				new LinkedList<>(),
				new LinkedList<>(List.of(IntegerOperator.PLUS))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(3))),
				new LinkedList<>(List.of(IntegerOperator.PLUS))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(4), new IntegerOperand(5))),
				new LinkedList<>(List.of(IntegerOperator.PLUS, IntegerOperator.MINUS))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(4), new IntegerOperand(5), new IntegerOperand(-9))),
				new LinkedList<>(List.of(IntegerOperator.PLUS, IntegerOperator.MINUS, IntegerOperator.MULTIPLIER))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(4), new IntegerOperand(5), new IntegerOperand(-9),
					new IntegerOperand(20))),
				new LinkedList<>(List.of(IntegerOperator.PLUS, IntegerOperator.MINUS, IntegerOperator.MULTIPLIER,
					IntegerOperator.DIVIDER))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(4), new IntegerOperand(5), new IntegerOperand(-9),
					new IntegerOperand(20), new IntegerOperand(0))),
				new LinkedList<>(List.of(IntegerOperator.PLUS, IntegerOperator.MINUS, IntegerOperator.MULTIPLIER,
					IntegerOperator.DIVIDER, IntegerOperator.MULTIPLIER))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(4), new IntegerOperand(5), new IntegerOperand(-9),
					new IntegerOperand(20), new IntegerOperand(0), new IntegerOperand(-4))),
				new LinkedList<>(List.of(IntegerOperator.PLUS, IntegerOperator.MINUS, IntegerOperator.MULTIPLIER,
					IntegerOperator.DIVIDER, IntegerOperator.MULTIPLIER, IntegerOperator.MINUS))
			)
		);
	}

	private static Stream<Arguments> getInvalidOperatorSize() {
		return Stream.of(
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(3), new IntegerOperand(4), new IntegerOperand(5))),
				new LinkedList<>(List.of(IntegerOperator.PLUS))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(3), new IntegerOperand(4), new IntegerOperand(5),
					new IntegerOperand(6), new IntegerOperand(7), new IntegerOperand(8))),
				new LinkedList<>(List.of(IntegerOperator.PLUS))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(3), new IntegerOperand(4), new IntegerOperand(5),
					new IntegerOperand(-6))),
				new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(3), new IntegerOperand(4), new IntegerOperand(5),
					new IntegerOperand(-6), new IntegerOperand(0))),
				new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS, IntegerOperator.MULTIPLIER))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(3), new IntegerOperand(4), new IntegerOperand(5),
					new IntegerOperand(-6), new IntegerOperand(3), new IntegerOperand(4))),
				new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS, IntegerOperator.MULTIPLIER,
					IntegerOperator.DIVIDER))
			),
			Arguments.of(
				new LinkedList<>(List.of(new IntegerOperand(3), new IntegerOperand(4), new IntegerOperand(5),
					new IntegerOperand(-6), new IntegerOperand(3), new IntegerOperand(4), new IntegerOperand(0),
					new IntegerOperand(-1), new IntegerOperand(-2), new IntegerOperand(-3))),
				new LinkedList<>(List.of(IntegerOperator.MINUS, IntegerOperator.PLUS, IntegerOperator.MULTIPLIER,
					IntegerOperator.DIVIDER))
			)
		);
	}
}
