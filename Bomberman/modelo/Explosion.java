import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Explosion {

	public static final int RADIO = 2;

	private int duracion;
	private Image imgCentro;
	private Image imgHorizontal;
	private Image imgVertical;
	private Image imgFinHorizontalD;
	private Image imgFinVerticalD;
	private Image imgFinHorizontalI;
	private Image imgFinVerticalI;

	private ArrayList<int[]> celdasArriba;
	private ArrayList<int[]> celdasAbajo;
	private ArrayList<int[]> celdasIzquierda;
	private ArrayList<int[]> celdasDerecha;
	private int[] celdaCentro;

	private AreaJuego areaJuego;

	public Explosion(AreaJuego areaJuego, int celdaFila, int celdaCol) {
		this.areaJuego = areaJuego;
		this.celdaCentro = new int[]{celdaFila, celdaCol};
		this.duracion = 15;

		cargarImagenes();
		calcularCeldas();
	}

	private void cargarImagenes() {
		imgCentro = new ImageIcon(getClass().getResource("FuegoCentro.png")).getImage();
		imgHorizontal = new ImageIcon(getClass().getResource("FuegoHorizontal.png")).getImage();
		imgVertical = new ImageIcon(getClass().getResource("FuegoVertical.png")).getImage();
		imgFinHorizontalD = new ImageIcon(getClass().getResource("FuegoHorizonalFinD.png")).getImage();
		imgFinVerticalD = new ImageIcon(getClass().getResource("FuegoVerticalFinD.png")).getImage();
		imgFinHorizontalI = new ImageIcon(getClass().getResource("FuegoHorizonalFinI.png")).getImage();
		imgFinVerticalI = new ImageIcon(getClass().getResource("FuegoVerticalFinI.png")).getImage();
	}


	private void calcularCeldas() {
		celdasArriba = calcularRama(-1, 0); // arriba: fila -1
		celdasAbajo = calcularRama( 1, 0); // abajo: fila +1
		celdasIzquierda = calcularRama( 0,-1); // izquierda: col  -1
		celdasDerecha = calcularRama( 0, 1); // derecha: col  +1

		destruirBloques(celdasArriba);
		destruirBloques(celdasAbajo);
		destruirBloques(celdasIzquierda);
		destruirBloques(celdasDerecha);
	}

	private ArrayList<int[]> calcularRama(int dFila, int dCol) {
		ArrayList<int[]> celdas = new ArrayList<>();
		int[][] mapa = areaJuego.getMapa();

		for (int i = 1; i <= RADIO; i++) {
			int fila = celdaCentro[0] + dFila * i;
			int col  = celdaCentro[1] + dCol  * i;

			if (fila < 0 || fila >= AreaJuego.FILAS || col < 0 || col >= AreaJuego.COLS) {
				break;
			}

			if (mapa[fila][col] == 1 || mapa[fila][col] == 3) {
				break;
			}

			if (mapa[fila][col] == 2) {
				celdas.add(new int[]{fila, col});
				break;
			}

			celdas.add(new int[]{fila, col});
		}
		return celdas;
	}

	private void destruirBloques(ArrayList<int[]> celdas) {
		int[][] mapa = areaJuego.getMapa();
		for (int[] celda : celdas) {
			if (mapa[celda[0]][celda[1]] == 2) {
				areaJuego.destruirObstaculo(celda[0], celda[1]);
				areaJuego.getJugador().sumarPuntos(Obstaculo.PUNTOS);
			}
		}
	}

	public void actualizar() {
		duracion--;
	}

	public boolean haTerminado() {
		return duracion <= 0;
	}

	public boolean colisionaCon(Rectangle rect) {
		if (rect.intersects(getRectCelda(celdaCentro[0], celdaCentro[1]))) {
			return true;
		}

		for (int[] celda : celdasArriba) {
			if (rect.intersects(getRectCelda(celda[0], celda[1]))) {
				return true;
			}
		}
		for (int[] celda : celdasAbajo) {
			if (rect.intersects(getRectCelda(celda[0], celda[1]))) {
				return true;
			}
		}
		for (int[] celda : celdasIzquierda) {
			if (rect.intersects(getRectCelda(celda[0], celda[1]))) {
				return true;
			}
		}
		for (int[] celda : celdasDerecha) { 
			if (rect.intersects(getRectCelda(celda[0], celda[1]))) {
				return true;
			}
		}

		return false;
	}

	public void dibujar(Graphics g) {
		dibujarCelda(g, imgCentro, celdaCentro[0], celdaCentro[1]);

		dibujarRama(g, celdasArriba, imgVertical, imgFinVerticalI);
		dibujarRama(g, celdasAbajo, imgVertical, imgFinVerticalD);
		dibujarRama(g, celdasIzquierda, imgHorizontal, imgFinHorizontalI);
		dibujarRama(g, celdasDerecha, imgHorizontal, imgFinHorizontalD);
	}

	private void dibujarRama(Graphics g, ArrayList<int[]> celdas, Image imgMedio, Image imgFin) {
		for (int i = 0; i < celdas.size(); i++) {
			int[] celda = celdas.get(i);

			if (i == celdas.size() - 1) {
				dibujarCelda(g, imgFin, celda[0], celda[1]);
			} else {
				dibujarCelda(g, imgMedio, celda[0], celda[1]);
			}
		}
	}

	private void dibujarCelda(Graphics g, Image img, int fila, int col) {
		g.drawImage(img, col  * AreaJuego.ANCHO_CELDA, fila * AreaJuego.ALTO_CELDA, AreaJuego.ANCHO_CELDA, AreaJuego.ALTO_CELDA, areaJuego);
	}

	private Rectangle getRectCelda(int fila, int col) {
		return new Rectangle(col * AreaJuego.ANCHO_CELDA, fila * AreaJuego.ALTO_CELDA, AreaJuego.ANCHO_CELDA, AreaJuego.ALTO_CELDA);
	}

}
