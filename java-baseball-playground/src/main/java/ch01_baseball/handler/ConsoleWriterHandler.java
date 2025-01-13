package ch01_baseball.handler;

public class ConsoleWriterHandler implements OutputHandler {

	@Override
	public void printMessage(String message) {
		System.out.println(message);
	}
}
