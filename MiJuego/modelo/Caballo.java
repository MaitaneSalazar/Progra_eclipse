import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Caballo {
	public static final int QUIETO = 0;
	public static final int CORRIENDO = 1;
	public static final int SALTANDO = 2;

	private Image[] arrayImagenesIzq;
	private Image[] arrayImagenesDcha;
	private int velocidad;
	private int posX, posY;
	private int ancho, alto;
	private int dirH, dirV;
	private int estado;
	private int imgActual;
	private int delayAnim;
	private int impulso;
	private int estadoAnterior;

	private AreaJuego areaJuego;

	public Caballo(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
		velocidad=10;
		estado=QUIETO;
		posX=10;
		posY=760;
		ancho=120;
		alto=80;
		dirH = 1;
		dirV = 0;
		imgActual=7;
		cargarImagenes();
		delayAnim = 0;
		impulso = 80;
		estadoAnterior = QUIETO;
	}

	public void cargarImagenes() {
		arrayImagenesIzq = new Image[8];
		arrayImagenesDcha = new Image[8];

		for(int i = 0; i<arrayImagenesIzq.length; i++) {
			arrayImagenesIzq[i]= new ImageIcon(getClass().getResource("CaballoI"+(i+1)+ ".png")).getImage();
			arrayImagenesDcha[i]= new ImageIcon(getClass().getResource("CaballoD"+(i+1)+ ".png")).getImage();
		}

	}

	public void dibujar(Graphics g) {
		if(estado==QUIETO) {
			imgActual = 7;
		}
		if(dirH == 1) {
			g.drawImage(arrayImagenesDcha[imgActual], posX, posY, ancho, alto, areaJuego);

		} else if(dirH == -1) {
			g.drawImage(arrayImagenesIzq[imgActual], posX, posY, ancho, alto, areaJuego);
		}
	}
	
	public void mover() {
		if(estado==QUIETO || estado == SALTANDO) {
			return;
		}
		if(estado == CORRIENDO) {
			if(posX > 0 && dirH == -1 || posX+ancho < areaJuego.getWidth() && dirH== 1) { //Limites de movimiento es la pantalla
				posX = posX + velocidad * dirH;
			} else if(posX+ancho > areaJuego.getWidth() && dirH== 1) {
				areaJuego.setPosXfondo(areaJuego.getPosXfondo()-10);
				if(areaJuego.getPosXfondo()+AreaJuego.ANCHO_FONDO <=areaJuego.getWidth()) {
					areaJuego.getEventosAreaJuego().getReloj().stop();
					areaJuego.getEventosAreaJuego().getRelojTransicion().start();
					areaJuego.setModo(AreaJuego.TRASICION);
				}
			}
			delayAnim++;
			
			if(delayAnim==2) {
				//Animacion
				imgActual++;
				if(imgActual == arrayImagenesDcha.length) {
					imgActual=0;
				}
				delayAnim = 0;
			}
		}
	}
	
	public void saltar() {
		//Si el caballo esta en estado de salto, se movera hacia arriba si esta quieto y arriba y un lado si se esta moviendo
		if(estado == SALTANDO) {
			imgActual = 5;
			if(estadoAnterior == QUIETO) {
				if(impulso>0) {
					posY-=10;
					impulso-=10;
				} else {
					posY+=10;
					impulso-=10;
					if(impulso<=-80) {
						estadoAnterior=estado;
						estado = QUIETO;
						impulso = 80;
					}
				}
				
			} else if (estadoAnterior == CORRIENDO) {
				if(impulso>0) {
					posY-=10;
					if(posX > 0 && dirH == -1 || posX+ancho < areaJuego.getWidth() && dirH== 1) { //Limites de movimiento es la pantalla
						posX = posX + velocidad * dirH;
					}
					impulso-=10;
				} else {
					posY+=10;
					if(posX > 0 && dirH == -1 || posX+ancho < areaJuego.getWidth() && dirH== 1) { //Limites de movimiento es la pantalla
						posX = posX + velocidad * dirH;
					}
					impulso-=10;
					if(impulso<=-80) {
						estadoAnterior=estado;
						estado = CORRIENDO;
						impulso = 80;
					}
				}
			}
		}
	}
	public Rectangle getRect() {
		Rectangle rect;
		rect = new Rectangle(posX+10, posY+20, ancho-20, alto-20);
		return rect;
		
		//return (new Rectangle(posX+10, posY+20, ancho-20, alto-20);
	}

	public Image[] getArrayImagenesIzq() {
		return arrayImagenesIzq;
	}

	public void setArrayImagenesIzq(Image[] arrayImagenesIzq) {
		this.arrayImagenesIzq = arrayImagenesIzq;
	}

	public Image[] getArrayImagenesDcha() {
		return arrayImagenesDcha;
	}

	public void setArrayImagenesDcha(Image[] arrayImagenesDcha) {
		this.arrayImagenesDcha = arrayImagenesDcha;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getImgActual() {
		return imgActual;
	}

	public void setImgActual(int imgActual) {
		this.imgActual = imgActual;
	}

	public AreaJuego getAreaJuego() {
		return areaJuego;
	}

	public void setAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
	}

	public int getDirH() {
		return dirH;
	}

	public void setDirH(int dirH) {
		this.dirH = dirH;
	}

	public int getDirV() {
		return dirV;
	}

	public void setDirV(int dirV) {
		this.dirV = dirV;
	}

	public int getImpulso() {
		return impulso;
	}

	public void setImpulso(int impulso) {
		this.impulso = impulso;
	}

	public int getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(int estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

}
