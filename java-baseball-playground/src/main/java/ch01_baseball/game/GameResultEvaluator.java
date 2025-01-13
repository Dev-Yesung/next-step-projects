package ch01_baseball.game;

import ch01_baseball.number.BaseBallNumbers;

public interface GameResultEvaluator {

	int judgeStrikeCount(BaseBallNumbers o1, BaseBallNumbers o2);

	int judgeBallCount(BaseBallNumbers o1, BaseBallNumbers o2);

	boolean isFinish(int strikeCount, int ballCount);
}
