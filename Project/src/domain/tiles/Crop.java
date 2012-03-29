package domain.tiles;

import java.util.Date;

import domain.Clock;
import domain.Controller;
import domain.Savable;
import domain.TileState;

public class Crop extends Savable implements TileState {
	private enum CropList {
		CARROT(2, 200, 400), RASPBERRY(4, 100, 500);

		public final int growdays;
		public final int seedprice;

		/**
		 * @return the growdays
		 */
		@SuppressWarnings("unused")
		public int getGrowdays() {
			return growdays;
		}

		/**
		 * @return the seedprice
		 */
		@SuppressWarnings("unused")
		public int getSeedprice() {
			return seedprice;
		}

		/**
		 * @return the marketprice
		 */
		@SuppressWarnings("unused")
		public int getMarketprice() {
			return marketprice;
		}

		public final int marketprice;

		private CropList(int growdays, int seedprice, int marketprice) {
			this.growdays = growdays;
			this.seedprice = seedprice;
			this.marketprice = marketprice;
		}
	}

	private CropList crop;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TileState executeAction(TileAction action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getExpiryTime() {
		return planted.getTime() + (Clock.MSECONDSADAY * crop.growdays);
	}

}
