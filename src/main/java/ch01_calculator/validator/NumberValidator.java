package ch01_calculator.validator;

public interface NumberValidator<T extends Number, R extends Number> {

	R add(T leftNumber, T rightNumber);
}
