// package ch01_calculator.executor;
//
// import static org.assertj.core.api.Assertions.*;
//
// import java.util.List;
// import java.util.stream.Stream;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;
//
// import ch01_calculator.dto.ParsedExpressions;
// import ch01_calculator.domain.operation.IntegerOperation;
//
// class SimpleCalculationIntegerExecutorTest {
//
// 	private final CalculationExecutor calculationExecutor
// 		= new SimpleCalculationExecutor(new IntegerOperation());
//
// 	@DisplayName("올바르게 파싱된 숫자와 연산자를 통해 계산에 성공한다.")
// 	@MethodSource("getValidParsedExpressions")
// 	@ParameterizedTest
// 	void success_calculate(ParsedExpressions parsedExpressions, String expect) {
// 		// when
// 		String actual = calculationExecutor.calculate(parsedExpressions);
//
// 		// then
// 		assertThat(actual).isEqualTo(expect);
// 	}
//
// 	@DisplayName("계산에 필요한 숫자가 없으면 실패")
// 	@MethodSource("getShortOfNumbersForCalculation")
// 	@ParameterizedTest
// 	void fail_if_short_of_numbers(ParsedExpressions parsedExpressions) {
// 		// when -> then
// 		assertThatThrownBy(() -> calculationExecutor.calculate(parsedExpressions))
// 			.isInstanceOf(RuntimeException.class);
// 	}
//
// 	@DisplayName("계산 결과 큐의 크기가 2이상이면 실패")
// 	@MethodSource("getInvalidParsedExpressions")
// 	@ParameterizedTest
// 	void fail_if_queue_size_is_over_than_or_equal_two(ParsedExpressions parsedExpressions) {
// 		// when -> then
// 		assertThatThrownBy(() -> calculationExecutor.calculate(parsedExpressions))
// 			.isInstanceOf(RuntimeException.class);
// 	}
//
// 	private static Stream<Arguments> getValidParsedExpressions() {
// 		return Stream.of(
// 			Arguments.of(new ParsedExpressions(
// 				List.of("+"), List.of("5", "30")), "35"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("+"), List.of("-5", "30")), "25"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("+"), List.of("5", "-30")), "-25"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("+"), List.of("-5", "-30")), "-35"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("-"), List.of("5", "30")), "-25"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("-"), List.of("-5", "30")), "-35"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("-"), List.of("5", "-30")), "35"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("-"), List.of("-5", "-30")), "25"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("*"), List.of("5", "30")), "150"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("*"), List.of("-5", "30")), "-150"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("*"), List.of("5", "-30")), "-150"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("*"), List.of("-5", "-30")), "150"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/"), List.of("5", "30")), "0"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/"), List.of("-5", "30")), "0"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/"), List.of("5", "-30")), "0"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/"), List.of("-5", "-30")), "0"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/"), List.of("30", "5")), "6"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/"), List.of("-30", "5")), "-6"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/"), List.of("30", "-5")), "-6"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/"), List.of("-30", "-5")), "6"),
// 			Arguments.of(new ParsedExpressions(
// 				List.of("/", "*", "+", "-"), List.of("-30", "-5", "3", "-3", "12")), "3")
// 		);
// 	}
//
// 	private static Stream<ParsedExpressions> getShortOfNumbersForCalculation() {
// 		return Stream.of(
// 			new ParsedExpressions(
// 				List.of("+"),
// 				List.of()),
// 			new ParsedExpressions(
// 				List.of("+"),
// 				List.of("3")),
// 			new ParsedExpressions(
// 				List.of("+", "-"),
// 				List.of("4", "5")),
// 			new ParsedExpressions(
// 				List.of("+", "-", "*"),
// 				List.of("4", "5", "-9")),
// 			new ParsedExpressions(
// 				List.of("+", "-", "*", "/"),
// 				List.of("4", "5", "-9", "20")),
// 			new ParsedExpressions(
// 				List.of("+", "-", "*", "/", "*"),
// 				List.of("4", "5", "-9", "20", "0")),
// 			new ParsedExpressions(
// 				List.of("+", "-", "*", "/", "*", "-"),
// 				List.of("4", "5", "-9", "20", "0", "-4"))
// 		);
// 	}
//
// 	private static Stream<ParsedExpressions> getInvalidParsedExpressions() {
// 		return Stream.of(
// 			new ParsedExpressions(
// 				List.of("+"),
// 				List.of("3", "4", "5")),
// 			new ParsedExpressions(
// 				List.of("+"),
// 				List.of("3", "4", "5", "6", "7", "8")),
// 			new ParsedExpressions(
// 				List.of("-", "+"),
// 				List.of("3", "4", "5", "-6")),
// 			new ParsedExpressions(
// 				List.of("-", "+", "*"),
// 				List.of("3", "4", "5", "-6", "0")),
// 			new ParsedExpressions(
// 				List.of("-", "+", "*", "/"),
// 				List.of("3", "4", "5", "-6", "3", "4")),
// 			new ParsedExpressions(
// 				List.of("-", "+", "*", "/"),
// 				List.of("3", "4", "5", "-6", "3", "4", "0", "-1", "-2", "-3"))
// 		);
// 	}
// }
