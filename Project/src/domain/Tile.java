package domain;

import java.util.Map;
import java.util.TreeMap;

import domain.tiles.StateList;
import domain.tiles.TileAction;

public class Tile extends Savable {
	private static TreeMap<Long,Tile> expiryMap = new TreeMap<Long,Tile>();

	private long expiryTime;
	private int xcoord;
	private int ycoord;
	private TileState state;
	private StateList type;

	public Tile(int x, int y) {
		this(x, y, StateList.NONE.getNew());
	}

	public Tile(int x, int y, StateList type) {
		this(x, y, type.getNew());
	}

	public Tile(int x, int y, TileState state) {
		this(x, y, state, state.getExpiryTime());
	}

	public Tile(int x, int y, TileState state, long expiryTime) {
		this(new Coordinate(x,y), state, expiryTime);
	}
	public Tile(Coordinate coord, TileState state, long expiryTime) {
		xcoord = coord.getX();
		ycoord = coord.getY();
		this.type = state.getStateType();
		this.state = state;
		this.expiryTime = expiryTime;
		if (expiryTime > 0) {
			while(expiryMap.get(expiryTime) != null) //almost imposible
				expiryTime++;
			expiryMap.put(expiryTime, this);
		}
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(TileState state) {
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

	public StateList getType() {
		return type;
	}

	public void setType(StateList type) {
		if (type == this.type)
			return;
		this.type = type;
		this.state = type.getNew();
	}

	/**
	 * @return
	 * @see domain.TileState#getActions()
	 */
	public TileAction[] getActions() {
		return state.getActions();
	}

	/**
	 * Gets the expireyTime for this instance.
	 *
	 * @return The expireyTime.
	 */
	public long getExpiryTime() {
		return this.expiryTime;
	}

	/**
	 * @return the state
	 */
	public TileState getState() {
		return state;
	}

	/**
	 * @param action
	 * @return
	 * @see domain.TileState#executeAction(domain.tiles.TileAction)
	 */
	public boolean executeAction(TileAction action) {
		try {
			TileState tmp = state.executeAction(action);
			if(tmp == null)
				return false;
			Controller.getInstance().getGame().setCash(Controller.getInstance().getGame().getCash()-action.getCost());
			this.state = tmp;
			this.type = this.state.getStateType();
			this.expiryTime = state.getExpiryTime();
			if (expiryTime > 0) {
				while(expiryMap.get(expiryTime) != null) //almost imposible
					expiryTime++;
				expiryMap.put(expiryTime, this);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static void update() {
		Map<Long,Tile> expiredMap = expiryMap.headMap(
				domain.Game.getGame().getClock().getTime());
		for (Map.Entry<Long, Tile> entry : expiredMap.entrySet()) {
			expiryMap.remove(entry.getKey());
			entry.getValue().executeAction(TileAction.Defaults.EXPIRE);
		}
	} 
}
