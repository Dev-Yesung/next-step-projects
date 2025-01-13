package ch01_baseball.participant.player;

import ch01_baseball.number.BaseBallNumbers;
import ch01_baseball.participant.Initializer;

public interface Player extends Initializer {

	void generateBaseBallNumbers(String value);

	BaseBallNumbers getBaseBallNumbers();
}
