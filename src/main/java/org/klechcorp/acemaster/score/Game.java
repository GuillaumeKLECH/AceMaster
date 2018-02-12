package org.klechcorp.acemaster.score;

class Game extends ScoringElmt {

	private static final int GAME_WIN_DIFF = 2;
	private static final int GAME_WIN_THRESH = 3;
	
	public final static String ZERO = "0";
	public static final String FIFTEEN = "15";
	public static final String THIRTY = "30";
	public static final String FORTY = "40";
	public static final String ADV = "Adv";

	Game(ScoringElmtParent _parent) {
		super(_parent, GAME_WIN_THRESH, GAME_WIN_DIFF);
	}
	
	@Override
	String translateScore(int _score) {
		switch (_score) {
		case 0:
			return ZERO;
		case 1:
			return FIFTEEN;
		case 2:
			return THIRTY;
		case 3:
			return FORTY;
		case 4:
			return ADV;
		default:
			return null;
		}
	}

	@Override
	String getScoreDescription() {
		return translateScore(score[0]) + "-" + translateScore(score[1]);	
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
		int otherInd = (_ind == 0) ? 1 : 0;
		if(score[otherInd] == 4) { // adv reset
			score[_ind] = score[otherInd] = 3;
		} else {
			score[_ind] += 1;
		}
		manageElmt();
	}

}
