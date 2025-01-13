package ch01_baseball.participant.player;

import ch01_baseball.number.BaseBallNumbers;
import ch01_baseball.number.DefaultBaseBallNumber;
import ch01_baseball.validator.StringValidator;

public class DefaultPlayer implements Player {

	private final BaseBallNumbers baseBallNumbers = new BaseBallNumbers();

	@Override
	public void initialize() {
		baseBallNumbers.clear();
	}

	@Override
	public void generateBaseBallNumbers(final String value) {
		if (StringValidator.isNullOrBlank(value)) {
			throw new RuntimeException("[ERROR] 숫자 값은 비어있을 수 없습니다.");
		}

		final char[] charArray = value.toCharArray();
		for (int idx = 0; idx < charArray.length; idx++) {
			final int number = Character.digit(charArray[idx], 10);
			baseBallNumbers.addIfNotAlreadyExist(new DefaultBaseBallNumber(idx, number));
		}
		if (!this.baseBallNumbers.isValidSize()) {
			throw new RuntimeException("[ERROR] ");
		}
	}

	@Override
	public BaseBallNumbers getBaseBallNumbers() {
		return baseBallNumbers;
	}
}
