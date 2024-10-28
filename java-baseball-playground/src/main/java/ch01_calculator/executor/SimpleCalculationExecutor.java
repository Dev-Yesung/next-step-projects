package ch01_calculator.executor;

import static ch01_calculator.processor.Processor.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ch01_calculator.dto.ParsedExpressions;
import ch01_calculator.operation.Operation;
import ch01_calculator.parser.Operator;

public class SimpleCalculationExecutor implements CalculationExecutor {

	private static final int VALID_OPERATION_OUTPUT_QUEUE_SIZE = 1;
	private final Operation<Integer, Integer> operation;

	public SimpleCalculationExecutor(Operation<Integer, Integer> operation) {
		this.operation = operation;
	}

	@Override
	public String calculate(ParsedExpressions parsedExpressions) {
		final List<String> numbers = parsedExpressions.numbers();
		final List<Operator> operators = parsedExpressions.operatorSymbols()
			.stream()
			.map(Operator::resolve)
			.toList();

		final List<String> queue = new ArrayList<>(numbers);
		for (final var operator : operators) {
			Integer leftOperand, rightOperand;
			try {
				leftOperand = Integer.parseInt(queue.removeFirst());
				rightOperand = Integer.parseInt(queue.removeFirst());
			} catch (NoSuchElementException | NumberFormatException e) {
				throw new RuntimeException("연산과정이 잘못 됐습니다.");
			}

			final String output = switch (operator) {
				case PLUS -> String.valueOf(operation.plus(leftOperand, rightOperand));
				case MINUS -> String.valueOf(operation.minus(leftOperand, rightOperand));
				case MULTIPLIER -> String.valueOf(operation.multiply(leftOperand, rightOperand));
				case DIVIDER -> String.valueOf(operation.divide(leftOperand, rightOperand));
			};
			queue.addFirst(output);
		}

		if (queue.size() != VALID_OPERATION_OUTPUT_QUEUE_SIZE) {
			throw new RuntimeException("연산과정이 잘못 됐습니다.");
		}

		return queue.getFirst();
	}
}
