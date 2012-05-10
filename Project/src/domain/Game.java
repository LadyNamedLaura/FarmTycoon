package domain;

import java.sql.SQLException;

import exceptions.InvalidStateException;

import api.Coordinate;
import api.TileAction;
import api.TileInfo;

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
				inv = new Inventory(farm.countBarn());
				inv.load();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			clock = new Clock();
			farm = new Farm();
			inv = new Inventory(0);
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

	int adjustCash(int adj) {
		return farm.adjustCash(adj);
	}

	public void save() throws SQLException {
		farm.save();
		clock.save();
		inv.save();
	}

	public TileInfo getTileInfo(Coordinate coord){
		return farm.getTile(coord).getInfo();
	}

	public api.TileAction[] getTileActions(Coordinate coord) throws InvalidStateException {
		return farm.getTile(coord).getActions();
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
	public void updateInv() {
		inv.setBarnCount(farm.countBarn());
	}

	public void sell(Product product) {
		inv.remove(product, 1);
		adjustCash(product.getPrice());
	}

	public Infection getInfection() {
		return farm.getInfecion();
	}

	public void update() {
		farm.update();
	}

}
