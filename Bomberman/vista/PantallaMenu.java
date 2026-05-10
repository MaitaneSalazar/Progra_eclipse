import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PantallaMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private Image fondo;

	public PantallaMenu() {
		setTitle("Bomberman");
		setIconImage(new ImageIcon(getClass().getResource("IconBomberman.png")).getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 20, 1500, 980);

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (fondo != null) {
					g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};
		panel.setLayout(null);
		setContentPane(panel);

		fondo = new ImageIcon(getClass().getResource("FondoTitulo.jpg")).getImage();

		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(212, 778, 219, 50);
		btnJugar.setFocusPainted(false);
		btnJugar.setContentAreaFilled(false);
		btnJugar.setBorderPainted(false);
		btnJugar.setForeground(new Color(0, 0, 160));
		btnJugar.setFont(new Font("Arial Black", Font.BOLD, 30));
		btnJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(btnJugar);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(222, 839, 209, 50);
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setForeground(new Color(0, 0, 160));
		btnSalir.setFont(new Font("Arial Black", Font.BOLD, 30));
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(btnSalir);

		btnJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bomberman juego = new Bomberman();
				juego.setVisible(true);
				dispose();
			}
		});

		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnJugar.setForeground(Color.YELLOW);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnJugar.setForeground(new Color(0, 0, 160));
			}
		});

		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setForeground(Color.YELLOW);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSalir.setForeground(new Color(0, 0, 160));
			}
		});
	}
}