package domain;

import java.util.ArrayList;
import java.util.Random;

public class Farm {
	private static final int width = 4;
	private static final int height = 4;
	
	private int cash;// EUR x 100
	private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	private Market market;
	private ArrayList<Creature> creatures;
	private Farmer farmer;
	
	public Farm() {
		tiles.ensureCapacity(width);
		for(int i=0; i<width;i++) {
			tiles.add(i, new ArrayList<Tile>());
			tiles.get(i).ensureCapacity(height);
			for(int j=0; j<height;j++) {
				tiles.get(i).add(j,new Tile(i,j));
			}
		}
		cash = 100000;
		
		switch(new Random().nextInt(4)){
		case 0:
			new Market(0,0);
			break;
		case 1:
			new Market(width,0);
			break;
		case 2:
			new Market(width,height);
			break;
		case 3:
			new Market(0,height);
			break;
		}
		
		farmer = new Farmer();
		
	}

	/**
	 * @return the width
	 */
	public static int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public static int getHeight() {
		return height;
	}

	/**
	 * @return the cash
	 */
	public int getCash() {
		return cash;
	}

	/**
	 * @return the tiles
	 */
	public ArrayList<ArrayList<Tile>> getTiles() {
		return tiles;
	}

	/**
	 * @return the market
	 */
	public Market getMarket() {
		return market;
	}

	/**
	 * @return the creatures
	 */
	public ArrayList<Creature> getCreatures() {
		return creatures;
	}

	/**
	 * @return the farmer
	 */
	public Farmer getFarmer() {
		return farmer;
	}

}
