import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class AreaDibujo extends Canvas {

	private Ahorcado ahorcado;
	private EventosAreaDibujo eventosAreaDibujo;
	private int desplazamiento;
	
	//Doble buffer
	private Image imagen;
	private Graphics pantVirtual;
	
	private Image []arrayImagenes;
	private Fichero fichero;
	
	public AreaDibujo(Ahorcado ahorcado) {
		this.ahorcado = ahorcado;
		this.setBackground(Color.WHITE);
		this.repaint();//repinta la funcion paint
		eventosAreaDibujo=new EventosAreaDibujo(this);
		desplazamiento=0;
		
		cargarImagenes();
	}

	private void cargarImagenes() {
		// TODO Auto-generated method stub
		fichero = new Fichero();
		//cargar imagenes por separado
		//arrayImagenes = fichero.leerImagenes();
		
		//cargar sprites que estan juntas en una imagen
		arrayImagenes = fichero.leerSprite();
		
	}

	@Override
	public void paint(Graphics g) { //cuando se crea se ejecuta
		// TODO Auto-generated method stub
		super.paint(g);

		//Dibuja todos los elementos que forman parte del juego
		//dibujarAhorcado(g);	
		dibujarImagenes(g);
	}


	@Override
	public void update(Graphics g) { //se llama cuando llamas repaint y es para que no se vea parpadeo (Doble buffer)
		imagen= createImage(this.getWidth(), this.getHeight()); //reservar espacio y tamaño en ram
		pantVirtual = imagen.getGraphics(); //igualar los graphics
		paint(pantVirtual); //pinta en la pantalla virtual
		g.drawImage(imagen, 0, 0, null); //muestra lo pintado en la pantalla virtual y el tamaño del canvas es el mismo que el de la imagen
	}
	
	private void dibujarImagenes(Graphics g) {
		g.drawImage(arrayImagenes[ahorcado.getNumFallos()], 0, 0, this.getWidth(), this.getHeight(), ahorcado);
		if(ahorcado.getNumFallos() == 7) {
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString(ahorcado.getTeclado().getPalabra(), 60, 340);
		}
	}

	private void dibujarAhorcado(Graphics g) {
		//dibujar patibulo
		g.setColor(Color.BLUE);
		g.drawLine(60, 280, 60, 320);
		g.drawLine(60, 280, 90, 280);
		if(ahorcado.getNumFallos()<7) {
			g.drawLine(90, 280, 210, 280);
		} else {
			g.drawLine(90, 280, 90, 320);
		}
		g.drawLine(210, 280, 160, 280);
		g.drawLine(210, 320, 210, 30);
		g.drawLine(210, 30, 125, 30);

		
		//dibujar partes del cuerpo
		switch (ahorcado.getNumFallos()) {
			case 7:
				desplazamiento += 5;
				//cuerda
				g.drawLine(125, 30, 125, 30+desplazamiento);
				//escribir la palabra correcta
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString(ahorcado.getTeclado().getPalabra(), 80, 350);
				g.setColor(Color.BLUE);
				if(desplazamiento >= 130) {
					eventosAreaDibujo.getReloj().stop();
				}
			case 6:
				//dibujar pierna dcha
				g.drawLine(140, 140+desplazamiento, 150, 170+desplazamiento);
			case 5:
				//dibujar pierna izq
				g.drawLine(110, 140+desplazamiento, 100, 170+desplazamiento);
			case 4:
				//dibujar brazo dcho
				g.drawLine(140, 80+desplazamiento, 160, 120+desplazamiento);
			case 3:
				//dibujar brazo izq
				g.drawLine(110, 80+desplazamiento, 90, 120+desplazamiento);
			case 2:
				//dibujar cuerpo
				g.fillRect(110, 80+desplazamiento, 30, 60);
			case 1:
				//dibujar cabeza
				g.drawOval(100, 30+desplazamiento, 50, 50);
		}
	}

	public EventosAreaDibujo getEventosAreaDibujo() {
		return eventosAreaDibujo;
	}

	public void setEventosAreaDibujo(EventosAreaDibujo eventosAreaDibujo) {
		this.eventosAreaDibujo = eventosAreaDibujo;
	}

	public int getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(int desplazamiento) {
		this.desplazamiento = desplazamiento;
	}
}
