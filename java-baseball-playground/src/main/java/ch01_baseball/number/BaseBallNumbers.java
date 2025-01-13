package ch01_baseball.number;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BaseBallNumbers {

	private static final int BASE_BALL_NUMBER_LIMIT_SIZE = 3;
	private final Set<BaseBallNumber> baseBallNumbers = new LinkedHashSet<>();

	public void addIfNotAlreadyExist(final BaseBallNumber baseBallNumber) {
		if (isValidSize()) {
			throw new RuntimeException("[ERROR] 숫자는 %s개 보다 많을 수 없습니다.".formatted(BASE_BALL_NUMBER_LIMIT_SIZE));
		}
		baseBallNumbers.add(baseBallNumber);
	}

	public boolean isValidSize() {
		return baseBallNumbers.size() == BASE_BALL_NUMBER_LIMIT_SIZE;
	}

	public void clear() {
		baseBallNumbers.clear();
	}

	public List<BaseBallNumber> getBaseBallNumbers() {
		return baseBallNumbers.stream()
			.toList();
	}

	public int getNextIndex() {
		return baseBallNumbers.size();
	}
}
