import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

public class AreaJuego extends JPanel{
	public static final int PRESENT=0;
	public static final int JUEGO=1;
	public static final int COMPROBAR=2;
	
	private int estadoJuego;

	private Cuadrado cuadrado;
	private Circulo circulo;
	private ArrayList<Cuadrado> arrayCuadrados;
	private ArrayList<Circulo> arrayCirculos;

	private String letras="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

	private JuegoLetras juegoLetras;
	private EventosAreaJuego eventosAreaJuego;
	private int aciertos;
	private int fallos;

	//CONSTRUCTORES
	public AreaJuego(JuegoLetras juegoLetras) {
		this.juegoLetras=juegoLetras;
		estadoJuego=PRESENT;
		circulo = new Circulo(Color.PINK, Cuadrado.TAM, Cuadrado.TAM, "a", 20, 500, 8, 1, -1);
		cuadrado = new Cuadrado(Color.CYAN, Cuadrado.TAM, Cuadrado.TAM, "A", 680, 20, 8, -1, 1);

		arrayCuadrados = new ArrayList<Cuadrado>();
		arrayCirculos = new ArrayList<Circulo>();
		eventosAreaJuego = new EventosAreaJuego(this);

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if(estadoJuego==PRESENT) {
			cuadrado.dibujar(g);
			circulo.dibujar(g);
		} else { //importante el orden porque el ultimo que se dibuja es el que esta por encima
			for (Cuadrado c : arrayCuadrados) {
				c.dibujar(g);
			}
			for (Circulo c : arrayCirculos) {
				c.dibujar(g);
			}
			if(estadoJuego==COMPROBAR) {
				g.setFont(new Font("Arial", Font.BOLD, 40));
				g.setColor(Color.GREEN);
				g.drawString("Aciertos: " + aciertos, 80, 400);
				g.setColor(Color.RED);
				g.drawString("Fallos: " + fallos, 80, 450);
			}
		}
	}


	public void crearObjetos() {
		//Crear cuadradados y circulos, posicionarlos
		int cantidad;
		Cuadrado cuadrado;
		Circulo circulo;

		arrayCuadrados.clear();
		arrayCirculos.clear();
		if(juegoLetras.getRdbtnFacil().isSelected()) {
			cantidad = 5;
		} else if(juegoLetras.getRdbtnMedio().isSelected()) {
			cantidad = 10;
		} else {
			cantidad = 15;
		}

		for(int i = 0; i<cantidad; i++) {
			cuadrado = new Cuadrado();
			circulo = new Circulo();
			cuadrado.setPosX(100+(Cuadrado.TAM+Cuadrado.SEP)*(i%5));
			cuadrado.setPosY(20+(Cuadrado.TAM+Cuadrado.SEP)*(i/5));
			arrayCuadrados.add(cuadrado);
			circulo.setPosX(100+(Circulo.TAM+Circulo.SEP)*(i%5));
			circulo.setPosXinic(circulo.getPosX());
			circulo.setPosY(300+(Circulo.TAM+Circulo.SEP)*(i/5));
			circulo.setPosYinic(circulo.getPosY());
			circulo.setColor(Color.PINK);
			arrayCirculos.add(circulo);
		}
		//Crear letra aleatorias para los cuadrados
		crearLetrasCuadrados();
		crearLetrasCirculos();

	}


	private void crearLetrasCirculos() {
		//LLENAR NUEVO ARRAYLIST CON POSICIONES DE LOS CUADRADOS (0,1,2,3,4,5,6...)
		Collections.shuffle(arrayCirculos);
		for (int i = 0; i<arrayCuadrados.size(); i++) {
			arrayCirculos.get(i).setLetra(arrayCuadrados.get(i).getLetra().toLowerCase());
		}
		//PARA CADA

	}

	private void crearLetrasCuadrados() {
		Random r;
		int pos;
		String letra;
		r=new Random();
		for(int i = 0; i<arrayCuadrados.size(); i++) {
			//generar letra y guardarla en el cudrado actual
			pos = r.nextInt(letras.length());
			letra = letras.charAt(pos)+"";
			arrayCuadrados.get(i).setLetra(letra);
			for(int j = 0; j<i; j++) {
				//comprobar que en las anteriores no esta repetida
				if(arrayCuadrados.get(j).getLetra().equals(letra))
				{
					i--;
					break;
				}

			}
		}
	}

	//GETTERS/SETTERS
	public int getEstadoJuego() {
		return estadoJuego;
	}

	public void setEstadoJuego(int estadoJuego) {
		this.estadoJuego = estadoJuego;
	}

	public Cuadrado getCuadrado() {
		return cuadrado;
	}

	public void setCuadrado(Cuadrado cuadrado) {
		this.cuadrado = cuadrado;
	}

	public Circulo getCirculo() {
		return circulo;
	}

	public void setCirculo(Circulo circulo) {
		this.circulo = circulo;
	}

	public ArrayList<Cuadrado> getArrayCuadrados() {
		return arrayCuadrados;
	}

	public void setArrayCuadrados(ArrayList<Cuadrado> arrayCuadrados) {
		this.arrayCuadrados = arrayCuadrados;
	}

	public ArrayList<Circulo> getArrayCirculos() {
		return arrayCirculos;
	}

	public void setArrayCirculos(ArrayList<Circulo> arrayCirculos) {
		this.arrayCirculos = arrayCirculos;
	}

	public String getLetras() {
		return letras;
	}

	public void setLetras(String letras) {
		this.letras = letras;
	}

	public JuegoLetras getJuegoLetras() {
		return juegoLetras;
	}

	public void setJuegoLetras(JuegoLetras juegoLetras) {
		this.juegoLetras = juegoLetras;
	}

	public EventosAreaJuego getEventosAreaJuego() {
		return eventosAreaJuego;
	}

	public void setEventosAreaJuego(EventosAreaJuego eventosAreaJuego) {
		this.eventosAreaJuego = eventosAreaJuego;
	}

	public void comprobar() {
		aciertos = 0;
		fallos = 0;
		estadoJuego = COMPROBAR;
		for(Circulo cir: arrayCirculos) {
			if(cir.getPareja().getLetra().equalsIgnoreCase(cir.getLetra()))
			{
				aciertos++;
				cir.setColor(Color.GREEN);
			}else {
				fallos++;
				cir.setColor(Color.RED);
			}
			
		}
		
	}

}
