import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AreaJuego extends JPanel{
	public static final int ANCHO_FONDO = 1600;
	private Image fondo;
	private EventosAreaJuego eventosAreaJuego;
	
	public AreaJuego() {
		fondo = new ImageIcon(getClass().getResource("Fondo.png")).getImage();
		eventosAreaJuego = new EventosAreaJuego(this);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(fondo, 0, 0, ANCHO_FONDO, getHeight(), null);
	}
}
