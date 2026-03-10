import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EventosForm {
	private VistaForm vistaForm;
	private int cont = 0;
	private int selectAnt = -1;
	
	public EventosForm(VistaForm vistaForm) {
		this.vistaForm=vistaForm;
		
		
		
		vistaForm.getBtnAdd().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!vistaForm.getTextNombre().getText().equals("") & cont < 8)
				{
					vistaForm.getModeloNombres().addElement(vistaForm.getTextNombre().getText());
					vistaForm.getTextos()[cont].setText(vistaForm.getTextNombre().getText());
					vistaForm.getChecks()[cont].setEnabled(true);
					vistaForm.getTextNombre().setText("");
					vistaForm.getTextNombre().requestFocus();
					cont++;
				} else
				{
					JOptionPane.showMessageDialog(vistaForm, "No se puede hacer esta operacion");
				}
			}
		});
		
		vistaForm.getList().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount() == 2) {
					if(vistaForm.getList().getSelectedIndex()>=0) {
						vistaForm.getChecks()[vistaForm.getList().getSelectedIndex()].setSelected(!vistaForm.getChecks()[vistaForm.getList().getSelectedIndex()].isSelected());
					}
				}
			}
		});
		
		vistaForm.getList().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(selectAnt > -1) {
					vistaForm.getMensajes()[selectAnt] = vistaForm.getTextArea().getText();
				}
				for(int pos = 0; pos < vistaForm.getTextos().length; pos++) {
					
					if(pos == vistaForm.getList().getSelectedIndex())
					{
						vistaForm.getTextos()[pos].setBackground(Color.CYAN);
					}else {
						vistaForm.getTextos()[pos].setBackground(vistaForm.getTextNombre().getBackground());
					}
				}
				selectAnt = vistaForm.getList().getSelectedIndex();
				
				switch(vistaForm.getList().getSelectedIndex()) {
				case 0:
					vistaForm.getTextArea().setText(vistaForm.getMensajes()[0]);
					break;
				case 1:
					vistaForm.getTextArea().setText(vistaForm.getMensajes()[1]);
					break;
				case 2:
					vistaForm.getTextArea().setText(vistaForm.getMensajes()[2]);
					break;
				case 3:
					vistaForm.getTextArea().setText(vistaForm.getMensajes()[3]);
					break;
				case 4:
					vistaForm.getTextArea().setText(vistaForm.getMensajes()[4]);
					break;
				case 5:
					vistaForm.getTextArea().setText(vistaForm.getMensajes()[5]);
					break;
				case 6:
					vistaForm.getTextArea().setText(vistaForm.getMensajes()[6]);
					break;
				case 7:
					vistaForm.getTextArea().setText(vistaForm.getMensajes()[7]);
					break;
				}
			}
		});
		
		
		vistaForm.getChckbxTodos().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				for(int pos = 0; pos < vistaForm.getChecks().length; pos++) {
					if(vistaForm.getChecks()[pos].isEnabled())
					{
						vistaForm.getChecks()[pos].setSelected(vistaForm.getChckbxTodos().isSelected());
					}
				}
			}
		});
		
		vistaForm.getBtnSave().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		vistaForm.getBtnExit().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
	}
		
}
