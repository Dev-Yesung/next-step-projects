package ch01_baseball.participant.computer;

import java.util.random.RandomGenerator;

import ch01_baseball.number.BaseBallNumber;
import ch01_baseball.number.BaseBallNumbers;
import ch01_baseball.number.DefaultBaseBallNumber;

public class DefaultComputer implements Computer {

	private final RandomGenerator randomGenerator;
	private final BaseBallNumbers baseBallNumbers = new BaseBallNumbers();

	public DefaultComputer(final RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	@Override
	public void initialize() {
		baseBallNumbers.clear();
	}

	@Override
	public void generateBaseBallNumbers() {
		while (!baseBallNumbers.isValidSize()) {
			final int number = generate();
			final int nextIndex = baseBallNumbers.getNextIndex();
			final BaseBallNumber baseBallNumber = new DefaultBaseBallNumber(number, nextIndex);
			baseBallNumbers.addIfNotAlreadyExist(baseBallNumber);
		}
	}

	@Override
	public BaseBallNumbers getBaseBallNumbers() {
		return baseBallNumbers;
	}

	@Override
	public int generate() {
		return randomGenerator.nextInt(BaseBallNumber.LOWER_BOUND_INCLUSIVE,
			BaseBallNumber.UPPER_BOUND_EXCLUSIVE);
	}
}
