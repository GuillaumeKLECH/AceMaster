package org.klechcorp.acemaster;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.klechcorp.acemaster.players.Player;

public class AceMaster {

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("ACE MASTERS 2018");
		System.out.println("==================================================");
		
		System.out.println("You are a man known only as the arm.");
		System.out.println("You can't move your legs but your right arm is extremely powerful.");
		System.out.println("Tennis Man around the world are lining up to try to catch your serves.");
		
		
		Scanner in = new Scanner(System.in);
		int i = -1;
		
		try {
			printPlayerList();
			i = in.nextInt();
			
			Match match = new Match(Player.values()[i]);
			match.play(in);
			
		} catch (InputMismatchException e) {
			System.out.println("Please be gentle with the program.");
		} finally {
			in.close();
		}
}

	private static void printPlayerList() {
		System.out.println("Who will be your opponent this time ?");
		int i = 1;
		for(Player en : Player.values()) {
			System.out.printf("%d - %s \n", i++, en.getName());
		}
		System.out.println("Please enter the number corresponding to the chosen player : ");
	}

}
