import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class CrearXML extends JFrame {
	
	protected JTextArea mostrarLibros;
	protected JFormattedTextField inputTitulo;
	protected JFormattedTextField inputAutor;
	protected JFormattedTextField inputPaginas;
	protected JFormattedTextField inputFecha;
	protected JButton añadirLibro;
	protected JButton borrarUltimo;
	protected JButton guardarArchivo;
	
	public CrearXML() {
		setDefaultCloseOperation(Interfaz.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 698);
		getContentPane().setLayout(null);
		
		añadirLibro = new JButton("Añadir libro");
		añadirLibro.setBounds(68, 227, 115, 37);
		getContentPane().add(añadirLibro);
		
		guardarArchivo = new JButton("Guardar archivo");
		guardarArchivo.setBounds(255, 593, 115, 37);
		getContentPane().add(guardarArchivo);
		
		mostrarLibros = new JTextArea();
		mostrarLibros.setBounds(36, 275, 556, 293);
		mostrarLibros.setEditable(true);
		JScrollPane scrollBar = new JScrollPane(mostrarLibros);
		scrollBar.setBounds(36, 275, 556, 293);
		getContentPane().add(scrollBar);
	
		borrarUltimo = new JButton("Borrar ultimo");
		borrarUltimo.setBounds(433, 227, 115, 37);
		getContentPane().add(borrarUltimo);
		
		inputTitulo = new JFormattedTextField();
		inputTitulo.setBounds(168, 27, 334, 28);
		getContentPane().add(inputTitulo);
		inputTitulo.setColumns(10);
		
		inputAutor = new JFormattedTextField();
		inputAutor.setBounds(168, 66, 334, 28);
		getContentPane().add(inputAutor);
		inputAutor.setColumns(10);
		
		inputPaginas = new JFormattedTextField();
		inputPaginas.setBounds(168, 105, 334, 28);
		getContentPane().add(inputPaginas);
		inputPaginas.setColumns(10);
		
		inputFecha = new JFormattedTextField();
		inputFecha.setBounds(168, 144, 334, 28);
		getContentPane().add(inputFecha);
		inputFecha.setColumns(10);
		
		JLabel titulo = new JLabel("Titulo:");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titulo.setBounds(90, 25, 68, 28);
		getContentPane().add(titulo);
		
		JLabel Autor = new JLabel("Autor:");
		Autor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Autor.setBounds(90, 64, 68, 28);
		getContentPane().add(Autor);
		
		JLabel Paginas = new JLabel("Paginas:");
		Paginas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Paginas.setBounds(90, 103, 68, 28);
		getContentPane().add(Paginas);
		
		JLabel Fecha = new JLabel("Fecha:");
		Fecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Fecha.setBounds(90, 142, 68, 28);
		getContentPane().add(Fecha);
		setVisible(true);
	}
}
