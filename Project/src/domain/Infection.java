package domain;

import java.util.Date;
import java.util.Random;

import exceptions.NoSuchTileException;

import api.Coordinate;
import api.TileAction;
/**
 * this class contains most of the logic to infect tiles.
 * @author simon
 *
 */
public class Infection implements TileAction {
	private Random rand;
	private long nextinfection;
	
	/**
	 * Create a new infection object.
	 * @param time the time at which the next infection will take place, if 0, the time will be randomly chosen.
	 */
	public Infection(long time) {
		rand = new Random();
		if(time==0)
			forecastnext();
		else
			nextinfection = time;
	}
	/**
	 * generate a random time for the next infection.
	 */
	private void forecastnext() {
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
	/**
	 * Spread the infection around the given tile.
	 * @param coordinate the coordinate of the tile to spread from.
	 * @param timestamp the time at which this should happen/have happened
	 */
	public void spreadFrom(Coordinate coordinate,long timestamp) {
		short x = coordinate.getX();
		short y = coordinate.getY();
		for(Coordinate tile : Coordinate.getCoordSet(new Coordinate(x-1,y-1), new Coordinate(x+2,y+2))){
			try{
				if(tile.equals(coordinate)){
					System.out.println("not infecting myself");
				} else {
					Game.getGame().executeAction(tile, this);
					System.out.println("infected "+tile.toString());
				}
			} catch (NoSuchTileException e) {}
		}
	}
	/**
	 * Execute the infection
	 * @param time the time at which this should happen/have happened
	 */
	private void doInfect(long time) {
		 Tile[] tiles = Game.getGame().getFarm().getTiles().values().toArray(new Tile[1]);
		 int count=0;
		 while(count <= tiles.length / 10) {
			 Coordinate coord = tiles[rand.nextInt(tiles.length)].getCoordinate();
			 Game.getGame().executeAction(coord, this);
			 count++;
		 }
	}
	/**
	 * Update this object.
	 * This executes the infection if the forecasted time has passed.
	 * A new forecasted time is created afterwards.
	 */
	public void update() {
		if (nextinfection < Game.getGame().getClock().getTime()){
			doInfect(nextinfection);
			MsgQue.get().put("MSG_INFECTION", nextinfection);
			forecastnext();
		}
	}
	/**
	 * @return the time at which the next infection will happen.
	 */
	public long getNext(){
		return nextinfection;
	}

	public String name() {return "INFECTION";}
	public int getCost() {return 0;}
	public int getTime() {return 0;}
}