package ch01_baseball.participant.judgement;

import ch01_baseball.participant.computer.Computer;
import ch01_baseball.participant.player.Player;

public interface Judgement {

	void initialize();

	JudgementResult judge(Player user, Computer computer);
}
