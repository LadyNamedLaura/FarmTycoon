package domain;

public class Tile {
	private int xcoord;
	private int ycoord;
	
	public Tile(int x,int y){
		xcoord = x;
		ycoord = y;
	}
	public int[] getCoords() {
		int[] coords = new int[2];
		coords[0]=xcoord;
		coords[1]=ycoord;
		return coords;
	}
}
