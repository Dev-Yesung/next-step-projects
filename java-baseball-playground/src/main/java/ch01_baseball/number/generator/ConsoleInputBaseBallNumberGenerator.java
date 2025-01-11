package ch01_baseball.number.generator;

import java.util.Objects;
import java.util.Scanner;

import ch01_baseball.number.BaseBallNumber;
import ch01_baseball.number.BaseBallNumbers;

public class ConsoleInputBaseBallNumberGenerator implements BaseballNumberGenerator {

	private final Scanner scanner;

	public ConsoleInputBaseBallNumberGenerator(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public BaseBallNumbers generateBaseBallNumbers() {
		while (true) {
			final String line = scanner.nextLine();
			if (Objects.isNull(line) || line.isBlank()) {
				System.out.println("[ERROR] 입력은 비어있을 수 없습니다.");
				continue;
			}

			final BaseBallNumbers baseBallNumbers = new BaseBallNumbers();
			line.trim().chars()
				.filter(Character::isDigit)
				.mapToObj(BaseBallNumber::new)
				.forEach(baseBallNumbers::add);
			if (baseBallNumbers.getSize() == BASEBALL_NUMBER_GENERATION_LIMIT_SIZE) {
				return baseBallNumbers;
			}

			System.out.printf("[ERROR] %s이상 %s미안의 정수만 입력해주세요.%n",
				NUMBER_LOWER_BOUND_INCLUSIVE, NUMBER_UPPER_BOUND_EXCLUSIVE);
		}
	}
}
