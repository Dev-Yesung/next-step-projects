package ch01_baseball.handler;

import java.util.Scanner;

public class ConsoleReaderHandler implements InputHandler {

	private final Scanner scanner;

	public ConsoleReaderHandler(final Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public String read() {
		return scanner.nextLine();
	}

	@Override
	public void terminate() {
		scanner.close();
	}
}
