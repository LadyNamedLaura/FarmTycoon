package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import domain.tiles.Crop;
import domain.tiles.Plowed;
import domain.tiles.Plowing;

import api.Coordinate;
import api.TileAction;

public class Storm implements TileAction {
	Random rand;
	private long next;
	Storm() {
		this(0);
	}
	Storm(long time) {
		rand = new Random();
		if(time==0)
			forecastnext();
		else
			next = time;
	}
	public void forecastnext() {
		int days = 0;
		while (true) {
			days++;
			if (rand.nextInt(20) == 0)
				break;
		}
		next = Game.getGame().getClock().getTime() + Clock.MSECONDSADAY * days
				+ (long) (rand.nextDouble() * Clock.MSECONDSADAY);
		System.out.println("next storm on "+new Date(next).toString());
	}
	public void doStorm(long time) {
		ArrayList<Coordinate> tiles = new ArrayList<Coordinate>();
		for(Tile tile : Game.getGame().getFarm().getTiles().values())
			if (tile.getState() instanceof Crop || tile.getState() instanceof Plowed || tile.getState() instanceof Plowing)
				tiles.add(tile.getCoordinate());
		int count = 0;
		if (tiles.size() > 0)
			while (count <= tiles.size() * 3 / 10) {
				Coordinate coord = tiles.get(rand.nextInt(tiles.size()));
				Game.getGame().executeAction(coord, this);
				count++;
			}
		tiles = new ArrayList<Coordinate>();
		for (Tile tile : Game.getGame().getFarm().getTiles().values())
			if (tile.getState() instanceof domain.tiles.Factory)
				tiles.add(tile.getCoordinate());
		count = 0;
		if (tiles.size() > 0)
			while (count <= tiles.size() / 2) {
				Coordinate coord = tiles.get(rand.nextInt(tiles.size()));
				Game.getGame().executeAction(coord, this);
				count++;
			}
	}
	public void update() {
		if (next < Game.getGame().getClock().getTime()){
			doStorm(next);
			MsgQue.get().put("MSG_STORM", next);
			forecastnext();
		}
	}
	public long getNext(){
		return next;
	}

	public String name() {return "STORM";}
	public int getCost() {return 0;}
	public int getTime() {return 0;}
}
