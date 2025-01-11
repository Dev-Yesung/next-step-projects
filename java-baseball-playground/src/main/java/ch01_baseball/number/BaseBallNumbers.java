package ch01_baseball.number;

import java.util.ArrayList;
import java.util.List;

public class BaseBallNumbers {

	private final List<BaseBallNumber> baseBallNumbers;

	public BaseBallNumbers() {
		this.baseBallNumbers = new ArrayList<>();
	}

	public void add(final BaseBallNumber baseBallNumber) {
		if (baseBallNumbers.contains(baseBallNumber)) {
			return;
		}
		baseBallNumbers.add(baseBallNumber);
	}

	public int getSize() {
		return baseBallNumbers.size();
	}

	public void clear() {
		baseBallNumbers.clear();
	}
}
