package domain.tiles;

import api.TileAction;
import api.TileInfo;
import domain.TileState;

public class Plowing implements TileState {
	private static final long PLOWTIME = domain.Clock.MSECONDSADAY;

	private long starttime;

	public Plowing() {
		this.starttime = domain.Game.getGame().getClock().getTime();
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
	@Override
	public TileInfo getInfo() {
		return new TileInfo(getClass().getSimpleName(), null, null);
	}
}
