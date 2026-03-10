import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class VistaForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField text0;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	private JTextField text6;
	private JTextField text7;
	private JButton btnAdd;
	private JCheckBox chckbxTodos;
	private JCheckBox chckbx7;
	private JCheckBox chckbx6;
	private JCheckBox chckbx5;
	private JCheckBox chckbx4;
	private JCheckBox chckbx3;
	private JCheckBox chckbx2;
	private JCheckBox chckbx1;
	private JCheckBox chckbx0;
	private JTextArea textArea;
	private JButton btnSave;
	private JButton btnExit;
	
	private EventosForm eventosForm;
	
	private JList list;
	private DefaultListModel<String> modeloNombres;
	
	private JTextField textos[];
	private JCheckBox checks[];

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaForm frame = new VistaForm();
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
	public VistaForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList();
		list.setBounds(45, 53, 175, 287);
		contentPane.add(list);
		
		textNombre = new JTextField();
		textNombre.setBounds(45, 386, 175, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		text0 = new JTextField();
		text0.setEnabled(false);
		text0.setBounds(258, 59, 132, 20);
		contentPane.add(text0);
		text0.setColumns(10);
		
		text1 = new JTextField();
		text1.setEnabled(false);
		text1.setBounds(258, 88, 132, 20);
		contentPane.add(text1);
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setEnabled(false);
		text2.setBounds(258, 119, 132, 20);
		contentPane.add(text2);
		text2.setColumns(10);
		
		text3 = new JTextField();
		text3.setEnabled(false);
		text3.setBounds(258, 150, 132, 20);
		contentPane.add(text3);
		text3.setColumns(10);
		
		text4 = new JTextField();
		text4.setEnabled(false);
		text4.setBounds(258, 181, 132, 20);
		contentPane.add(text4);
		text4.setColumns(10);
		
		text5 = new JTextField();
		text5.setEnabled(false);
		text5.setBounds(258, 212, 132, 20);
		contentPane.add(text5);
		text5.setColumns(10);
		
		text6 = new JTextField();
		text6.setEnabled(false);
		text6.setBounds(258, 243, 132, 20);
		contentPane.add(text6);
		text6.setColumns(10);
		
		text7 = new JTextField();
		text7.setEnabled(false);
		text7.setBounds(258, 274, 132, 20);
		contentPane.add(text7);
		text7.setColumns(10);
		
		btnAdd = new JButton("Añadir");
		btnAdd.setBounds(89, 417, 89, 23);
		contentPane.add(btnAdd);
		
		chckbx0 = new JCheckBox("");
		chckbx0.setEnabled(false);
		chckbx0.setBounds(407, 58, 25, 23);
		contentPane.add(chckbx0);
		
		chckbx1 = new JCheckBox("");
		chckbx1.setEnabled(false);
		chckbx1.setBounds(407, 87, 25, 23);
		contentPane.add(chckbx1);
		
		chckbx2 = new JCheckBox("");
		chckbx2.setEnabled(false);
		chckbx2.setBounds(407, 118, 25, 23);
		contentPane.add(chckbx2);
		
		chckbx3 = new JCheckBox("");
		chckbx3.setEnabled(false);
		chckbx3.setBounds(407, 149, 25, 23);
		contentPane.add(chckbx3);
		
		chckbx4 = new JCheckBox("");
		chckbx4.setEnabled(false);
		chckbx4.setBounds(407, 180, 25, 23);
		contentPane.add(chckbx4);
		
		chckbx5 = new JCheckBox("");
		chckbx5.setEnabled(false);
		chckbx5.setBounds(407, 211, 25, 23);
		contentPane.add(chckbx5);
		
		chckbx6 = new JCheckBox("");
		chckbx6.setEnabled(false);
		chckbx6.setBounds(407, 242, 25, 23);
		contentPane.add(chckbx6);
		
		chckbx7 = new JCheckBox("");
		chckbx7.setEnabled(false);
		chckbx7.setBounds(407, 273, 25, 23);
		contentPane.add(chckbx7);
		
		chckbxTodos = new JCheckBox("Todos");
		chckbxTodos.setBounds(407, 332, 97, 23);
		contentPane.add(chckbxTodos);
		
		textArea = new JTextArea();
		textArea.setBounds(453, 72, 313, 222);
		contentPane.add(textArea);
		
		btnSave = new JButton("Guardar Fichero");
		btnSave.setBounds(542, 305, 132, 23);
		contentPane.add(btnSave);
		
		btnExit = new JButton("Salir");
		btnExit.setBounds(566, 430, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblSave = new JLabel("Guardar");
		lblSave.setBounds(407, 39, 46, 14);
		contentPane.add(lblSave);
		
		JLabel lblMensaje = new JLabel("Texto del mensaje:");
		lblMensaje.setBounds(453, 54, 97, 14);
		contentPane.add(lblMensaje);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(45, 372, 46, 14);
		contentPane.add(lblNombre);
		
		eventosForm = new EventosForm(this);
		
		textos = new JTextField [8];
		checks = new JCheckBox [8];
		llenarArrays(textos, checks);
	}
	
	private void llenarArrays(JTextField[] textos, JCheckBox[] checks)
	{
		textos[0] = text0;
		textos[1] = text1;
		textos[2] = text2;
		textos[3] = text3;
		textos[4] = text4;
		textos[5] = text5;
		textos[6] = text6;
		textos[7] = text7;
		
		checks[0] = chckbx0;
		checks[1] = chckbx1;
		checks[2] = chckbx2;
		checks[3] = chckbx3;
		checks[4] = chckbx4;
		checks[5] = chckbx5;
		checks[6] = chckbx6;
		checks[7] = chckbx7;
	}


	public JTextField getTextNombre() {
		return textNombre;
	}

	public void setTextNombre(JTextField textNombre) {
		this.textNombre = textNombre;
	}

	public JTextField getText0() {
		return text0;
	}

	public void setText0(JTextField text0) {
		this.text0 = text0;
	}

	public JTextField getText1() {
		return text1;
	}

	public void setText1(JTextField text1) {
		this.text1 = text1;
	}

	public JTextField getText2() {
		return text2;
	}

	public void setText2(JTextField text2) {
		this.text2 = text2;
	}

	public JTextField getText3() {
		return text3;
	}

	public void setText3(JTextField text3) {
		this.text3 = text3;
	}

	public JTextField getText4() {
		return text4;
	}

	public void setText4(JTextField text4) {
		this.text4 = text4;
	}

	public JTextField getText5() {
		return text5;
	}

	public void setText5(JTextField text5) {
		this.text5 = text5;
	}

	public JTextField getText6() {
		return text6;
	}

	public void setText6(JTextField text6) {
		this.text6 = text6;
	}

	public JTextField getText7() {
		return text7;
	}

	public void setText7(JTextField text7) {
		this.text7 = text7;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JCheckBox getChckbxTodos() {
		return chckbxTodos;
	}

	public void setChckbxTodos(JCheckBox chckbxTodos) {
		this.chckbxTodos = chckbxTodos;
	}

	public JCheckBox getChckbx7() {
		return chckbx7;
	}

	public void setChckbx7(JCheckBox chckbx7) {
		this.chckbx7 = chckbx7;
	}

	public JCheckBox getChckbx6() {
		return chckbx6;
	}

	public void setChckbx6(JCheckBox chckbx6) {
		this.chckbx6 = chckbx6;
	}

	public JCheckBox getChckbx5() {
		return chckbx5;
	}

	public void setChckbx5(JCheckBox chckbx5) {
		this.chckbx5 = chckbx5;
	}

	public JCheckBox getChckbx4() {
		return chckbx4;
	}

	public void setChckbx4(JCheckBox chckbx4) {
		this.chckbx4 = chckbx4;
	}

	public JCheckBox getChckbx3() {
		return chckbx3;
	}

	public void setChckbx3(JCheckBox chckbx3) {
		this.chckbx3 = chckbx3;
	}

	public JCheckBox getChckbx2() {
		return chckbx2;
	}

	public void setChckbx2(JCheckBox chckbx2) {
		this.chckbx2 = chckbx2;
	}

	public JCheckBox getChckbx1() {
		return chckbx1;
	}

	public void setChckbx1(JCheckBox chckbx1) {
		this.chckbx1 = chckbx1;
	}

	public JCheckBox getChckbx0() {
		return chckbx0;
	}

	public void setChckbx0(JCheckBox chckbx0) {
		this.chckbx0 = chckbx0;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public DefaultListModel<String> getModeloNombres() {
		return modeloNombres;
	}

	public void setModeloNombres(DefaultListModel<String> modeloNombres) {
		this.modeloNombres = modeloNombres;
	}

	public JTextField[] getTextos() {
		return textos;
	}

	public void setTextos(JTextField[] textos) {
		this.textos = textos;
	}

	public JCheckBox[] getChecks() {
		return checks;
	}

	public void setChecks(JCheckBox[] checks) {
		this.checks = checks;
	}
}
