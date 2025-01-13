package ch01_baseball.game;

import ch01_baseball.message.GameMessage;

public class GameResult {

	private final int strikeCount;
	private final int ballCount;
	private final boolean isFinish;

	public GameResult(final int strikeCount, final int ballCount, final boolean isFinish) {
		this.strikeCount = strikeCount;
		this.ballCount = ballCount;
		this.isFinish = isFinish;
	}

	public String getResultMessage() {
		if (isFinish) {
			return GameMessage.FINISH_RESULT.formatted(strikeCount, ballCount);
		}
		return GameMessage.RESULT.formatted(strikeCount, ballCount);
	}

	public boolean isFinish() {
		return isFinish;
	}
}
