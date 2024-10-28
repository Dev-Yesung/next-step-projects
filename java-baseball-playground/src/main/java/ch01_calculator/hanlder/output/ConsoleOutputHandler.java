package ch01_calculator.hanlder.output;

public class ConsoleOutputHandler implements OutputHandler {

	@Override
	public void displayMessage(String message) {
		System.out.printf("%s%n", message);
	}

	@Override
	public void displayOutput(String expression, String output) {
		System.out.printf("Output : %s = %s%n", expression, output);
	}

	@Override
	public void displayError(String errorMessage) {
		System.out.printf("[ERROR] %s%n", errorMessage);
	}
}
