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
		return translateScore(p1Score) + "-" + translateScore(p2Score);	
	}


	@Override
	void player1Scored() {
		if(p2Score == 4) {
			p2Score = 3;
			p1Score = 3;
		} else {
			p1Score += 1;
		}
		manageElmt();
	}


	@Override
	void player2Scored() {
		if(p1Score == 4) {
			p2Score = 3;
			p1Score = 3;
		} else {
			p2Score += 1;
		}
		manageElmt();
		
	}


	

}
