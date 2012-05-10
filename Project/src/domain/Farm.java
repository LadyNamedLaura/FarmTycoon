package domain;

import java.sql.SQLException;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import api.Coordinate;

import domain.tiles.Market;
import exceptions.NoSuchTileException;

/**
 * The Class which has control over the whole farm. 
 * @author simon
 *
 */
public class Farm extends Savable {
	public static final Coordinate size = new Coordinate(4,4);
	private static final int STARTCASH = 1000;

	private int cash;
	private Map<Coordinate,Tile> tileMap = new HashMap<Coordinate,Tile>();
	private Market market = null;
	private Infection infection;
	private Storm storm;

	public Farm() {
		this(STARTCASH);
	}

	public Farm(int cash) {
		this(cash, false);
	}

	public Farm(int cash, boolean loadTiles) {
		this(cash, loadTiles, 0, 0);
	}
	public Farm(int cash, boolean loadTiles, long infection, long storm) {
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
		this.infection = new Infection(infection);
		this.storm = new Storm(storm);
		this.cash = cash;
		if (this.market == null) {
			Coordinate[] mcoord = {
				new Coordinate(0,0),
				new Coordinate(0,size.getY()-1),
				new Coordinate(size.getX()-1,0),
				new Coordinate(size.getX()-1,size.getY()-1)};
			int i = new Random().nextInt(4);

			this.tileMap.get(mcoord[i]).setState(new Market());
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
	
	int adjustCash(int adj) {
		this.cash += adj;
		return this.cash;
	}

	public Tile getTile(Coordinate coord) {
		if(tileMap.get(coord)==null)
			throw new NoSuchTileException();
		return tileMap.get(coord);
	}

	/**
	 * @return the market
	 */
	public Market getMarket() {
		return market;
	}

	public int getId() {
		return 0;
	}

	public void save() throws SQLException {
		super.save();
		for (Tile tile : tileMap.values())
			tile.save();
	}

	public Map<Coordinate, Tile> getTiles() {
		return tileMap;
	}

	public void update() {
		Tile.update();
		storm.update();
		infection.update();
	}

	public Infection getInfecion() {
		return infection;
	}
	public Infection getStorm() {
		return infection;
	}

	public int countBarn() {
		int count=0;
		for(Tile tile:tileMap.values())
			if(tile.getState() instanceof domain.tiles.Barn)
				count++;
		return count;
	}
}
