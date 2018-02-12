package org.klechcorp.acemaster.score;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ScoreManager  extends ScoringElmt  implements ScoringElmtParent {
	private static final int MATCH_WIN_DIFF = 1;
	private static final int MATCH_WIN_THRESH = 2;
	private static final int MAX_NBR_OF_SET = 5;
	
	List<Set> listOfSet = new ArrayList<>(MAX_NBR_OF_SET);
	
	protected ScoreManager(ScoringElmtParent _parent) {
		super(_parent, MATCH_WIN_THRESH, MATCH_WIN_DIFF);
		listOfSet.add(new Set(this));
	}
	
	@Override
	String translateScore(int _score) {
		return null;
	}
	@Override
	void player1Scored() {
		listOfSet.get(p1Score + p2Score).player1Scored();
	}
	@Override
	void player2Scored() {
		listOfSet.get(p1Score + p2Score).player2Scored();
		
	}
	@Override
	String getScoreDescription() {
		StringJoiner sj = new StringJoiner(" | ");
		for(Set set : listOfSet) {
			sj.add(set.getScoreDescription());
		}
		return sj.toString();
	}
	
	@Override
	public void player1Won() {
		++p1Score;
		manageElmt();
		if(!ended())
			listOfSet.add(new Set(this));
	}

	@Override
	public void player2Won() {
		++p2Score;
		manageElmt();
		if(!ended())
			listOfSet.add(new Set(this));
	}


}
