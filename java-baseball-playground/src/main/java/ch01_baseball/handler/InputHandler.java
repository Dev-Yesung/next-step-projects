package ch01_baseball.handler;

/**
 * 애플리케이션으로의 입력을 책임지는 인터페이스
 */
public interface InputHandler {

	String read();

	default void terminate() {
	}
}
