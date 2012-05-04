package domain;

import exceptions.InvalidStateException;
import api.TileInfo;

public interface TileState {
	public api.TileAction[] getActions() throws InvalidStateException;
	public TileState executeAction(api.TileAction action);
	public long getExpiryTime();
	public TileInfo getInfo();
}
