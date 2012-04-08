package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import domain.tiles.StateList;
import domain.tiles.TileAction;

public class Tile extends Savable {
	private static SortedMap<Long,Tile> expiryMap = Collections.synchronizedSortedMap(new TreeMap<Long,Tile>());

	private long expiryTime;
	private Coordinate coord;
	private TileState state;
	private StateList type;

	public Tile(Coordinate coord) {
		this(coord, StateList.NONE.getNew(), 0);
	}

	public Tile(Coordinate coord, StateList type) {
		this(coord, type.getNew());
	}

	public Tile(Coordinate coord, TileState state) {
		this(coord, state, state.getExpiryTime());
	}
	public Tile(Coordinate coord, TileState state, long expiryTime) {
		this.coord = coord;
		this.type = state.getStateType();
		this.state = state;
		this.expiryTime = expiryTime;
		if (expiryTime > 0) {
			synchronized (expiryMap) {
				while(expiryMap.get(expiryTime) != null) //almost imposible
					expiryTime++;
				expiryMap.put(expiryTime, this);
			}
		}
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(TileState state) {
		this.state = state;
	}

	public Coordinate getCoordinate() {
		return coord;
	}

	public int getId() {
		return coord.hashCode();
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
				synchronized (expiryMap) {
					while(expiryMap.get(expiryTime) != null) //almost imposible
						expiryTime++;
					expiryMap.put(expiryTime, this);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static void update() {
		Map<Long,Tile> expiredMap;
		expiredMap = new HashMap<Long,Tile>(expiryMap.headMap(
				domain.Game.getGame().getClock().getTime()));
		for (Map.Entry<Long, Tile> entry : expiredMap.entrySet()) {
			expiryMap.remove(entry.getKey());
			entry.getValue().executeAction(TileAction.Defaults.EXPIRE);
		}
	}
}
