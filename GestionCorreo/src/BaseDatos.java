import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos {
	//DATOS
	private Connection cn;
	private String driver, cadenaConexion;

	public BaseDatos() {
		//getClass().forName(driver); proyectos antiguos
		cadenaConexion="jdbc:mysql://localhost:3306/gestioncorreo";
		driver = "com.mysql.jdbc.Driver";
		try {
			cn = DriverManager.getConnection(cadenaConexion, "root", "");
		} catch (SQLException e) {
			//e.printStackTrace();
			cn=null;
		}
	}

	public int insertar(Persona per) {
		String sentencia;
		PreparedStatement sqlSent;
		sentencia = "INSERT INTO personas VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
		try {
			sqlSent = cn.prepareStatement(sentencia);
			sqlSent.setString(1, per.getNombre());
			sqlSent.setString(2, per.getCorreo());
			sqlSent.setString(3, per.getWeb());
			sqlSent.setBoolean(4, false);
			sqlSent.setInt(5, -1);
			sqlSent.setString(6, "");
			sqlSent.setString(7, "");


			if(per.getClass()==Empleado.class) {
				sqlSent.setBoolean(4, true);
				sqlSent.setInt(5, ((Empleado)per).getEdad());
				sqlSent.setString(6, ((Empleado)per).getDireccion());
				sqlSent.setString(7, ((Empleado)per).getTelefono());
			}
			return sqlSent.executeUpdate();
		} catch (SQLException e) {

		}
		return 0;
	}

	public ResultSet ObtenerTodos() {
		String sentencia;
		PreparedStatement sqlSent;
		ResultSet rs;

		sentencia = "SELECT * FROM personas";
		try {
			sqlSent = cn.prepareStatement(sentencia, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = sqlSent.executeQuery();

			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int guardarMail(String asunto, String texto) {

		String sent;
		//PREPARAR LA SENTENCIA 
		sent = "INSERT INTO mails (asunto,texto) VALUES (?,?)";
		PreparedStatement sentencia;
		ResultSet rs;

		try {
			sentencia=cn.prepareStatement(sent);
			sentencia.setString(1, asunto); //el numero es el ? que es 
			sentencia.setString(2, texto);	//el numero es el ? que es 
			int cont= sentencia.executeUpdate(); 
			sent="SELECT MAX(idMail) FROM mails"; //para obtener el id del mail enviado
			sentencia=cn.prepareStatement(sent, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // no se muy bien que hacen los resultset del parentesis
			rs=sentencia.executeQuery();  
			rs.first();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	public int guardarEnvios(int idMail, String strMails) {
		String [] mails;
		mails=strMails.split(", "); //nueva funcion split, divide en array que se va dimensionando los string en base a algo que separa, como / o ,
		String sent;
		PreparedStatement sentencia;
		ResultSet rs;
		int idPersona,cont;
		cont=0;

		for (String mail : mails) {
			//BUSCAr EL ID CORRESPONDIENTE A ESTE MAIL 
			sent="SELECT idPersona FROM personas WHERE correo=?";
			try {
				sentencia=cn.prepareStatement(sent,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				sentencia.setString(1, mail);
				rs=sentencia.executeQuery();
				rs.first();
				idPersona=rs.getInt(1);
				sent="INSERT INTO enviados VALUES (null,?,?)";
				sentencia=cn.prepareStatement(sent);
				sentencia.setInt(1,idMail);
				sentencia.setInt(2, idPersona);
				sentencia.executeUpdate();
				cont++;
			}catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		return cont;
	}

	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}
}
