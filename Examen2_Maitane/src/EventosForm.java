import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosForm {
	private VistaForm vistaForm;
	private int cont = 0;
	
	public EventosForm(VistaForm vistaForm) {
		this.vistaForm=vistaForm;
		
		vistaForm.getBtnAdd().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(vistaForm.getTextNombre().getText() != "" & cont < 8)
				{
					vistaForm.getModeloNombres().addElement(vistaForm.getTextNombre().getText());
					vistaForm.getTextNombre().setText("");
					vistaForm.getTextNombre().requestFocus();
					cont++;
				}
				
			}
		});
		
	}
		
}
