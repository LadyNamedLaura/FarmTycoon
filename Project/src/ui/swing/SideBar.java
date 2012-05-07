package ui.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.TileInfo;

import ui.Translator;

import domain.Game;
import exceptions.InvalidStateException;

public class SideBar extends javax.swing.JPanel {

	private JPanel selectedImage;
	private JLabel selectedName;
	private Game game;
	private GameScreen gameScreen;
	private JPanel actionsPanel;
	private TileInfo info;

	private class ActionButton extends JLabel {
		private api.TileAction action;

		ActionButton(api.TileAction action) {
			super(Translator.getString(action.name()));
			this.action = action;
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					execute();
				}
			});
		}

		private void execute() {
			if(action.name()=="ENTER") {
				if(info.getField().equals("Market")) {
					new MarketWindow(game);
				}
			}
			game.executeAction(gameScreen.getSelected().getCoords(), action);
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
			this.setPreferredSize(new java.awt.Dimension(200, 570));
			{
				selectedImage = new JPanel() {
					public Image img;

					public void paintComponent(Graphics g) {
						try {
							img = Images.getImage(
									game.getTileInfo(
											gameScreen.getSelected()
													.getCoords()).toString()
											.toUpperCase()).getScaledInstance(
									200, 200, Image.SCALE_DEFAULT);
						} catch (Exception e) {
							img = null;
						}
						if (img != null)
							g.drawImage(img, 0, 0, null);
					}

				};
				this.add(selectedImage);
				selectedImage.setSize(200, 200);
			}
			selectedName = new JLabel();
			this.add(selectedName);
			actionsPanel = new JPanel();
			this.add(actionsPanel);
			actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		this.selectedImage.repaint();
		if (gameScreen.getSelected() != null) {
			if (info==null || !game.getTileInfo(gameScreen.getSelected().getCoords())
					.toString().equals(info.toString())) {
				this.info = game.getTileInfo(gameScreen.getSelected()
						.getCoords());
				this.selectedName
						.setText(Translator.getString(info.toString()));
				actionsPanel.removeAll();
				try {
					for (api.TileAction action : game.getTileActions(gameScreen
							.getSelected().getCoords())) {
						ActionButton button = new ActionButton(action);
						actionsPanel.add(button);
					}
				} catch (NullPointerException e) {

				} catch (InvalidStateException e) {
					
				}
			}
		}
	}
}
