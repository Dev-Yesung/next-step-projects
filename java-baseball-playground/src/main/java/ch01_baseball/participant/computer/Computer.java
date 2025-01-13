package ch01_baseball.participant.computer;

import ch01_baseball.number.BaseBallNumbers;
import ch01_baseball.participant.AutoNumberGenerator;
import ch01_baseball.participant.Initializer;

public interface Computer extends Initializer, AutoNumberGenerator {

	void generateBaseBallNumbers();

	BaseBallNumbers getBaseBallNumbers();
}
