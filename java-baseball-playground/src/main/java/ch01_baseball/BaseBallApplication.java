package ch01_baseball;

import java.util.Scanner;

import ch01_baseball.game.BaseBallGame;
import ch01_baseball.game.DefaultBaseBallGame;
import ch01_baseball.handler.ConsoleReaderHandler;
import ch01_baseball.handler.ConsoleWriterExceptionHandler;
import ch01_baseball.handler.ConsoleWriterHandler;
import ch01_baseball.handler.ExceptionHandler;
import ch01_baseball.handler.InputHandler;
import ch01_baseball.handler.OutputHandler;
import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.computer.DefaultComputer;
import ch01_baseball.participant.judgement.DefaultJudgement;
import ch01_baseball.participant.judgement.Judgement;
import ch01_baseball.participant.player.DefaultPlayer;
import ch01_baseball.participant.player.Player;

/**
 * 숫자야구게임 실행(필요한 객체를 생성)과 종료하는 책임
 */
public final class BaseBallApplication {

	public static void main(String[] args) {
		final InputHandler inputHandler = new ConsoleReaderHandler(new Scanner(System.in));
		final OutputHandler outputHandler = new ConsoleWriterHandler();
		final ExceptionHandler exceptionHandler = new ConsoleWriterExceptionHandler();
		final BaseBallGame baseBallGame = new DefaultBaseBallGame(inputHandler, outputHandler, exceptionHandler);
		final Judgement judgement = new DefaultJudgement();
		final Player player = new DefaultPlayer();
		final Computer computer = new DefaultComputer();

		new BaseBallApplication().run(baseBallGame, judgement, player, computer);
	}

	private void run(
		final BaseBallGame baseBallGame,
		final Judgement judgement,
		final Player player,
		final Computer computer
	) {
		while (true) {
			baseBallGame.initialize();
			judgement.initialize();
			player.initialize();
			computer.initialize();

			if (baseBallGame.isGameEnd()) {
				baseBallGame.terminate();
				break;
			}
			baseBallGame.play(judgement, player, computer);
		}
	}
}
