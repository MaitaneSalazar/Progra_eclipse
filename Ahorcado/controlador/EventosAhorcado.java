import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosAhorcado {
	
	private Ahorcado ahorcado;
	
	public EventosAhorcado(Ahorcado ahorcado) {
		ahorcado.getBtnNueva().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ahorcado.elegirPalabra();
				ahorcado.getBtnNueva().setEnabled(false);
				ahorcado.getAreaDibujo().setDesplazamiento(0);
				//System.out.println(ahorcado.getAreaDibujo().getWidth() + " - " + ahorcado.getAreaDibujo().getHeight());

			}
		});
		
		ahorcado.getBtnSalir().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
}
