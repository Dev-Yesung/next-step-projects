package ch01_baseball.handler;

/**
 * 애플리케이션으로부터의 응답을 출력하는 책임지는 인터페이스
 */
public interface OutputHandler {

	void printMessage(String message);

	default void terminate() {
	}
}
