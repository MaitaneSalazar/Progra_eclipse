import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.function.ToIntFunction;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EventosPeliculas {

	private VistaPeliculas vistaPeliculas;
	private AreaPeliculas areaPeliculas;

	public EventosPeliculas(VistaPeliculas vistaPeliculas, JPanel area) {
		this.vistaPeliculas = vistaPeliculas;

		vistaPeliculas.getBtnTodos().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vistaPeliculas.getModelo1().removeAllElements();

				Scanner sc;

				sc = new Scanner(System.in);

				try {
					sc = new Scanner(new File("peliculas.txt"));
					while(sc.hasNext()) {
						if(sc.hasNextInt()) {
							sc.nextLine();
						}else if(sc.hasNextLine())
						{
							vistaPeliculas.getModelo1().addElement(sc.nextLine() + "");
						}

					}
				} catch (FileNotFoundException e1) {
					//e.printStackTrace();
					System.out.println("No se puede encontrar el fichero");
				}
				sc.close();
			}
		});
		vistaPeliculas.getBtnA().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vistaPeliculas.getModelo1().removeAllElements();

				Scanner sc;

				sc = new Scanner(System.in);
				try {
					sc = new Scanner(new File("peliculas.txt"));
					while(sc.hasNext()) {
						if(sc.hasNextInt()) {
							sc.nextLine();
						}else if(sc.hasNextLine())
						{

							String pelicula = sc.nextLine();
							
							if(pelicula.substring(0, 1).equals(vistaPeliculas.getBtnA().getText())) {
								vistaPeliculas.getModelo1().addElement(pelicula);
							}

						}

					}
				} catch (FileNotFoundException e1) {
					//e.printStackTrace();
					System.out.println("No se puede encontrar el fichero");
				}
				sc.close();
			}
		});
		vistaPeliculas.getBtnE().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vistaPeliculas.getModelo1().removeAllElements();

				Scanner sc;

				sc = new Scanner(System.in);
				try {
					sc = new Scanner(new File("peliculas.txt"));
					while(sc.hasNext()) {
						if(sc.hasNextInt()) {
							sc.nextLine();
						}else if(sc.hasNextLine())
						{

							String pelicula = sc.nextLine();
							
							if(pelicula.substring(0, 1).equals(vistaPeliculas.getBtnE().getText())) {
								vistaPeliculas.getModelo1().addElement(pelicula);
							}

						}

					}
				} catch (FileNotFoundException e1) {
					//e.printStackTrace();
					System.out.println("No se puede encontrar el fichero");
				}
				sc.close();
			}
		});
		vistaPeliculas.getBtnI().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vistaPeliculas.getModelo1().removeAllElements();

				Scanner sc;

				sc = new Scanner(System.in);
				try {
					sc = new Scanner(new File("peliculas.txt"));
					while(sc.hasNext()) {
						if(sc.hasNextInt()) {
							sc.nextLine();
						}else if(sc.hasNextLine())
						{

							String pelicula = sc.nextLine();
							
							if(pelicula.substring(0, 1).equals(vistaPeliculas.getBtnI().getText())) {
								vistaPeliculas.getModelo1().addElement(pelicula);
							}

						}

					}
				} catch (FileNotFoundException e1) {
					//e.printStackTrace();
					System.out.println("No se puede encontrar el fichero");
				}
				sc.close();
			}
		});

		vistaPeliculas.getBtnO().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vistaPeliculas.getModelo1().removeAllElements();

				Scanner sc;

				sc = new Scanner(System.in);
				try {
					sc = new Scanner(new File("peliculas.txt"));
					while(sc.hasNext()) {
						if(sc.hasNextInt()) {
							sc.nextLine();
						}else if(sc.hasNextLine())
						{

							String pelicula = sc.nextLine();
							
							if(pelicula.substring(0, 1).equals(vistaPeliculas.getBtnO().getText())) {
								vistaPeliculas.getModelo1().addElement(pelicula);
							}

						}

					}
				} catch (FileNotFoundException e1) {
					//e.printStackTrace();
					System.out.println("No se puede encontrar el fichero");
				}
				sc.close();
			}
		});
		
		vistaPeliculas.getBtnU().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vistaPeliculas.getModelo1().removeAllElements();

				Scanner sc;

				sc = new Scanner(System.in);
				try {
					sc = new Scanner(new File("peliculas.txt"));
					while(sc.hasNext()) {
						if(sc.hasNextInt()) {
							sc.nextLine();
						}else if(sc.hasNextLine())
						{

							String pelicula = sc.nextLine();
							
							if(pelicula.substring(0, 1).equals(vistaPeliculas.getBtnU().getText())) {
								vistaPeliculas.getModelo1().addElement(pelicula);
							}

						}

					}
				} catch (FileNotFoundException e1) {
					//e.printStackTrace();
					System.out.println("No se puede encontrar el fichero");
				}
				sc.close();
			}
		});

		vistaPeliculas.getBtnFlecha1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(vistaPeliculas.getList1().getSelectedValue() != null) {
					if(vistaPeliculas.getComboBoxYear().getSelectedIndex() != 0) {

						vistaPeliculas.getModelo2().addElement(vistaPeliculas.getComboBoxYear().getSelectedItem() + " - " + vistaPeliculas.getList1().getSelectedValue());
					} else {
						vistaPeliculas.getModelo2().addElement("???? - " + vistaPeliculas.getList1().getSelectedValue());
					}
				}
			}
		});

		vistaPeliculas.getBtnFlecha2().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				area.repaint();
			}
		});

		vistaPeliculas.getBtnSave().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Guardar peliculas");
				if (fc.showSaveDialog(vistaPeliculas) != JFileChooser.APPROVE_OPTION) return;

				File fichero = fc.getSelectedFile();
				try (PrintWriter pw = new PrintWriter(new FileWriter(fichero))) {
					int cantidad = vistaPeliculas.getModelo2().size();
					boolean alguienGuardado = false;
					int cont = 1;
					pw.println("Listado de peliculas posteriores a 2009:");
					for (int i = 0; i < cantidad; i++) {
						if(!vistaPeliculas.getModelo2().getElementAt(i).substring(0, 4).equals("????")) {
							if(Integer.parseInt(vistaPeliculas.getModelo2().getElementAt(i).substring(0, 4)) >2009) {
								pw.println((cont) + ".- " + vistaPeliculas.getModelo2().getElementAt(i).substring(7));
								cont++;
								alguienGuardado = true;
							} 
						}
					}
					vistaPeliculas.getModelo2().removeAllElements();
					if (!alguienGuardado) {
						JOptionPane.showMessageDialog(vistaPeliculas,
								"No hay ninguna pelicula posterior a 2009.",
								"Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}

					JOptionPane.showMessageDialog(vistaPeliculas, "Mensajes guardados correctamente.",
							"Guardar", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(vistaPeliculas, "Error al guardar: " + ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		vistaPeliculas.getBtnSalir().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}
