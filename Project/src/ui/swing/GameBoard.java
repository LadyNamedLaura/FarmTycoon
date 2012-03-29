package ui.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import domain.Game;

public class GameBoard extends javax.swing.JPanel {
	private Game game;
	private TilePanel[][] tiles;
	private GameScreen gameScreen;

	public GameBoard(domain.Game game, GameScreen gameScreen) {
		super();
		this.game = game;
		this.gameScreen = gameScreen;
		initGUI();
	}

	private void initGUI() {
		try {
			GridBagLayout layout = new GridBagLayout();
			layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			layout.rowHeights = new int[] { 7, 7, 7, 7 };
			layout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			layout.columnWidths = new int[] { 7, 7, 7, 7 };
			this.setLayout(layout);
			tiles = new TilePanel[domain.Farm.width][domain.Farm.height];
			for (int i = 0; i < domain.Farm.width; i++) {
				for (int j = 0; j < domain.Farm.height; j++) {
					TilePanel tile = new TilePanel(game,i,j);
					this.add(tile, new GridBagConstraints(i, j, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
					tile.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							gameScreen.select((TilePanel) evt.getComponent());
						}
					});
					tiles[i][j] = tile;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update() {
		domain.Tile.update();
		for (TilePanel[] list:tiles)
			for (TilePanel tile:list)
				tile.update();
	}

}
