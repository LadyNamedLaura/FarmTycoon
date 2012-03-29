package domain;

import java.sql.SQLException;

import domain.tiles.TileAction;

public class Game {
	private Farm farm;
	private Clock clock;
	private static Game current;

	public Game(boolean load) {
		current=this;
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

	public void setCash(int cash) {
		farm.setCash(cash);
	}

	public String[][] getTiles() {
		String[][] tiles = new String[Farm.width][Farm.height];
		for (int i = 0; i < Farm.width; i++)
			for (int j = 0; j < Farm.height; j++)
				tiles[i][j] = getTileType(i, j);

		return tiles;
	}

	public void save() throws SQLException {
		farm.save();
		clock.save();
	}

	public String getTileType(int[] coords) {
		return getTileType(coords[0], coords[1]);
	}

	public String getTileType(int x, int y) {
		return farm.getTile(x, y).getType().name();
	}

	public domain.tiles.TileAction[] getTileActions(int[] coords) {
		return getTileActions(coords[0],coords[1]);
	}
	public domain.tiles.TileAction[] getTileActions(int x, int y) {
		return farm.getTile(x, y).getActions();
	}

	public boolean executeAction(int[] coords, TileAction action) {
		return executeAction(coords[0],coords[1],action);
	}
	public boolean executeAction(int x,int y, TileAction action) {
		return farm.getTile(x,y).executeAction(action);
	}

	public static Game getGame() {
		return current;
	}
}
