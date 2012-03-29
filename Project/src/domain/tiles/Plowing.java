package domain.tiles;

import domain.TileState;

public class Plowing implements TileState {
	private static final StateList stateType = StateList.PLOWING;
	private static final long PLOWTIME = domain.Clock.MSECONDSADAY;

	private long starttime;

	public Plowing() {
		this.starttime = domain.Game.getGame().getClock().getTime();
	}
	public StateList getStateType() {
		return stateType;
	}

	public TileAction[] getActions() {
		return null;
	}

	public TileState executeAction(TileAction action) {
		if((TileAction.Defaults) action == TileAction.Defaults.EXPIRE)
			return new Plowed();
		return null;
	}

	public long getExpiryTime() {
		return starttime + PLOWTIME;
	}
}
