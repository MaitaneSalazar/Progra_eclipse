import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class VistaPeliculas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnA;
	private JButton btnTodos;
	private JButton btnE;
	private JButton btnI;
	private JButton btnO;
	private JButton btnU;
	private JButton btnFlecha1;
	private JButton btnFlecha2;
	private JButton btnSave;
	private JButton btnSalir;
	private JComboBox <String>comboBoxYear;
	private JList<String> list1;
	private JList<String> list2;
	private DefaultListModel<String> modelo1, modelo2;
	private JPanel area;
	
	private String []arrayYears;
	
	private EventosPeliculas eventosPeliculas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPeliculas frame = new VistaPeliculas();
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
	public VistaPeliculas() {
		setTitle("Peliculas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Cargar del fichero:");
		lblTitulo.setBounds(29, 29, 118, 14);
		contentPane.add(lblTitulo);
		
		btnTodos = new JButton("Todos");
		btnTodos.setBounds(22, 54, 227, 23);
		contentPane.add(btnTodos);
		
		btnA = new JButton("A");
		btnA.setBounds(22, 88, 45, 23);
		contentPane.add(btnA);
		
		btnE = new JButton("E");
		btnE.setBounds(67, 88, 45, 23);
		contentPane.add(btnE);
		
		btnI = new JButton("I");
		btnI.setBounds(112, 88, 45, 23);
		contentPane.add(btnI);
		
		btnO = new JButton("O");
		btnO.setBounds(157, 88, 45, 23);
		contentPane.add(btnO);
		
		btnU = new JButton("U");
		btnU.setBounds(202, 88, 45, 23);
		contentPane.add(btnU);
		
		btnFlecha1 = new JButton(">>");
		btnFlecha1.setBounds(262, 229, 72, 23);
		contentPane.add(btnFlecha1);
		
		btnFlecha2 = new JButton(">>");
		btnFlecha2.setBounds(589, 229, 72, 23);
		contentPane.add(btnFlecha2);
		
		btnSave = new JButton("Guardar");
		btnSave.setBounds(413, 409, 89, 23);
		contentPane.add(btnSave);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(248, 462, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblYear = new JLabel("Año:");
		lblYear.setBounds(29, 413, 46, 14);
		contentPane.add(lblYear);
		
		arrayYears = new String [107];
		
		for(int i = 0; i<107; i++) {
			if(i!=0) {
				int suma = 1929 + i;
				arrayYears[i] = suma + "";
			} else {
				arrayYears[0] = "Desconocido";
			}
			
		}
		
		comboBoxYear = new JComboBox<>(arrayYears);
		comboBoxYear.setBounds(83, 409, 130, 22);
		contentPane.add(comboBoxYear);
		
		area = new AreaPeliculas(this);
		area.setBounds(682, 113, 194, 284);
		contentPane.add(area);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 122, 227, 280);
		contentPane.add(scrollPane);
		
		list1 = new JList();
		scrollPane.setViewportView(list1);
		
		modelo1=new DefaultListModel<String>();
		list1.setModel(modelo1);
		
		modelo1.addElement("Inception");
		modelo1.addElement("Inside Out");
		modelo1.addElement("Iron Man 3");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(354, 113, 207, 284);
		contentPane.add(scrollPane_1);
		
		list2 = new JList();
		scrollPane_1.setViewportView(list2);
		
		modelo2=new DefaultListModel<String>();
		list2.setModel(modelo2);
		
		
		eventosPeliculas = new EventosPeliculas(this, area);

	}

	public JList<String> getList1() {
		return list1;
	}

	public void setList1(JList<String> list1) {
		this.list1 = list1;
	}

	public JList<String> getList2() {
		return list2;
	}

	public void setList2(JList<String> list2) {
		this.list2 = list2;
	}

	public String[] getArrayYears() {
		return arrayYears;
	}

	public void setArrayYears(String[] arrayYears) {
		this.arrayYears = arrayYears;
	}

	public void setComboBoxYear(JComboBox<String> comboBoxYear) {
		this.comboBoxYear = comboBoxYear;
	}

	public JButton getBtnA() {
		return btnA;
	}

	public void setBtnA(JButton btnA) {
		this.btnA = btnA;
	}

	public JButton getBtnTodos() {
		return btnTodos;
	}

	public void setBtnTodos(JButton btnTodos) {
		this.btnTodos = btnTodos;
	}

	public JButton getBtnE() {
		return btnE;
	}

	public void setBtnE(JButton btnE) {
		this.btnE = btnE;
	}

	public JButton getBtnI() {
		return btnI;
	}

	public void setBtnI(JButton btnI) {
		this.btnI = btnI;
	}

	public JButton getBtnO() {
		return btnO;
	}

	public void setBtnO(JButton btnO) {
		this.btnO = btnO;
	}

	public JButton getBtnU() {
		return btnU;
	}

	public void setBtnU(JButton btnU) {
		this.btnU = btnU;
	}

	public JButton getBtnFlecha1() {
		return btnFlecha1;
	}

	public void setBtnFlecha1(JButton btnFlecha1) {
		this.btnFlecha1 = btnFlecha1;
	}

	public JButton getBtnFlecha2() {
		return btnFlecha2;
	}

	public void setBtnFlecha2(JButton btnFlecha2) {
		this.btnFlecha2 = btnFlecha2;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JComboBox getComboBoxYear() {
		return comboBoxYear;
	}

	public DefaultListModel<String> getModelo1() {
		return modelo1;
	}

	public void setModelo1(DefaultListModel<String> modelo1) {
		this.modelo1 = modelo1;
	}

	public DefaultListModel<String> getModelo2() {
		return modelo2;
	}

	public void setModelo2(DefaultListModel<String> modelo2) {
		this.modelo2 = modelo2;
	}
}
