import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AreaJuego extends JPanel{
	public static final int ANCHO_FONDO = 1600;
	public static final int FILAS     = 13;
	public static final int COLS      = 15;
	public static final int ANCHO_CELDA = 100;
	public static final int ALTO_CELDA = 65;
	private Image fondo;
	private Image imgBloque;
	private Image imgMuro;
	private int posXfondo;
	private EventosAreaJuego eventosAreaJuego;
	private Jugador jugador;
	private ArrayList<Enemigo> arrayEnemigos;
	private int[][] mapa = {
		    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
		    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
		    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
		    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
		    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
		    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

	public AreaJuego() {
		fondo = new ImageIcon(getClass().getResource("Fondo.png")).getImage();
		imgBloque = new ImageIcon(getClass().getResource("Extra.png")).getImage();
		imgMuro = new ImageIcon(getClass().getResource("Vacio.png")).getImage();
		posXfondo = 0;
		eventosAreaJuego = new EventosAreaJuego(this);
		jugador = new Jugador(this);
		arrayEnemigos = new ArrayList<Enemigo>();
	}
	
	private void dibujarMapa(Graphics g) {
        for (int fila = 0; fila < mapa.length; fila++) {
            for (int col = 0; col < mapa[0].length; col++) {
                int x = col  * ANCHO_CELDA;
                int y = fila * ALTO_CELDA;
                if (mapa[fila][col] == 1)
                    g.drawImage(imgMuro, x, y, ANCHO_CELDA, ALTO_CELDA, this);
                if (mapa[fila][col] == 2)
                    g.drawImage(imgBloque, x, y, ANCHO_CELDA, ALTO_CELDA, this);
            }
        }
    }

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(fondo, posXfondo, 0, ANCHO_FONDO, getHeight(), null);
		dibujarMapa(g);
		jugador.dibujar(g);
	}


	public int[][] getMapa() {
		return mapa;
	}

	public void setMapa(int[][] mapa) {
		this.mapa = mapa;
	}

	public int getPosXfondo() {
		return posXfondo;
	}

	public void setPosXfondo(int posXfondo) {
		this.posXfondo = posXfondo;
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
}
