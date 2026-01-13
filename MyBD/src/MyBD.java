import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBD extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int NAVEGACION=0;
	private static final int EDICION=1;
	private static final int ADD=2;
	
	private JPanel contentPane;
	private JButton btnEdit;
	private JButton btnAdd;
	private JButton btnDel;
	private JButton btnOk;
	private JButton btnCancel;
	private JButton btnSig;
	private JButton btnUlt;
	private JButton btnSalir;
	private JTextField txtNumReg;
	private JButton btnAnt;
	private JButton btnPrim;
	private JTextField txtTelf;
	private JTextField txtDir;
	private JTextField txtNom;
	private JTextField txtNum;

	private BaseDatos baseDatos;
	private ResultSet rs;
	private int regActual;
	private int modo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyBD frame = new MyBD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MyBD() throws SQLException {
		setTitle("Socios de Almi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNumSocio = new JLabel("Nº Socio:");
		lblNumSocio.setBounds(44, 76, 67, 14);
		contentPane.add(lblNumSocio);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(44, 125, 67, 14);
		contentPane.add(lblNombre);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(44, 176, 67, 14);
		contentPane.add(lblDireccion);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(44, 223, 67, 14);
		contentPane.add(lblTelefono);

		btnEdit = new JButton("Editar");
		btnEdit.setBounds(340, 72, 89, 23);
		contentPane.add(btnEdit);

		btnAdd = new JButton("Añadir");
		btnAdd.setBounds(340, 121, 89, 23);
		contentPane.add(btnAdd);

		btnDel = new JButton("Eliminar");
		btnDel.setBounds(340, 172, 89, 23);
		contentPane.add(btnDel);

		btnOk = new JButton("Ok");
		btnOk.setEnabled(false);
		btnOk.setBounds(439, 99, 89, 23);
		contentPane.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.setEnabled(false);
		btnCancel.setBounds(553, 99, 89, 23);
		contentPane.add(btnCancel);

		txtNum = new JTextField();
		txtNum.setEnabled(false);
		txtNum.setBounds(121, 73, 151, 20);
		contentPane.add(txtNum);
		txtNum.setColumns(10);

		txtNom = new JTextField();
		txtNom.setEnabled(false);
		txtNom.setBounds(121, 122, 151, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);

		txtDir = new JTextField();
		txtDir.setEnabled(false);
		txtDir.setBounds(121, 173, 151, 20);
		contentPane.add(txtDir);
		txtDir.setColumns(10);

		txtTelf = new JTextField();
		txtTelf.setEnabled(false);
		txtTelf.setBounds(121, 220, 151, 20);
		contentPane.add(txtTelf);
		txtTelf.setColumns(10);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(494, 368, 89, 23);
		contentPane.add(btnSalir);

		JLabel lblTitulo = new JLabel("SOCIOS DE ALMI");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitulo.setBounds(195, 11, 234, 32);
		contentPane.add(lblTitulo);

		btnPrim = new JButton("<<");
		btnPrim.setBounds(29, 289, 64, 23);
		contentPane.add(btnPrim);

		btnAnt = new JButton("<");
		btnAnt.setBounds(103, 289, 64, 23);
		contentPane.add(btnAnt);

		txtNumReg = new JTextField();
		txtNumReg.setBounds(177, 290, 64, 20);
		contentPane.add(txtNumReg);
		txtNumReg.setColumns(10);

		btnSig = new JButton(">");
		btnSig.setBounds(251, 289, 64, 23);
		contentPane.add(btnSig);

		btnUlt = new JButton(">>");
		btnUlt.setBounds(325, 289, 64, 23);
		contentPane.add(btnUlt);

		/*for (Component componentes : panel.getComponents()) {
			componentes.setEnabled(false);
		}*/
												
		registrarEventos();

		baseDatos = new BaseDatos();
		if(baseDatos.getCn()==null) {
			System.out.println("No se ha podido hacer la conexion");
			//Desactivar los controles y sacar JOPtion de informacion
			System.exit(1);
		}

		rs=baseDatos.obtenerTodos();
		modo=NAVEGACION;
		if(rs.first()) {
			regActual = 1;
			cargarDatos();
		}

	}

	private void registrarEventos() {
		btnSig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//AVANZAR AL SIGUIENTE
				try {
					if(rs.next()) {
						regActual++;
						cargarDatos();
					}
					if(rs.isAfterLast()) { //el cursor se pone despues del ultimo y hay que ponerlo en el ultimo
						rs.previous();
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnAnt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.previous()) {
						regActual--;
						cargarDatos();
					}
					if(rs.isBeforeFirst()) {
						rs.next();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		btnPrim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.first()) {
						regActual = 1;
						cargarDatos();
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		btnUlt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					while (!rs.isLast()) {
						regActual++;
						rs.next();
					}
					cargarDatos();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modoEdicion(true);
				modo=EDICION;
				txtNom.requestFocus();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modoEdicion(true);
				modo=ADD;
				txtNum.setText("");
				txtNom.setText("");
				txtDir.setText("");
				txtTelf.setText("");
				txtNumReg.setText("");
				txtNom.requestFocus();
			}
		});
		
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(MyBD.this, "¿Estás seguro de que quieres eliminar los datos de " + txtNom.getText() + "?",
									"Eliminar", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					if(baseDatos.eliminarSocio(Integer.parseInt(txtNum.getText())) == 0) {
						JOptionPane.showMessageDialog(MyBD.this, "No se han podido eliminar los datos del socio");
					} else {
						try {
							rs=baseDatos.obtenerTodos();
							if(rs.isLast()) {
								regActual--;
								rs.last();
							} else {
								rs.absolute(regActual);
							}
							cargarDatos();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modoEdicion(false);
				modo=NAVEGACION;
				cargarDatos();
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNom.getText().trim(); //trim para que no tenga en cuenta los espacios adicionales que puedas poner
				String direccion = txtDir.getText().trim();
				String telefono = txtTelf.getText().trim();
				if(modo==EDICION) {
					int numSocio = Integer.parseInt(txtNum.getText().trim());
					if(baseDatos.actualizarSocio(numSocio, nombre, direccion, telefono)==0) {
						JOptionPane.showMessageDialog(MyBD.this, "No se han podido actualizar los datos del socio");
						txtNom.requestFocus();
						return;
					}
					rs=baseDatos.obtenerTodos();
					try {
						rs.absolute(regActual);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (modo==ADD) {
					if(baseDatos.insertarSocio(nombre, direccion, telefono)==0) {
						JOptionPane.showMessageDialog(MyBD.this, "No se han podido añadir los datos del socio");
						txtNom.requestFocus();
						return;
					}
					rs=baseDatos.obtenerTodos();
					try {
						ResultSet rsAux;
						rsAux=baseDatos.obtenerDatos("SELECT COUNT(*) FROM SOCIOS");
						rsAux.first();
						regActual = rsAux.getInt(1);
						rs.absolute(regActual); //rs.last();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				modoEdicion(false);
				modo=NAVEGACION;
				cargarDatos();
			}
		});
		
		txtNumReg.addActionListener(new ActionListener() {//PULSAR ENTER EN EL JTextField

			@Override
			public void actionPerformed(ActionEvent e) {
				int num, numReg;
				ResultSet rsAux;
				//COGER EL NUMERO ESCRITO (CONTROLAR ERROR SI NO ES NUMERO)

				try {
					num=Integer.parseInt(txtNumReg.getText().trim());
					//comprobar que esiste ese numero de registros e ir a esa posicion
					rsAux=baseDatos.obtenerDatos("SELECT COUNT(*) FROM SOCIOS");
					rsAux.first();
					numReg=rsAux.getInt(1);
					if(num>0 && num<=numReg) {
						rs.absolute(num);
						regActual=num;
						cargarDatos();
					}else {
						cargarDatos();
					}
				} catch(NumberFormatException ex) {
					cargarDatos();
					txtNumReg.selectAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}

	public void cargarDatos() {
		//CARGA LOS DATOS DE REGISTRO ACTUAL EN LOS JTextField DE JFrame
		try {
			txtNum.setText(rs.getInt("numSocio")+"");
			txtNom.setText(rs.getString("nombre"));
			txtDir.setText(rs.getString("direccion"));
			txtTelf.setText(rs.getString("telefono"));
			txtNumReg.setText("Reg. " + regActual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modoEdicion(boolean modo) {
		//si es true se puede editar los jtextfield y utilizar el ok y cancel y se desactivan los botones de flechas, editar, añadir y eliminar
		//si es false vuelve al formato inicial
		
		for (Component componente : contentPane.getComponents()) {
		    // Saltar btnSalir y txtNum
		    if (componente == btnSalir || componente == txtNum) {
		        continue;
		    }
		    if (componente.isEnabled() != modo) {
		        componente.setEnabled(modo);
		    } else {
		        componente.setEnabled(!modo);
		    }
		}
		
	}
}
