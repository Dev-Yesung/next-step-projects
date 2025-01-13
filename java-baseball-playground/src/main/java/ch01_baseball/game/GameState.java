package ch01_baseball.game;

import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.player.Player;

public enum GameState {

	/**
	 * 게임 시작 전 상태
	 */
	READY,

	/**
	 * 게임 플레이 중
	 */
	PLAYING,

	CONTINUOUS,

	/**
	 * 게임종료
	 */
	END,
	;

	public static GameState resolveState(final GameResult gameResult) {
		return gameResult.isFinish() ? END : CONTINUOUS;
	}

	public void initialize(final Player player, final Computer computer) {
		switch (this) {
			case PLAYING -> {
				player.initialize();
				computer.initialize();
			}
			case CONTINUOUS -> player.initialize();
		}
	}
}
