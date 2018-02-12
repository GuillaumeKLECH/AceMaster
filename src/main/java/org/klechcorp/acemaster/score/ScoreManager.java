package org.klechcorp.acemaster.score;

import java.util.StringJoiner;

public class ScoreManager {

	private static final int MAX_NBR_OF_SET = 5;
	public final static String ZERO = "0";
	public static final String FIFTEEN = "15";
	public static final String THIRTY = "30";
	public static final String FORTY = "40";
	public static final String ADV = "Adv";

	private int p1Score = 0;
	private int p2Score = 0;
	
	private boolean tieBreakMode = false;
	private boolean p1WonMatch = false;
	private boolean p2WonMatch = false;
	
	
	private int 		curSetNbr = 0;
	private int[] 	p1SetRes = new int[MAX_NBR_OF_SET];
	private int[] 	p2SetRes = new int[MAX_NBR_OF_SET];

	private void manageGame() {
		if(!tieBreakMode) {
			if(p1Score > 3 || p2Score > 3) { //possible game win
				int scoreDiff = p1Score - p2Score;
				if(Math.abs(scoreDiff) >= 2) { // we have a game win
					if(scoreDiff > 0) {
						p1SetRes[curSetNbr] += 1;
					} else {
						p2SetRes[curSetNbr] += 1;
					}
					p1Score = p2Score = 0;
					manageSets();
				}
			}
		} else {
			if(p1Score >= 7 || p2Score >= 7) {
				if(p1Score > p2Score) {
					p1SetRes[curSetNbr] += 1;
				} else {
					p2SetRes	[curSetNbr] += 1;
				}
				p1Score = p2Score = 0;
				manageSets();
			}
		}
	}

	private void manageSets() {
		if(p1SetRes[curSetNbr] > 5 || p2SetRes[curSetNbr] > 5) { //Possible Set win
			int scoreDiff = p1SetRes[curSetNbr] - p2SetRes[curSetNbr];
			if(Math.abs(scoreDiff) >= 2 || p1SetRes[curSetNbr] == 7 || p1SetRes[curSetNbr] == 7) {
				tieBreakMode = false;
				++curSetNbr;
				manageMatch();
			} else if (scoreDiff == 0 && p1SetRes[curSetNbr] == 6) {
				tieBreakMode = true;
			}
		}
	}
	
	private void manageMatch() {
		if(curSetNbr >= 3) { //Possible match won
			int nbSetForP1 = 0, nbSetForP2 = 0;
			for(int i = 0; i < p1SetRes.length; ++i) {
				if(p1SetRes[i] >= 6 && p1SetRes[i] > p2SetRes[i]) {
					if(++nbSetForP1 == 3) {
						p1WonMatch = true;
					}
				} else if (p2SetRes[i] >= 6 && p2SetRes[i] > p1SetRes[i]) {
					if(++nbSetForP2 == 3) {
						p2WonMatch = true;
					}
				}
			}
		}
	}

	public void playerOneScored() {
		if(!matchWon()) {
			p1Score += 1;
			manageGame();
		}
	}

	public void playerTwoScored() {
		if(!matchWon()) {
			p2Score += 1;
			manageGame();
		}
	}
	
	public boolean matchWon() {
		return p1WonMatch || p2WonMatch;
	}
	
	public int whoWon() {
		if(p1WonMatch) {
			return 0;
		} else if(p2WonMatch) {
			return 1;
		} else {
			return -1;
		}
	}

	public String getSetResult() {
		StringJoiner sj = new StringJoiner(" | ");
		for(int i = 0; i < MAX_NBR_OF_SET; ++i) {
			sj.add(p1SetRes[i] + "/" + p2SetRes[i]);
		}
		return sj.toString();
	}

	private String translateScore(int score) {
		if(!tieBreakMode) {
			switch (score) {
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
		} else {
			return Integer.toString(score);
		}
	}

	public String getCurrentGameScore() {
		return translateScore(p1Score) + "-" + translateScore(p2Score);
	}

}
