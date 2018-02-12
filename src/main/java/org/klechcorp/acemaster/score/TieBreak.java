package org.klechcorp.acemaster.score;

public class TieBreak extends ScoringElmt {

	private static final int TIE_WIN_DIFF = 1;
	private static final int TIE_WIN_THRESH = 6;


	protected TieBreak(ScoringElmtParent _parent) {
		super(_parent, TIE_WIN_THRESH, TIE_WIN_DIFF);
	}

	@Override
	String getScoreDescription() {
		return translateScore(p1Score) + "-" + translateScore(p2Score);
	}


	@Override
	String translateScore(int _score) {
		return Integer.toString(_score);
	}


	@Override
	void player1Scored() {
		++p1Score;
		manageElmt();
	}


	@Override
	void player2Scored() {
		++p2Score;
		manageElmt();
	}

}
