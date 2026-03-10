import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EventosRefranes {
	private Refranes refranes;
	private int numElements;
	
	public EventosRefranes(Refranes refranes) {
		this.refranes=refranes;
		
		refranes.getBtnCargar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fichero;
				Scanner scFichero;
				if(refranes.getRbRefran1().isSelected()) {
					fichero="Refran1.txt";
				}else if(refranes.getRbRefran2().isSelected()) {
					fichero="Refran2.txt";
				}else {
					fichero="Refran3.txt";
				}
				
				try {
					scFichero=new Scanner(new File(fichero));
					refranes.getModelo().clear();
					numElements=0;
					//LLER EL FICHERO LINEA A LINEA GUARDANDO LOS DATOS
					refranes.setStrRefran(scFichero.nextLine());
					while(scFichero.hasNext()) {
						refranes.getModelo().addElement(scFichero.nextLine());
						numElements++;
					}
					scFichero.close();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		refranes.getBtnSubir().addActionListener(new ActionListener() {
			//SUBE EL ELEMENTO SELECCIONADO UNA POSICIÓN ARRIBA Y LO MANTIENE SELECCIONADO
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int pos;
				String palabra;
				pos=refranes.getLstPalabras().getSelectedIndex();
				palabra=refranes.getLstPalabras().getSelectedValue();
				
				if(pos>0) {
					refranes.getModelo().remove(pos);
					refranes.getModelo().add(pos-1, palabra);
					refranes.getLstPalabras().setSelectedIndex(pos-1);
				}
			}
		});
		
		refranes.getBtnEliminar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(refranes.getLstPalabras().getSelectedIndex()!=-1) {
					refranes.getModelo().remove(refranes.getLstPalabras().getSelectedIndex());
				}
			}
		});
		
		refranes.getBtnBajar().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int pos = refranes.getLstPalabras().getSelectedIndex();
		        String palabra = refranes.getLstPalabras().getSelectedValue();

		        // Solo bajar si hay algo seleccionado Y no es el último
		        if (pos != -1 && pos < refranes.getModelo().getSize() - 1) {
		            refranes.getModelo().remove(pos);
		            refranes.getModelo().add(pos + 1, palabra); // insertar una posición abajo
		            refranes.getLstPalabras().setSelectedIndex(pos + 1); // mantener selección
		        }
		    }
		});
		
		refranes.getBtnSalir().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
		
	}//FIN DEL CONSTRUCTOR
			
}







