import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Repaso_Ficheros {
	static Scanner scan = new Scanner(System.in);
	/*
	 * // Ejercicio 1 public static void main(String[] args) { String select; String
	 * nombre; String apellidos; String ciudad; int numero_archivo; boolean exit =
	 * false;
	 * 
	 * inicio: while (exit != true) { System.out.
	 * println("Seleccione una opción: \n1/Crear archivo \n2/Mostrar archivo \n3/Salir del programa"
	 * ); select = scan.next(); scan.nextLine(); switch (select) { case "1": // En
	 * caso de crear archivo, se solicitan los datos por pantalla y se pasan como //
	 * argumentos al metodo crearFichero().
	 * System.out.println("Introduzca el nombre de usuario: "); nombre =
	 * scan.nextLine(); System.out.println("Introduzca los apellidos: "); apellidos
	 * = scan.nextLine();
	 * System.out.println("Introduzca la ciudad de nacimiento: "); ciudad =
	 * scan.nextLine(); try { crearFichero(nombre, apellidos, ciudad);
	 * System.out.println("Archivo creado correctamente."); } catch (IOException e)
	 * { System.out.println("Error de E/S de datos.\n" + e.getMessage()); continue
	 * inicio; } break; case "2": try {
	 * System.out.println("Introduzca el numero del archivo a mostrar: ");
	 * numero_archivo = Integer.parseInt(scan.nextLine());
	 * mostrarFichero(numero_archivo); } catch (NumberFormatException e) {
	 * System.out.println("Error en el formato de datos\n" + e.getMessage());
	 * continue inicio; } catch (FileNotFoundException e) {
	 * System.out.println("Archivo no encontrado " + e.getMessage()); } catch
	 * (IOException e) { System.out.println("Error de E/S de datos " +
	 * e.getMessage()); } break; case "3": exit = true; break; default: } } }
	 * 
	 * public static void crearFichero(String nombre, String apellidos, String
	 * ciudad) throws IOException { // Declaramos una string que sera el nombre que
	 * llevara el archivo y un contador // para los archivos ya creados. String
	 * nombre_archivo = "usuario_1.txt"; int cont = 1; File file = null; // Bucle
	 * infinito que suma una unidad al contador cada vez que se encunetra un //
	 * archivo creado y lo añade al nombre del archivo a crear while (true) { file =
	 * new File(nombre_archivo); // Una vez que el contador supera el numero de
	 * archivos creados, se crea un // nuevo archivo. if (!file.exists()) {
	 * file.createNewFile(); break; } else { cont++; nombre_archivo = "usuario_" +
	 * String.valueOf(cont) + ".txt"; } } // Una vez que salimos del bucle que
	 * escriben en el archivo recien creado los // datos que llegan como agumentos.
	 * FileWriter fw = new FileWriter(file); fw.write(nombre + "\n");
	 * fw.write(apellidos + "\n"); fw.write(ciudad + "\n"); fw.flush(); fw.close();
	 * }
	 * 
	 * public static void mostrarFichero(int numero_archivo) throws
	 * FileNotFoundException, IOException { File file = new File("usuario_" +
	 * String.valueOf(numero_archivo) + ".txt");
	 * 
	 * if (!file.exists()) { System.out.
	 * println("El numero de archivo introducido no existe en el sistema."); } else
	 * { FileReader fr = new FileReader(file); String linea; BufferedReader br = new
	 * BufferedReader(fr); while ((linea = br.readLine()) != null) {
	 * System.out.println(linea); } br.close(); } }
	 */

	// Ejercicio 2
	public static void main(String[] args) {
		//Declaramos el array de numeros y se lo pasamos como argumentos al metodo llenarArray
		int[] numeros = new int[300];
		llenarArray(numeros);
	}

	
	public static int[] llenarArray(int[] numeros) {
		//Este metodo genera numeros aleatorios entre 0 y 999 hasta llenar el array, despues lo devuelve
		int aux;
		Random rn = new Random();
		for (int i = 0; i <= numeros.length; i++) {
			aux = rn.nextInt(1000);
			System.out.println(aux);
			numeros[i] = aux;
		}
		return numeros;
	}
}
