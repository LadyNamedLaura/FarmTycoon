package domain;

import domain.tiles.StateList;

public interface TileState {
	public StateList getStateType();
	public domain.tiles.TileAction[] getActions();
	public TileState executeAction(domain.tiles.TileAction action);
	public long getExpiryTime();
}
