package ui.swing;

import java.awt.Graphics;
import java.awt.Image;

import domain.Coordinate;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("serial")
public class TilePanel extends javax.swing.JPanel {
	private domain.Game game;
	private Coordinate coords;
	private Image bgimage;
	private static Image cursor;
	public boolean selected = false;

	// just to prevent jigloo from craching
	public TilePanel() {
		super();
		initGUI();
	}

	TilePanel(domain.Game game, int x, int y) {
		super();
		this.game = game;
		this.coords = new Coordinate(x,y);
		initGUI();
		update();
	}

	private void initGUI() {
	}

	public void update() {
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		bgimage = Images.getImage(game.getTileType(coords),
				this.getSize());
		cursor = Images.getImage("SELECTED", this.getSize());
		if (bgimage != null)
			g.drawImage(bgimage, 0, 0, null);
		if (selected && cursor != null)
			g.drawImage(cursor, 0, 0, null);
	}

	public Coordinate getCoords() {
		return coords;
	}
}