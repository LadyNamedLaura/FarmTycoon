package ui.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
	public domain.Tile tile;
	private Image bgimage;
	private static Image cursor;
	public boolean selected = false;

	//just to prevent jigloo from craching
	public TilePanel() {
		super();
		initGUI();
	}
	public TilePanel(domain.Tile tile) {
		super();
		this.tile = tile;
		initGUI();
		update();
	}

	private void initGUI() {
		try {
			this.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent evt) {
					thisComponentResized(evt);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bgimage = Images.getImage(tile.getType().name(),this.getSize());
		} catch (IllegalArgumentException e) {
			bgimage = null;
		}
		try {
			if (cursor != null)
				cursor = Images.getImage("SELECTED",this.getSize());
		} catch (IllegalArgumentException e) {
			cursor = null;
		}
	}

	public void update() {
	}
    public void paintComponent(Graphics g) {
    	if(bgimage != null)
    		g.drawImage(bgimage, 0, 0, null);
    	if(selected && cursor != null)
    		g.drawImage(cursor, 0, 0, null);
    }
    
    private void thisComponentResized(ComponentEvent evt) {
		bgimage = Images.getImage(tile.getType().name(),this.getSize());
		cursor = Images.getImage("SELECTED",this.getSize());
    }
    
}