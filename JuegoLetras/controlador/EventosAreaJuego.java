import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class EventosAreaJuego {
	
	private AreaJuego areaJuego;
	private Timer reloj;
	
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
