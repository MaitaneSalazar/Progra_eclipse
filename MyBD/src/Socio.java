
public class Socio {
	//DATOS (private)
	private int numSocio;
	private String nombre, direccion, telefono;
	
	//CONSTRUCTOR/ES
	public Socio() {
		numSocio = 1;
		nombre = "";
		direccion = "";
		telefono = "";
	}
	
	public Socio(int numSocio, String nombre, String direccion, String telefono) {
		this.numSocio=numSocio;
		this.nombre=nombre;
		this.direccion=direccion;
		this.telefono=telefono;
	}
	
	//METODOS (FUNCIONES)
	
	
	
	//GETTERS Y SETTERS
	public int getnumSocio() {
		return numSocio;
	}
	
	public void setnumSocio(int numSocio) {
		this.numSocio = numSocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
