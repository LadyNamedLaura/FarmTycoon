package domain.tiles;

import domain.TileState;


public class Plowed implements TileState
{
	private static final StateList stateType = StateList.PLOWED;

	public StateList getStateType() {
		return stateType;
	}

	public TileAction[] getActions() {
		return null;
	}

	public TileState executeAction(TileAction action) {
		return null;
	}

	@Override
	public long getExpiryTime() {
		return 0;
	}
}
