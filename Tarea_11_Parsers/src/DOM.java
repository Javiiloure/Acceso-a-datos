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
	
	public void monstrarXML(JTextArea cuadro, File archivo) {
		try {
			String aux = "";
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			
			NodeList lista = document.getElementsByTagName("Elemento");
			for(int i = 0; i < lista.getLength(); i++) {
				Node nodo = lista.item(i);
				
				 if(i == 0) {
             		aux += nodo.getNodeName();
             	} else {
             		aux += "\n\n" + nodo.getNodeName();
             	}
            
                 Element element = (Element) nodo;
                 aux += "\nId: " + element.getAttribute("id");
                 List<Element> subelementos = getChildElements(element);
                
                for(int x = 1; x <= subelementos.size(); x++) {
                	 aux += "\nSubelemento_" + x + " " + element.getElementsByTagName("Subelemento_" + x).item(0).getTextContent();
                	 }
			}
			cuadro.setText(aux);
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error",
				      "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void añadirNodo() {
		
	}
	
	public static List<Element> getChildElements(Element ele) {
		  //Assert.notNull(ele, "Element must not be null");
		  NodeList nl = ele.getChildNodes();
		  List<Element> childEles = new ArrayList<>();
		  for (int i = 0; i < nl.getLength(); i++) {
		    Node node = nl.item(i);
		    if (node instanceof Element) {
		      childEles.add((Element) node);
		    }
		  }
		  return childEles;
		}
}
