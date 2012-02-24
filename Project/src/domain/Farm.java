package domain;

import java.util.ArrayList;
import java.util.Random;

public class Farm {
	private static final int width = 4;
	private static final int height = 4;
	
	private int cash;// EUR x 100
	private ArrayList<ArrayList<Tile>> tiles;
	private Market market;
	private ArrayList<Creature> creatures;
	private Farmer farmer;
	
	public Farm() {
		tiles.ensureCapacity(width);
		for(int i=0; i<width;i++) {
			tiles.add(i, new ArrayList<Tile>());
			tiles.get(i).ensureCapacity(height);
			for(int j=0; j<height;j++) {
				tiles.get(i).add(j,new Tile(i,j));
			}
		}
		cash = 100000;
		
		switch(new Random().nextInt(4)){
		case 0:
			new Market(0,0);
			break;
		case 1:
			new Market(width,0);
			break;
		case 2:
			new Market(width,height);
			break;
		case 3:
			new Market(0,height);
			break;
		}
		
	}

}
