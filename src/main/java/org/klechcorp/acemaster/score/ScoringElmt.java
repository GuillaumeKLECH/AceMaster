package org.klechcorp.acemaster.score;

import java.util.Arrays;

abstract class ScoringElmt {
	protected int[] score = new int[] {0,0};

	private final int winThreshold;
	private final int winDiff;
	
	protected final ScoringElmtParent parent;
	protected boolean ended = false;
	
	protected ScoringElmt(ScoringElmtParent _parent, int _winThresh, int _winDiff) {
		this.winThreshold = _winThresh;
		this.winDiff = _winDiff;
		this.parent = _parent;
	}
	
	public boolean ended() {
		return this.ended;
	}
	
	void reset() {
		Arrays.fill(this.score, 0);
	}
	
	abstract String translateScore(int _score);
	
	void manageElmt() {
		if(score[0] > this.winThreshold || score[1] > this.winThreshold) { // Possible win
			onPossibleWin();
		}
	}
	
	void onPossibleWin() {
		int scoreDiff = score[0] - score[1];
		if(Math.abs(scoreDiff) >= this.winDiff) { // we have a win
			if(scoreDiff > 0) { // Player One win
				this.parent.player1Won();
			} else { // Player Two win
				this.parent.player2Won();
			}
			this.ended = true;
		}
	}
	
	public abstract void player1Scored();
	public abstract void player2Scored();
	
	public abstract String getScoreDescription();

}
