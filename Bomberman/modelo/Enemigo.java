import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemigo extends Thread{
	private int posX, posY;
	private int ancho, alto;
	private int puntos;
	private int velocidad;
	private Image imagen;
	private AreaJuego areaJuego;
	private Random r;
	private boolean estaVivo;

	public Enemigo(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
		r=new Random();
		ancho=50;
		alto=70;
		posX=r.nextInt(0, areaJuego.getWidth()-ancho); //Aleatoria dentro del panel
		posY-=alto;
		velocidad=r.nextInt(2, 11); //Aleatoria entre 2 y 10
		imagen = new ImageIcon(getClass().getResource("Enemy0.png")).getImage();
	}

	public Rectangle getRect() {
		Rectangle rect;
		rect = new Rectangle(posX, posY, ancho, alto);
		return rect;
	}
}
