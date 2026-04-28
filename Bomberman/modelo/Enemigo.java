import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemigo {

	public static final int VIVO = 0;
	public static final int MUERTO = 1;

	private static final int IZQUIERDA = 0;
	private static final int DERECHA = 1;
	private static final int ARRIBA = 2;
	private static final int ABAJO = 3;

	private int posX, posY;
	private int ancho, alto;
	private int velocidad;
	private int direccion;
	private int pasosCambioDireccion;
	private int contadorPasos;
	private int estado;
	private int temporizadorMuerte;

	private Image imgVivo0, imgVivo1;
	private Image imgMuerte;
	private int imgActual;
	private int delayAnim;

	private AreaJuego areaJuego;
	private Random random;

	public Enemigo(AreaJuego areaJuego, int posX, int posY) {
		this.areaJuego = areaJuego;
		this.posX = posX;
		this.posY = posY;

		ancho = 80;
		alto = 80;
		velocidad = 3;
		imgActual = 0;
		delayAnim = 0;
		estado = VIVO;
		temporizadorMuerte = 12;
		random = new Random();

		direccion = random.nextInt(4);
		pasosCambioDireccion = random.nextInt(30) + 20;
		contadorPasos = 0;

		cargarImagenes();
	}

	private void cargarImagenes() {
		imgVivo0 = new ImageIcon(getClass().getResource("Enemy0.png")).getImage();
		imgVivo1 = new ImageIcon(getClass().getResource("Enemy1.png")).getImage();
		imgMuerte = new ImageIcon(getClass().getResource("Enemy2.png")).getImage();
	}

	public void mover() {
		if (estado == MUERTO) return;

		int posAntX = posX;
		int posAntY = posY;

		if (direccion == IZQUIERDA) {
			posX -= velocidad;
		}
		if (direccion == DERECHA) {
			posX += velocidad;
		}
		if (direccion == ARRIBA) {
			posY -= velocidad;
		}
		if (direccion == ABAJO) {
			posY += velocidad;
		}

		if (hayColision()) {
			posX = posAntX;
			posY = posAntY;

			boolean encontrada = false;
			int[] direcciones = {IZQUIERDA, DERECHA, ARRIBA, ABAJO};

			for (int i = direcciones.length - 1; i > 0; i--) {
				int j = random.nextInt(i + 1);
				int temp = direcciones[i];
				direcciones[i] = direcciones[j];
				direcciones[j] = temp;
			}

			for (int d : direcciones) {
				direccion = d;
				if (direccion == IZQUIERDA) {
					posX -= velocidad;
				}
				if (direccion == DERECHA) {
					posX += velocidad;
				}
				if (direccion == ARRIBA) {
					posY -= velocidad;
				}
				if (direccion == ABAJO) {
					posY += velocidad;
				}

				if (!hayColision()) {
					encontrada = true;
					break;
				}

				posX = posAntX;
				posY = posAntY;
			}

			if (!encontrada) {
				return;
			}

			contadorPasos = 0;
			pasosCambioDireccion = random.nextInt(30) + 20;
		}

		contadorPasos++;
		if (contadorPasos >= pasosCambioDireccion) {
			cambiarDireccion();
		}

		delayAnim++;
		if (delayAnim == 5) {
			if (imgActual == 0) {
				imgActual = 1;
			} else {
				imgActual = 0;
			}
			delayAnim = 0;
		}
	}

	public void morir() {
		estado = MUERTO;
	}

	public boolean actualizarMuerte() {
		if (estado == MUERTO) {
			temporizadorMuerte--;
			if (temporizadorMuerte <= 0) {
				return true;
			}
		}
		return false;
	}

	public void dibujar(Graphics g) {
		if (estado == VIVO) {
			Image imgActual;

			if (this.imgActual == 0) {
				imgActual = imgVivo0;
			} else {
				imgActual = imgVivo1;
			}
			g.drawImage(imgActual, posX, posY, ancho, alto, areaJuego);
		} else if (estado == MUERTO) {
			g.drawImage(imgMuerte, posX, posY, ancho, alto, areaJuego);
		}
	}

	private void cambiarDireccion() {
		direccion = random.nextInt(4);
		pasosCambioDireccion = random.nextInt(30) + 20;
		contadorPasos = 0;
	}

	private boolean hayColision() {
		int[][] mapa = areaJuego.getMapa();
		Rectangle rectEnemigo = getRect();

		for (int fila = 0; fila < AreaJuego.FILAS; fila++) {
			for (int col = 0; col < AreaJuego.COLS; col++) {
				if (mapa[fila][col] == 1 || mapa[fila][col] == 2 || mapa[fila][col] == 3) {
					Rectangle rectMuro = new Rectangle(
							col  * AreaJuego.ANCHO_CELDA,
							fila * AreaJuego.ALTO_CELDA,
							AreaJuego.ANCHO_CELDA,
							AreaJuego.ALTO_CELDA
							);
					if (rectEnemigo.intersects(rectMuro)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public Rectangle getRect() {
		int margen = 15;
		return new Rectangle(
				posX + margen,
				posY + margen,
				ancho - margen * 2,
				alto  - margen * 2
				);
	}

	public int getEstado()  { 
		return estado; 
	}

	public int getPosX()    { 
		return posX; 
	}

	public int getPosY()    { 
		return posY; 
	}
}