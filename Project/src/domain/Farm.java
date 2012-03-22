package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import domain.tiles.Market;
import domain.tiles.StateList;

public class Farm extends Savable {
	public static final int width = 4;
	public static final int height = 4;
	private static final int STARTCASH = 1000;

	private int cash;// EUR x 100
	private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	private Market market = null;
	private ArrayList<Creature> creatures;
	private Farmer farmer;

	public Farm() {
		this(STARTCASH);
	}

	public Farm(int cash, boolean loadTiles) {
		tiles.ensureCapacity(width);
		for (int i = 0; i < width; i++) {
			tiles.add(i, new ArrayList<Tile>());
			tiles.get(i).ensureCapacity(height);
			for (int j = 0; j < height; j++) {
				if (loadTiles)
					try {
						tiles.get(i).add(j,
								(Tile) Tile.load(Tile.class, (i * 10) + j));
						TileState state = tiles.get(i).get(j).getState();
						if (state instanceof Market)
							this.market = (Market) state;
					} catch (Exception e) {
						e.printStackTrace();
						tiles.get(i).add(j, new Tile(i, j));
					}
				else
					tiles.get(i).add(j, new Tile(i, j));
			}
		}
		this.cash = cash;
		this.farmer = new Farmer();
		if (this.market == null) {
			switch (new Random().nextInt(4)) {
			case 0:
				this.tiles.get(0).get(0).setType(StateList.MARKET);
				this.market = (Market) this.tiles.get(0).get(0).getState();
				break;
			case 1:
				this.tiles.get(width - 1).get(0).setType(StateList.MARKET);
				this.market = (Market) this.tiles.get(width - 1).get(0)
						.getState();
				break;
			case 2:
				this.tiles.get(width - 1).get(height - 1)
						.setType(StateList.MARKET);
				this.market = (Market) this.tiles.get(width - 1)
						.get(height - 1).getState();
				break;
			case 3:
				this.tiles.get(0).get(height - 1).setType(StateList.MARKET);
				this.market = (Market) this.tiles.get(0).get(height - 1)
						.getState();
				break;
			}
			if (this.market == null) {
				throw new java.lang.NullPointerException(
						"failed to create market");
			}
		}
		if (this.market == null) {
			throw new java.lang.NullPointerException("no market");
		}

	}

	public Farm(int cash) {
		this(cash, false);
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
	
	public void setCash(int cash){
		this.cash = cash;
	}
	/**
	 * @return the tiles
	 */
	public ArrayList<ArrayList<Tile>> getTiles() {
		return tiles;
	}
	
	public Tile getTile(int x, int y) {
		return tiles.get(x).get(y);
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
		for (ArrayList<Tile> row : tiles)
			for (Tile tile : row)
				tile.save();
	}
}
