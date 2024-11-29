package ch01_calculator.processor;

public interface OutputHandler {

	void displayMessage(String message);

	void displayOutput(String expression, String output);

	void displayError(String errorMessage);
}
