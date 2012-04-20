package domain.tiles;

import domain.TileState;

/**
 * @author Rigès De Witte, Simon Peeters,Barny Pieters,Laurens Van Damme
 * 
 */
public class Market implements TileState {

	/**
	 * Initializes a new market at the given coordinates
	 * 
	 * @param x
	 *            x-coordinate for market
	 * @param y
	 *            y-coordinate for market
	 */
	public Market() {
	}

	@Override
	public StateList getStateType() {
		return StateList.MARKET;
	}

	@Override
	public TileAction[] getActions() {
		return null;
	}

	@Override
	public TileState executeAction(TileAction action) {
		return null;
	}

	@Override
	public long getExpiryTime() {
		return 0;
	}

	@Override
	public String stateInfo() {
		return "MARKET";
	}

}
