package domain;

import java.sql.SQLException;

import domain.tiles.TileAction;

public class Game {
	private Farm farm;
	private Clock clock;
	private static Game current;
	private Inventory inv;

	public Game(boolean load) {
		current=this;
		if (load) {
			try {
				clock = (Clock) Clock.load(Clock.class, 0);
				farm = (Farm) Farm.load(Farm.class, 0);
				inv = Inventory.load();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			clock = new Clock();
			farm = new Farm();
			inv = new Inventory();
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
		String[][] tiles = new String[farm.getWidth()][farm.getHeight()];
		for (Coordinate i :Coordinate.getCoordSet(new Coordinate(0, 0),Farm.size))
			tiles[i.getX()][i.getY()] = getTileType(i);

		return tiles;
	}

	public void save() throws SQLException {
		farm.save();
		clock.save();
		inv.save();
	}

	public String getTileType(int x, int y) {
		return getTileType(new Coordinate(x, y));
	}
	public String getTileType(Coordinate coord){
		return farm.getTile(coord).getType().name();
	}
	public String getTileInfo(Coordinate coord){
		return farm.getTile(coord).getState().stateInfo();
	}

	public domain.tiles.TileAction[] getTileActions(int x, int y) {
		return getTileActions(new Coordinate(x,y));
	}
	public domain.tiles.TileAction[] getTileActions(Coordinate coord) {
		return farm.getTile(coord).getActions();
	}

	public boolean executeAction(int x,int y, TileAction action) {
		return executeAction(new Coordinate(x,y), action);
	}
	
	public boolean executeAction(Coordinate coord, TileAction action){
		return farm.getTile(coord).executeAction(action);
	}

	public void skipDay() {
		clock.skipDay();
	}

	public void speedUp() {
		clock.setMultiplier(clock.getMultiplier()*2);
	}

	public void slowDown() {
		clock.setMultiplier(clock.getMultiplier()/2);
	}

	public static Game getGame() {
		return current;
	}

	public Inventory getInv() {
		return inv;
	}

}
