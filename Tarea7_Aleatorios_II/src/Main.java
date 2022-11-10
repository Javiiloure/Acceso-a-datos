import java.io.FileOutputStream;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String origen = "";
		String destino = "";
		JFileChooser filechooser = new JFileChooser();				
		try {
			
			// Pedimos la ruta de origen
			JOptionPane.showMessageDialog(null, "Introduzca la ruta de origen en el siguiente cuadro.");
			filechooser.showOpenDialog(filechooser);
			origen = filechooser.getSelectedFile().getAbsolutePath();

			// Pedimos la ruta de destino
			JOptionPane.showMessageDialog(null, "Introduzca la ruta de destino en el siguiente cuadro.");
			filechooser.showOpenDialog(filechooser);
			destino = filechooser.getSelectedFile().getAbsolutePath();
			
			FileInputStream fis = new FileInputStream(origen);
			FileOutputStream fos = new FileOutputStream(destino);
			
			// Declaramos un array de bytes y con el metodo avaliable() le asignamos el tamaño
			// de bytes disponible para lectura que tenga el fichero de origen
			byte [] bytes = new byte[fis.available()];
			
			// Copiamos los bytes del origen al array
			fis.read(bytes);
			
			// Escribimos los bytes en el destino
			fos.write(bytes);
			
			fis.close();
			fos.close();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
