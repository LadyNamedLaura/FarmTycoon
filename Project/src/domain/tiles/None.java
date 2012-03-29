package domain.tiles;

import domain.TileState;

public class None implements TileState {
	public StateList getStateType() {return StateList.NONE;}

	private enum Actions implements TileAction {
		PLOW(1, 50), BUILDBARN(0, 500), COWS(0, 250), CHICKENS(0, 100);

		private int time, cost;

		public int getCost() {return cost;}
		public int getTime() {return time;}

		Actions(int time, int cost) {
			this.time = time;
			this.cost = cost;
		}
	}

	public Actions[] getActions() {
		return Actions.values();
	}

	public TileState executeAction(TileAction action) {
		switch((Actions) action){
		case PLOW:	return new Plowing();
		case BUILDBARN:	return new Building("barn");
		default:	return null;
		}
	}

	@Override
	public long getExpiryTime() {
		return 0;
	}
}
