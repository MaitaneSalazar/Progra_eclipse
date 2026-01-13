
public class Persona {
	//DATOS
	public static final int GUARDADO=0;
	public static final int MODIFICADO=1;
	public static final int NUEVO=2;
	private int idPersona;
	private String nombre, correo, web;
	private int estado;
	
	//CONSTRUCTOR/ES
	public Persona() {
		idPersona = -1;
		nombre = "";
		correo = "";
		web = "";
		estado=NUEVO;
	}
	
	public Persona(String nombre, String correo, String web) {
		idPersona=-1;
		this.nombre=nombre;
		this.correo=correo;
		this.web=web;
		estado=NUEVO;
	}
	//METODOS
	public boolean esCorreoCorrecto() {
		if(!correo.matches("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,}")) {
			return false;
		}
		//QUE TENGA UNA ARROBA
		if(correo.indexOf("@") != correo.lastIndexOf("@")) {
			return false;
		}
			
		//ENTRE EL ARROBA Y EL PUNTO AL MENOS DOS LETRAS
		if(correo.lastIndexOf("@") + 3 > correo.lastIndexOf(".") ) {
			return false;
		}
		
		//ANTES DEL ARROBA AL MENOS DOS LETRAS
		if(correo.indexOf("@")<2) {
			return false;
		}
		
		//Despues del ultimo punto (despues del arroba) dos letras
		if(correo.lastIndexOf(".") < correo.lastIndexOf("@") && correo.length() < correo.lastIndexOf(".") + 3) {
			return false;
		}
		
		//Que haya espacios
		if(correo.contains(" ")) {
			return false;
		}
		
		return true;
	}
	
	//GETTERS Y SETTERS
	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
