import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Jugador {
	public static final int VIVO = 0;
	public static final int MUERTO = 1;

	private Image[] arrayImagenesIzq;
	private Image[] arrayImagenesDcha;
	private Image[] arrayImagenesEspalda;
	private Image[] arrayImagenesFrente;
	private Image[] arrayImagenesMuerte;
	private HUD hud;
	private int velocidad;
	private int posX, posY;
	private int ancho, alto;
	private int dirH, dirV;
	private int estado;
	private int imgActual;
	private int delayAnim;
	private int vidas;
	private int invencibilidad;

	private AreaJuego areaJuego;

	public Jugador(AreaJuego areaJuego, HUD hud) {
		this.areaJuego = areaJuego;
		this.hud = hud;
		velocidad=14;
		estado=VIVO;
		posX=100;
		posY=660;
		ancho=105;
		alto=130;
		dirH = 0;
		dirV = 0;
		imgActual=1;
		cargarImagenes();
		delayAnim = 0;
		vidas = 3;
		invencibilidad = 0;

		hud.setVidas(vidas);
		hud.setScore(0);
	}

	public void cargarImagenes() {
		arrayImagenesIzq      = new Image[3];
		arrayImagenesDcha     = new Image[3];
		arrayImagenesEspalda  = new Image[3];
		arrayImagenesFrente   = new Image[3];
		arrayImagenesMuerte   = new Image[7];


		for (int i = 0; i < arrayImagenesIzq.length; i++) {
			arrayImagenesIzq[i]     = new ImageIcon(getClass().getResource("JugadorAndarI" + i + ".png")).getImage();
			arrayImagenesDcha[i]    = new ImageIcon(getClass().getResource("JugadorAndarD" + i + ".png")).getImage();
			arrayImagenesEspalda[i] = new ImageIcon(getClass().getResource("JugadorAndarE" + i + ".png")).getImage();
			arrayImagenesFrente[i]  = new ImageIcon(getClass().getResource("JugadorAndarF" + i + ".png")).getImage();
		}


		for (int i = 0; i < arrayImagenesMuerte.length; i++) {
			arrayImagenesMuerte[i] = new ImageIcon(getClass().getResource("JugadorMuerte" + i + ".png")).getImage();
		}
	}

	public void dibujar(Graphics g) {

		if(estado == VIVO) {
			if (invencibilidad > 0 && (invencibilidad / 4) % 2 == 0) {
				return;
			}

			if(dirH == 0 && dirV == 0) {
				imgActual = 1;
				g.drawImage(arrayImagenesFrente[imgActual], posX, posY, ancho, alto, areaJuego);
			}else if(dirH == 1) {
				g.drawImage(arrayImagenesDcha[imgActual], posX, posY, ancho, alto, areaJuego);
			} else if(dirH == -1) {
				g.drawImage(arrayImagenesIzq[imgActual], posX, posY, ancho, alto, areaJuego);
			} else if(dirV == 1) {
				g.drawImage(arrayImagenesEspalda[imgActual], posX, posY, ancho, alto, areaJuego);
			} else if(dirV == -1) {
				g.drawImage(arrayImagenesFrente[imgActual], posX, posY, ancho, alto, areaJuego);
			}
		} else if (estado == MUERTO) {
			g.drawImage(arrayImagenesMuerte[imgActual], posX, posY, ancho, alto, areaJuego);
		}

	}

	public void mover() {
		if (dirH == 0 && dirV == 0) {
			return;
		}

		int posAntX = posX;
		int posAntY = posY;

		if (posX > 80 && dirH == -1 || posX + ancho < areaJuego.getWidth() - 60 && dirH == 1) {
			posX = posX + velocidad * dirH;
		}
		if (posY > 5 && dirV == 1 || posY + alto < areaJuego.getHeight() - 62 && dirV == -1) {
			posY = posY - velocidad * dirV;
		}

		delayAnim++;
		if (delayAnim == 3) {
			imgActual++;
			if (imgActual == arrayImagenesDcha.length) {
				imgActual = 0;
			}
			delayAnim = 0;
		}

		Rectangle rectNuevo = new Rectangle(posX+30, posY+60, ancho-60, alto-95);
		if (hayColision(rectNuevo)) {
			posX = posAntX;
			posY = posAntY;
		}
	}

	private boolean hayColision(Rectangle rectJugador) {
	    int[][] mapa = areaJuego.getMapa();

	    for (int fila = 0; fila < AreaJuego.FILAS; fila++) {
	        for (int col = 0; col < AreaJuego.COLS; col++) {
	            if (mapa[fila][col] == 1 || mapa[fila][col] == 2) {
	                Rectangle rectMuro = new Rectangle(
	                    col  * AreaJuego.ANCHO_CELDA,
	                    fila * AreaJuego.ALTO_CELDA - 15,
	                    AreaJuego.ANCHO_CELDA,
	                    AreaJuego.ALTO_CELDA
	                );
	                if (rectJugador.intersects(rectMuro)) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}

	public void perderVida() {
		if (invencibilidad > 0) return;

		vidas--;
		if (vidas <= 0) {
			vidas = 0;
			estado = MUERTO;
			imgActual = 0;  
			delayAnim = 0;
		}
		hud.setVidas(vidas);
		invencibilidad = 60;
	}
	public void morir() {
		// Si ya es la ultima imagen para
		if (imgActual == arrayImagenesMuerte.length - 1) {
			return;
		}

		delayAnim++;
		if (delayAnim == 5) {
			imgActual++;
			delayAnim = 0;
		}
	}

	public void actualizar() {
		if (invencibilidad > 0) {
			invencibilidad--;
		}
	}

	public void sumarPuntos(int puntos) {
		int scoreActual = hud.getScore();
		hud.setScore(scoreActual + puntos);
	}

	public Rectangle getRect() {
		Rectangle rect;
		rect = new Rectangle(posX+30, posY+60, ancho-60, alto-95);
		return rect;
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

}
