package ch01_baseball.handler;

public class ConsoleWriterExceptionHandler implements ExceptionHandler {

	@Override
	public void printMessage(final RuntimeException e) {
		System.out.println(e.getMessage());
	}
}
