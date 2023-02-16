import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOM {

	public void crearXML() throws Exception {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document document = docBuilder.newDocument();
		
		Element root = document.createElement("Elementos");
		document.appendChild(root);
		
		int elementos = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el numero de elementos: "));
		for(int i = 1; i <= elementos; i++) {
			
			// Creamos un elemento padre
			Element elemento = document.createElement("Elemento");
			elemento.setAttribute("id", String.valueOf(i));
			root.appendChild(elemento);
			
			// Creamos los elementos hijos para el padre
			int subelementos = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el numero de subelementos del elemento " + i + ":"));
			for (int x = 1; x <= subelementos; x++) {
				Element subelemento = document.createElement("Subelemento_" + x);
				subelemento.setTextContent("Subelemento " + x);
				elemento.appendChild(subelemento);
			}
		}
		
		// Creamos el archivo y escribimos los elementos
		String nombre_archivo = JOptionPane.showInputDialog("Introduzca el nombre del archivo: ");
		File file = new File(nombre_archivo + ".xml");
		TransformerFactory tranFactory = TransformerFactory.newInstance();
		Transformer transformer = tranFactory.newTransformer();
		Source src = new DOMSource(document);
		Result dest = new StreamResult(file);

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(src, dest);
		
		mostrarXML(Interfaz.contenido, file);
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
			String id;
			Element elementos = document.getDocumentElement();
			List<Element> lista = getChildElements(elementos);
			
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer transformer = tranFactory.newTransformer();
			Source src = new DOMSource(document);
			Result dest = new StreamResult(archivo);
			
		// Cuadro con opciones a elegir elemento padre o hijo
		String[] opciones = {"Elemento padre", "Elemento hijo"}; 
		int select = JOptionPane.showOptionDialog(null, "Seleccione el tipo de elemento a crear: ",
                "Tipo de nodo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
		
		switch(select) {
		case 0:
			// Creamos elemento padre
			id = String.valueOf(lista.size() + 1);
			Element elemento = document.createElement("Elemento");
			Attr attr = document.createAttribute("id");
			attr.setValue(String.valueOf(id));
			elemento.setAttributeNode(attr);
			
			String subelementos = JOptionPane.showInputDialog("Introduzca el numero de subelementos: ");
			Element subelemento;
			
			// Creamos los elementos hijos
			for (int i = 1; i <= Integer.parseInt(subelementos); i++) {
				subelemento = document.createElement("Subelemento_" + i);
				subelemento.appendChild(document.createTextNode("Subelemento " + i));
				elemento.appendChild(subelemento);
			}			
			elementos.appendChild(elemento);

			// Escribimos los cambios en el archivo
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(src, dest);
			mostrarXML(cuadro, archivo);
			break;
		case 1:
			// Creamos el elemento hijo
			id = JOptionPane.showInputDialog("Introduzca el id del padre: ");
			Element padre = document.createElement("Padre");
			boolean encontrado = false;
			
			for(int i = 0; i < lista.size(); i++) {
				if(lista.get(i).getAttribute("id").equals(id)) {
					padre = lista.get(i);
					encontrado = true;
					break;
				}
			}
			
			if (encontrado) {
				// Obtenemos el numero de subelementos que tiene el padre
				// y creamos el nuevo subelemento
				lista = getChildElements(padre);
				int aux = lista.size() + 1;
				Element hijo = document.createElement("Subelemento_" + aux);
				hijo.setTextContent("Subelemento " + aux);
				padre.appendChild(hijo);

				// Escribimos los cambios en el archivo
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.transform(src, dest);
				mostrarXML(cuadro, archivo);
			} else {
				JOptionPane.showMessageDialog(null, "No se ha encontrado el elemento con el id dado.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
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
