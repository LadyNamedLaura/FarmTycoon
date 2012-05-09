package domain;

import java.util.Date;
import java.util.Random;

import exceptions.NoSuchTileException;

import api.Coordinate;

public class Infection {
	Random rand;
	private long nextinfection;
	Infection() {
		this(0);
	}
	Infection(long time) {
		rand = new Random();
		if(time==0)
			forecastnext();
		else
			nextinfection = time;
	}
	public void forecastnext() {
		int days = 0;
		while (true) {
			days++;
			if (rand.nextInt(15) == 0)
				break;
		}
		nextinfection = Game.getGame().getClock().getTime() + Clock.MSECONDSADAY * days
				+ (long) (rand.nextDouble() * Clock.MSECONDSADAY);
		System.out.println("next infection on "+new Date(nextinfection).toString());
	}
	public void spreadFrom(Coordinate coordinate,long timestamp) {
		short x = coordinate.getX();
		short y = coordinate.getY();
		for(Coordinate tile : Coordinate.getCoordSet(new Coordinate(x-1,y-1), new Coordinate(x+2,y+2))){
			try{
				if(tile.equals(coordinate)){
					System.out.println("not infecting myself");
				} else {
					Game.getGame().executeAction(tile, api.TileAction.Defaults.INFECT);
					System.out.println("infected "+tile.toString());
				}
			} catch (NoSuchTileException e) {}
		}
	}
	public void doInfect(long time) {
		 Tile[] tiles = Game.getGame().getFarm().getTiles().values().toArray(new Tile[1]);
		 int count=0;
		 while(count <= tiles.length / 10) {
			 Coordinate coord = tiles[rand.nextInt(tiles.length)].getCoordinate();
			 Game.getGame().executeAction(coord, api.TileAction.Defaults.INFECT);
			 count++;
		 }
	}
	public void update() {
		if (nextinfection < Game.getGame().getClock().getTime()){
			doInfect(nextinfection);
			MsgQue.get().put("MSG_INFECTION", nextinfection);
			forecastnext();
		}
	}
	public long getNext(){
		return nextinfection;
	}
}