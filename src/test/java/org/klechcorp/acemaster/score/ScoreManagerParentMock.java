package org.klechcorp.acemaster.score;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreManagerParentMock implements ScoringElmtParent {

	private boolean p1Won = false;
	private boolean p2Won = false;
	
	@Override
	public void player1Won() {
		p1Won = true;
		
	}

	@Override
	public void player2Won() {
		p2Won = true;
	}
	
	public void reset() {
		p1Won = false;
		p2Won = false;
	}
	
	public void assertP1Won() {
		assertTrue(p1Won);
	}
	public void assertP1DidNotWin() {
		assertFalse(p1Won);
	}
	
	public void assertP2Won() {
		assertTrue(p2Won);
	}
	
	public void assertP2DidNotWin() {
		assertFalse(p2Won);
	}
}
