package ch01_baseball;

import java.util.Arrays;
import java.util.List;

import ch01_baseball.judgement.Judgement;
import ch01_baseball.player.Player;

public class BaseBallGame {

	private final Judgement judgement;
	private final List<Player> players;

	public BaseBallGame(final Judgement judgement, final Player... players) {
		this.judgement = judgement;
		this.players = Arrays.asList(players);
	}

	public void play() {

	}
}
