import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Recursividad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNumero1;
	private JButton btnFact;
	private JTextField txtNum;
	private JTextField textFact;
	private JTextField textNum1;
	private JTextField textNum2;
	private JTextField textPot;
	private JButton btnCalcularPotencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recursividad frame = new Recursividad();
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
	public Recursividad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Numero");
		lblNewLabel.setBounds(121, 93, 46, 14);
		contentPane.add(lblNewLabel);

		txtNum = new JTextField();
		txtNum.setBounds(194, 90, 54, 20);
		contentPane.add(txtNum);
		txtNum.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Factorial");
		lblNewLabel_1.setBounds(325, 93, 46, 14);
		contentPane.add(lblNewLabel_1);

		textFact = new JTextField();
		textFact.setBounds(381, 90, 111, 20);
		contentPane.add(textFact);
		textFact.setColumns(10);

		btnFact = new JButton("Calcular fact");
		btnFact.setBounds(522, 89, 120, 23);
		contentPane.add(btnFact);

		lblNumero1 = new JLabel("Base");
		lblNumero1.setBounds(121, 190, 46, 14);
		contentPane.add(lblNumero1);
		
		textNum1 = new JTextField();
		textNum1.setColumns(10);
		textNum1.setBounds(202, 187, 46, 20);
		contentPane.add(textNum1);
		
		JLabel lblNumero = new JLabel("Exponente");
		lblNumero.setBounds(121, 231, 70, 14);
		contentPane.add(lblNumero);
		
		textNum2 = new JTextField();
		textNum2.setColumns(10);
		textNum2.setBounds(201, 228, 46, 20);
		contentPane.add(textNum2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Potencia");
		lblNewLabel_1_1.setBounds(325, 210, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textPot = new JTextField();
		textPot.setColumns(10);
		textPot.setBounds(381, 207, 111, 20);
		contentPane.add(textPot);
		
		btnCalcularPotencia = new JButton("Calcular potencia");
		btnCalcularPotencia.setBounds(522, 206, 120, 23);
		contentPane.add(btnCalcularPotencia);

		registrarEventos();

	}//FIN DEL CONSTRUCTOR


	public void registrarEventos() {
		btnFact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int num;
				double resultado;
				
				//COGER EL VALOR DEL NUMERO 
				try {
					num=Integer.parseInt(txtNum.getText()); 
					if(num<0) {
						JOptionPane.showMessageDialog(null, "No existe factorial de numeros negativos");
						return;
					}
					//LLAMAR A LA FUCNION FACTORIAL PARA QUE LO CALCULE Y ME DEVUELVA EL RESULTADO
					resultado = factorial(num);
					textFact.setText(Double.toString(resultado)); //resultado+" " lo convierte a string
				} catch (NumberFormatException ex) {
					return;
				}
			}
		});
		
		btnCalcularPotencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CON try_catch RECOGER LOS VALORES DE LA BASE Y DEL EXPONENTE
				//COMPROBAR QUE SON NUMEROS
				int num1, num2;
				double resultado;
				try {
					num1=Integer.parseInt(textNum1.getText());
					num2=Integer.parseInt(textNum2.getText());
					resultado = potencia(num1, num2);
					textPot.setText(Double.toString(resultado)); //resultado+" " lo convierte a string
				} catch (NumberFormatException ex) {
					return;
				}
				
				//SI SON VALIDOS HACER LA LLAMADA A LA POTENCIA
			}
		});
	}//FIN DE REGISTRAR EVENTOS
	
	public double factorial(int num) {
		double result;
		
		if(num==0 || num==1) {
			return 1; //si hay return se puede omitir el else
		}
		
		result=num*factorial(num-1);
		return result;
	}
	
	public double potencia(int base, int exp) {
		double result;
		boolean neg = false;
		
		if(exp==0) {
			return 1;
		} else if (exp==1) {
			return base;
		} else if(exp<0) {
			exp=-exp;
			neg=true;
		}
		
		result=base*potencia(base, exp-1);
		
		if (neg==true) {
			result=1/result;
		}
		
		return result;
	}
}
