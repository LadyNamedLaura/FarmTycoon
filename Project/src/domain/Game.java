package domain;

public class Game {
	private Farm farm;
	private Clock clock;
	
	public Game() {
		clock = new Clock(1);
		farm = new Farm();
	}
	public Farm getFarm() {
		return farm;
	}
	public Clock getClock() {
		return clock;
	}
}
