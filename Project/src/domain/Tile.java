package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import exceptions.InvalidStateException;

import api.Coordinate;
import api.TileAction;
import api.TileInfo;


public class Tile extends Savable {
	private final static SortedMap<Long,Tile> expiryMap = Collections.synchronizedSortedMap(new TreeMap<Long,Tile>());

	private long expiryTime;
	private final Coordinate coord;
	private TileState state;

	public Tile(Coordinate coord) {
		this(coord, new domain.tiles.None(), 0);
	}

	public Tile(Coordinate coord, TileState state, long expiryTime) {
		this.coord = coord;
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

	/**
	 * @return
	 * @throws InvalidStateException 
	 * @see domain.TileState#getActions()
	 */
	public TileAction[] getActions() throws InvalidStateException {
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

	public boolean executeAction(TileAction action) {
		return executeAction(action, Game.getGame().getClock().getTime());
	}
	/**
	 * @param action
	 * @return
	 * @see domain.TileState#executeAction(api.TileAction)
	 */
	public boolean executeAction(TileAction action, long timestamp) {
		try {
			TileState tmp = state.executeAction(action, this, timestamp);
			if(tmp == null){
				System.out.println(action.name()+" on "+getCoordinate().toString()+" returned null");
				return false;
			}
			Controller.getInstance().getGame().adjustCash(-action.getCost());
			this.state = tmp;
			this.expiryTime = state.getExpiryTime();
			if (expiryTime > 0) {
				System.out.println("tile "+getCoordinate()+" expires: "+new java.util.Date(expiryTime).toString());
				synchronized (expiryMap) {
					while(expiryMap.get(expiryTime) != null){ //almost impossible
						System.out.println("imposible colission");
						expiryTime++;
					}
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
			if(entry.getValue().expiryTime == entry.getKey())
				entry.getValue().executeAction(TileAction.Defaults.EXPIRE, entry.getKey());
			else
				System.out.println("bogus expiry time deleted from map");
		}
	}
	
	public TileInfo getInfo() {
		return state.getInfo();
	}
}