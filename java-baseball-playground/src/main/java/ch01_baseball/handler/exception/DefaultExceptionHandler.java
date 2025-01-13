package ch01_baseball.handler.exception;

public class DefaultExceptionHandler implements ExceptionHandler {

	@Override
	public void handle(final RuntimeException e) {
		System.out.println(e.getMessage());
	}
}
