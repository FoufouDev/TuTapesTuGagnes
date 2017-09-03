package fr.github.game;

public enum State {

	LOBBY,
	GAME,
	END;
	
	private static State now;
	
	public static boolean isState(State whatState){
		return now == whatState;
	}
	public static void setState(State whatState){
		now = whatState;
	}
}
