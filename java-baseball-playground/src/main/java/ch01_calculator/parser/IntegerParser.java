package ch01_calculator.parser;

public class IntegerParser implements NumberParser<Integer> {

	@Override
	public Integer parseNumber(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("[ERROR] 정수 형태의 문자만 변환이 가능합니다.");
		}
	}
}
