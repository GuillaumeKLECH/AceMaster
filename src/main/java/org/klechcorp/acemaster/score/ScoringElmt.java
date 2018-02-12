package org.klechcorp.acemaster.score;

abstract class ScoringElmt {
	protected int p1Score = 0;
	protected int p2Score = 0;
	
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
		p1Score = p2Score = 0;
	}
	
	abstract String translateScore(int _score);
	
	void manageElmt() {
		if(p1Score > this.winThreshold || p2Score > this.winThreshold) { // Possible win
			onPossibleWin();
		}
	}
	
	void onPossibleWin() {
		int scoreDiff = p1Score - p2Score;
		if(Math.abs(scoreDiff) >= this.winDiff) { // we have a win
			if(scoreDiff > 0) { // Player One win
				this.parent.player1Won();
			} else { // Player Two win
				this.parent.player2Won();
			}
			this.ended = true;
		}
	}
	
	abstract void player1Scored();
	abstract void player2Scored();
	
	abstract String getScoreDescription();

}
