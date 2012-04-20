package ui.swing;

import java.awt.FlowLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import javax.swing.WindowConstants;

import ui.Translator;

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
	private GameBoard gameBoard;
	private JButton saveButton;
	private JLabel moneyLabel;
	private JPanel toolBarPanel;
	private JLabel timeSkip;
	private JLabel timeSlowDown;
	private JLabel timeSpeedUp;
	private JPanel timeController;
	private JPanel contentPanel;
	private JLabel timeLabel;
	private JToolBar toolBar;
	private SideBar sidebar;
	private domain.Game game;
	public TilePanel selectedPanel = null;
	Timer timer = new Timer();

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
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			BoxLayout layout = new BoxLayout(getContentPane(),
					javax.swing.BoxLayout.Y_AXIS);
			getContentPane().setLayout(layout);
			this.setPreferredSize(new java.awt.Dimension(790, 570));
			{
				toolBar = new JToolBar();
				getContentPane().add(toolBar);
				toolBar.setFloatable(false);
				toolBar.setPreferredSize(new java.awt.Dimension(50, 40));
				{
					toolBarPanel = new JPanel();
					GridBagLayout toolBarPanelLayout = new GridBagLayout();
					toolBar.add(toolBarPanel);
					toolBarPanel.setPreferredSize(new java.awt.Dimension(104,
							27));
					toolBarPanelLayout.rowWeights = new double[] { 0.1, 0.1 };
					toolBarPanelLayout.rowHeights = new int[] { 7, 7 };
					toolBarPanelLayout.columnWeights = new double[] { 0.1, 0.1,
							0.1 };
					toolBarPanelLayout.columnWidths = new int[] { 7, 7, 7 };
					toolBarPanel.setLayout(toolBarPanelLayout);
					toolBarPanel.setOpaque(false);
					{
						saveButton = new JButton();
						toolBarPanel.add(saveButton, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
						saveButton.setText(Translator.getString("SAVE"));
						saveButton.setPreferredSize(new java.awt.Dimension(50,
								40));
						saveButton.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								try {
									game.save();
								} catch (SQLException e) {
									e.printStackTrace();
								}

							}
						});
					}
					{
						timeLabel = new JLabel();
						toolBarPanel.add(timeLabel, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
						timeLabel.setText("time");
					}
					{
						moneyLabel = new JLabel();
						toolBarPanel.add(moneyLabel, new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
						moneyLabel.setText("€ ??");
					}
					{
						timeController = new JPanel();
						FlowLayout timeControllerLayout = new FlowLayout();
						timeControllerLayout.setHgap(10);
						toolBarPanel.add(timeController, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						timeController.setLayout(timeControllerLayout);
						timeController.setOpaque(false);
						{
							timeSlowDown = new JLabel();
							timeController.add(timeSlowDown);
							timeSlowDown.setText(" << ");
							timeSlowDown.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent evt) {
									game.slowDown();
								}
							});
						}
						{
							timeSkip = new JLabel();
							timeController.add(timeSkip);
							timeSkip.setText(" >>| ");
							timeSkip.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent evt) {
									game.skipDay();
								}
							});
						}
						{
							timeSpeedUp = new JLabel();
							timeController.add(timeSpeedUp);
							timeSpeedUp.setText(" >> ");
							timeSpeedUp.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent evt) {
									game.speedUp();
								}
							});
						}
					}
				}
			}
			{
				contentPanel = new JPanel();
				getContentPane().add(contentPanel);
				BoxLayout jPanel3Layout = new BoxLayout(contentPanel,
						javax.swing.BoxLayout.X_AXIS);
				contentPanel.setLayout(jPanel3Layout);
				contentPanel.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent evt) {
						jPanel3ComponentResized(evt);
					}
				});
				{
					gameBoard = new GameBoard(game, this);
					contentPanel.add(gameBoard);
				}
				{
					sidebar = new SideBar(game, this);
					contentPanel.add(sidebar);
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

	public void drawn() {
		drawnMoney(game.getCash());
		drawnClock(game.getClock().getDate());
		gameBoard.update();
		sidebar.update();
	}

	private void drawnMoney(int money) {
		moneyLabel.setText(String.format(
				ui.Translator.getString("moneystring"), money));
	}

	private void drawnClock(Date time) {
		timeLabel.setText(time.toString());
	}

	public void select(TilePanel tile) {
		if (this.selectedPanel != null) {
			this.selectedPanel.selected = false;
			this.selectedPanel.repaint();
		}
		this.selectedPanel = tile;
		this.selectedPanel.selected = true;
		this.selectedPanel.repaint();

		this.sidebar.update();
	}

	TilePanel getSelected() {
		return selectedPanel;
	}

	private void jPanel3ComponentResized(ComponentEvent evt) {
		sidebar.setSize(150, evt.getComponent().getSize().height);
	}

	public String getSelectedType() {
		return game.getTileType(selectedPanel.getCoords());
	}
}
