import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TresEnRaya extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int NO_FIN=0;
	public static final int TABLAS=1;
	public static final int GANADOR=2;
	
	private JPanel contentPane;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnnuevapartida;
	private JButton btnsalir;
	private JLabel lblJugador2;
	private JLabel lblJugador1;
	private JButton btncolor1;
	private JButton btncolor2;
	private JButton botones[];
	private int turno;
	private JColorChooser dlgColor;
	private Timer reloj;
	private int pos1, pos2, pos3;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TresEnRaya frame = new TresEnRaya();
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
	public TresEnRaya() {
		setTitle("Tres en raya");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 5, 0, 0));
		
		btn1 = new JButton("");
		contentPane.add(btn1);
		
		btn2 = new JButton("");
		contentPane.add(btn2);
		
		btn3 = new JButton("");
		contentPane.add(btn3);
		
		lblJugador1 = new JLabel("Jugador 1");
		lblJugador1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblJugador1);
		
		btncolor1 = new JButton("");
		btncolor1.setBackground(new Color(0, 64, 128));
		contentPane.add(btncolor1);
		
		btn4 = new JButton("");
		contentPane.add(btn4);
		
		btn5 = new JButton("");
		contentPane.add(btn5);
		
		btn6 = new JButton("");
		contentPane.add(btn6);
		
		lblJugador2 = new JLabel("Jugador 2");
		lblJugador2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblJugador2);
		
		btncolor2 = new JButton("");
		btncolor2.setBackground(new Color(128, 0, 64));
		contentPane.add(btncolor2);
		
		btn7 = new JButton("");
		contentPane.add(btn7);
		
		btn8 = new JButton("");
		contentPane.add(btn8);
		
		btn9 = new JButton("");
		contentPane.add(btn9);
		
		btnnuevapartida = new JButton("Nueva partida");
		contentPane.add(btnnuevapartida);
		
		btnsalir = new JButton("Salir");
		contentPane.add(btnsalir);
		
		botones = new JButton [9];
		llenarArray(botones);
		
		turno=1;
		
		registrarEventos();
		
		cambioEstado(false);
	}
	
	public void registrarEventos() {
		reloj=new Timer(500, new ActionListener() { //40 seria verlo fluido
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Codigo para que parpadee las fichas que han hecho tres en raya
				if(turno==1) {
					if(botones[pos1].getBackground().equals(btncolor1.getBackground())) {
						botones[pos1].setBackground(null);
						botones[pos2].setBackground(null);
						botones[pos3].setBackground(null);
					} else {
						botones[pos1].setBackground(btncolor1.getBackground());
						botones[pos2].setBackground(btncolor1.getBackground());
						botones[pos3].setBackground(btncolor1.getBackground());
					}
				} else {
					if(botones[pos1].getBackground().equals(btncolor2.getBackground())) {
						botones[pos1].setBackground(null);
						botones[pos2].setBackground(null);
						botones[pos3].setBackground(null);
					} else {
						botones[pos1].setBackground(btncolor2.getBackground());
						botones[pos2].setBackground(btncolor2.getBackground());
						botones[pos3].setBackground(btncolor2.getBackground());
					}
				}
			}
		});
		btnnuevapartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambioEstado(true);
				btnnuevapartida.setEnabled(false);
				btncolor1.setEnabled(false);
				btncolor2.setEnabled(false);
				reloj.stop();
				elegirJugadores();
				resaltarNombre();
				restaurarColor();
			}
		});
		
		btncolor1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dlgColor = new JColorChooser();
				Color color;
				color=dlgColor.showDialog(rootPane, "Elige color", btncolor1.getBackground());
				
				if (color!=null) {
					btncolor1.setBackground(color);
				}
			}
		});
		
		btncolor2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dlgColor = new JColorChooser();
				Color color;
				color=dlgColor.showDialog(rootPane, "Elige color", btncolor1.getBackground());
				
				if (color!=null) {
					btncolor2.setBackground(color);
				}
			}
		});
		
		btnsalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		for (JButton boton : botones) { //foreach
			boton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int resultado;
					boton.setEnabled(false);
					
					if(turno==1)
					{
						boton.setBackground(btncolor1.getBackground());
					} else {
						boton.setBackground(btncolor2.getBackground());
					}
					
					resultado =comprobarFin();
					
					if(resultado==TABLAS) {
						JOptionPane.showMessageDialog(TresEnRaya.this, "TABLAS");
					} else if (resultado==GANADOR) {
						reloj.start();
						cambioEstado(false);
						if(turno==1) {
							JOptionPane.showMessageDialog(TresEnRaya.this, "Ganador: " + lblJugador1.getText());
						} else {
							JOptionPane.showMessageDialog(TresEnRaya.this, "Ganador: " + lblJugador2.getText());
						}
						btnnuevapartida.setEnabled(true);
						return;
					}
					
					if(turno==1) {
						turno=2;
					} else {
						turno=1;
					}
					resaltarNombre();
				}
			});
		}
	}
	public void llenarArray(JButton[] botones2) {
		botones2[0] = btn1;
		botones2[1] = btn2;
		botones2[2] = btn3;
		botones2[3] = btn4;
		botones2[4] = btn5;
		botones2[5] = btn6;
		botones2[6] = btn7;
		botones2[7] = btn8;
		botones2[8] = btn9;
	}

	//Funcion que habilite y/o deshabilite todos los botones
	public void cambioEstado(boolean estado) {
		for(int i=0; i<botones.length; i++) {
			botones[i].setEnabled(estado);
		}
	}
	
	public void restaurarColor() {
		for(int i=0; i<botones.length; i++) {
			botones[i].setBackground(btnsalir.getBackground());
		}
	}
	
	public void elegirJugadores() {
		Scanner scFich;
		String linea;
		int numJugadores, j1, j2;
		
		try {
			scFich = new Scanner(new File("Jugadores.txt"));
			scFich.nextLine();
			numJugadores = scFich.nextInt();
			scFich.nextLine();
			
			j1= (int)(Math.random() * numJugadores + 1);
			
			do {
				j2= (int)(Math.random() * numJugadores + 1);
			} while (j1==j2);
			
			for(int i=1; i<=numJugadores; i++) {
				linea=scFich.nextLine();
				if (j1==i) {
					lblJugador1.setText(linea);
				} else if (j2==i) {
					lblJugador2.setText(linea);
				}
			}
			scFich.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		}
	}
		
		public int comprobarFin() {
			
			//Horizontales
			for(int fila=0; fila<3; fila++) {
				if(botones[fila*3+0].getBackground().equals(botones[fila*3+1].getBackground()) 
						&& botones[fila*3+0].getBackground().equals(botones[fila*3+2].getBackground()) 
						&& !botones[fila*3+0].getBackground().equals(btnsalir.getBackground())) {
					pos1=fila*3;
					pos2=fila*3+1;
					pos3=fila*3+2;
					return GANADOR;
				}
				
			}
			
			//Verticales
			for(int col=0; col<3; col++) {
				if(botones[col+0].getBackground().equals(botones[col+3].getBackground()) 
					&& botones[col+0].getBackground().equals(botones[col+6].getBackground()) 
					&& !botones[col+0].getBackground().equals(btnsalir.getBackground())) {
					pos1=col;
					pos2=col+3;
					pos3=col+6;
					return GANADOR;
				}
				
			}
			
			//Diagonales
			if(botones[0].getBackground().equals(botones[4].getBackground()) 
				&& botones[0].getBackground().equals(botones[8].getBackground()) 
				&& !botones[0].getBackground().equals(btnsalir.getBackground())) {
				pos1=0;
				pos2=4;
				pos3=8;
					return GANADOR;
			}
			
			if(botones[2].getBackground().equals(botones[4].getBackground()) 
				&& botones[2].getBackground().equals(botones[6].getBackground()) 
				&& !botones[2].getBackground().equals(btnsalir.getBackground())) {
				pos1=2;
				pos2=4;
				pos3=6;
					return GANADOR;
			}
			
			//Empate
			int activadas = 0;
			for(int i=0; i<botones.length; i++) {
				if(botones[i].isEnabled()) {
					activadas++;
				}
			}
			
			if (activadas == 0) {
				return TABLAS;
			}
			
			//No hay nada de lo anterior
			return NO_FIN;
		}
		
	public void resaltarNombre() {
		if(turno==1) {
			lblJugador1.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 15));
			lblJugador1.setForeground(btncolor1.getBackground());
			lblJugador2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblJugador2.setForeground(Color.BLACK);
		} else {
			lblJugador2.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 15));
			lblJugador2.setForeground(btncolor2.getBackground());
			lblJugador1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblJugador1.setForeground(Color.BLACK);
		}
	}
}
