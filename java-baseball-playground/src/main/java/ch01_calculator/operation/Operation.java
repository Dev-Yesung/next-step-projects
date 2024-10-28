package ch01_calculator.operation;

public interface Operation<T extends Number, R extends Number> {

	R plus(T leftNumber, T rightNumber);

	R minus(T leftNumber, T rightNumber);

	R multiply(T leftNumber, T rightNumber);

	R divide(T leftNumber, T rightNumber);
}
