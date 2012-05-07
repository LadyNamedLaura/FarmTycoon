package ui.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ui.Translator;
import domain.Game;
import domain.Product;

public class MarketWindow extends JFrame {
	private Game game;

	public MarketWindow(Game game) {
		super(Translator.getString("Market"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.game=game;
		initGUI();
	}
	
	private void initGUI() {
		try {
			int i=0;
			GridBagLayout layout = new GridBagLayout();
			layout.columnWeights = new double[]{1.0,0.1,1.0};
			layout.columnWidths = new int[]{10,1,8};
			
			getContentPane().setLayout(layout);

			getContentPane().removeAll();
			for(Entry<Product, Integer> item : game.getInv().entrySet()) {
				if(item.getValue()>0) {
					JLabel name  = new JLabel(Translator.getString(item.getKey().name()));
					JLabel count = new JLabel(item.getValue().toString());
					JButton button = new SellButton(item.getKey());
					button.setPreferredSize(new Dimension(200,40));
					getContentPane().add(name, new GridBagConstraints(0, i, 1, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
					getContentPane().add(count, new GridBagConstraints(1, i, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
					getContentPane().add(button, new GridBagConstraints(2, i, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
					i++;
				}
			}
			this.setVisible(true);
			this.setSize(400, 30 + (i*40));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private class SellButton extends JButton implements MouseListener {
		private Product product;
		SellButton (Product product){
			super(Translator.getString("sellstring")+String.format(Translator.getString("moneystring"), product.getPrice()));
			this.product = product;
			addMouseListener(this);
		}
		public void mouseClicked(MouseEvent arg0) {
			game.sell(product);
			initGUI();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
}
