import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) {
		
		//Crea el String con la cadena XML
		String texto ="<Libros><Libro id=\"1\"><Titulo>El secreto</Titulo><Autor>Javier Loureiro</Autor><Paginas>284</Paginas><Fecha>14/02/2022</Fecha></Libro><Libro id=\"1\"><Titulo>El secreto</Titulo><Autor>Javier Loureiro</Autor><Paginas>284</Paginas><Fecha>14/02/2022</Fecha></Libro></Libros>";

		//Guarda en nombre el nombre del archivo que se creará.
		String nombre = "libros.xml";

		try{
			//Se crea un Nuevo objeto FileWriter
			FileWriter fichero = new  FileWriter (nombre);
			
			//Se escribe el fichero 
			fichero.write(texto + "\r\n");
			
			//Se cierra el fichero 
			fichero.close();
			}catch (IOException ex) { 
				System.out.println("Error al acceder al fichero");
		}
	}
	
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
	
	public static void crearXML() {
		
	}
}