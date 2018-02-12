package org.klechcorp.acemaster.score;

public class Set extends ScoringElmt implements ScoringElmtParent {

	private static final int SET_WIN_DIFF = 2;
	private static final int SET_WIN_THRESH = 5;
	
	private final Game game = new Game(this);
	private final TieBreak tb = new TieBreak(this);
	private boolean tieBreakMode = false;
	
	
	protected Set(ScoringElmtParent _parent) {
		super(_parent, SET_WIN_THRESH, SET_WIN_DIFF);
	}

	@Override
	public void player1Won() { // player 1 won the game
		playerWon(0);
	}

	@Override
	public void player2Won() { // player 2 won the game
		playerWon(1);
	}
	
	private void playerWon(int _ind) {
		++score[_ind];
		game.reset();
		tb.reset();
		manageElmt();
	}

	@Override
	void reset() {
		tieBreakMode = false;
		super.reset();
	}

	@Override
	void onPossibleWin() {
		int scoreDiff = score[0] - score[1];
		if(Math.abs(scoreDiff) >= 2 || score[1] == 7 || score[0] == 7) {
			if(scoreDiff > 0) {
				this.parent.player1Won();
			} else {
				this.parent.player2Won();
			}
			this.ended = true;
		} else if (scoreDiff == 0 && score[0] == 6) {
			tieBreakMode = true;
		}
	}

	@Override
	String getScoreDescription() {
		return translateScore(score[0]) + "/" + translateScore(score[1]) + getGameScore();
	}

	private String getGameScore() {
		if(this.ended) {
			return "";
		} else {
			return " : " + (tieBreakMode ? tb.getScoreDescription() : game.getScoreDescription());
		}
	}

	@Override
	String translateScore(int _score) {
		return Integer.toString(_score);
	}

	@Override
	void player1Scored() {
		if(this.tieBreakMode) {
			tb.player1Scored();
		} else {
			game.player1Scored();
		}
	}

	@Override
	void player2Scored() {
		if(this.tieBreakMode) {
			tb.player2Scored();
		} else {
			game.player2Scored();
		}
	}

}
