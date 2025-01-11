package ch01_baseball.player;

import ch01_baseball.number.generator.BaseballNumberGenerator;

public class User extends Player {

	public User(final BaseballNumberGenerator baseballNumberGenerator) {
		super(baseballNumberGenerator);
	}

	@Override
	public void generateBaseBallNumbers() {
		this.baseBallNumbers = baseballNumberGenerator.generateBaseBallNumbers();
	}
}
