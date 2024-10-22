package ch01_calculator.parser;

public interface NumberParser<T> {

	T parseNumber(String number);
}
