package domain.tiles;

import java.util.Date;

import domain.Clock;
import domain.Controller;
import domain.Savable;
import domain.TileState;

public class Crop extends Savable implements TileState {
	private enum State {
		GROWING, READY, ROTTEN;
	}
	private enum CropList {
		PATATO		(  2,  10,  20),
		CARROT		(  3,  15,  30),
		WHEAT		(  4,  20,  40),
		CORN		(  5,  30,  60),
		GRAPE		(  6,  40,  80),
		SALAD		(  7,  50,  90),
		TOMATO		(  8,  60, 110),
		STRAWBERRY	(  9,  80, 145),
		RASPBERRY	( 10, 100, 190),
		COCOA		( 11, 120, 220),
		SOY		( 12, 150, 290);

		public final int growdays, seedprice, marketprice;

		public int getGrowdays() 	{return growdays;}
		public int getSeedprice() 	{return seedprice;}
		public int getMarketprice()	{return marketprice;}

		private CropList(int growdays, int seedprice, int marketprice) {
			this.growdays = growdays;
			this.seedprice = seedprice;
			this.marketprice = marketprice;
		}
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

	private CropList crop;
	private State state;
	private Date planted;

	public Crop(String type) {
		this(type, Controller.getInstance().getClock().getDate());
	}

	public Crop(String type, long planted) {
		this(type, new Date(planted));
	}

	public Crop(int id, String type, Date planted) {
		this(type, planted);
		this.id = id;
	}

	public Crop(String type, Date planted) {
		this.crop = CropList.valueOf(type);
		this.planted = planted;
	}

	public static CropList[] getTypes() {
		return CropList.values();
	}

	public boolean isReady() {
		return ((planted.getTime() + (Clock.MSECONDSADAY * crop.growdays)) < Controller
				.getInstance().getClock().getTime());
	}

	public String getType() {
		return crop.name();
	}

	public Date getPlanted() {
		return this.planted;
	}

	public StateList getStateType() {
		return StateList.CROP;
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
		if((TileAction.Defaults) action == TileAction.Defaults.EXPIRE) {
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
					domain.Game.getGame().getCash()+crop.getMarketprice());
			return new None();
		}
		return null;
	}

	@Override
	public long getExpiryTime() {
		switch(state) {
		case GROWING:	return planted.getTime() + (Clock.MSECONDSADAY * crop.growdays);
		case READY:	return planted.getTime() + (Clock.MSECONDSADAY * crop.growdays * 3 / 2);
		default:	return 0;
		}
	}

}
