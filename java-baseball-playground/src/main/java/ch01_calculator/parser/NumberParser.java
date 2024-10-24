package ch01_calculator.parser;

public interface NumberParser<T extends Number> {

	T parseNumber(String number);
}
