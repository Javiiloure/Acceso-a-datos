import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		
		// Para probar el programa se ha escrito un archivo de texto con la palabra raton repetida varias veces
		// Cuando se ejecute el programa sustituira la palabra raton por teclado en el archivo todas las veces que aparezca
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
			// Lanzamos JFileChooser en case 1 para seleccionar el archivo
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
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					cambiarPalabra(br, file, palabra_buscar, palabra_modificar);
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

	public static void cambiarPalabra(BufferedReader br, File file, String palabra_buscar, String palabra_modificar) throws IOException {
		
		// String linea para analizr cada linea
		String linea = "";
		//String contenido para guardar el contenido final que se escribira en el fichero
		String contenido_final = "";
		
		// Bucle while que finaliza cuando no qeudan más lineas
		while((linea = br.readLine()) != null) {
			// En cada linea se analiza si contiene la palabra que se esta buscando y si la contiene se sustituye todas las veces que aparezca 
			// por la otra palara y se guarda esa nueva string en contenido_final
			if(linea.contains(palabra_buscar)) {
				linea = linea.replaceAll(palabra_buscar, palabra_modificar);
				contenido_final += linea + "\n";
				System.out.println(linea);
			//Si la linea no contiene la palabra simplemente se suma a contenido final
			} else {
				contenido_final += linea + "\n";
				System.out.println(linea);
			}
		}
		
		// Una vez terminado el bucle, declaramos un FileWriter sin append a true para sustituir todo el contenido anterior del fichero
		// por el nuevo contenido
		FileWriter fw = new FileWriter(file);
		fw.write(contenido_final);
		fw.flush();
		fw.close();
	}
}
