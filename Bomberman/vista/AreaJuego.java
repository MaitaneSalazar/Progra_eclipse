import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AreaJuego extends JPanel{
	public static final int ANCHO_FONDO = 1600;
	private Image fondo;
	private int posXfondo;
	private EventosAreaJuego eventosAreaJuego;
	private Jugador jugador;
	private ArrayList<Enemigo> arrayEnemigos;

	public AreaJuego() {
		fondo = new ImageIcon(getClass().getResource("Fondo.png")).getImage();
		posXfondo = 0;
		eventosAreaJuego = new EventosAreaJuego(this);
		jugador = new Jugador(this);
		arrayEnemigos = new ArrayList<Enemigo>();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(fondo, posXfondo, 0, ANCHO_FONDO, getHeight(), null);
		jugador.dibujar(g);
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
