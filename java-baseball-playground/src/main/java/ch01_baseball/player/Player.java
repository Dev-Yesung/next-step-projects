package ch01_baseball.player;

import ch01_baseball.number.BaseBallNumbers;
import ch01_baseball.number.generator.BaseballNumberGenerator;

public abstract class Player {

	protected final BaseballNumberGenerator baseballNumberGenerator;
	protected BaseBallNumbers baseBallNumbers;

	protected Player(BaseballNumberGenerator baseballNumberGenerator) {
		this.baseballNumberGenerator = baseballNumberGenerator;
		this.baseBallNumbers = new BaseBallNumbers();
	}

	public abstract void generateBaseBallNumbers();
}
