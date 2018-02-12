package org.klechcorp.acemaster.score;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.klechcorp.acemaster.Helper;

public class ScoreManagerTest {
	
	private static final String ONE_GAME = "1/0 : 0-0";
	private static final String ONE_GAME2 = "0/1 : 0-0";
	private static final String ONE_SET = "6/0 | 1/0 : 0-0";
	private static final String ONE_SET_TIE = "7/6 | 0/0 : 0-0";
	private static final String ONE_SET_TIE_2 = "6/7 | 0/0 : 0-0";
	private static final String ONE_SET2 = "0/6 | 0/1 : 0-0";
	
	protected ScoreManager scoreMan;
	protected ScoreManagerParentMock parMock;
	
	@BeforeEach
	public void setUp() {
		parMock = new ScoreManagerParentMock();
		scoreMan = new ScoreManager(parMock);
	}
	
	@Test
	public void canScorePoint() {
		scoreMan.player1Scored();
		assertTrue(scoreMan.getScoreDescription().contains("15-0"));
	}
	
	@Test
	void canStoreTwoPoints() throws Exception {
		Helper.actRepeater(2, () -> {scoreMan.player1Scored();});
		assertTrue(scoreMan.getScoreDescription().contains("30-0"));
	}
	
	@Test
	public void canScorePoint2() {
		scoreMan.player2Scored();
		assertTrue(scoreMan.getScoreDescription().contains("0-15"));
	}
	
	@Test
	public void canScoreAdv() {
		p1Adv();
		assertTrue(scoreMan.getScoreDescription().contains("Adv-40"));
	}
	
	@Test
	public void canResetAdv() {
		p1Adv();
		scoreMan.player2Scored();
		assertTrue(scoreMan.getScoreDescription().contains("40-40"));
	}
	
	@Test
	void canScoreAdv2() throws Exception {
		p2Adv();
		assertTrue(scoreMan.getScoreDescription().contains("40-Adv"));
		
	}
	
	@Test
	void canScoreAGame() throws Exception {
		p1WinGame();
		assertEquals(ONE_GAME, scoreMan.getScoreDescription());
	}

	@Test
	void canScoreAGameAfterAdvantage() {
		p1Adv();
		scoreMan.player1Scored();
		assertEquals(ONE_GAME, scoreMan.getScoreDescription());
	}
	
	@Test
	void canScoreAGame2() throws Exception {
		p2WinGame();
		assertEquals(ONE_GAME2, scoreMan.getScoreDescription());
	}

	
	@Test
	void canWinSet() throws Exception {
		p1WinSet();
		p1WinGame();
		assertEquals(ONE_SET, scoreMan.getScoreDescription());
	}
	
	@Test
	void canWinSet2() throws Exception {
		p2WinSet();
		p2WinGame();
		assertEquals(ONE_SET2, scoreMan.getScoreDescription());
	}
	
	@Test
	void canTieBreak() throws Exception {
		Helper.actRepeater(6, () -> {p1WinGame(); p2WinGame();});
		Helper.actRepeater(6, () -> {scoreMan.player1Scored();});
		assertTrue(scoreMan.getScoreDescription().contains("6-0"));
	}
	
	@Test
	void canWinTieBreak() throws Exception {
		Helper.actRepeater(6, () -> {p1WinGame(); p2WinGame();});
		Helper.actRepeater(7, () -> {scoreMan.player1Scored();});
		assertEquals(ONE_SET_TIE, scoreMan.getScoreDescription());
	}
	
	@Test
	void canWinTieBreakTight() throws Exception {
		Helper.actRepeater(6, () -> {p1WinGame(); p2WinGame();});
		Helper.actRepeater(6, () -> {scoreMan.player1Scored(); scoreMan.player2Scored();});
		scoreMan.player1Scored();
		assertEquals(ONE_SET_TIE, scoreMan.getScoreDescription());
	}
	
	@Test
	void canWinTieBreak2() throws Exception {
		Helper.actRepeater(6, () -> {p1WinGame(); p2WinGame();});
		Helper.actRepeater(7, () -> {scoreMan.player2Scored();});
		assertEquals(ONE_SET_TIE_2, scoreMan.getScoreDescription());
	}
	
	@Test
	void canWinMatch() throws Exception {
		Helper.actRepeater(3, () -> {p1WinSet(); });
		assertEquals(scoreMan.ended(), true);
		parMock.assertP1Won();
	}
	
	@Test
	void canWinMatch2() throws Exception {
		Helper.actRepeater(3, () -> {p2WinSet(); });
		assertEquals(scoreMan.ended(), true);
		parMock.assertP2Won();
	}
	
	@Test
	void canWinMatchIn5Set() {
		Helper.actRepeater(2, () -> {p1WinSet();p2WinSet();});
		p1WinSet();
		assertEquals(scoreMan.ended(), true);
		parMock.assertP1Won();
	}
	
	@Test
	void matchNotWonAfter3Set() throws Exception {
		Helper.actRepeater(2, () -> {p1WinSet();p2WinSet();});
		assertEquals(scoreMan.ended(), false);
		parMock.assertP2DidNotWin();
		parMock.assertP1DidNotWin();
	}
	
	private void p1WinSet() {
		Helper.actRepeater(6, () -> {p1WinGame();});
	}
	
	private void p2WinSet() {
		Helper.actRepeater(6, () -> {p2WinGame();});
	}
	
	private void p1Adv() {
		Helper.actRepeater(3, () -> {scoreMan.player1Scored();scoreMan.player2Scored();});
		scoreMan.player1Scored();
	}
	
	private void p2Adv() {
		Helper.actRepeater(3, () -> {scoreMan.player1Scored();scoreMan.player2Scored();});
		scoreMan.player2Scored();
	}
	
	private void p1WinGame() {
		Helper.actRepeater(4, () -> {scoreMan.player1Scored();});
	}
	
	private void p2WinGame() {
		Helper.actRepeater(4, () -> { scoreMan.player2Scored(); });
	}

}
