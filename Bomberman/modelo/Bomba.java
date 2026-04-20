import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bomba {
	public static final int ACTIVA   = 0;
	public static final int EXPLOTANDO = 1;

	private int posX, posY;
	private int celda_col; 
	private int celda_fila;
	private int estado;
	private int imgActual;
	private int delayAnim;
	private int temporizador;

	private Image[] imagenes;
	private AreaJuego areaJuego;

	public Bomba(AreaJuego areaJuego, int posX, int posY) {
		this.areaJuego = areaJuego;

		this.celda_col  = posX / AreaJuego.ANCHO_CELDA;
		this.celda_fila = posY / AreaJuego.ALTO_CELDA;

		this.posX = celda_col  * AreaJuego.ANCHO_CELDA;
		this.posY = celda_fila * AreaJuego.ALTO_CELDA;

		estado      = ACTIVA;
		imgActual   = 0;
		delayAnim   = 0;
		temporizador = 75;

		imagenes = new Image[3];
		for (int i = 0; i < imagenes.length; i++) {
			imagenes[i] = new ImageIcon(getClass().getResource("Bomba" + i + ".png")).getImage();
		}
	}

	public void actualizar() {
		if (estado == ACTIVA) {

			delayAnim++;
			if (delayAnim == 8) {
				imgActual++;
				if (imgActual == imagenes.length) {
					imgActual = 0;
				}
				delayAnim = 0;
			}

			temporizador--;
			if (temporizador <= 0) {
				estado = EXPLOTANDO;
			}
		}
	}

	public void dibujar(Graphics g) {
		if (estado == ACTIVA) {
			g.drawImage(
					imagenes[imgActual],
					posX, posY,
					AreaJuego.ANCHO_CELDA, AreaJuego.ALTO_CELDA,
					areaJuego
					);
		}
	}

	public int getEstado()     { 
		return estado; 
	}

	public int getCeldaCol()   { 
		return celda_col; 
	}

	public int getCeldaFila()  { 
		return celda_fila; 
	}

	public int getPosX()       { 
		return posX; 
	}

	public int getPosY()       { 
		return posY; 
	}

}