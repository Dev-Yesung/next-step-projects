package ch01_calculator.parser;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import ch01_calculator.executor.IntegerExecutor;
import ch01_calculator.operand.IntegerOperand;
import ch01_calculator.operator.IntegerOperator;

public class SequentialIntegerExpressionParser implements ExpressionParser {

	@Override
	public IntegerExecutor parse(final String expression) {
		final String[] tokens = expression.split(" ");
		if (isEven(tokens.length) || tokens.length == 1) {
			throw new IllegalArgumentException("[ERROR] 입력된 식의 형태가 올바르지 않습니다.");
		}

		final Deque<IntegerOperand> operands = new LinkedList<>();
		final Queue<IntegerOperator> operators = new LinkedList<>();
		for (int i = 0; i < tokens.length; i++) {
			final String token = tokens[i];
			if (isEven(i)) {
				final IntegerOperand operand = new IntegerOperand(token);
				operands.offer(operand);
				continue;
			}

			final IntegerOperator operator = IntegerOperator.resolve(token);
			operators.offer(operator);
		}

		return new IntegerExecutor(operands, operators);
	}

	private boolean isEven(final int number) {
		return number % 2 == 0;
	}
}
