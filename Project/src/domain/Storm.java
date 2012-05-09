package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import domain.tiles.Crop;
import domain.tiles.Plowed;
import domain.tiles.Plowing;

import api.Coordinate;

public class Storm {
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
	public void doInfect(long time) {
		ArrayList<Coordinate> tiles = new ArrayList<Coordinate>();
		for(Tile tile : Game.getGame().getFarm().getTiles().values())
			if (tile.getState() instanceof Crop || tile.getState() instanceof Plowed || tile.getState() instanceof Plowing)
				tiles.add(tile.getCoordinate());
		int count=0;
		while(count <= tiles.size() * 3 / 10) {
			Coordinate coord = tiles.get(rand.nextInt(tiles.size()));
			Game.getGame().executeAction(coord, api.TileAction.Defaults.DESTROY);
			count++;
		}
		System.out.println("destroyed "+count+" fields");
	}
	public void update() {
		if (next < Game.getGame().getClock().getTime()){
			doInfect(next);
			forecastnext();
		}
	}
	public long getNext(){
		return next;
	}
}
