package domain.tiles;

import domain.TileState;

public class Plowing implements TileState {
	private static final StateList stateType = domain.tiles.StateList.PLOWING;

	public StateList getStateType() {
		return stateType;
	}

	public TileAction[] getActions() {
		return null;
	}

	public TileState executeAction(TileAction action) {
		return null;
	}
}
