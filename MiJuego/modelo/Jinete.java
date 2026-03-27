import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Jinete extends Thread{
	private int posX, posY;
	private int ancho, alto;
	private int puntos;
	private int velocidad;
	private Image imagen;
	private AreaJuego areaJuego;
	private Random r;
	private boolean estaVivo;

	public Jinete(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
		r=new Random();
		ancho=50;
		alto=70;
		posX=r.nextInt(0, areaJuego.getWidth()-ancho); //Aleatoria dentro del panel
		posY-=alto;
		velocidad=r.nextInt(2, 11); //Aleatoria entre 2 y 10
		imagen = new ImageIcon(getClass().getResource("Jinete.png")).getImage();
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		estaVivo = true;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(estaVivo) {
			caer();
			if(posY>= areaJuego.getHeight()) {
				estaVivo=false;
				areaJuego.getArrayJinetes().remove(this);
			}
			try { //temporizar
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void dibujar(Graphics g) {
		g.drawImage(imagen, posX, posY, ancho, alto, areaJuego);
	}
	
	public void caer() {
		//Caer desde arriba moviendose en verticual aleatoriamente de izquierda a derecha
		int num;
		posY+=velocidad;
		num=r.nextInt(3);
		if(num==1 && posX + ancho < areaJuego.getWidth()-velocidad) {
			posX+=velocidad;
		} else if(num==2 && posX > velocidad) {
			posX-=velocidad;
		}
	}
	
	public Rectangle getRect() {
		Rectangle rect;
		rect = new Rectangle(posX, posY, ancho, alto);
		return rect;
		
		//return (new Rectangle(posX, posY, ancho, alto);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	public boolean isEstaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}
}
