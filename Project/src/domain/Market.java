package domain;

/**
 * @author Rigès De Witte, Simon Peeters,Barny Pieters,Laurens Van Damme 
 *
 */
public class Market {
	private int xcoord;
	private int ycoord;
	
	/**
	 * Initializes a new market at the given coordinates
	 * 
	 * @param x x-coordinate for market
	 * @param y y-coordinate for market
	 */
	public Market(int x,int y){
		xcoord = x;
		ycoord = y;
	}
	/**
	 * Gets the objects coordinates 
	 * @return the coordinates in the form [x,y]
	 */
	public int[] getCoords() {
		int[] coords = new int[2];
		coords[0]=xcoord;
		coords[1]=ycoord;
		return coords;
	}

}
