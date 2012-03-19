package domain.tiles;

import domain.TileState;

public class None implements TileState {
	private static final StateList stateType = domain.tiles.StateList.NONE;

	public StateList getStateType() {
		return stateType;
	}
}
