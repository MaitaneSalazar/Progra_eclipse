import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

public class EventosAreaJuego {

	private AreaJuego areaJuego;
	private Circulo circuloSeleccionado;
	private Cuadrado cuadradoSeleccionado;
	private Timer reloj;
	int despX,despY;

	public EventosAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;

		reloj = new Timer(40, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Mover las figuras que forman parte del juego
				areaJuego.getCuadrado().mover();
				areaJuego.getCirculo().mover();
				if(areaJuego.getCuadrado().getRect().intersects(areaJuego.getCirculo().getRect())) {
					reloj.stop();
				}
				areaJuego.repaint();
			}
		});

		reloj.start();

		this.areaJuego.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if(circuloSeleccionado!=null) {
					if(cuadradoSeleccionado!=null) {
						cuadradoSeleccionado.setColor(Color.CYAN);
						circuloSeleccionado.setPosX(cuadradoSeleccionado.getPosX());
						circuloSeleccionado.setPosY(cuadradoSeleccionado.getPosY()+Cuadrado.TAM*3/4);
						circuloSeleccionado.setPareja(cuadradoSeleccionado);
						cuadradoSeleccionado.setEmparejado(true);
					}else {
						circuloSeleccionado.setPosX(circuloSeleccionado.getPosXinic());
						circuloSeleccionado.setPosY(circuloSeleccionado.getPosYinic());
					}
					circuloSeleccionado=null;
					cuadradoSeleccionado=null;

					if(todosEmparejados()) {
						areaJuego.getJuegoLetras().getBtnStart().setEnabled(true);
						areaJuego.getJuegoLetras().getBtnStart().setText("Comprobar");
					}

					areaJuego.repaint();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int mouseX, mouseY;
				mouseX=e.getX();
				mouseY=e.getY();
				Rectangle mouseRect;
				mouseRect = new Rectangle(e.getX(), e.getY(), 1, 1); //LE HACEMOS UN PRQUEÑO RECTANGULO AL RATON 
				//COMPROBAR SI HEMOS COGIDO UN CIRCULO Y ASIGNARLO
				for ( Circulo  circulo : areaJuego.getArrayCirculos() ) {
					if (mouseRect.intersects(circulo.getRect()) && circulo.getPareja()==null) {
						circuloSeleccionado = circulo;
						despX=e.getX()-circuloSeleccionado.getPosX();
						despY=e.getY()-circuloSeleccionado.getPosY();
						break;
					}
				}

			}
		}); // FIN DEL MOUSE LISTENER

		areaJuego.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				Rectangle cirRect, cuadRect;
				if(circuloSeleccionado != null) {
					circuloSeleccionado.setPosX(e.getX()-despX);
					circuloSeleccionado.setPosY(e.getY()-despY);
					cirRect=circuloSeleccionado.getRect();
					for(Cuadrado cuad: areaJuego.getArrayCuadrados()) {
						cuadRect=cuad.getRect();
						if(cirRect.intersects(cuadRect) && !cuad.isEmparejado()) {
							cuad.setColor(Color.YELLOW);
							cuadradoSeleccionado = cuad;
							break;
						} else {
							cuad.setColor(Color.CYAN);
							cuadradoSeleccionado = null;
						}
					}		
					areaJuego.repaint();
				}
			}
		});
	}

	protected boolean todosEmparejados() {
		for(Cuadrado cuad: areaJuego.getArrayCuadrados()) {
			if(!cuad.isEmparejado()) {
				return false;
			}
		}
		return true;
	}

	public AreaJuego getAreaJuego() {
		return areaJuego;
	}

	public void setAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
	}

	public Timer getReloj() {
		return reloj;
	}

	public void setReloj(Timer reloj) {
		this.reloj = reloj;
	}
}
