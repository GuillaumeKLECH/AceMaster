package org.klechcorp.acemaster;


public class Helper {
	@FunctionalInterface
	public interface Action {
		public void perform();
	}
	
	public static void actRepeater(int _nb, Action _act) {
		for(int i = 0 ; i < _nb; ++i) {
			_act.perform();
		}
	}
}
