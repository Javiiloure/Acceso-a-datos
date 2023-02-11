import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz interfaz = new Interfaz();
					interfaz.setVisible(true);
					interfaz.setResizable(false);
					interfaz.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// En este metodo modificamos el cuadro de texto que nos llega desde la 
	// interfaz mostrando los libros del archivo que se seleccione
	public static void mostrarXML(JTextArea cuadro) {
		String aux ="";
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
			     "xml files (*.xml)", "xml");
		filechooser.setFileFilter(xmlfilter);
		filechooser.showOpenDialog(filechooser);
		
		try {
			File archivo = filechooser.getSelectedFile();
		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			
			NodeList lista = document.getElementsByTagName("Libro");
			
			for (int temp = 0; temp < lista.getLength(); temp++) {
                Node nodo = lista.item(temp);
                if(temp == 0) {
                		aux += nodo.getNodeName();
                	} else {
                		aux += "\n\n" + nodo.getNodeName();
                	}
                
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                	
                    Element element = (Element) nodo;
                    aux += "\nId: " + element.getAttribute("id");
                    aux += "\nTitulo: " + element.getElementsByTagName("Titulo").item(0).getTextContent();
                    aux += "\nAutor: " + element.getElementsByTagName("Autor").item(0).getTextContent();
                    aux += "\nPaginas: " + element.getElementsByTagName("Paginas").item(0).getTextContent();
                    aux += "\nFecha: " + element.getElementsByTagName("Fecha").item(0).getTextContent();
                }
            }
			cuadro.setText(aux);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Metodo para crear un nuevo archivo al que le llega el Frame de crear archivos
	public static void crearXML(CrearXML crearXML) {
		
		//ArrayList de libros que usaremos a la hora de guardar el archivo
		ArrayList<Libro> libros = new ArrayList<Libro>();
		
		crearXML.añadirLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String titulo = crearXML.inputTitulo.getText();
				String autor = crearXML.inputAutor.getText();
				String paginas = crearXML.inputPaginas.getText();
				String fecha = crearXML.inputFecha.getText();
				
				Libro libro = new Libro(titulo, autor, paginas, fecha);
				libros.add(libro);
				crearXML.mostrarLibros.setText(crearXML.mostrarLibros.getText() + libro.toString() + "\n");
			}
		});
		
		// Borramos el ultimo libro añadido
		crearXML.borrarUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				libros.remove(libros.size() - 1);
				String aux = "";
				int last = crearXML.mostrarLibros.getText().lastIndexOf("Libro");
				for(int i = 0; i < last; i ++) {
					aux += crearXML.mostrarLibros.getText().charAt(i);
				}
				crearXML.mostrarLibros.setText(aux);
			}
		});
		
		// Guardamos el archivo
		crearXML.guardarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String nombre = JOptionPane.showInputDialog("Introduzca el nombre del archivo: ");
				String aux = "";
				int id = 1;
				try {
					FileWriter fw = new FileWriter(nombre + ".xml", true);
					fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
					fw.write("<Libros>\n");
					// Bucle for que escribe en el archivo XML los libros del ArrayList
					for (int i = 0; i < libros.size(); i++) {
						aux += "\t<Libro id=\"" + id +"\">\n\t\t<Titulo>" + libros.get(i).getTitulo() + "</Titulo>\n\t\t"
								+ "<Autor>" + libros.get(i).getAutor() + "</Autor>\n\t\t"
								+ "<Paginas>" + libros.get(i).getPaginas() + "</Paginas>\n\t\t"
								+ "<Fecha>" + libros.get(i).getFecha() + "</Fecha>\n" + "\t</Libro>\n";
						id++;
					}
					fw.write(aux);
					fw.write("</Libros>");
					fw.flush();
					fw.close();
					crearXML.setVisible(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}