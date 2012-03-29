package domain.tiles;

import domain.TileState;

public class None implements TileState {
	private static final StateList stateType = domain.tiles.StateList.NONE;

	public StateList getStateType() {
		return stateType;
	}

	private enum Actions implements TileAction {
		PLOW(1, 50), BUILDBARN(0, 500), COWS(0, 250), CHICKENS(0, 100);

		private int time;
		private int cost;

		Actions(int time, int cost) {
			this.time = time;
			this.cost = cost;
		}

		public int getCost() {
			return cost;
		}

		public int getTime() {
			return time;
		}
	}

	public Actions[] getActions() {
		return Actions.values();
	}

	public TileState executeAction(TileAction action) {
		switch((Actions) action){
		case PLOW:
			return new Plowing();
		case BUILDBARN:
			return new Building("barn");
		case COWS:
			
			break;
		}
		return null;
	}

	@Override
	public long getExpiryTime() {
		return 0;
	}
}
