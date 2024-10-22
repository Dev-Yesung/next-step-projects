package ch01_calculator.hanlder;

public interface OutputHandler {

	void displayOutput(String expression, String output);

	void displayError(String errorMessage);
}
