package ch01_baseball.game;

import ch01_baseball.handler.InputHandler;
import ch01_baseball.handler.OutputHandler;
import ch01_baseball.handler.exception.ExceptionHandler;
import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.player.Player;

public class BaseBallGameProxy extends BaseBallGame {

	private final ExceptionHandler exceptionHandler;
	private final BaseBallGame baseBallGame;

	public BaseBallGameProxy(
		final InputHandler inputHandler,
		final OutputHandler outputHandler,
		final ExceptionHandler exceptionHandler,
		final BaseBallGame baseBallGame
	) {
		super(inputHandler, outputHandler);
		this.exceptionHandler = exceptionHandler;
		this.baseBallGame = baseBallGame;
	}

	@Override
	public void play(final Player player, final Computer computer) {
		try {
			baseBallGame.play(player, computer);
		} catch (RuntimeException e) {
			exceptionHandler.handle(e);
		}
	}
}
