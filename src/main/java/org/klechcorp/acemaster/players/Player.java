package org.klechcorp.acemaster.players;

public enum Player {
	ONE_EYED_LOYD("One-Eyed Loyd", 0, 70, 100),
	ONE_EYED_ROB("One-Eyed Rob", 100, 70, 0),
	CROSS_EYED_MICKAEL("Cross-Eyed Mickael", 100, 0, 100),
	RANDOM_BOBBY("Random Bobby", 50, 50, 50);

	private final String name;
	private final int left; // Chance that the player will catch left
	private final int middle;// same with middle
	private final int right;// same with right

	private Player(String _name, int _left, int _middle, int _right) {
		this.name = _name;
		this.left = _left;
		this.middle = _middle;
		this.right = _right;
	}
	
	//try to catch methods simulate the behaviour of the player
	//given the chance of the player the methods return true if 
	// the player succeeded to catch or false otherwise
	public boolean tryToCatchLeft(int _chance) {
		return _chance < this.left;
	}

	public boolean tryToCatchMiddle(int _chance) {
		return _chance < this.middle;
	}

	public boolean tryToCatchRight(int _chance) {
		return _chance < this.right;
	}
	
	public String getName() {return this.name;}

}
