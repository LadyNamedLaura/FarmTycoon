package ui.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import javax.swing.WindowConstants;

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
public class GameScreen extends javax.swing.JFrame {
	private JPanel jPanel1;
	private JButton jButton1;
	private JLabel moneyLabel;
	private JPanel jPanel6;
	private JPanel jPanel5;
	private JPanel jPanel4;
	private JPanel jPanel3;
	private JPanel selectedImage;
	private JLabel timeLabel;
	private JToolBar jToolBar1;
	private JPanel jPanel2;
	private domain.Game game;
	private TilePanel selected = null;
	Timer timer = new Timer();

	private ArrayList<ArrayList<TilePanel>> tiles = new ArrayList<ArrayList<TilePanel>>();

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public GameScreen(domain.Game game) {
		super();
		this.game = game;
		initGUI();

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				drawn();
			}
		}, 0, 200); // 5 updates/sec
	}

	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(getContentPane(),
					javax.swing.BoxLayout.X_AXIS);
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel4 = new JPanel();
				BoxLayout jPanel4Layout = new BoxLayout(jPanel4,
						javax.swing.BoxLayout.Y_AXIS);
				jPanel4.setLayout(jPanel4Layout);
				getContentPane().add(jPanel4);
				jPanel4.setPreferredSize(new java.awt.Dimension(790, 570));
				{
					jPanel5 = new JPanel();
					GridBagLayout jPanel5Layout = new GridBagLayout();
					jPanel5Layout.rowWeights = new double[] { 0.1, 0.1, 0.1,
							0.1 };
					jPanel5Layout.rowHeights = new int[] { 7, 7, 7, 7 };
					jPanel5Layout.columnWeights = new double[] { 0.1, 0.1, 0.1,
							0.1 };
					jPanel5Layout.columnWidths = new int[] { 7, 7, 7, 7 };
					jPanel4.add(jPanel5);
					jPanel5.setPreferredSize(new java.awt.Dimension(46, 39));
					jPanel5.setLayout(jPanel5Layout);
					{
						jToolBar1 = new JToolBar();
						jPanel5.add(jToolBar1, new GridBagConstraints(0, 0, 4,
								5, 0.0, 0.0, GridBagConstraints.CENTER,
								GridBagConstraints.BOTH,
								new Insets(0, 0, 0, 0), 0, 0));
						jToolBar1.setFloatable(false);
						jToolBar1.setPreferredSize(new java.awt.Dimension(50,
								40));
						{
							jPanel6 = new JPanel();
							GridBagLayout jPanel6Layout = new GridBagLayout();
							jToolBar1.add(jPanel6);
							jPanel6.setPreferredSize(new java.awt.Dimension(
									104, 27));
							jPanel6Layout.rowWeights = new double[] { 0.1 };
							jPanel6Layout.rowHeights = new int[] { 7 };
							jPanel6Layout.columnWeights = new double[] { 0.1,
									0.1, 0.1 };
							jPanel6Layout.columnWidths = new int[] { 7, 7, 7 };
							jPanel6.setLayout(jPanel6Layout);
							jPanel6.setOpaque(false);
							{
								jButton1 = new JButton();
								jPanel6.add(jButton1, new GridBagConstraints(0,
										0, 1, 1, 0.0, 0.0,
										GridBagConstraints.WEST,
										GridBagConstraints.VERTICAL,
										new Insets(0, 0, 0, 0), 0, 0));
								jButton1.setText("Save");
								jButton1.setPreferredSize(new java.awt.Dimension(
										50, 40));
								jButton1.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent evt) {
										jButton1MouseClicked(evt);
									}
								});
							}
							{
								timeLabel = new JLabel();
								jPanel6.add(timeLabel, new GridBagConstraints(
										1, 0, 1, 1, 0.0, 0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.VERTICAL,
										new Insets(0, 0, 0, 0), 0, 0));
								timeLabel.setText("time");
							}
							{
								moneyLabel = new JLabel();
								jPanel6.add(moneyLabel, new GridBagConstraints(
										2, 0, 1, 1, 0.0, 0.0,
										GridBagConstraints.EAST,
										GridBagConstraints.VERTICAL,
										new Insets(0, 0, 0, 0), 0, 0));
								moneyLabel.setText("tile02");
							}
						}
					}
				}
				{
					jPanel3 = new JPanel();
					jPanel4.add(jPanel3);
					BoxLayout jPanel3Layout = new BoxLayout(jPanel3,
							javax.swing.BoxLayout.X_AXIS);
					jPanel3.setLayout(jPanel3Layout);
					jPanel3.addComponentListener(new ComponentAdapter() {
						public void componentResized(ComponentEvent evt) {
							jPanel3ComponentResized(evt);
						}
					});
					{
						jPanel1 = new JPanel();
						jPanel3.add(jPanel1);
						GridBagLayout jPanel1Layout = new GridBagLayout();
						jPanel1Layout.rowWeights = new double[] { 0.1, 0.1,
								0.1, 0.1 };
						jPanel1Layout.rowHeights = new int[] { 7, 7, 7, 7 };
						jPanel1Layout.columnWeights = new double[] { 0.1, 0.1,
								0.1, 0.1 };
						jPanel1Layout.columnWidths = new int[] { 7, 7, 7, 7 };
						jPanel1.setLayout(jPanel1Layout);
						// jPanel1.setPreferredSize(new java.awt.Dimension(231,
						// 237));
						ArrayList<ArrayList<domain.Tile>> tilelist;
						tilelist = game.getFarm().getTiles();
						tiles.ensureCapacity(domain.Farm.width);
						for (int i = 0; i < domain.Farm.width; i++) {
							tiles.add(i, new ArrayList<TilePanel>());
							tiles.get(i).ensureCapacity(domain.Farm.height);
							for (int j = 0; j < domain.Farm.height; j++) {
								TilePanel tile = new TilePanel(tilelist.get(i)
										.get(j));
								jPanel1.add(tile, new GridBagConstraints(i, j,
										1, 1, 0.0, 0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.BOTH, new Insets(0,
												0, 0, 0), 0, 0));
								tile.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent evt) {
										tileMouseClicked(evt);
									}
								});
								tiles.get(i).add(j, tile);
							}
						}
					}
					{
						jPanel2 = new JPanel();
						jPanel3.add(jPanel2);
						BoxLayout jPanel2Layout = new BoxLayout(jPanel2,
								javax.swing.BoxLayout.Y_AXIS);
						jPanel2.setLayout(jPanel2Layout);
						jPanel2.setPreferredSize(new java.awt.Dimension(150,
								570));
						{
							selectedImage = new JPanel() {
								public Image img;

								public void paintComponent(Graphics g) {
									try {
										img = Images.getImage(
												getSelected().tile.getType()
														.name())
												.getScaledInstance(150, 150,
														Image.SCALE_DEFAULT);
									} catch (Exception e) {
										img = null;
									}
									if (img != null)
										g.drawImage(img, 0, 0, null);
								}

							};
							jPanel2.add(selectedImage);
							selectedImage.setSize(150, 150);
							selectedImage.setPreferredSize(new Dimension(150,
									150));
						}
					}
				}
			}
			this.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent evt) {
					timer.cancel();
				}
			});
			// pack();
			this.setSize(800, 600);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void drawn() {
		drawnMoney(game.getCash());
		drawnClock(game.getClock().getDate());
		for (int i = 0; i < domain.Farm.width; i++) {
			for (int j = 0; j < domain.Farm.height; j++) {
				tiles.get(i).get(j).update();
			}
		}
	}

	private void drawnMoney(int money) {
		moneyLabel.setText(String.format(
				ui.Translator.getString("moneystring"), money));
	}

	private void drawnClock(Date time) {
		timeLabel.setText(time.toString());
	}

	private void tileMouseClicked(MouseEvent evt) {
		if (this.selected != null) {
			this.selected.selected = false;
			this.selected.repaint();
		}
		this.selected = (TilePanel) evt.getComponent();
		this.selected.selected = true;
		this.selected.repaint();

		this.updateSideBar();
		this.selectedImage.repaint();
	}
	private void updateSideBar(){
		
	}

	private TilePanel getSelected() {
		return selected;
	}

	private void jButton1MouseClicked(MouseEvent evt) {
		try {
			game.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void jPanel3ComponentResized(ComponentEvent evt) {
		jPanel2.setSize(150, evt.getComponent().getSize().height);
	}
}
