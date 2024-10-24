package ch01_calculator.hanlder.input;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

	private final Scanner scanner;

	public ConsoleInputHandler() {
		this.scanner = new Scanner(System.in);
	}

	@Override
	public String readInput() {
		return scanner.nextLine();
	}
}
