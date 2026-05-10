import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HUD extends JPanel {

	private Image fondoHUD;
	private int vidas;
	private int score;

	public HUD() {
		setPreferredSize(new Dimension(1500, 80));
		fondoHUD = new ImageIcon(getClass().getResource("hud.png")).getImage();

		vidas = 3;
		score = 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(fondoHUD, 0, 0, getWidth(), getHeight(), this);

		g.setColor(new Color(255, 128, 0));
		g.setFont(new Font("Arial Black", Font.BOLD, 40));

		g.drawString("SCORE: " + score, 500, 60);

		g.drawString("LIVES: " + vidas, getWidth() - 650, 60);
	}


	public void setVidas(int vidas) {
		this.vidas = vidas;
		repaint();
	}

	public void setScore(int score) {
		this.score = score;
		repaint();
	}

	public int getVidas() { 
		return vidas; 
	}

	public int getScore() { 
		return score; 
	}
}