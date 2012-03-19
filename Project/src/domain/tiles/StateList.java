package domain.tiles;

import domain.TileState;

public enum StateList {
	NONE(None.class), PLOWING(Plowing.class),
	// PLOWED(null),
	CROP(Crop.class),
	// ANIMAL(Animal.class),
	BUILDING(Building.class), MARKET(Market.class);

	Class<? extends TileState> type;

	StateList(Class<? extends TileState> type) {
		this.type = type;
	}

	public TileState getNew() {
		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	public Class<? extends TileState> getTypeClass() {
		return type;
	}
}