package domain;

import java.sql.SQLException;

public class Game {
	private Farm farm;
	private Clock clock;
	
	public Game(boolean load) {
		if (load) {
			try {
				clock = (Clock) Clock.load(Clock.class, 0);
				farm = (Farm) Farm.load(Farm.class, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			clock = new Clock();
			farm = new Farm();
		}
	}
	public Farm getFarm() {
		return farm;
	}
	public Clock getClock() {
		return clock;
	}
	public void save() throws SQLException {
		farm.save();
		clock.save();
	}
}
