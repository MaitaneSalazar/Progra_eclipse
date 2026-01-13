import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class GestionCorreo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldCorreo;
	private JTextField textFieldWeb;
	private JCheckBox chckbxEmpleado;
	private JButton btnAdd;
	private JTextField textFieldEdad;
	private JTextField textFieldDir;
	private JTextField textFieldTelf;
	private JList<String> listNombres;
	private JList<String> listWebs;
	private JList<String> listCorreos;
	private DefaultListModel<String> modeloNombres, modeloCorreos, modeloWebs;
	private JPanel panelEmpleado;
	private ArrayList<Persona> arrayPersonas;
	private JButton btnSaveId;
	private JButton btnLoadId;
	private BaseDatos bd;
	private JButton btnSend;
	private EnviarMail enviarMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionCorreo frame = new GestionCorreo();
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
	public GestionCorreo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(40, 63, 46, 14);
		contentPane.add(lblNombre);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setBounds(40, 91, 46, 14);
		contentPane.add(lblCorreo);

		JLabel lblWeb = new JLabel("Web");
		lblWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeb.setBounds(40, 119, 46, 14);
		contentPane.add(lblWeb);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(96, 60, 154, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setBounds(96, 88, 154, 20);
		contentPane.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);

		textFieldWeb = new JTextField();
		textFieldWeb.setBounds(96, 116, 154, 20);
		contentPane.add(textFieldWeb);
		textFieldWeb.setColumns(10);

		btnAdd = new JButton("Añadir");
		btnAdd.setBounds(308, 115, 89, 23);
		contentPane.add(btnAdd);

		chckbxEmpleado = new JCheckBox("Empleado");
		chckbxEmpleado.setSelected(true);
		chckbxEmpleado.setBounds(312, 74, 97, 23);
		contentPane.add(chckbxEmpleado);

		panelEmpleado = new JPanel();
		panelEmpleado.setBounds(427, 42, 214, 105);
		contentPane.add(panelEmpleado);
		panelEmpleado.setLayout(null);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(10, 14, 46, 14);
		panelEmpleado.add(lblEdad);

		JLabel lblDir = new JLabel("Dirección");
		lblDir.setBounds(10, 45, 46, 14);
		panelEmpleado.add(lblDir);

		JLabel lblTelf = new JLabel("Teléfono");
		lblTelf.setBounds(10, 73, 46, 14);
		panelEmpleado.add(lblTelf);

		textFieldEdad = new JTextField();
		textFieldEdad.setBounds(59, 11, 86, 20);
		panelEmpleado.add(textFieldEdad);
		textFieldEdad.setColumns(10);

		textFieldDir = new JTextField();
		textFieldDir.setBounds(59, 39, 86, 20);
		panelEmpleado.add(textFieldDir);
		textFieldDir.setColumns(10);

		textFieldTelf = new JTextField();
		textFieldTelf.setBounds(59, 70, 86, 20);
		panelEmpleado.add(textFieldTelf);
		textFieldTelf.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 181, 160, 262);
		contentPane.add(scrollPane);

		listNombres = new JList<String>();
		scrollPane.setViewportView(listNombres);
		modeloNombres = new DefaultListModel<String>();
		listNombres.setModel(modeloNombres);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(28, 156, 46, 14);
		contentPane.add(lblNombres);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(218, 181, 160, 262);
		contentPane.add(scrollPane_1);

		listCorreos = new JList<String>();
		scrollPane_1.setViewportView(listCorreos);
		modeloCorreos = new DefaultListModel<String>();
		listCorreos.setModel(modeloCorreos);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(411, 183, 160, 262);
		contentPane.add(scrollPane_2);

		listWebs = new JList<String>();
		scrollPane_2.setViewportView(listWebs);
		modeloWebs = new DefaultListModel<String>();
		listWebs.setModel(modeloWebs);

		JLabel lblCorreos = new JLabel("Correos");
		lblCorreos.setBounds(218, 156, 46, 14);
		contentPane.add(lblCorreos);

		JLabel lblWebs = new JLabel("Webs");
		lblWebs.setBounds(411, 158, 46, 14);
		contentPane.add(lblWebs);

		btnSaveId = new JButton("Guardar ID");
		btnSaveId.setBounds(581, 208, 89, 23);
		contentPane.add(btnSaveId);

		btnLoadId = new JButton("Cargar ID");
		btnLoadId.setBounds(581, 242, 89, 23);
		contentPane.add(btnLoadId);
		
		btnSend = new JButton("Enviar mail");
		btnSend.setBounds(581, 338, 89, 23);
		contentPane.add(btnSend);

		arrayPersonas = new ArrayList<Persona>();

		bd = new BaseDatos();
		registrarEventos();
	}

	private void registrarEventos() {
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				enviarMail = new EnviarMail(GestionCorreo.this);
				enviarMail.setVisible(true);
				GestionCorreo.this.setVisible(false);
				enviarMail.recibirDatos();
			}
		});
		
		btnSaveId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//RECORRER EL ARRAY LIST Y PARA CADA OBJETO INSERTARLO EN LA BD COMO PERSONA O COMO EMPLEADO SI NO HA SIDO GUARDADO
				for (Persona per : arrayPersonas) {
					if(per.getEstado() == Persona.NUEVO) {
						if(bd.insertar(per)==1) {
							per.setEstado(Persona.GUARDADO);
						}
					}	
				}
			}
		});

		btnLoadId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Persona per;
				Empleado emp;
				ResultSet rs = bd.ObtenerTodos();
				int result = JOptionPane.showConfirmDialog(GestionCorreo.this,"La informacion de la lista se va a sobreescribir. ¿Estas seguro de que quieres continuar?", "Sobreescribir",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION){
					modeloNombres.clear();
					modeloCorreos.clear();
					modeloWebs.clear();
					try {
						if(rs.first()) {
							do {
								per = new Persona(rs.getString("nombre"), rs.getString("correo"), rs.getString("web"));
								per.setEstado(Persona.GUARDADO);
								per.setIdPersona(rs.getInt("idPersona"));
								if(rs.getBoolean("esEmpleado")) {
									emp = new Empleado(per, rs.getInt("edad"), rs.getString("direccion"), rs.getString("telefono"));
									emp.setEstado(Persona.GUARDADO);
									arrayPersonas.add(emp);
								} else {
									arrayPersonas.add(per);
								}
								modeloNombres.addElement(per.getNombre());
								modeloCorreos.addElement(per.getCorreo());
								modeloWebs.addElement(per.getWeb());
							}while (rs.next());
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//cada fila del ResultSet crear un objeto de clase Persona o de clase Empleado
					//Añade ese objeto al arrayList
					//Muestra los datos de ese objeto en los JList
				}
			}
		});

		listNombres.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				seleccionarListas(listNombres.getSelectedIndex());

			}
		});

		listCorreos.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				seleccionarListas(listCorreos.getSelectedIndex());

			}
		});

		listWebs.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				seleccionarListas(listWebs.getSelectedIndex());

			}
		});

		chckbxEmpleado.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//si esta marcado visible y sino invisible
				panelEmpleado.setVisible(chckbxEmpleado.isSelected());

			}
		});

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//EL NOMBRE ES OBLIGATORIO
				//CREAR UN OBJETO DE CLASE PERSONA O EMPLEADO CON LOS DATOS DE LOS JTEXTFIELD Y AÑADIRLO AL ARRAYLIST
				Persona per;
				Empleado emp;

				//SI EL NOMBRE ESTA VACIO
				if(textFieldNombre.getText().equals("")) {
					textFieldNombre.requestFocus();
					textFieldNombre.selectAll();
					return;
				}

				per=new Persona(textFieldNombre.getText().trim(), textFieldCorreo.getText().trim(), textFieldWeb.getText().trim());

				if(!per.esCorreoCorrecto()) {
					textFieldCorreo.requestFocus();
					textFieldCorreo.selectAll();
					return;
				}

				if(chckbxEmpleado.isSelected()) {
					try {
						emp = new Empleado(per, Integer.parseInt(textFieldEdad.getText().trim()), textFieldDir.getText().trim(), textFieldTelf.getText().trim());
						arrayPersonas.add(emp);
					} catch (NumberFormatException e1) {
						textFieldEdad.requestFocus();
						textFieldEdad.selectAll();
						return;
					}
				} else {
					arrayPersonas.add(per);
				}

				//AÑADIR LOS DATOS DE LOS JTEXTFIELD (O DEL OBJETO PERSONA) A LOS JLIST (VISUAL)
				modeloNombres.addElement(per.getNombre());

				if(per.getCorreo().trim().equals("")) {
					modeloCorreos.addElement(" ");
				} else {
					modeloCorreos.addElement(per.getCorreo());
				}

				if(per.getWeb().trim().equals("")) {
					modeloWebs.addElement(" ");
				} else {
					modeloWebs.addElement(per.getWeb());
				}

				textFieldNombre.setText("");
				textFieldCorreo.setText("");
				textFieldWeb.setText("");
				textFieldEdad.setText("");
				textFieldDir.setText("");
				textFieldTelf.setText("");
				textFieldNombre.requestFocus();
			}
		});
	}

	protected void seleccionarListas(int selectedIndex) {
		listNombres.setSelectedIndex(selectedIndex);
		listCorreos.setSelectedIndex(selectedIndex);
		listWebs.setSelectedIndex(selectedIndex);
	}

	public JList<String> getListNombres() {
		return listNombres;
	}

	public void setListNombres(JList<String> listNombres) {
		this.listNombres = listNombres;
	}

	public JList<String> getListWebs() {
		return listWebs;
	}

	public void setListWebs(JList<String> listWebs) {
		this.listWebs = listWebs;
	}

	public JList<String> getListCorreos() {
		return listCorreos;
	}

	public void setListCorreos(JList<String> listCorreos) {
		this.listCorreos = listCorreos;
	}

	public ArrayList<Persona> getArrayPersonas() {
		return arrayPersonas;
	}

	public void setArrayPersonas(ArrayList<Persona> arrayPersonas) {
		this.arrayPersonas = arrayPersonas;
	}

	public BaseDatos getBd() {
		return bd;
	}

	public void setBd(BaseDatos bd) {
		this.bd = bd;
	}
}

