package ch01_calculator.parser;

import java.util.ArrayList;
import java.util.List;

import ch01_calculator.dto.ParsedExpressions;

public class SequentialExpressionIntegerParser implements ExpressionParser {

	private static final int ONLY_ONE_TOKEN_SIZE = 1;
	private static final int EVEN_DIVISOR = 2;
	private static final int EVEN_REMAINDER = 0;
	private static final int ODD_REMAINDER = 1;
	private final NumberParser<Integer> numberParser;

	public SequentialExpressionIntegerParser(NumberParser<Integer> numberParser) {
		this.numberParser = numberParser;
	}

	@Override
	public ParsedExpressions parse(final String expression) {
		final String[] tokens = expression.split(" ");
		if (tokens.length == ONLY_ONE_TOKEN_SIZE
			|| tokens.length % EVEN_DIVISOR != ODD_REMAINDER) {
			throw new RuntimeException("[ERROR] 입력된 식의 형태가 올바르지 않습니다.");
		}

		final List<String> operatorSymbols = new ArrayList<>();
		final List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < tokens.length; i++) {
			if (i % EVEN_DIVISOR == EVEN_REMAINDER) {
				numbers.add(numberParser.parseNumber(tokens[i]));
			} else {
				Operator.validate(tokens[i]);
				operatorSymbols.add(tokens[i]);
			}
		}

		return new ParsedExpressions(operatorSymbols, numbers);
	}
}
