import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstaculo {

	public static final int PUNTOS = 50;

	private int fila;
	private int col;
	private Image imagen;
	private AreaJuego areaJuego;

	public Obstaculo(AreaJuego areaJuego, int fila, int col) {
		this.areaJuego = areaJuego;
		this.fila      = fila;
		this.col       = col;
		imagen = new ImageIcon(getClass().getResource("Obstaculo.png")).getImage();
	}

	public void dibujar(Graphics g) {
		g.drawImage(imagen,
				col  * AreaJuego.ANCHO_CELDA,
				fila * AreaJuego.ALTO_CELDA,
				AreaJuego.ANCHO_CELDA,
				AreaJuego.ALTO_CELDA,
				areaJuego
				);
	}

	public int getFila() { 
		return fila; 
	}

	public int getCol()  { 
		return col; 
	}

}