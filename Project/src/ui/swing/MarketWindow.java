package ui.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.Game;
import domain.Inventory;
import domain.Product;

import java.awt.GridLayout;
import java.util.Map.Entry;

import javax.swing.JButton;

import ui.Translator;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MarketWindow extends JFrame {
	private Game game;
	private JLabel productLabel1;
	private JButton sellButton1;
	private JLabel countLabel1;

	public MarketWindow(Game game) {
		this.game=game;
		initGUI();
	}
	
	private void initGUI() {
		try {
			int i=0;
			for(Entry<Product, Integer> item : game.getInv().entrySet()) {
				if(item.getValue()>0) {
					i++;
					getContentPane().add(new JLabel(Translator.getString(item.getKey().name())));
					getContentPane().add(new JLabel(item.getValue().toString()));
					getContentPane().add(new JButton("verkoop aan "+String.format(Translator.getString("moneystring"), item.getKey().getPrice())));
				}
			}
			GridLayout thisLayout = new GridLayout(i, 3);
			getContentPane().setLayout(thisLayout);
			this.setVisible(true);
			this.setSize(400, 30 + (i*50));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
