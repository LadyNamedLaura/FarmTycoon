package domain.tiles;

import api.TileAction;
import api.TileInfo;
import domain.Game;
import domain.TileState;

public class None implements TileState {

	private enum Actions implements TileAction {
		PLOW(2, 50), BUILDBARN(0, 500), ANIMALS(0, 50);

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

	public TileState executeAction(TileAction action, domain.Tile tile, long timestamp) {
		if(action instanceof Actions){
			switch((Actions) action){
			case PLOW:	return new Plowing();
			case BUILDBARN:	return new Building("barn");
			case ANIMALS: return new Animal();
			default:	return null;
			}
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
