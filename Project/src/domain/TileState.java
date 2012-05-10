package domain;

import exceptions.InvalidStateException;
import exceptions.InventoryFullException;
import api.TileInfo;

public interface TileState {
	public api.TileAction[] getActions() throws InvalidStateException;
	public TileState executeAction(api.TileAction action, domain.Tile tile, long timestamp) throws InventoryFullException;
	public long getExpiryTime();
	public TileInfo getInfo();
}
