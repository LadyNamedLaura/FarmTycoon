package domain.tiles;

import domain.Savable;
import domain.TileState;

/**
 * Class containing all possible buildings
 * 
 * @author Rigès De Witte
 * @author Simon Peeters
 * @author Barny Pieters
 * @author Laurens Van Damme
 * 
 */
public class Building extends Savable implements TileState {
	/**
	 * Private enum containing the properties of the different building types;
	 * 
	 */
	private enum BuildingList {
		Factory, Barn
	}

	private BuildingList building;

	/**
	 * Construct a new building.
	 * 
	 * @param type
	 *            the name of the building to construct
	 */
	public Building(String type) {
		this(BuildingList.valueOf(type));
	}

	/**
	 * Construct a new building.
	 * 
	 * @param type
	 *            the BuildingList object of the building type to construct
	 */
	public Building(BuildingList type) {
		this.building = type;
	}

	/**
	 * Get a list of all possible building types.
	 * 
	 * @return an array containing all available types.
	 */
	public static BuildingList[] getTypes() {
		return BuildingList.values();
	}

	/**
	 * get the type name of the current building
	 * 
	 * @return A string referencing the type of the building.
	 */
	public String getType() {
		return building.name();
	}

	/**
	 * @return the StateList item for this class
	 */
	@Override
	public StateList getStateType() {
		return StateList.BUILDING;
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

	public long getExpiryTime() {
		return 0;
	}

}
