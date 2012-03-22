package ui.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class TitleScreen extends javax.swing.JFrame {
	private static Image backgroundImage;
	private JLabel newGameButton, loadGameButton;
	private JPanel background;

	public TitleScreen() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setSize(480, 300);
			backgroundImage = ImageIO.read(TitleScreen.class.getClassLoader()
					.getResource("ui/swing/images/titleBackground.png"));
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setMinimumSize(new java.awt.Dimension(480, 300));
			this.setMaximumSize(new java.awt.Dimension(480, 300));
			getContentPane().setLayout(null);

			newGameButton = new JLabel();
			getContentPane().add(newGameButton);
			newGameButton.setText(ui.Translator.getString("newGame"));
			newGameButton.setBounds(344, 12, 122, 38);
			newGameButton.setFont(new java.awt.Font("Abyssinica SIL", 1, 16));
			newGameButton.setForeground(new java.awt.Color(0, 0, 0));

			newGameButton.addMouseListener(new MouseAdapter() {
				public void mouseExited(MouseEvent evt) {
					evt.getComponent().setFont(
							new java.awt.Font("Abyssinica SIL", 1, 16));
				}

				public void mouseEntered(MouseEvent evt) {
					evt.getComponent().setFont(
							new java.awt.Font("Abyssinica SIL", 3, 16));
				}

				public void mouseClicked(MouseEvent evt) {
					StartUp.newGame();
				}
			});
			loadGameButton = new JLabel();
			getContentPane().add(loadGameButton);
			loadGameButton.setText(ui.Translator.getString("loadGame"));
			loadGameButton.setBounds(344, 50, 122, 38);
			loadGameButton.setFont(new java.awt.Font("Abyssinica SIL", 1, 16));
			if (domain.Controller.getInstance().saveExists())
				loadGameButton.setForeground(new java.awt.Color(0, 0, 0));
			else
				loadGameButton.setForeground(new java.awt.Color(128, 128, 128));

			loadGameButton.addMouseListener(new MouseAdapter() {
				public void mouseExited(MouseEvent evt) {
					evt.getComponent().setFont(
							new java.awt.Font("Abyssinica SIL", 1, 16));
				}

				public void mouseEntered(MouseEvent evt) {
					evt.getComponent().setFont(
							new java.awt.Font("Abyssinica SIL", 3, 16));
				}

				public void mouseClicked(MouseEvent evt) {
					StartUp.loadGame();
				}
			});
			background = new JPanel() {
				public void paint(Graphics g) {
					super.paint(g);
					g.drawImage(TitleScreen.backgroundImage, 0, 0, null);
				}
			};
			getContentPane().add(background);
			background.setBounds(0, 0, 480, 300);
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
