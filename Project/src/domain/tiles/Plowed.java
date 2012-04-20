package domain.tiles;

import domain.TileState;

public class Plowed implements TileState
{
	private static final StateList stateType = StateList.PLOWED;

	public StateList getStateType() {
		return stateType;
	}

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
	public String stateInfo() {
		return "PLOWING";
	}
}
