package ch01_baseball.number.generator;

import ch01_baseball.number.BaseBallNumbers;

public interface BaseballNumberGenerator {

	int NUMBER_LOWER_BOUND_INCLUSIVE = 1;
	int NUMBER_UPPER_BOUND_EXCLUSIVE = 10;
	int BASEBALL_NUMBER_GENERATION_LIMIT_SIZE = 3;

	BaseBallNumbers generateBaseBallNumbers();
}
