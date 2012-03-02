package domain;

public class Game {
	private Farm farm;
	private double time;
	
	public Game() {
		farm = new Farm();
	}
	public Farm getFarm() {
		return farm;
	}
	public double getTime() {
		return time;
	}
}
