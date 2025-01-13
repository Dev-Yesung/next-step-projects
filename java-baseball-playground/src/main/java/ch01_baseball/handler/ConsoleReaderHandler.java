package ch01_baseball.handler;

import java.util.Scanner;

import ch01_baseball.validator.StringValidator;

public class ConsoleReaderHandler implements InputHandler {

	private final Scanner scanner;

	public ConsoleReaderHandler(final Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public String read() {
		final String line = scanner.nextLine();
		if (StringValidator.isNullOrBlank(line)) {
			throw new RuntimeException("[ERROR] 입력 값은 비어있을 수 없습니다.");
		}

		return line;
	}

	@Override
	public void terminate() {
		scanner.close();
	}
}
