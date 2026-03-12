import java.awt.Color;
import java.awt.Graphics;

public class Circulo extends Cuadrado {
	//DATOS
	private int posXinic, posYinic;
	private Cuadrado pareja;

	//CONSTRUCTORES
	public Circulo() {
		super();
		posXinic=getPosX();
		posYinic=getPosY();
		pareja=null;
	}

	public Circulo(Color color, int ancho, int alto, String letra, int posX, int posY, int velocidad, int dirH, int dirV) {
		super(color, ancho, alto, letra, posX, posY, velocidad, dirH, dirV);
		posXinic=posX;
		posYinic=posY;
		pareja=null;
	}

	//MÉTODOS (FUNCIONES)
	public void dibujar(Graphics g) {
		//Relleno
		g.setColor(getColor());
		g.fillOval(getPosX(), getPosY(), getAncho(), getAlto());

		//Borde
		g.setColor(Color.BLACK);
		g.drawOval(getPosX(), getPosY(), getAncho(), getAlto());

		//Letra
		dibujarLetra(g);
	}

	//GETTERS/SETTERS
	public int getPosXinic() {
		return posXinic;
	}

	public void setPosXinic(int posXinic) {
		this.posXinic = posXinic;
	}

	public int getPosYinic() {
		return posYinic;
	}

	public void setPosYinic(int posYinic) {
		this.posYinic = posYinic;
	}

	public Cuadrado getPareja() {
		return pareja;
	}

	public void setPareja(Cuadrado pareja) {
		this.pareja = pareja;
	}
}
