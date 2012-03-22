package ui.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Translator;

import domain.Game;

public class SideBar extends javax.swing.JPanel {

	private JPanel selectedImage;
	private JLabel selectedName;
	private Game game;
	private GameScreen gameScreen;
	private int[] oldselected;
	private JPanel actionsPanel;

	private class ActionButton extends JLabel {
		private domain.tiles.TileAction action;

		ActionButton(domain.tiles.TileAction action) {
			super(Translator.getString(action.name()));
			this.action = action;
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					execute();
				}
			});
		}

		private void execute() {
			game.executeAction(oldselected, action);
			gameScreen.drawn();
		}
	}

	public SideBar(domain.Game game, GameScreen gameScreen) {
		super();
		this.game = game;
		this.gameScreen = gameScreen;
		initGUI();
	}

	private void initGUI() {
		try {
			BoxLayout layout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(layout);
			this.setPreferredSize(new java.awt.Dimension(150, 570));
			{
				selectedImage = new JPanel() {
					public Image img;

					public void paintComponent(Graphics g) {
						try {
							img = Images.getImage(gameScreen.getSelectedType())
									.getScaledInstance(150, 150,
											Image.SCALE_DEFAULT);
						} catch (Exception e) {
							img = null;
						}
						if (img != null)
							g.drawImage(img, 0, 0, null);
					}

				};
				this.add(selectedImage);
				selectedImage.setSize(150, 150);
				selectedImage.setPreferredSize(new Dimension(150, 150));
			}
			selectedName = new JLabel();
			this.add(selectedName);
			actionsPanel = new JPanel();
			this.add(actionsPanel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		this.selectedImage.repaint();
		if (gameScreen.getSelected() != null) {
			if (gameScreen.getSelected().getCoords() != oldselected) {
				this.oldselected = gameScreen.getSelected().getCoords();
				this.selectedName.setText(Translator.getString(game
						.getTileType(oldselected)));
				actionsPanel.removeAll();
				try {
					for (domain.tiles.TileAction action : game
							.getTileActions(oldselected)) {
						ActionButton button = new ActionButton(action);
						actionsPanel.add(button);
					}
				} catch (NullPointerException e) {

				}
			}
		}
	}
}
