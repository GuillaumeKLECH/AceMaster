package org.klechcorp.acemaster.score;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.klechcorp.acemaster.Helper;

public class ScoreManagerTest {
	
	private static final String ONE_GAME = "1/0 | 0/0 | 0/0 | 0/0 | 0/0";
	private static final String ONE_GAME2 = "0/1 | 0/0 | 0/0 | 0/0 | 0/0";
	private static final String ONE_SET = "6/0 | 1/0 | 0/0 | 0/0 | 0/0";
	private static final String ONE_SET_TIE = "7/6 | 0/0 | 0/0 | 0/0 | 0/0";
	private static final String ONE_SET_TIE_2 = "6/7 | 0/0 | 0/0 | 0/0 | 0/0";
	private static final String ONE_SET2 = "0/6 | 0/1 | 0/0 | 0/0 | 0/0";
	
	protected ScoreManager scoreMan;
	
	@BeforeEach
	public void setUp() {
		scoreMan = new ScoreManager();
	}
	
	@Test
	public void canScorePoint() {
		scoreMan.playerOneScored();
		assertEquals("15-0", scoreMan.getCurrentGameScore());
	}
	
	@Test
	void canStoreTwoPoints() throws Exception {
		Helper.actRepeater(2, () -> {scoreMan.playerOneScored();});
		assertEquals("30-0", scoreMan.getCurrentGameScore());
	}
	
	@Test
	public void canScorePoint2() {
		scoreMan.playerTwoScored();
		assertEquals("0-15", scoreMan.getCurrentGameScore());
	}
	
	@Test
	public void canScoreAdv() {
		p1Adv();
		assertEquals("Adv-40", scoreMan.getCurrentGameScore());
	}
	
	@Test
	void canScoreAGame() throws Exception {
		p1WinGame();
		assertEquals(ONE_GAME, scoreMan.getSetResult());
	}

	@Test
	void canScoreAGameAfterAdvantage() {
		p1Adv();
		scoreMan.playerOneScored();
		assertEquals(ONE_GAME, scoreMan.getSetResult());
	}
	
	@Test
	void canScoreAGame2() throws Exception {
		p2WinGame();
		assertEquals(ONE_GAME2, scoreMan.getSetResult());
	}

	
	@Test
	void canWinSet() throws Exception {
		p1WinSet();
		p1WinGame();
		assertEquals(scoreMan.getSetResult(), ONE_SET);
	}
	
	@Test
	void canWinSet2() throws Exception {
		p2WinSet();
		p2WinGame();
		assertEquals(scoreMan.getSetResult(), ONE_SET2);
	}
	
	@Test
	void canTieBreak() throws Exception {
		Helper.actRepeater(6, () -> {p1WinGame(); p2WinGame();});
		Helper.actRepeater(6, () -> {scoreMan.playerOneScored();});
		assertEquals(scoreMan.getCurrentGameScore(), "6-0");
	}
	
	@Test
	void canWinTieBreak() throws Exception {
		Helper.actRepeater(6, () -> {p1WinGame(); p2WinGame();});
		Helper.actRepeater(7, () -> {scoreMan.playerOneScored();});
		assertEquals(scoreMan.getSetResult(), ONE_SET_TIE);
	}
	
	@Test
	void canWinTieBreak2() throws Exception {
		Helper.actRepeater(6, () -> {p1WinGame(); p2WinGame();});
		Helper.actRepeater(7, () -> {scoreMan.playerTwoScored();});
		assertEquals(scoreMan.getSetResult(), ONE_SET_TIE_2);
	}
	
	@Test
	void canWinMatch() throws Exception {
		Helper.actRepeater(3, () -> {p1WinSet(); });
		assertEquals(scoreMan.matchWon(), true);
		assertEquals(scoreMan.whoWon(), 0);
	}
	
	@Test
	void canWinMatch2() throws Exception {
		Helper.actRepeater(3, () -> {p2WinSet(); });
		assertEquals(scoreMan.matchWon(), true);
		assertEquals(scoreMan.whoWon(), 1);
	}
	
	@Test
	void canWinMatchIn5Set() {
		Helper.actRepeater(2, () -> {p1WinSet();p2WinSet();});
		p1WinSet();
		assertEquals(scoreMan.matchWon(), true);
		assertEquals(scoreMan.whoWon(), 0);
	}
	
	@Test
	void matchNotWonAfter3Set() throws Exception {
		Helper.actRepeater(2, () -> {p1WinSet();p2WinSet();});
		assertEquals(scoreMan.matchWon(), false);
		assertEquals(scoreMan.whoWon(), -1);
	}
	
	private void p1WinSet() {
		Helper.actRepeater(6, () -> {p1WinGame();});
	}
	
	private void p2WinSet() {
		Helper.actRepeater(6, () -> {p2WinGame();});
	}
	
	private void p1Adv() {
		Helper.actRepeater(3, () -> {scoreMan.playerOneScored();scoreMan.playerTwoScored();});
		scoreMan.playerOneScored();
	}
	
	private void p1WinGame() {
		Helper.actRepeater(4, () -> {scoreMan.playerOneScored();});
	}
	
	private void p2WinGame() {
		Helper.actRepeater(4, () -> { scoreMan.playerTwoScored(); });
	}

}
