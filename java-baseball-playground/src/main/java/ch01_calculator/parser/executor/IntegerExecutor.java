package ch01_calculator.parser.executor;

import java.util.Deque;
import java.util.Queue;

import ch01_calculator.operand.IntegerOperand;
import ch01_calculator.operand.Operand;
import ch01_calculator.operator.IntegerOperator;

public class IntegerExecutor implements Executor {

	private final Deque<IntegerOperand> operands;
	private final Queue<IntegerOperator> operators;

	public IntegerExecutor(
		final Deque<IntegerOperand> operands,
		final Queue<IntegerOperator> operators
	) {
		validate(operands, operators);
		this.operands = operands;
		this.operators = operators;
	}

	@Override
	public Operand<Integer> execute() {
		while (!operators.isEmpty()) {
			final IntegerOperand leftOperand = operands.removeFirst();
			final IntegerOperator operator = operators.remove();
			final IntegerOperand rightOperand = operands.removeFirst();

			final IntegerOperand result = operator.calculate(leftOperand, rightOperand);

			operands.offerFirst(result);
		}

		return operands.removeFirst();
	}

	private void validate(
		final Deque<IntegerOperand> operands,
		final Queue<IntegerOperator> operators
	) {
		if (operands.size() < 2) {
			throw new IllegalArgumentException("[ERROR] 피연산자가 적어도 2개 이상이어야 합니다.");
		}
		if (operators.size() != operands.size() - 1) {
			throw new IllegalArgumentException("[ERROR] 연산자와 피연산자의 개수가 맞지 않습니다.");
		}
	}
}
