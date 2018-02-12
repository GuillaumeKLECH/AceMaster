package org.klechcorp.acemaster.score;

public class TieBreak extends ScoringElmt {

	private static final int TIE_WIN_DIFF = 1;
	private static final int TIE_WIN_THRESH = 6;


	protected TieBreak(ScoringElmtParent _parent) {
		super(_parent, TIE_WIN_THRESH, TIE_WIN_DIFF);
	}

	@Override
	String getScoreDescription() {
		return translateScore(score[0]) + "-" + translateScore(score[1]);
	}


	@Override
	String translateScore(int _score) {
		return Integer.toString(_score);
	}


	@Override
	void player1Scored() {
		playerScored(0);
	}


	@Override
	void player2Scored() {
		playerScored(1);
	}
	
	private void playerScored(int _ind) {
		++score[_ind];
		manageElmt();
	}

}
