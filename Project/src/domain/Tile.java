package domain;

import domain.tiles.StateList;
import domain.tiles.TileAction;

public class Tile extends Savable {
	private int xcoord;
	private int ycoord;
	private TileState state;
	private StateList type;

	/**
	 * @return the state
	 */
	public TileState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(TileState state) {
		this.state = state;
	}

	public Tile(int x, int y) {
		this(x, y, StateList.NONE.getNew());
	}

	public Tile(int x, int y, StateList type) {
		this(x, y, type.getNew());
	}

	public Tile(int x, int y, TileState state) {
		xcoord = x;
		ycoord = y;
		this.type = state.getStateType();
		this.state = state;
	}

	public int[] getCoords() {
		int[] coords = new int[2];
		coords[0] = xcoord;
		coords[1] = ycoord;
		return coords;
	}

	public int getId() {
		return (xcoord * 10) + ycoord;
	}

	public StateList getType() {
		return type;
	}

	public void setType(StateList type) {
		if (type == this.type)
			return;
		this.type = type;
		this.state = type.getNew();
	}

	/**
	 * @return
	 * @see domain.TileState#getActions()
	 */
	public TileAction[] getActions() {
		return state.getActions();
	}

	/**
	 * @param action
	 * @return
	 * @see domain.TileState#executeAction(domain.tiles.TileAction)
	 */
	public boolean executeAction(TileAction action) {
		try {
			TileState tmp = state.executeAction(action);
			if(tmp == null)
				return false;
			Controller.getInstance().getGame().setCash(Controller.getInstance().getGame().getCash()-action.getCost());
			this.state = tmp;
			this.type = this.state.getStateType();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
