import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOM {

	public void crearXML() {

	}

	public void mostrarXML(JTextArea cuadro, File archivo) throws Exception {

		// Declaramos una String auxiliar para guardar el contenido de los elementos
		String aux = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
		Document document = documentBuilder.parse(archivo);
		document.getDocumentElement().normalize();

		NodeList lista = document.getElementsByTagName("Elemento");
		
		// Recorremos la lista de elementos
		for (int i = 0; i < lista.getLength(); i++) {
			Node nodo = lista.item(i);

			// Condicional para formatear bien el texto y concatenar en aux los elementos
			// padre
			if (i == 0) {
				aux += nodo.getNodeName();
			} else {
				aux += "\n\n" + nodo.getNodeName();
			}

			Element element = (Element) nodo;
			aux += "\nId: " + element.getAttribute("id");
			// Guardamos en una lista los hijos del elemento
			List<Element> subelementos = getChildElements(element);

			// Bucle para concatenar en aux los valores de los hijos
			for (int x = 1; x <= subelementos.size(); x++) {
				aux += "\nSubelemento_" + x + " "
						+ element.getElementsByTagName("Subelemento_" + x).item(0).getTextContent();
			}
		}

		// Cambiamos el texto de la caja por aux
		cuadro.setText(aux);
	}

	public void añadirNodo(JTextArea cuadro, File archivo) throws Exception{
		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			

		
		// Cuadro con opciones a elegir elemento padre o hijo
		String[] opciones = {"Elemento padre", "Elemento hijo"}; 
		int select = JOptionPane.showOptionDialog(null, "Seleccione el tipo de elemento a crear: ",
                "Tipo de nodo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
		
		switch(select) {
			// Creamos elemento padre
		case 0:
			
			Element raiz = document.getDocumentElement();
			NodeList lista = document.getElementsByTagName("Elemento");
			int id = lista.getLength() + 1;
			
			Element element = document.createElement("Elemento_X");
			document.appendChild(element);
			element.setAttribute("id", String.valueOf(id));
			break;
			// Creamos elemento hijo
		case 1:
			System.out.println("Hola");
		}
	}

	// Metodo para conseguir los hijos de un elemento padre
	public static List<Element> getChildElements(Element ele) {

		NodeList nl = ele.getChildNodes();
		List<Element> childEles = new ArrayList<>();
		// Recorremos la NodeList
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			// Si el nodo es una instancia de elemento lo guardamos en la lista
			if (node instanceof Element) {
				childEles.add((Element) node);
			}
		}
		return childEles;
	}
}
