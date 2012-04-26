package domain.tiles;

import api.TileAction;
import api.TileInfo;
import domain.TileState;

public class Plowed implements TileState
{

	public TileAction[] getActions() {
		return Crops.values();
	}

	public TileState executeAction(TileAction action) {
		if(action instanceof Crops) {
			Crops crop = (Crops) action;
			return new Crop(crop);
		}
		return null;
	}

	@Override
	public long getExpiryTime() {
		return 0;
	}

	@Override
	public TileInfo getInfo() {
		return new TileInfo(getClass().getSimpleName(), null, null);
	}
}
