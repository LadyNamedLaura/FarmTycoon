package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Farm extends Savable {
	public static final int width = 4;
	public static final int height = 4;
	private static final int STARTCASH = 100000;

	private int cash;// EUR x 100
	private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	private Market market;
	private ArrayList<Creature> creatures;
	private Farmer farmer;
	
	public Farm(){
		this(STARTCASH);
	}
	public Farm(int cash, Market market, boolean loadTiles) {
		tiles.ensureCapacity(width);
		for(int i=0; i<width;i++) {
			tiles.add(i, new ArrayList<Tile>());
			tiles.get(i).ensureCapacity(height);
			for(int j=0; j<height;j++) {
				if (loadTiles)
					try {
						tiles.get(i).add(j,(Tile) Tile.load(Tile.class, (i*10)+j));
					} catch (Exception e ){
						e.printStackTrace();
						tiles.get(i).add(j,new Tile(i,j,null));
					}
				else
					tiles.get(i).add(j,new Tile(i,j,null));
			}
		}
		this.cash = cash;
		this.market = market;
		this.farmer = new Farmer();
		
	}
	public Farm(int cash) {
		this(cash,null,false);
		switch(new Random().nextInt(4)){
		case 0:
			market = new Market(0,0);
			break;
		case 1:
			market = new Market(width,0);
			break;
		case 2:
			market = new Market(width,height);
			break;
		case 3:
			market = new Market(0,height);
			break;
		}
		
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
	//Farm has always id==0
	public int getId() {
		return 0;
	}
	public void save() throws SQLException {
		super.save();
		for (ArrayList<Tile>row : tiles)
			for (Tile tile : row)
				tile.save();
	}
}
