package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import domain.tiles.Market;
import domain.tiles.StateList;

public class Farm extends Savable {
	public static final Coordinate size = new Coordinate(4,4);
	private static final int STARTCASH = 1000;

	private int cash;
	private Map<Coordinate,Tile> tileMap = new HashMap<Coordinate,Tile>();
	private Market market = null;
	private ArrayList<Creature> creatures;
	private Farmer farmer;

	public Farm() {
		this(STARTCASH);
	}

	public Farm(int cash) {
		this(cash, false);
	}

	public Farm(int cash, boolean loadTiles) {
		for (Coordinate i : Coordinate.getCoordSet(new Coordinate(0,0),size)) {
			if (loadTiles)
				try {
					tileMap.put(i, (Tile) Tile.load(
						Tile.class, (i.hashCode())));
					TileState state = tileMap.get(i).getState();
					if (state instanceof Market)
						this.market = (Market) state;
				} catch (Exception e) {
					e.printStackTrace();
					tileMap.put(i, new Tile(i));
				}
			else
				tileMap.put(i, new Tile(i));
		}
		this.cash = cash;
		this.farmer = new Farmer();
		if (this.market == null) {
			Coordinate[] mcoord = {
				new Coordinate(0,0),
				new Coordinate(0,size.getY()-1),
				new Coordinate(size.getX()-1,0),
				new Coordinate(size.getX()-1,size.getY()-1)};
			int i = new Random().nextInt(4);

			this.tileMap.get(mcoord[i]).setType(StateList.MARKET);
			this.market = (Market) this.tileMap.get(mcoord[i]).getState();
			if (this.market == null) {
				throw new java.lang.NullPointerException(
						"failed to create market");
			}
		}
		if (this.market == null) {
			throw new java.lang.NullPointerException("no market");
		}

	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return size.getX();
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return size.getY();
	}

	/**
	 * @return the cash
	 */
	public int getCash() {
		return cash;
	}
	
	public void setCash(int cash){
		this.cash = cash;
	}
	/**
	 * @return the tiles
	 */
	public Map<Coordinate,Tile> getTiles() {
		return tileMap;
	}

	public Tile getTile(Coordinate coord) {
		return tileMap.get(coord);
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

	// Farm has always id==0
	public int getId() {
		return 0;
	}

	public void save() throws SQLException {
		super.save();
		for (Tile tile : tileMap.values())
			tile.save();
	}
}
