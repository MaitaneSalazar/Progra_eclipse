import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Bomberman extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AreaJuego areaJuego;
	private HUD hud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bomberman frame = new Bomberman();
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
	public Bomberman() {
		setIconImage(new ImageIcon(getClass().getResource("IconBomberman.png")).getImage());
		setTitle("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 20, 1500, 980);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		hud = new HUD();
		contentPane.add(hud, BorderLayout.NORTH);

		areaJuego = new AreaJuego(hud);
		contentPane.add(areaJuego, BorderLayout.CENTER);
		areaJuego.setFocusable(true);
		areaJuego.requestFocus();
	}

}
