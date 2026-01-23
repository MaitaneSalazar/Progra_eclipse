import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class EventosAreaDibujo {
	private AreaDibujo areaDibujo;
	private Timer reloj;
	
	public EventosAreaDibujo(AreaDibujo areaDibujo) {
		this.areaDibujo=areaDibujo;
		reloj = new Timer(40, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				areaDibujo.repaint();
				
			}
		});
	}

	public Timer getReloj() {
		return reloj;
	}

	public void setReloj(Timer reloj) {
		this.reloj = reloj;
	}
}
