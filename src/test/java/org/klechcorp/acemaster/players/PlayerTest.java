package org.klechcorp.acemaster.players;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	
	@Test
	void canCatchLeft() throws Exception {
		assertTrue(Player.RANDOM_BOBBY.tryToCatchLeft(49));
	}
	
	@Test
	void canCatchMidddle() throws Exception {
		assertTrue(Player.RANDOM_BOBBY.tryToCatchMiddle(49));
	}
	
	@Test
	void canCatchRight() throws Exception {
		assertTrue(Player.RANDOM_BOBBY.tryToCatchRight(49));
	}
	
	@Test
	void playerWinIfEqual() throws Exception {
		assertFalse(Player.RANDOM_BOBBY.tryToCatchMiddle(50));
	}
	
	@Test
	void cannotCatchLeft() throws Exception {
		assertFalse(Player.RANDOM_BOBBY.tryToCatchLeft(51));
	}
	
	@Test
	void cannotCatchMidddle() throws Exception {
		assertFalse(Player.RANDOM_BOBBY.tryToCatchMiddle(51));
	}
	
	@Test
	void cannotCatchRight() throws Exception {
		assertFalse(Player.RANDOM_BOBBY.tryToCatchRight(51));
	}

}
