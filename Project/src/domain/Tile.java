package domain;

public class Tile extends Savable {
	private int xcoord;
	private int ycoord;
	private TileState state;

	/**
	 * @return the state
	 */
	public TileState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(TileState state) {
		this.state = state;
	}

	public Tile(int x, int y, TileState state) {
		xcoord = x;
		ycoord = y;
		this.state = state;
	}

	public int[] getCoords() {
		int[] coords = new int[2];
		coords[0] = xcoord;
		coords[1] = ycoord;
		return coords;
	}

	public int getId() {
		return (xcoord * 10) + ycoord;
	}
}
