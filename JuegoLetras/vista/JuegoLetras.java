import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class JuegoLetras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton rdbtnFacil;
	private JRadioButton rdbtnMedio;
	private JRadioButton rdbtnDificil;
	private JButton btnStart;
	private JButton btnExit;

	private ButtonGroup grpNivel;
	private AreaJuego areaJuego;

	private EventosJuegoLetras eventosJuegoLetras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JuegoLetras frame = new JuegoLetras();
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
	public JuegoLetras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.WEST);

		JLabel lblNivel = new JLabel("Elige Nivel:");
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		rdbtnFacil = new JRadioButton("Facil");
		rdbtnFacil.setBackground(new Color(192, 192, 192));
		rdbtnFacil.setSelected(true);
		rdbtnFacil.setFont(new Font("Tahoma", Font.PLAIN, 14));

		rdbtnMedio = new JRadioButton("Medio");
		rdbtnMedio.setBackground(new Color(192, 192, 192));
		rdbtnMedio.setFont(new Font("Tahoma", Font.PLAIN, 14));

		rdbtnDificil = new JRadioButton("Dificil");
		rdbtnDificil.setBackground(new Color(192, 192, 192));
		rdbtnDificil.setFont(new Font("Tahoma", Font.PLAIN, 14));

		//Crear el grupo para que solo pueda haber uno seleccionado a la vez
		grpNivel = new ButtonGroup();
		grpNivel.add(rdbtnFacil);
		grpNivel.add(rdbtnMedio);
		grpNivel.add(rdbtnDificil);

		btnStart = new JButton("Empezar");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnExit = new JButton("Salir");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(14)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtnMedio)
												.addComponent(rdbtnFacil)
												.addComponent(rdbtnDificil))
										.addGap(53))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnExit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnStart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNivel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNivel)
						.addGap(18)
						.addComponent(rdbtnFacil)
						.addGap(18)
						.addComponent(rdbtnMedio)
						.addGap(18)
						.addComponent(rdbtnDificil)
						.addPreferredGap(ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
						.addComponent(btnStart)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnExit)
						.addGap(13))
				);
		panel.setLayout(gl_panel);

		areaJuego = new AreaJuego(this);
		contentPane.add(areaJuego, BorderLayout.CENTER);
		//Despues de contruir areaJuego se lo pasamos al cuadrado y al circulo para que puedan saber su tamaño para rebotar
		areaJuego.getCuadrado().setAreaJuego(areaJuego);
		areaJuego.getCirculo().setAreaJuego(areaJuego);
		eventosJuegoLetras = new EventosJuegoLetras(this);
	}

	public JRadioButton getRdbtnFacil() {
		return rdbtnFacil;
	}

	public void setRdbtnFacil(JRadioButton rdbtnFacil) {
		this.rdbtnFacil = rdbtnFacil;
	}

	public JRadioButton getRdbtnMedio() {
		return rdbtnMedio;
	}

	public void setRdbtnMedio(JRadioButton rdbtnMedio) {
		this.rdbtnMedio = rdbtnMedio;
	}

	public JRadioButton getRdbtnDificil() {
		return rdbtnDificil;
	}

	public void setRdbtnDificil(JRadioButton rdbtnDificil) {
		this.rdbtnDificil = rdbtnDificil;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}

	public ButtonGroup getGrpNivel() {
		return grpNivel;
	}

	public void setGrpNivel(ButtonGroup grpNivel) {
		this.grpNivel = grpNivel;
	}

	public AreaJuego getAreaJuego() {
		return areaJuego;
	}

	public void setAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
	}
}
