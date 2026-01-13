import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class EnviarMail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPara;
	private JTextField textCC;
	private JLabel lblAsunto;
	private JTextField textAsunto;
	private JLabel lblMensaje;
	private JTextArea textMensaje;
	private JList<String> list;
	private JScrollPane scrollPane;
	private JButton btnFlecha;
	private JButton btnVolver;
	private JButton btnEnviar;
	private GestionCorreo gestionCorreo;
	private DefaultListModel<String> modeloMails;
	private Timer reloj;

	/**
	 * Create the frame.
	 */
	public EnviarMail(GestionCorreo gestionCorreo) {
		setResizable(false);
		this.gestionCorreo = gestionCorreo;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPara = new JLabel("Para:");
		lblPara.setBounds(31, 25, 46, 14);
		contentPane.add(lblPara);

		textPara = new JTextField();
		textPara.setEditable(false);
		textPara.setBounds(87, 22, 406, 20);
		contentPane.add(textPara);
		textPara.setColumns(10);

		JLabel lblCC = new JLabel("CC:");
		lblCC.setBounds(31, 58, 46, 14);
		contentPane.add(lblCC);

		textCC = new JTextField();
		textCC.setEditable(false);
		textCC.setBounds(87, 55, 406, 20);
		contentPane.add(textCC);
		textCC.setColumns(10);

		lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(31, 89, 46, 14);
		contentPane.add(lblAsunto);

		textAsunto = new JTextField();
		textAsunto.setBounds(87, 86, 406, 20);
		contentPane.add(textAsunto);
		textAsunto.setColumns(10);

		lblMensaje = new JLabel("Mensaje:");
		lblMensaje.setBounds(31, 129, 46, 14);
		contentPane.add(lblMensaje);

		textMensaje = new JTextArea();
		textMensaje.setLineWrap(true);
		textMensaje.setBounds(87, 124, 406, 300);
		contentPane.add(textMensaje);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(503, 367, 89, 23);
		contentPane.add(btnEnviar);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(503, 401, 89, 23);
		contentPane.add(btnVolver);

		btnFlecha = new JButton(">");
		btnFlecha.setBounds(503, 18, 74, 54);
		contentPane.add(btnFlecha);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(602, 25, 256, 310);
		contentPane.add(scrollPane);

		list = new JList<String>();
		scrollPane.setViewportView(list);
		modeloMails = new DefaultListModel<String>();
		list.setModel(modeloMails);

		registrarEventos();
	}

	public void recibirDatos() {
		if(gestionCorreo.getListCorreos().getSelectedIndex()!=-1) {
			textPara.setText(gestionCorreo.getListCorreos().getSelectedValue());
		}
		for(int i=0; i<gestionCorreo.getListCorreos().getModel().getSize(); i++) {
			if(i!=gestionCorreo.getListCorreos().getSelectedIndex()) {
				modeloMails.addElement(gestionCorreo.getListCorreos().getModel().getElementAt(i));
			}
		}

	}

	private void registrarEventos() {
		btnEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				int idMail;

				if((idMail=gestionCorreo.getBd().guardarMail(textAsunto.getText(), textMensaje.getText()))==0) {
					JOptionPane.showMessageDialog(null, "No se ha podido guardar el mensaje");
					return;
				}
				if(gestionCorreo.getBd().guardarEnvios(idMail,textPara.getText())==0) {
					JOptionPane.showMessageDialog(null, "No se han podido guardar todos los envios");
					return;
				}
			}
		});

		reloj = new Timer(25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EnviarMail enviarMail = EnviarMail.this;
				if(btnFlecha.getText().equals(">")) {
					enviarMail.setSize(enviarMail.getWidth()+5, enviarMail.getHeight());
					if(enviarMail.getWidth()>=900) {
						btnFlecha.setText("<");
						reloj.stop();
					}
				} else if(btnFlecha.getText().equals("<")) {
					enviarMail.setSize(enviarMail.getWidth()-5, enviarMail.getHeight());
					if(enviarMail.getWidth()<=615) {
						btnFlecha.setText(">");
						reloj.stop();
					}
				}
			}
		});

		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gestionCorreo.setVisible(true);
				EnviarMail.this.dispose();
			}
		});

		btnFlecha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reloj.start();
			}
		});

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //doble click
					if(list.getSelectedIndex()>=0) { //si se ha seleccionado algo de la lista
						if(textPara.getText().equals("")) {
							textPara.setText(list.getSelectedValue());
						} else {
							textPara.setText(textPara.getText() + ", " + list.getSelectedValue());
						}
						modeloMails.remove(list.getSelectedIndex());
					}	
				}
			}
		});
	}
}
