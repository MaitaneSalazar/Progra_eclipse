import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos {
	private Connection cn;
	private String driver, cadenaConexion;
	
	public BaseDatos() {
		cadenaConexion="jdbc:mysql://localhost:3306/socios";
		driver = "com.mysql.jdbc.Driver";
		try {
			cn = DriverManager.getConnection(cadenaConexion, "root", "");
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	
	//FUNCIONES
	//OBTENER DATOS (RESULTADO DE SELECT)
	public ResultSet obtenerTodos() { //DEVUELVE TODOS LOS DATOS DE TODOS LOS SOCIOS
		String sentencia;
		PreparedStatement sqlSent;
		ResultSet rs;
		
		sentencia = "SELECT * FROM socios";
		try {
			sqlSent = cn.prepareStatement(sentencia, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = sqlSent.executeQuery();
			
			return rs;
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet obtenerDatos(String sentencia) {
		PreparedStatement sqlSent;
		ResultSet rs;
		try {
			sqlSent=cn.prepareStatement(sentencia, ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			rs=sqlSent.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//INSERTAR DATOS EN LA BD
	public int insertarSocio(String nombre, String direccion, String telefono) {
		String sentencia;
		PreparedStatement sqlSent;
		
		//sentencia = "INSERT INTO socios VALUES (NULL, '" + nombre + "', '" + direccion + "', '" + telefono + "')";
		sentencia = "INSERT INTO socios VALUES (NULL, ?, ?, ?)";
		try {
			sqlSent = cn.prepareStatement(sentencia);
			sqlSent.setString(1, nombre);
			sqlSent.setString(2, direccion);
			sqlSent.setString(3, telefono);
			return sqlSent.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertarSocio(Socio socio) {
		String sentencia;
		PreparedStatement sqlSent;
		
		sentencia = "INSERT INTO socios (nombre, direccion, telefono) VALUES ('" + socio.getNombre() + "', '" + socio.getDireccion() + "', '" + socio.getTelefono() + "')";
		try {
			sqlSent = cn.prepareStatement(sentencia);
			return sqlSent.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int actualizarSocio(int numSocio, String nombre, String direccion, String telefono) {
		String sentencia;
		PreparedStatement sqlSent;
		
		//sentencia = "UPDATE socios SET nombre='"+ nombre +"', direccion ='"+ direccion +"', telefono='"+ telefono + "' WHERE numSocio ="+ numSocio;
		sentencia = "UPDATE socios SET nombre=?, direccion =?, telefono=? WHERE numSocio =?";
		try {
			sqlSent = cn.prepareStatement(sentencia);
			sqlSent.setString(1, nombre);
			sqlSent.setString(2, direccion);
			sqlSent.setString(3, telefono);
			sqlSent.setInt(4, numSocio);	
			return sqlSent.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int actualizarSocio(String sent) {
		String sentencia;
		PreparedStatement sqlSent;
		
		sentencia = sent;
		try {
			sqlSent = cn.prepareStatement(sentencia);
			return sqlSent.executeUpdate();

			} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	//GETTERS/SETTERS
	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}
	
	public int eliminarSocio(int id) {
		String sentencia;
		PreparedStatement sqlSent;
		
		sentencia = "DELETE FROM socios WHERE numSocio =?";
		try {
			sqlSent = cn.prepareStatement(sentencia);
			sqlSent.setInt(1, id);
			return sqlSent.executeUpdate();

			} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
