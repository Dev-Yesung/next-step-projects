package ch01_baseball;

import java.util.Random;
import java.util.Scanner;

import ch01_baseball.game.BaseBallGame;
import ch01_baseball.game.BaseBallGameProxy;
import ch01_baseball.game.DefaultBaseBallGame;
import ch01_baseball.handler.ConsoleReaderHandler;
import ch01_baseball.handler.ConsoleWriterHandler;
import ch01_baseball.handler.InputHandler;
import ch01_baseball.handler.OutputHandler;
import ch01_baseball.handler.exception.DefaultExceptionHandler;
import ch01_baseball.handler.exception.ExceptionHandler;
import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.computer.DefaultComputer;
import ch01_baseball.participant.player.DefaultPlayer;
import ch01_baseball.participant.player.Player;

/**
 * 숫자야구게임 실행(필요한 객체를 생성)과 종료하는 책임
 */
public final class BaseBallApplication {

	public static void main(String[] args) {
		final InputHandler inputHandler = new ConsoleReaderHandler(new Scanner(System.in));
		final OutputHandler outputHandler = new ConsoleWriterHandler();
		final ExceptionHandler exceptionHandler = new DefaultExceptionHandler();
		final BaseBallGame baseBallGame = new BaseBallGameProxy(inputHandler, outputHandler, exceptionHandler,
			new DefaultBaseBallGame(inputHandler, outputHandler));

		final Player player = new DefaultPlayer();
		final Computer computer = new DefaultComputer(new Random(System.currentTimeMillis()));

		new BaseBallApplication().run(baseBallGame, player, computer);
	}

	private void run(
		final BaseBallGame baseBallGame,
		final Player player,
		final Computer computer
	) {
		baseBallGame.startUp();
		baseBallGame.selectMode();
		do {
			baseBallGame.play(player, computer);
		} while (!baseBallGame.isGameEnd());
		baseBallGame.terminate();
	}
}
