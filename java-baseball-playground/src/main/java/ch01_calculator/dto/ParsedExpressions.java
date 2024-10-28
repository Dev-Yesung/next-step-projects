package ch01_calculator.dto;

import java.util.List;

public record ParsedExpressions(
	List<String> operatorSymbols,
	List<String> numbers
) {
}
