import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Xagu extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int FILAS=10;
	public static final int COLS=10;
	private JPanel contentPane;
	private JButton btnCargar;
	private JButton btnBuscar;
	private JButton btnSalir;
	private JPanel panelMapa;
	private JPanel panelBotones;
	
	private JLabel [][]casillas;
	private int filaXagu, colXagu;
	private boolean salida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Xagu frame = new Xagu();
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
	public Xagu() {
		setTitle("Xagu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.NORTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
		
		btnCargar = new JButton("Cargar Mapa");
		panelBotones.add(btnCargar);
		
		btnBuscar = new JButton("Buscar Salida");
		panelBotones.add(btnBuscar);
		
		btnSalir = new JButton("Salir");
		panelBotones.add(btnSalir);
		
		panelMapa = new JPanel();
		contentPane.add(panelMapa, BorderLayout.CENTER);
		panelMapa.setLayout(new GridLayout(FILAS, COLS, 0, 0));

		crearLabels();
		registrarEventos();
	}
	
	public void registrarEventos() {
		btnCargar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cargarMapa();
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarSalida(casillas, filaXagu, colXagu);
				
			}
		});
	}
	
	public void buscarSalida(JLabel [][]casillas, int filaXagu, int colXagu) {
		//CASOS UNICOS
		if(filaXagu==0 || filaXagu==FILAS-1 || colXagu==0 || colXagu==COLS-1) {
			casillas[filaXagu][colXagu].setText("X");
			salida=true;
			return;
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//RECURSIVIDAD
		casillas[filaXagu][colXagu].setText("-");
		
		//ARRIBA
		if(!salida && casillas[filaXagu-1][colXagu].getText().equals(""))
		{
			buscarSalida(casillas, filaXagu-1, colXagu);
		}
		
		//DERECHA
		if(!salida && casillas[filaXagu][colXagu+1].getText().equals(""))
		{
			buscarSalida(casillas, filaXagu, colXagu+1);
		}
		
		//ABAJO
		if(!salida && casillas[filaXagu+1][colXagu].getText().equals(""))
		{
			buscarSalida(casillas, filaXagu+1, colXagu);
		}
		
		//IZQUIERDA
		if(!salida && casillas[filaXagu][colXagu-1].getText().equals(""))
		{
			buscarSalida(casillas, filaXagu, colXagu-1);
		}
		
		if(salida) {
			casillas[filaXagu][colXagu].setText("X");
		}
		
	}
	
	protected void cargarMapa() {
		Scanner scFich;
		FileDialog dlgMapa;
		String linea;
		String fich;
		
		salida=false; 
		dlgMapa = new FileDialog(this, "Cargar Mapa", FileDialog.LOAD);
		//dlgMapa.setDirectory(System.getProperty("user.home")+ "\\Desktop"); //El home del usuario actual
		dlgMapa.setDirectory(".\\img");
		dlgMapa.setVisible(true);
		fich = dlgMapa.getFile();
		
		if(fich != null) {
			try {
				scFich= new Scanner(new File(dlgMapa.getDirectory()+fich));
				//scFich= new Scanner(new File(getClass().getResource(fich)+fich));
				int fila = 0;
				while(scFich.hasNext()) {
					//LEER LINEA
					linea=scFich.nextLine();
					//PROCESAR LINEA
					for(int col=0; col<COLS; col++) {
						//si es un 0 vaciar casilla
						//si es un 1 cargar muro
						//si es un 2 cargar raton
						if(linea.charAt(col)=='0') {
							casillas[fila][col].setText("");
							casillas[fila][col].setIcon(null);
						}
						if(linea.charAt(col)=='1') {
							//casillas[fila][col].setIcon(new ImageIcon(".\\img\\muro.jpg"));
							casillas[fila][col].setIcon(new ImageIcon(getClass().getResource("muro.jpg"))); //Cargar como recurso para que se incluya en el ejecutable
							casillas[fila][col].setText("*");
						}
						if(linea.charAt(col)=='2') {
							//casillas[fila][col].setIcon(new ImageIcon(".\\img\\xagu.jpg"));
							casillas[fila][col].setIcon(new ImageIcon(getClass().getResource("xagu.jpg")));
							casillas[fila][col].setText("*");
							filaXagu=fila;
							colXagu=col;
						}
					}
					fila++;
				}
				scFich.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void crearLabels() {
		JLabel lblAux;
		//DIMENSIONAR EL ARRAY DE CASILLAS
		casillas = new JLabel[FILAS][COLS];
		//CREAR LOS 100 JLABELS CON SUS CARACTERISTICAS:
			//TEXTO, TAMAÑO, POSICION, COLOR, ETC...
			//AÑADIRLO AL PANEL
			//AÑADIRLO AL ARRAY
		for(int fila=0; fila<FILAS; fila++) {
			for(int col=0; col<COLS; col++) {
				lblAux= new JLabel("*");
				lblAux.setHorizontalAlignment(JLabel.CENTER);
				lblAux.setFont(new Font("Arial", Font.BOLD, 20));
				panelMapa.add(lblAux);
				casillas[fila][col] = lblAux;
			}
		}
	}
}
