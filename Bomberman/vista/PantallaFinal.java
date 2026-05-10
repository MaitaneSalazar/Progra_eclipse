import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
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

public class PantallaFinal extends JFrame {
	
	public static final int VICTORIA = 0;
    public static final int DERROTA  = 1;

    private static final long serialVersionUID = 1L;
    private Image fondo;
    private int score;

    public PantallaFinal(int resultado, int score) {
        this.score = score;

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

                g.setFont(new Font("Arial BLACK", Font.BOLD, 80));
                g.setColor(new Color(244, 0, 161));
                g.drawString("SCORE: " + score, 450, 300);
            }
        };
        panel.setLayout(null);
        setContentPane(panel);

        if (resultado == VICTORIA) {
            fondo = new ImageIcon(getClass().getResource("PantallaGanar.png")).getImage();
        } else {
            fondo = new ImageIcon(getClass().getResource("PantallaMuerte.png")).getImage();
        }

        JButton btnMenu = new JButton("MENU");
        btnMenu.setBounds(418, 853, 214, 50);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(new Font("Arial", Font.BOLD, 50));
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnMenu);

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBounds(850, 853, 240, 50);
        btnSalir.setFocusPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 50));
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnSalir);

        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaMenu menu = new PantallaMenu();
                menu.setVisible(true);
                dispose();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMenu.setForeground(Color.YELLOW);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMenu.setForeground(Color.WHITE);
			}
		});
        
        btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setForeground(Color.YELLOW);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSalir.setForeground(Color.WHITE);
			}
		});
    }
}