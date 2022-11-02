import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main {
	static Scanner scan = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String palabra_buscar = "raton";
		String palabra_modificar = "teclado";
		String ruta = "C:\\Users\\javie\\Desktop\\Acceso a Datos\\Tarea6_Aleatorios_I\\texto.txt";
		String select = "";
		boolean exit = false;
		File file = null;

		// Iniciamos bucle while para crear un menu
		while (!exit) {
			System.out.println("Archivo seleccionado: " + ruta);
			System.out.println("Palabra a buscar: " + palabra_buscar);
			System.out.println("palabra a modificar: " + palabra_modificar);
			System.out.println(
					"Seleccione una opción:\n1/Seleccionar archivo \n2/Modificar palabras \n3/Sustituir palabras en el archivo \n4/Salir del programa");
			select = scan.nextLine();

			// Declaramos un switch para elegir las disitintas opciones
			switch (select) {
			// Iniciamos JFileChooser en case 1 para seleccionar el archivo
			case "1":
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(fileChooser);
				try {
					ruta = fileChooser.getSelectedFile().getAbsolutePath();
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
				break;
			// En case 2 modificamos las palabras a que se quieren sustituir
			case "2":
				System.out.println("Introduzca la palabra a ser modificada: ");
				palabra_buscar = scan.nextLine();
				System.out.println("Introduzca la palabra que se escribirá: ");
				palabra_modificar = scan.nextLine();
				break;
			// En case 3 llamamdos al metodo que sustituira la palabra a buscar por la
			// palabra 2.
			case "3":
				file = new File(ruta);
				try {
					//FileWriter fw = new FileWriter(file);
					FileReader fr = new FileReader(file);
					//BufferedWriter bw = new BufferedWriter(fw);
					BufferedReader br = new BufferedReader(fr);
					cambiarPalabra(br, palabra_buscar, palabra_modificar);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			// En case 4 igualamos exit a false para salir del menu y terminar el programa
			case "4":
				exit = true;
				break;
			// Un default para controlar que se seleccione una opcion corracta
			default:
				System.out.println("Seleccione una opción valida.");
			}
		}
	}

	public static void cambiarPalabra(BufferedReader br, String palabra_buscar, String palabra_modificar) throws IOException {

		String linea = "";
		int index;
		System.out.println("aux");
		while((linea = br.readLine()) != null) {
			
			System.out.println(linea);
			if(linea.contains(palabra_buscar)) {
				linea.replace(palabra_buscar, palabra_modificar);
				System.out.println(linea);
			}
		}
	}
}
