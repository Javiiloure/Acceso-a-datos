
import javax.xml.bind.annotation.XmlRootElement;

public class Libro {

	int id;
	String titulo;
	String autor;
	String paginas;
	String fecha;
	
	public Libro() {
		this.titulo = "";
		this.autor = "";
		this.paginas = "";
		this.fecha = "";
	}
	
	public Libro(String titulo, String autor, String paginas, String fecha) {
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.fecha = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", paginas=" + paginas + ", fecha=" + fecha + "]";
	}
}
