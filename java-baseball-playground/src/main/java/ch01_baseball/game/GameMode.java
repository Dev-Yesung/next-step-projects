package ch01_baseball.game;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import ch01_baseball.message.ExceptionMessage;
import ch01_baseball.message.GameMessage;

/**
 * 어떤 모드의 게임을 진행할 것인지에 관한 책임을 가짐
 */
public enum GameMode {

	/**
	 * 일반적인 사용자 솔로 플레이
	 */
	SOLO_COMMON(
		"1",
		GameMessage.SOLO_COMMON
	),

	/**
	 * 사용자 vs 컴퓨터 > 대결
	 */
	SOLO_BATTLE(
		"2",
		GameMessage.SOLO_BATTLE
	),

	INVALID(
		"INVALID_MODE_SELECTION",
		ExceptionMessage.INVALID_MODE_SELECTION
	);

	private static final Map<String, GameMode> valueMap = Arrays.stream(values())
		.filter(it -> it != INVALID)
		.collect(Collectors.toUnmodifiableMap(GameMode::getKey, it -> it));
	private final String key;
	private final String message;

	GameMode(final String key, final String message) {
		this.key = key;
		this.message = message;
	}

	public static GameMode resolve(final String value) {
		final GameMode gameMode = valueMap.getOrDefault(value, INVALID);
		if (gameMode == INVALID) {
			throw new RuntimeException(gameMode.getMessage());
		}

		return gameMode;
	}

	public String getMessage() {
		return message;
	}

	private String getKey() {
		return key;
	}
}
