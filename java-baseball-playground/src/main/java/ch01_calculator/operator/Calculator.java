package ch01_calculator.operator;

public interface Calculator<T> {

	T calculate(T leftOperand, T rightOperand);
}
