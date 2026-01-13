import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class miTuning extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					miTuning frame = new miTuning();
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
	public miTuning() {
		setTitle("MiTuning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnEnviar = new JButton("Enviar");
		panelSur.add(btnEnviar);
		
		JButton btnNewButton = new JButton("Cancelar");
		panelSur.add(btnNewButton);
		
		JPanel panelArriba = new JPanel();
		contentPane.add(panelArriba, BorderLayout.NORTH);
		panelArriba.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Taller Tuning");
		panelArriba.add(lblNewLabel);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panelCentro.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Direccion");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Provincia");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_4);
		
		JComboBox comboBox = new JComboBox();
		panel_2.add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("Edad");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_5);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_2.add(comboBox_1);
		
		JLabel lblNewLabel_6 = new JLabel("Sexo");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_6);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Hombre");
		panel_6.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Mujer");
		panel_6.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("NB");
		panel_6.add(rdbtnNewRadioButton_2);
		
		JPanel panel_5 = new JPanel();
		panelCentro.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("Preferencias:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_7.add(lblNewLabel_7);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Tunning");
		panel_7.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Recambios");
		panel_7.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("Puesta a Punto");
		panel_7.add(chckbxNewCheckBox_6);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Competicion");
		panel_7.add(chckbxNewCheckBox_4);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		panel_8.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel_8 = new JLabel("");
		panel_8.add(lblNewLabel_8);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Equipamiento");
		panel_8.add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Reparaciones");
		panel_8.add(chckbxNewCheckBox_5);
		
		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("Mantenimiento");
		panel_8.add(chckbxNewCheckBox_7);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Vehiculos Ocasion");
		panel_8.add(chckbxNewCheckBox_1);
		
		JPanel panel_4 = new JPanel();
		panelCentro.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_9 = new JLabel("Comentarios: ");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblNewLabel_9);
		
		textField_3 = new JTextField();
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		panel_4.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("");
		panel_4.add(lblNewLabel_12);
		
		JLabel lblNewLabel_10 = new JLabel("Pedidos: ");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblNewLabel_10);
		
		textField_4 = new JTextField();
		panel_4.add(textField_4);
		textField_4.setColumns(10);

	}
}
