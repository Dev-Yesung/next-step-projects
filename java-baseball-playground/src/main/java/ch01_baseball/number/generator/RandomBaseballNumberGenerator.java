package ch01_baseball.number.generator;

import java.util.random.RandomGenerator;

import ch01_baseball.number.BaseBallNumber;
import ch01_baseball.number.BaseBallNumbers;

public class RandomBaseballNumberGenerator implements BaseballNumberGenerator {

	private final RandomGenerator randomGenerator;

	public RandomBaseballNumberGenerator(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	@Override
	public BaseBallNumbers generateBaseBallNumbers() {
		final BaseBallNumbers baseBallNumbers = new BaseBallNumbers();
		while (baseBallNumbers.getSize() < BASEBALL_NUMBER_GENERATION_LIMIT_SIZE) {
			final int randomNumber = randomGenerator.nextInt(
				NUMBER_LOWER_BOUND_INCLUSIVE, NUMBER_UPPER_BOUND_EXCLUSIVE);
			baseBallNumbers.add(new BaseBallNumber(randomNumber));
		}

		return baseBallNumbers;
	}
}
