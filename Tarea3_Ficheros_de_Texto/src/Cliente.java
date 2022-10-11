import java.util.concurrent.atomic.AtomicInteger;
public class Cliente {
	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	int id;
	String nombre;
	String apellidos;
	int telefono;
	String direccion;
	
	public Cliente(String nombre, String apellidos, int telefono, String direccion) {
		this.id = atomicInteger.incrementAndGet();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public Cliente() {
		this.id = atomicInteger.incrementAndGet();
		this.nombre = "";
		this.apellidos = "";
		this.telefono = 0;
		this.direccion = "";
	}
	
	@Override
	public String toString() {
		return "Cliente " + id + " Nombre=" + nombre + ", Apellidos=" + apellidos + ", Telefono=" + telefono
				+ ", Direccion=" + direccion + "]";
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
