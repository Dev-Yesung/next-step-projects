package ch01_calculator.parser;

import java.util.ArrayList;
import java.util.List;

import ch01_calculator.dto.ParsedExpressions;

public class SequentialExpressionParser implements ExpressionParser {

	private static final int ONLY_ONE_TOKEN_SIZE = 1;
	private static final int EVEN_DIVISOR = 2;
	private static final int EVEN_REMAINDER = 0;
	private static final int ODD_REMAINDER = 1;

	@Override
	public ParsedExpressions parse(final String expression) {
		final String[] tokens = expression.split(" ");
		if (tokens.length == ONLY_ONE_TOKEN_SIZE
			|| tokens.length % EVEN_DIVISOR != ODD_REMAINDER) {
			throw new RuntimeException("입력된 식의 형태가 올바르지 않습니다.");
		}

		final List<String> operatorSymbols = new ArrayList<>();
		final List<String> numbers = new ArrayList<>();
		for (int i = 0; i < tokens.length; i++) {
			if (i % EVEN_DIVISOR == EVEN_REMAINDER) {
				try {
					Integer.parseInt(tokens[i]);
				} catch (NumberFormatException e) {
					throw new RuntimeException("지원하지 않는 숫자입니다.");
				}
				numbers.add(tokens[i]);
			} else {
				Operator.validate(tokens[i]);
				operatorSymbols.add(tokens[i]);
			}
		}

		return new ParsedExpressions(operatorSymbols, numbers);
	}
}
