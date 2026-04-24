import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AreaJuego extends JPanel{
	public static final int FILAS     = 13;
	public static final int COLS      = 15;
	public static final int ANCHO_CELDA = 100;
	public static final int ALTO_CELDA = 65;
	public static final int ANCHO_FONDO = 1600;
	private Image fondo;
	private Image imgMuro;
	private HUD hud;
	private EventosAreaJuego eventosAreaJuego;
	private Jugador jugador;
	private Bomba bomba;
	private Explosion explosion;
	private ArrayList<Enemigo> arrayEnemigos;
	private ArrayList<Obstaculo> arrayObstaculos;
	private int[][] mapa = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,0,1,0,1,1,1,0,1,0,1,0,1},
			{1,0,0,0,0,0,1,0,1,0,0,0,0,0,1},
			{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

	public AreaJuego(HUD hud) {
		this.hud = hud;
		fondo = new ImageIcon(getClass().getResource("Fondo.png")).getImage();
		imgMuro = new ImageIcon(getClass().getResource("Vacio.png")).getImage();
		eventosAreaJuego = new EventosAreaJuego(this);
		jugador = new Jugador(this, hud);
		arrayEnemigos = new ArrayList<Enemigo>();
		arrayObstaculos = new ArrayList<Obstaculo>();
		generarBloques();
		// Añadir enemigos en posiciones concretas libres del mapa
		arrayEnemigos.add(new Enemigo(this, 1 * ANCHO_CELDA, 1 * ALTO_CELDA)); // celda [1][1]
		arrayEnemigos.add(new Enemigo(this, 3 * ANCHO_CELDA, 1 * ALTO_CELDA)); // celda [1][3]
		arrayEnemigos.add(new Enemigo(this, 1 * ANCHO_CELDA, 3 * ALTO_CELDA)); // celda [3][1]
	}

	private void dibujarMapa(Graphics g) {
		for (int fila = 0; fila < mapa.length; fila++) {
			for (int col = 0; col < mapa[0].length; col++) {
				int x = col  * ANCHO_CELDA;
				int y = fila * ALTO_CELDA;
				if (mapa[fila][col] == 1) {
					g.drawImage(imgMuro, x, y, ANCHO_CELDA, ALTO_CELDA, this);
				}
			}
		}
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(fondo, 0, 0, ANCHO_FONDO, getHeight(), null);
		dibujarMapa(g);

		if (bomba != null) {
			bomba.dibujar(g);
		}
		if (explosion != null) {
			explosion.dibujar(g);
		}

		for (int i = 0; i < arrayEnemigos.size(); i++) {
			arrayEnemigos.get(i).dibujar(g);
		}

		for (int i = 0; i < arrayObstaculos.size(); i++) {
			arrayObstaculos.get(i).dibujar(g);
		}

		jugador.dibujar(g);

		dibujarDebugColisiones(g);
	}

	private void dibujarDebugColisiones(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;


		for (int fila = 0; fila < FILAS; fila++) {
			for (int col = 0; col < COLS; col++) {
				int x = col * ANCHO_CELDA;
				int y = fila * ALTO_CELDA + 20;
				if (mapa[fila][col] == 1) {
					g2.setColor(new Color(255, 0, 0, 80));
					g2.fillRect(x, y-10, ANCHO_CELDA, ALTO_CELDA);
					g2.setColor(Color.RED);
					g2.drawRect(x, y-15, ANCHO_CELDA, ALTO_CELDA);
				}
			}
		}

		// Dibujar hitbox del jugador
		Rectangle rectJugador = jugador.getRect();
		g2.setColor(new Color(0, 0, 255, 80)); 
		g2.fillRect(rectJugador.x, rectJugador.y, rectJugador.width, rectJugador.height);
		g2.setColor(Color.BLUE);
		g2.drawRect(rectJugador.x, rectJugador.y, rectJugador.width, rectJugador.height);
	}

	private boolean esCeldaLibre(int[][] celdasLibres, int fila, int col) {
		for (int[] celda : celdasLibres) {
			if (celda[0] == fila && celda[1] == col) {
				return true;
			}
		}
		return false;
	}

	private void generarBloques() {
		Random random = new Random();

		int[][] celdasLibres = {
				{11, 1}, {11, 2}, {10, 1},
				{1, 1},  {1, 2},
				{1, 3},  {1, 4},
				{3, 1},  {3, 2}
		};

		for (int fila = 0; fila < FILAS; fila++) {
			for (int col = 0; col < COLS; col++) {
				if (mapa[fila][col] == 0) {
					if (!esCeldaLibre(celdasLibres, fila, col)) {
						if (random.nextInt(100) < 60) {
							mapa[fila][col] = 2;
							arrayObstaculos.add(new Obstaculo(this, fila, col));
						}
					}
				}
			}
		}
	}

	public ArrayList<Obstaculo> getArrayObstaculos() { return arrayObstaculos; }

	// Elimina el obstáculo de la lista y pone la celda a 0
	public void destruirObstaculo(int fila, int col) {
		mapa[fila][col] = 0;
		for (int i = arrayObstaculos.size() - 1; i >= 0; i--) {
			Obstaculo o = arrayObstaculos.get(i);
			if (o.getFila() == fila && o.getCol() == col) {
				arrayObstaculos.remove(i);
				break;
			}
		}
	}

	public int[][] getMapa() {
		return mapa;
	}

	public void setMapa(int[][] mapa) {
		this.mapa = mapa;
	}

	public EventosAreaJuego getEventosAreaJuego() {
		return eventosAreaJuego;
	}

	public void setEventosAreaJuego(EventosAreaJuego eventosAreaJuego) {
		this.eventosAreaJuego = eventosAreaJuego;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public ArrayList<Enemigo> getArrayEnemigos() {
		return arrayEnemigos;
	}

	public void setArrayEnemigos(ArrayList<Enemigo> arrayEnemigos) {
		this.arrayEnemigos = arrayEnemigos;
	}

	public HUD getHud() {
		return hud;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
	}

	public Bomba getBomba() {
		return bomba;
	}

	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
	}

	public Explosion getExplosion() {
		return explosion;
	}

	public void setExplosion(Explosion explosion) {
		this.explosion = explosion;
	}
}
