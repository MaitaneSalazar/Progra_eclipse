import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Cuadrado {
	public static final int TAM=30;
	public static final int SEP=35;
	//DATOS
	private Color color;
	private int ancho, alto;
	private String letra;
	private int posX, posY;
	
	private int velocidad;
	private int dirH; //-1 izquierda, 1 derecha, 0 no se mueve en horizontal
	private int dirV; //-1 arriba, 1 abajo, 0 no se mueve en vertical
	
	private AreaJuego areaJuego;
	
	//CONSTRUCTORES
	public Cuadrado() {
		color=Color.CYAN;
		ancho=TAM;
		alto=TAM;
		letra="";
		posX=0;
		posY=0;
		velocidad=5;
		dirH=-1;
		dirV=1;
	}
	
	public Cuadrado(Color color, int ancho, int alto, String letra, int posX, int posY, int velocidad, int dirH, int dirV) {
		super();
		this.color = color;
		this.ancho = ancho;
		this.alto = alto;
		this.letra = letra;
		this.posX = posX;
		this.posY = posY;
		this.velocidad = velocidad;
		this.dirH = dirH;
		this.dirV = dirV;
	}
	
	//MÉTODOS (FUNCIONES)
	public void dibujar(Graphics g) {
		//Relleno
		g.setColor(color);
		g.fillRect(posX, posY, ancho, alto);
		
		//Borde
		g.setColor(Color.BLACK);
		g.drawRect(posX, posY, ancho, alto);
		
		//Letra
		dibujarLetra(g);
	}
	
	public void dibujarLetra(Graphics g) {
		FontMetrics fm;
		Rectangle2D rect;
		
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		fm=g.getFontMetrics();
		rect=fm.getStringBounds(letra, g);
		g.drawString(letra, posX+ancho/2-(int)(rect.getWidth()/2), posY+alto/2+(int)(rect.getHeight()/2)-2);
	}
	
	public void mover()
	{
		posX = posX + velocidad * dirH;
		posY = posY + velocidad * dirV;
		//Controlar rebotes con las paredes
		if(posX<=0 || posX >= areaJuego.getWidth()-ancho) {
			dirH=-dirH;
		}
		if(posY<=0 || posY >= areaJuego.getHeight()-alto) {
			dirV=-dirV;
		}
			
	}
	
	//GETTERS/SETTERS
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
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

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
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

	public AreaJuego getAreaJuego() {
		return areaJuego;
	}

	public void setAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
	}

	public Rectangle getRect() {
		Rectangle r;
		
		r= new Rectangle(posX, posY, ancho, alto);
		
		return r;
		
	}
	
}
