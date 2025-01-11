package ch01_baseball.player;

import ch01_baseball.number.BaseBallNumber;
import ch01_baseball.number.generator.BaseballNumberGenerator;

public class Computer extends Player {

	public Computer(final BaseballNumberGenerator baseballNumberGenerator) {
		super(baseballNumberGenerator);
	}

	@Override
	public void generateBaseBallNumbers() {
		baseBallNumbers.clear();
		while (baseBallNumbers.getSize() <= BASEBALL_NUMBER_GENERATION_LIMIT_SIZE) {
			final BaseBallNumber baseBallNumber = baseballNumberGenerator.generateBaseBallNumbers();
			baseBallNumbers.add(baseBallNumber);
		}
	}
}
