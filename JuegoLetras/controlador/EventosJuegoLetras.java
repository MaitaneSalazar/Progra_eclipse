import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosJuegoLetras {
	private JuegoLetras juegoLetras;

	public EventosJuegoLetras(JuegoLetras juegoLetras) {
		this.juegoLetras= juegoLetras;

		juegoLetras.getBtnStart().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(juegoLetras.getBtnStart().getText().equals("Empezar")) {
					
					//Desactivar boton empezar
					juegoLetras.getBtnStart().setEnabled(false);
					//Desactivar radiobuttons de nivel
					juegoLetras.getRdbtnFacil().setEnabled(false);
					juegoLetras.getRdbtnMedio().setEnabled(false);
					juegoLetras.getRdbtnDificil().setEnabled(false);
					//Crear los arrays de circulos y cuadrados de areadibujo
					juegoLetras.getAreaJuego().crearObjetos();
					//Desactivar el timer
					juegoLetras.getAreaJuego().getEventosAreaJuego().getReloj().stop();
					//Cambiar el estado
					juegoLetras.getAreaJuego().setEstadoJuego(AreaJuego.JUEGO);
				} else {
					juegoLetras.getAreaJuego().comprobar();
					juegoLetras.getBtnStart().setText("Empezar");
					juegoLetras.getRdbtnFacil().setEnabled(true);
					juegoLetras.getRdbtnMedio().setEnabled(true);
					juegoLetras.getRdbtnDificil().setEnabled(true);
				}

				//Repaint
				juegoLetras.getAreaJuego().repaint();
			}
		});
	}
}
