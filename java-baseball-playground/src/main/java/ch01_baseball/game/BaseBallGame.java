package ch01_baseball.game;

import ch01_baseball.handler.InputHandler;
import ch01_baseball.handler.OutputHandler;
import ch01_baseball.message.GameMessage;
import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.player.Player;

/**
 * 게임 진행에 관한 책임
 */
public abstract class BaseBallGame {

	protected final InputHandler inputHandler;
	protected final OutputHandler outputHandler;
	protected GameRuleType gameRuleType;
	protected GameState gameState;
	protected GameMode gameMode;

	public BaseBallGame(
		final InputHandler inputHandler,
		final OutputHandler outputHandler
	) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
	}

	public void startUp() {
		outputHandler.printMessage(GameMessage.WELCOME);
		this.gameState = GameState.READY;
		this.gameRuleType = GameRuleType.NORMAL;
	}

	public void selectMode() {
		outputHandler.printMessage(GameMessage.MODE_SELECTION);
		this.gameMode = GameMode.resolve(inputHandler.read());
	}

	public abstract void play(Player user, Computer computer);

	public boolean isGameEnd() {
		return gameState == GameState.END;
	}

	public void terminate() {
		outputHandler.printMessage(gameMode.getMessage());
		inputHandler.terminate();
		outputHandler.terminate();
	}
}
