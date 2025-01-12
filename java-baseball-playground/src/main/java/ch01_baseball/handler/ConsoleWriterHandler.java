package ch01_baseball.handler;

import ch01_baseball.participant.judgement.JudgementResult;

public class ConsoleWriterHandler implements OutputHandler {

	@Override
	public void printMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void printJudgementResult(JudgementResult judgementResult) {

	}
}
