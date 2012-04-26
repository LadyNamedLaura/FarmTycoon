package domain;

import api.TileInfo;

public interface TileState {
	public api.TileAction[] getActions();
	public TileState executeAction(api.TileAction action);
	public long getExpiryTime();
	public TileInfo getInfo();
}
