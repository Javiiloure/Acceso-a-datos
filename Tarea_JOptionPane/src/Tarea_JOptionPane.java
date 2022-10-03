import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Tarea_JOptionPane {
	public static void main(String[] args) {

		boolean exit = false;
		int select;
		String nombre_archivo;
		String contenido;
		String ruta = "";
		File file = new File(ruta);

		String[] opciones = { "Crear archivo", "Mostrar archivo", "Terminar" };
		String[] opciones_mostrar = { "Nombre", "Contenido", "Cancelar" };
		while (exit != true) {
			select = JOptionPane.showOptionDialog(null, "Seleccione una opci�n", "Men� gestor de archivos",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones, opciones[1]);
			switch (select) {
			case 0:
				nombre_archivo = JOptionPane.showInputDialog("Introduzca el nombre del archivo: ");
				contenido = JOptionPane.showInputDialog("Introduzca el contenido del archivo: ");
				ruta = JOptionPane.showInputDialog("Introduzca la ruta del archivo: ");
				try {
					file = new File(ruta);
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					file.createNewFile();
					bw.write(contenido);
					bw.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error de E/S de datos");
				}
				break;
			case 1:
				ruta = JOptionPane.showInputDialog(null, "Introduzca la ruta del archivo: ");
				file = new File(ruta);
				// Comprueba que el archivo introducido exista
				if (!file.exists()) {
					select = JOptionPane.showOptionDialog(null, "Seleccione una opci�n", "Mostrar archivo",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones_mostrar,
							opciones_mostrar[1]);
					// Muestra el nombre del archivo
					if (select == 0) {
						JOptionPane.showMessageDialog(null, "Nombre de archivo: " + file.getName());
						// Muestra el contenido del archivo
					} else if (select == 1) {
						try {
							FileReader fr = new FileReader(file);
							BufferedReader br = new BufferedReader(fr);
							String linea;
							while (br.readLine() != null) {
								linea = br.readLine();
								JOptionPane.showMessageDialog(null, linea);
							}
						} catch (FileNotFoundException e) {
							JOptionPane.showMessageDialog(null, "Archivo no encontrado");
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Error de E/S de datos.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Archivo no encontrado");
					}
				}
				break;
			case 2:
				exit = true;
			}
		}
	}
}
