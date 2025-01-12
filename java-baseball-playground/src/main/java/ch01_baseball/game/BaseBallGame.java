package ch01_baseball.game;

import ch01_baseball.handler.ExceptionHandler;
import ch01_baseball.handler.InputHandler;
import ch01_baseball.handler.OutputHandler;
import ch01_baseball.handler.message.GameMessage;
import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.judgement.Judgement;
import ch01_baseball.participant.player.Player;

/**
 * 게임 진행에 관한 책임
 */
public abstract class BaseBallGame {

	protected final InputHandler inputHandler;
	protected final OutputHandler outputHandler;
	protected final ExceptionHandler exceptionHandler;
	protected GameMode gameMode;

	public BaseBallGame(
		final InputHandler inputHandler,
		final OutputHandler outputHandler,
		final ExceptionHandler exceptionHandler
	) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.exceptionHandler = exceptionHandler;
	}

	public void initialize() {
		outputHandler.printMessage(GameMessage.WELCOME);
		outputHandler.printMessage(GameMessage.MODE_SELECTION);
		this.gameMode = GameMode.resolve(inputHandler.read());
	}

	/**
	 * 게임 진행에 관한 책임
	 */
	public abstract void play(Judgement judgement, Player user, Computer computer);

	public boolean isGameEnd() {
		return gameMode == GameMode.END;
	}

	public void terminate() {
		outputHandler.printMessage(gameMode.getMessage());
		inputHandler.terminate();
		outputHandler.terminate();
	}
}
