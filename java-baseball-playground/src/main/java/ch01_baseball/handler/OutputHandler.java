package ch01_baseball.handler;

import ch01_baseball.participant.judgement.JudgementResult;

/**
 * 애플리케이션으로부터의 응답을 출력하는 책임지는 인터페이스
 */
public interface OutputHandler {

	void printMessage(String message);

	void printJudgementResult(JudgementResult judgementResult);

	default void terminate() {
	}
}
