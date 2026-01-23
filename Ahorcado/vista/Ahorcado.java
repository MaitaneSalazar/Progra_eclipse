import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

public class Ahorcado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPalabra;
	private JButton btnNueva;
	private JButton btnSalir;
	private ArrayList<String> arrayPalabras;
	private int numFallos;
	private Fichero fichero;
	
	private Teclado teclado;
	private JPanel panelCentro;
	private AreaDibujo areaDibujo;
	private EventosAhorcado eventosAhorcado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ahorcado frame = new Ahorcado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ahorcado() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lblPalabra = new JLabel("_ _ _ _ _ _ _");
		lblPalabra.setFont(new Font("Verdana", Font.BOLD, 25));
		lblPalabra.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPalabra, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnNueva = new JButton("Nueva Palabra");
		btnNueva.setEnabled(false);
		panelSur.add(btnNueva);
		
		btnSalir = new JButton("Salir");
		panelSur.add(btnSalir);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		teclado = new Teclado(this);
		panelCentro.add(teclado);
		
		areaDibujo = new AreaDibujo(this);
		panelCentro.add(areaDibujo);
		
		
		eventosAhorcado = new EventosAhorcado(this);
		
		fichero = new Fichero();
		arrayPalabras = fichero.cargarPalabras("Palabras.txt"); //Guardar las palabras del fichero
		
		elegirPalabra();
	}
	
	public void elegirPalabra() {
		//Elige una palabra del arraylist al azar y la elimina del arraylist
		String palabra;
		Random r;
		int posicion;
		
		r = new Random();
		if(arrayPalabras.size() == 0) {
			JOptionPane.showMessageDialog(null, "No quedan palabras");
			return;
		}
		posicion=r.nextInt(arrayPalabras.size()); //entre 0 y el numero que has metido sin incluir ese numero
		palabra = arrayPalabras.get(posicion);
		arrayPalabras.remove(posicion);
		
		//reinicia los botones (color y enabled)
		teclado.estadoTeclado(true);
		
		//reinicia los fallos y repintar el canvas
		numFallos = 0;
		areaDibujo.repaint();
		
		//pone tantos guiones seguidos de un espacio como letras tenga la palabra
		lblPalabra.setText("");
		for (int cont = 0; cont < palabra.length(); cont++) {
			lblPalabra.setText(lblPalabra.getText() + "_ ");
		}
		
		teclado.setPalabra(palabra);
	}

	public ArrayList<String> getArrayPalabras() {
		return arrayPalabras;
	}

	public void setArrayPalabras(ArrayList<String> arrayPalabras) {
		this.arrayPalabras = arrayPalabras;
	}

	public int getNumFallos() {
		return numFallos;
	}

	public void setNumFallos(int numFallos) {
		this.numFallos = numFallos;
	}

	public JLabel getLblPalabra() {
		return lblPalabra;
	}

	public void setLblPalabra(JLabel lblPalabra) {
		this.lblPalabra = lblPalabra;
	}

	public JButton getBtnNueva() {
		return btnNueva;
	}

	public void setBtnNueva(JButton btnNueva) {
		this.btnNueva = btnNueva;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public Teclado getTeclado() {
		return teclado;
	}

	public void setTeclado(Teclado teclado) {
		this.teclado = teclado;
	}

	public AreaDibujo getAreaDibujo() {
		return areaDibujo;
	}

	public void setAreaDibujo(AreaDibujo areaDibujo) {
		this.areaDibujo = areaDibujo;
	}

}
