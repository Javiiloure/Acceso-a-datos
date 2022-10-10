import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Fichero_texto {

	static String linea;

	public static void main(String[] args) {

		// Booleano para manejar el bucle principal
		boolean exit = false;
		// Opciones para el menu
		String[] opciones = { "Crear archivo", "Mostrar archivo", "Terminar" };
		// Entero para manejar JOptionPane
		int select;
		// Archivo a manejar
		// Cambiar el nombre de usuario segun el equipo
		File file = new File("C:\\Users\\javie\\Desktop\\FichaUsuario.txt");

		// Iniciamos un bucle que se cierra cuando el usuario seleccione terminar y
		// dentro construimos el menu.
		while (exit != true) {
			try {
				select = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Menu gestor de archivos",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones, opciones[1]);
				switch (select) {
				// Si el usuario selecciona crear fichero se llama al metodo CrearFichero
				case 0:
					CrearFichero(file);
					break;
				// Si el usuario selecciona mostrar fichero llamamos al metodo MostrarFichero
				case 1:
					MostrarFichero(file);
				case 2:
					exit = true;
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	public static void CrearFichero(File file) {
		try {
			// Si el archivo no existe creamos uno y llamamos al metodo EscribirFichero para
			// agregar contenido
			if (!file.exists()) {
				file.createNewFile();
				JOptionPane.showMessageDialog(null, "Archivo creado correctamente.");
				FileWriter fw = new FileWriter(file, true);
				PrintWriter pw = new PrintWriter(fw);
				EscribirFichero(pw);
				// Si el archivo ya existe simplemente llamamos al metodo EscribirFichero
			} else {
				JOptionPane.showMessageDialog(null, "El archivo ya existe en este sistema, se añadirán más datos");
				FileWriter fw = new FileWriter(file, true);
				PrintWriter pw = new PrintWriter(fw);
				EscribirFichero(pw);
				pw.close();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error de E/S de datos");
		}
	}

	public static void EscribirFichero(PrintWriter pw) {
		// Recogemos linea por linea los datos a introducir
		linea = JOptionPane.showInputDialog("Introduzca el nombre de usuario: ");
		pw.println(linea);
		linea = JOptionPane.showInputDialog("Introduzca los apellidos de usuario: ");
		pw.println(linea);
		linea = JOptionPane.showInputDialog("Introduzca la fecha de nacimiento: ");
		pw.println(linea);
		pw.println(" ");
		pw.flush();
	}

	public static void MostrarFichero(File file) throws FileNotFoundException {
		//Declaramos FileReader y BufferedReader y llamamos a LeerFichero pasando BufferedReader como argumento
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		LeerFichero(br);
	}

	public static void LeerFichero(BufferedReader br) {
		String aux = "";
		int cont = 0;
		try {
			//Declaramos un bucle que lee el archivo linea por linea y guardando cada una en la variable, se cierra el bucle 
			//cuando se termina el archivo
			while ((linea = br.readLine()) != null) {
				//Añadimos a nuestra cadena auxiliar cada linea y un salto de linea, sumamos al contador
				aux += linea + "\n";
				cont++;
				//Si el contador llega a 3, se muestra un mensaje con la cadena auxiliar y se reinicia junto con el contador
				if(cont == 3) {
					JOptionPane.showMessageDialog(null, aux);
					cont = 0;
					aux = "";
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
