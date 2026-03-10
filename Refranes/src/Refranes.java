import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class Refranes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton rbRefran1;
	private JRadioButton rbRefran2;
	private JRadioButton rbRefran3;
	private JList<String> lstPalabras;
	private JButton btnCargar;
	private JButton btnSubir;
	private JButton btnBajar;
	private JButton btnSalir;
	private JButton btnEliminar;
	
	private ButtonGroup grpBotones;
	
	private String strRefran;
	private DefaultListModel<String> modelo;
	private EventosRefranes eventosRefranes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Refranes frame = new Refranes();
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
	public Refranes() {
		setTitle("RefranesAlmi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rbRefran1 = new JRadioButton("Refran1");
		rbRefran1.setSelected(true);
		rbRefran1.setBounds(47, 35, 109, 23);
		contentPane.add(rbRefran1);
		
		rbRefran2 = new JRadioButton("Refran2");
		rbRefran2.setBounds(171, 35, 109, 23);
		contentPane.add(rbRefran2);
		
		rbRefran3 = new JRadioButton("Refran3");
		rbRefran3.setBounds(299, 35, 109, 23);
		contentPane.add(rbRefran3);
		
		grpBotones=new ButtonGroup();
		grpBotones.add(rbRefran1);
		grpBotones.add(rbRefran2);
		grpBotones.add(rbRefran3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 80, 205, 218);
		contentPane.add(scrollPane);
		
		lstPalabras = new JList();
		scrollPane.setViewportView(lstPalabras);
		
		modelo=new DefaultListModel<String>();
		lstPalabras.setModel(modelo);
		
		btnCargar = new JButton("Cargar Refran");
		btnCargar.setBounds(299, 80, 123, 23);
		contentPane.add(btnCargar);
		
		btnSubir = new JButton("Subir");
		btnSubir.setBounds(45, 473, 89, 23);
		contentPane.add(btnSubir);
		
		btnBajar = new JButton("Bajar");
		btnBajar.setBounds(147, 473, 89, 23);
		contentPane.add(btnBajar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(246, 473, 89, 23);
		contentPane.add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(353, 473, 89, 23);
		contentPane.add(btnSalir);

		eventosRefranes=new EventosRefranes(this);
		
	}//FIN DEL CONSTRUCTOR

	public JRadioButton getRbRefran1() {
		return rbRefran1;
	}

	public void setRbRefran1(JRadioButton rbRefran1) {
		this.rbRefran1 = rbRefran1;
	}

	public JRadioButton getRbRefran2() {
		return rbRefran2;
	}

	public void setRbRefran2(JRadioButton rbRefran2) {
		this.rbRefran2 = rbRefran2;
	}

	public JRadioButton getRbRefran3() {
		return rbRefran3;
	}

	public void setRbRefran3(JRadioButton rbRefran3) {
		this.rbRefran3 = rbRefran3;
	}

	public JButton getBtnCargar() {
		return btnCargar;
	}

	public void setBtnCargar(JButton btnCargar) {
		this.btnCargar = btnCargar;
	}

	public JButton getBtnSubir() {
		return btnSubir;
	}

	public void setBtnSubir(JButton btnSubir) {
		this.btnSubir = btnSubir;
	}

	public JButton getBtnBajar() {
		return btnBajar;
	}

	public void setBtnBajar(JButton btnBajar) {
		this.btnBajar = btnBajar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public ButtonGroup getGrpBotones() {
		return grpBotones;
	}

	public void setGrpBotones(ButtonGroup grpBotones) {
		this.grpBotones = grpBotones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStrRefran() {
		return strRefran;
	}

	public void setStrRefran(String strRefran) {
		this.strRefran = strRefran;
	}

	public DefaultListModel<String> getModelo() {
		return modelo;
	}

	public void setModelo(DefaultListModel<String> modelo) {
		this.modelo = modelo;
	}

	public JList<String> getLstPalabras() {
		return lstPalabras;
	}

	public void setLstPalabras(JList<String> lstPalabras) {
		this.lstPalabras = lstPalabras;
	}
}
