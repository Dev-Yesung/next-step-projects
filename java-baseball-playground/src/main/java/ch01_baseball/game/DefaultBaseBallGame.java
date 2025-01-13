package ch01_baseball.game;

import ch01_baseball.handler.InputHandler;
import ch01_baseball.handler.OutputHandler;
import ch01_baseball.message.GameMessage;
import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.player.Player;

public class DefaultBaseBallGame extends BaseBallGame {

	public DefaultBaseBallGame(
		final InputHandler inputHandler,
		final OutputHandler outputHandler
	) {
		super(inputHandler, outputHandler);
	}

	@Override
	public void play(
		final Player player,
		final Computer computer
	) {
		gameState = GameState.PLAYING;
		while (gameState == GameState.PLAYING || gameState == GameState.CONTINUOUS) {
			gameState.initialize(player, computer);

			computer.generateBaseBallNumbers();

			outputHandler.printMessage(GameMessage.PLAYER_NUMBERS_INPUT);
			player.generateBaseBallNumbers(inputHandler.read());

			final GameResult gameResult = gameRuleType.judge(player, computer);
			outputHandler.printMessage(gameResult.getResultMessage());
			gameState = GameState.resolveState(gameResult);
		}
	}
}
