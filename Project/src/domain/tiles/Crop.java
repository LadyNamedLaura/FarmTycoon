package domain.tiles;

import java.util.Date;

import domain.Clock;
import domain.Game;
import domain.Savable;
import domain.TileState;

public class Crop extends Savable implements TileState {
	public StateList getStateType() {return StateList.CROP;	}

	private enum State {
		GROWING, READY, ROTTEN;
	}
	private enum Actions implements TileAction {
		CLEAR(0, 20), 
		HARVEST(0, 0);

		private int time, cost;

		public int getCost() {return cost;}
		public int getTime() {return time;}

		Actions(int time, int cost) {
			this.time = time;
			this.cost = cost;
		}
	}

	private Crops crop;
	private State state;
	private Date planted;

	public Crop(String type) {
		this(type, Game.getGame().getClock().getDate());
	}

	public Crop(String type, long planted) {
		this(type, new Date(planted));
	}

	public Crop(int id, String type, Date planted) {
		this(type, planted);
		this.id = id;
	}

	public Crop(String type, Date planted) {
		this(Crops.valueOf(type), planted);
	}

	public Crop(Crops crop) {
		this(crop,  Game.getGame().getClock().getDate());
	}

	public Crop(Crops crop, Date date) {
		this.crop = crop;
		this.planted = date;
		this.state = State.GROWING;
	}

	public static Crops[] getTypes() {
		return Crops.values();
	}

	public String getType() {
		return crop.name();
	}

	public Date getPlanted() {
		return this.planted;
	}



	@Override
	public TileAction[] getActions() {
		switch(state) {
		case READY:	return new TileAction[]{Actions.CLEAR, Actions.HARVEST};
		case GROWING:
		case ROTTEN:	return new TileAction[]{Actions.CLEAR};
		default:	return null;
		}
	}

	@Override
	public TileState executeAction(TileAction action) {
		if(action == TileAction.Defaults.EXPIRE) {
			switch(state) {
			case GROWING:	this.state=State.READY;  break;
			case READY:	this.state=State.ROTTEN; break;
			default:	return null;
			}
			return this;
		}
		if((Actions) action == Actions.CLEAR) {
			return new None();
		}
		if( this.state == State.READY && (Actions) action == Actions.HARVEST) {
			domain.Game.getGame().setCash(
					domain.Game.getGame().getCash()+crop.getProduct().getPrice());
			return new None();
		}
		return null;
	}

	@Override
	public long getExpiryTime() {
		switch(state) {
		case GROWING:	return planted.getTime() + (Clock.MSECONDSADAY * crop.getTime());
		case READY:	return planted.getTime() + (Clock.MSECONDSADAY * crop.growdays * 3 / 2);
		default:	return 0;
		}
	}
	
	public String stateInfo() {
		return "CROP_"+crop.name()+"_"+state.name();
	}
}
