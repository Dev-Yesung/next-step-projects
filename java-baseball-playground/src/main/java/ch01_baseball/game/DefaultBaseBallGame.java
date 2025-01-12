package ch01_baseball.game;

import ch01_baseball.handler.ExceptionHandler;
import ch01_baseball.handler.InputHandler;
import ch01_baseball.handler.OutputHandler;
import ch01_baseball.handler.message.GameMessage;
import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.judgement.Judgement;
import ch01_baseball.participant.judgement.JudgementResult;
import ch01_baseball.participant.player.Player;

public class DefaultBaseBallGame extends BaseBallGame {

	public DefaultBaseBallGame(
		final InputHandler inputHandler,
		final OutputHandler outputHandler,
		final ExceptionHandler exceptionHandler
	) {
		super(inputHandler, outputHandler, exceptionHandler);
	}

	@Override
	public void play(
		final Judgement judgement,
		final Player player,
		final Computer computer
	) {
		try {
			while (true) {
				computer.generateBaseBallNumbers();

				outputHandler.printMessage(GameMessage.PLAYER_NUMBERS_INPUT);
				player.generateBaseBallNumbers(inputHandler.read());

				final JudgementResult result = judgement.judge(player, computer);
				outputHandler.printJudgementResult(result);
				if (result.isCorrect()) {
					return;
				}
			}
		} catch (RuntimeException e) {
			exceptionHandler.printMessage(e);
		}
	}
}
