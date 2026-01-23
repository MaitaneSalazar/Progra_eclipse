import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

public class EventosTeclado {

	public EventosTeclado(Teclado teclado) {
		JButton []array;
		array = teclado.getArrayTeclado();
		
		for(JButton btn : array) {
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//cogera el texto del boton
					char letra = btn.getText().charAt(0);
					String palabra = teclado.getPalabra();
					JLabel lblPalabra = teclado.getAhorcado().getLblPalabra();
					String palAux = "";
					boolean esta = false;
					//sustituir las posiciones en las que esta la letra del boton si esta en la palabra y poner el boton en verde comprobar final
					for(int pos=0; pos < palabra.length(); pos++) {
						if(letra == palabra.charAt(pos)) {
							palAux = palAux + letra + " ";
							esta = true;
						} else {
							palAux = palAux + lblPalabra.getText().substring(pos*2, pos*2+2);
						}
					}
					lblPalabra.setText(palAux);
					
					if(esta) {
						btn.setBackground(Color.GREEN);
						if(!lblPalabra.getText().contains("_")) {
							teclado.estadoTeclado(false);
							teclado.getAhorcado().getBtnNueva().setEnabled(true);
						}
					} else { //si la letra no esta poner en rojo y sumar fallo y repaint y comprobar final
						btn.setBackground(Color.RED);
						teclado.getAhorcado().setNumFallos(teclado.getAhorcado().getNumFallos()+1);
						teclado.getAhorcado().getAreaDibujo().repaint();
						if(teclado.getAhorcado().getNumFallos() == 7) {
							teclado.estadoTeclado(false);
							teclado.getAhorcado().getBtnNueva().setEnabled(true);
							teclado.getAhorcado().getAreaDibujo().getEventosAreaDibujo().getReloj().start();
						}
					}
					btn.setEnabled(false);
								
					//deshabilitar el boton
				}
			});
		}
	}
}
