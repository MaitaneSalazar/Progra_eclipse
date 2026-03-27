import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AreaJuego extends JPanel{
	public static final int ANCHO_FONDO = 2000;
	public static final int JUEGO=1;
	public static final int TRASICION=2;
	private int puntuacion;
	private Image[] fondos;
	private int nivel;
	private Caballo caballo;
	private ArrayList<Jinete> arrayJinetes;
	private EventosAreaJuego eventosAreaJuego;
	private int posXfondo;
	private int modo;

	public AreaJuego() {
		fondos = new Image[3];
		for(int i = 0; i<fondos.length; i++) {
			fondos[i]= new ImageIcon(getClass().getResource("montana"+(i+1)+ ".jpg")).getImage();
		}
		nivel = 0;
		posXfondo = 0;
		modo = JUEGO;
		caballo = new Caballo(this);
		arrayJinetes = new ArrayList<Jinete>();
		eventosAreaJuego = new EventosAreaJuego(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		/*Dibujar los fondos uno detras de otro
		g.drawImage(fondos[0], posXfondo, 0, ANCHO_FONDO, getHeight(), null);
		g.drawImage(fondos[1], posXfondo+ANCHO_FONDO, 0, ANCHO_FONDO, getHeight(), null);
		g.drawImage(fondos[2], posXfondo+ANCHO_FONDO*2, 0, ANCHO_FONDO, getHeight(), null);*/
		
		//Dibujar fondos con transicion
		
		if(modo == JUEGO) {
			g.drawImage(fondos[nivel], posXfondo, 0, ANCHO_FONDO, getHeight(), null);
		}
		if(modo == TRASICION) {
			g.drawImage(fondos[nivel], posXfondo, 0, ANCHO_FONDO, getHeight(), null);
			g.drawImage(fondos[(nivel+1)%3], posXfondo+ANCHO_FONDO, 0, ANCHO_FONDO, getHeight(), null);
		}
		caballo.dibujar(g);
		for(Jinete jinete: arrayJinetes) {
			jinete.dibujar(g);
		}
		
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Image[] getFondos() {
		return fondos;
	}

	public void setFondos(Image[] fondos) {
		this.fondos = fondos;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Caballo getCaballo() {
		return caballo;
	}

	public void setCaballo(Caballo caballo) {
		this.caballo = caballo;
	}

	public EventosAreaJuego getEventosAreaJuego() {
		return eventosAreaJuego;
	}

	public void setEventosAreaJuego(EventosAreaJuego eventosAreaJuego) {
		this.eventosAreaJuego = eventosAreaJuego;
	}

	public int getPosXfondo() {
		return posXfondo;
	}

	public void setPosXfondo(int posXfondo) {
		this.posXfondo = posXfondo;
	}

	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}

	public ArrayList<Jinete> getArrayJinetes() {
		return arrayJinetes;
	}

	public void setArrayJinetes(ArrayList<Jinete> arrayJinetes) {
		this.arrayJinetes = arrayJinetes;
	}

}
