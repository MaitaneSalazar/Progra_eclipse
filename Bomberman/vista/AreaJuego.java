import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AreaJuego extends JPanel {

    public static final int FILAS = 13;
    public static final int COLS = 15;
    public static final int ANCHO_CELDA = 100;
    public static final int ALTO_CELDA = 65;
    public static final int ANCHO_FONDO = 1600;
    public static final int MURO_FILA = 6;
    public static final int MURO_COL = 7;

    private Image fondo;
    private Image imgMuro;
    private Image[] imagenesMuroEspecial;
    private int imgMuroEspecial;
    private int delayMuroEspecial;
    private boolean muroAnimando;

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
        {1,0,0,0,0,0,1,4,1,0,0,0,0,0,1},
        {1,0,1,0,1,0,1,3,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public AreaJuego(HUD hud) {
        this.hud = hud;
        fondo = new ImageIcon(getClass().getResource("Fondo.png")).getImage();
        imgMuro = new ImageIcon(getClass().getResource("Vacio.png")).getImage();

        imagenesMuroEspecial = new Image[5];
        for (int i = 0; i < imagenesMuroEspecial.length; i++) {
            imagenesMuroEspecial[i] = new ImageIcon(getClass().getResource("muro" + i + ".png")).getImage();
        }
        
        imgMuroEspecial = 0;
        delayMuroEspecial = 0;
        muroAnimando = false;

        eventosAreaJuego = new EventosAreaJuego(this);
        jugador = new Jugador(this, hud);
        arrayEnemigos = new ArrayList<Enemigo>();
        arrayObstaculos = new ArrayList<Obstaculo>();

        generarBloques();
        generarEnemigos(3);
    }

    private void generarBloques() {
        Random random = new Random();

        int[][] celdasLibres = {
            {11, 1}, {11, 2}, {10, 1},  // zona jugador
            {6,  7}, {6, 6} // muro especial
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

    private void generarEnemigos(int cantidad) {
        Random random = new Random();

        int[][] celdasLibres = {
            {11, 1}, {11, 2}, {10, 1},  // zona jugador
            {6,  7}, {6, 6} // muro especial
        };

        int enemigosCreados = 0;
        int intentos = 0;

        while (enemigosCreados < cantidad && intentos < 1000) {
            intentos++;

            int fila = random.nextInt(FILAS - 2) + 1;
            int col  = random.nextInt(COLS  - 2) + 1;

            if (mapa[fila][col] == 0 && !esCeldaLibre(celdasLibres, fila, col)) {
                int posX = col  * ANCHO_CELDA + (ANCHO_CELDA - 80) / 2;
                int posY = fila * ALTO_CELDA  + (ALTO_CELDA  - 80) / 2;
                arrayEnemigos.add(new Enemigo(this, posX, posY));
                mapa[fila][col] = 3; // marcamos temporalmente
                enemigosCreados++;
            }
        }

        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLS; col++) {
                if (mapa[fila][col] == 3 && !(fila == MURO_FILA && col == MURO_COL)) {
                    mapa[fila][col] = 0;
                }
            }
        }
    }

    private boolean esCeldaLibre(int[][] celdasLibres, int fila, int col) {
        for (int[] celda : celdasLibres) {
            if (celda[0] == fila && celda[1] == col) {
                return true;
            }
        }
        return false;
    }

    public void actualizarMuroEspecial() {
        if (!muroAnimando && arrayEnemigos.isEmpty() && mapa[MURO_FILA][MURO_COL] == 3) {
            muroAnimando = true;
            imgMuroEspecial = 0;
            delayMuroEspecial = 0;
        }

        
        if (muroAnimando) {
            delayMuroEspecial++;
            if (delayMuroEspecial == 6) {
                imgMuroEspecial++;
                delayMuroEspecial = 0;

                if (imgMuroEspecial >= imagenesMuroEspecial.length) {
                    mapa[MURO_FILA][MURO_COL] = 0;
                    muroAnimando = false;
                }
            }
        }
    }

    private void dibujarMapa(Graphics g) {
        for (int fila = 0; fila < mapa.length; fila++) {
            for (int col = 0; col < mapa[0].length; col++) {
                int x = col  * ANCHO_CELDA;
                int y = fila * ALTO_CELDA;
                if (mapa[fila][col] == 1) {
                    g.drawImage(imgMuro, x, y, ANCHO_CELDA, ALTO_CELDA, this);
                }
                if (mapa[fila][col] == 3) {
                    g.drawImage(imagenesMuroEspecial[imgMuroEspecial],
                        x, y, ANCHO_CELDA, ALTO_CELDA, this);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(fondo, 0, 0, ANCHO_FONDO, getHeight(), null);
        dibujarMapa(g);

        for (int i = 0; i < arrayObstaculos.size(); i++) {
            arrayObstaculos.get(i).dibujar(g);
        }

        if (bomba != null) {
        	bomba.dibujar(g);
        }
        if (explosion != null) {
        	explosion.dibujar(g);
        }

        for (int i = 0; i < arrayEnemigos.size(); i++) {
            arrayEnemigos.get(i).dibujar(g);
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
                    g2.fillRect(x, y - 10, ANCHO_CELDA, ALTO_CELDA);
                    g2.setColor(Color.RED);
                    g2.drawRect(x, y - 15, ANCHO_CELDA, ALTO_CELDA);
                }
            }
        }

        Rectangle rectJugador = jugador.getRect();
        g2.setColor(new Color(0, 0, 255, 80));
        g2.fillRect(rectJugador.x, rectJugador.y, rectJugador.width, rectJugador.height);
        g2.setColor(Color.BLUE);
        g2.drawRect(rectJugador.x, rectJugador.y, rectJugador.width, rectJugador.height);
    }

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
