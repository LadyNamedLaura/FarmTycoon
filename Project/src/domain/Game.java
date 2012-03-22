package domain;

import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public int getCash() {
		return farm.getCash();
	}
	public String[][] getTiles() {
		String[][] tiles = new String[Farm.width][Farm.height];
		ArrayList<ArrayList<Tile>> list = farm.getTiles();
		for(int i=0; i< Farm.width;i++)
			for(int j=0; j< Farm.height;j++) {
				tiles[i][j] = list.get(i).get(j).getType().name();
			}
		
		return tiles;
	}

	public void save() throws SQLException {
		farm.save();
		clock.save();
	}
}
