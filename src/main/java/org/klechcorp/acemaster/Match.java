package org.klechcorp.acemaster;

import java.util.Random;
import java.util.Scanner;

import org.klechcorp.acemaster.players.Player;
import org.klechcorp.acemaster.score.ScoreManager;
import org.klechcorp.acemaster.score.ScoringElmtParent;

public class Match implements ScoringElmtParent {
	
	private final Player opponent;
	private final ScoreManager scoreMan = new ScoreManager(this);
	private final Random rand = new Random();
	
	public Match(Player _opp) {
		this.opponent = _opp;
	}
	
	public void play(Scanner in) {
		System.out.println("The public is here and can't wait.");
		System.out.println("Unleash the arm !!!");
		int dir = 0;
		boolean couldCatch = false;
		
		while(!scoreMan.ended()) {
			System.out.println("Input 1,2,3 for serving left, middle or right");
			int chance = rand.nextInt(101);
			dir = in.nextInt();
			switch(dir) {
			case 1:
				couldCatch = opponent.tryToCatchLeft(chance);
				break;
			case 2:
				couldCatch = opponent.tryToCatchMiddle(chance);
				break;
			case 3:
				couldCatch = opponent.tryToCatchRight(chance);
				break;
			default:
				System.out.println("Please input a value from [1,2,3]");
				continue;
			}
			
			if(couldCatch) {
				scoreMan.player2Scored();
			} else {
				scoreMan.player1Scored();
			}
			System.out.println(scoreMan.getScoreDescription());
		}
	}

	@Override
	public void player1Won() {
		System.out.println("Once again you WON !!!!!!!");
		
	}

	@Override
	public void player2Won() {
		System.out.println("UHH you lost ?!! better luck next time");
	}

}
