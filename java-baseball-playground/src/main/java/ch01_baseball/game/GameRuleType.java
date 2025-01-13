package ch01_baseball.game;

import java.util.List;

import ch01_baseball.number.BaseBallNumber;
import ch01_baseball.number.BaseBallNumbers;
import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.player.Player;

public enum GameRuleType implements GameResultEvaluator {

	NORMAL {

		private static final int STRIKE_COUNT_LIMIT = 3;
		private static final int BALL_COUNT_LIMIT = 0;

		@Override
		public int judgeStrikeCount(final BaseBallNumbers o1, final BaseBallNumbers o2) {
			final List<BaseBallNumber> o1BaseBallNumbers = o1.getBaseBallNumbers();
			final List<BaseBallNumber> o2BaseBallNumbers = o2.getBaseBallNumbers();
			int strikeCount = 0;
			for (var o1BaseBallNumber : o1BaseBallNumbers) {
				if (o2BaseBallNumbers.contains(o1BaseBallNumber)) {
					strikeCount++;
				}
			}

			return strikeCount;
		}

		@Override
		public int judgeBallCount(final BaseBallNumbers o1, final BaseBallNumbers o2) {
			final List<BaseBallNumber> o1BaseBallNumbers = o1.getBaseBallNumbers();
			final List<BaseBallNumber> o2BaseBallNumbers = o2.getBaseBallNumbers();
			int ballCount = 0;
			for (var o1BaseBallNumber : o1BaseBallNumbers) {
				if (!o2BaseBallNumbers.contains(o1BaseBallNumber)) {
					ballCount++;
				}
			}

			return ballCount;
		}

		@Override
		public boolean isFinish(final int strikeCount, final int ballCount) {
			return strikeCount == STRIKE_COUNT_LIMIT && ballCount == BALL_COUNT_LIMIT;
		}
	};

	public GameResult judge(
		final Player player,
		final Computer computer
	) {
		final BaseBallNumbers playerBaseBallNumbers = player.getBaseBallNumbers();
		final BaseBallNumbers computerBaseBallNumbers = computer.getBaseBallNumbers();
		final int strikeCount = judgeStrikeCount(playerBaseBallNumbers, computerBaseBallNumbers);
		final int ballCount = judgeBallCount(playerBaseBallNumbers, computerBaseBallNumbers);
		final boolean isFinish = isFinish(strikeCount, ballCount);

		return new GameResult(strikeCount, ballCount, isFinish);
	}
}
