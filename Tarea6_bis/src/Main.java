import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.util.InputMismatchException;
import java.io.IOException;

public class Main {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		String select = "";
		boolean exit = false;
		double number;
		double sustituto;
		File file = new File("doubles.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("Error de E/S de datos");
			System.out.println(e.getMessage());
		}
		
		// Bucle par mostrar el menu hasta seleccionar salir
		while (exit != true) {
			System.out.println(
					"Bienvenido a doubles, seleccione una opción: \n1/Añadir double al principio \n2/Añadir double al final "
							+ "\n3/Mostrar fichero \n4/Sustituir double por otro \n5/Salir del programa");
			select = scan.nextLine();
			switch (select) {
			// Case 1 para añadir el numero al principio
			case "1":
				System.out.println("Introduzca el numero decimal a añadir: ");
				try {
					number = Double.parseDouble(scan.nextLine());
					añadir_principio(number, file);
				} catch (InputMismatchException e) {
					System.out.println("Formato introducido erroneo, el formato es XX.XX");
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println("Error de E/S de datos.\n" + e.getMessage());
				}
				break;
			// Case 2 para añadir el numero al final de archivo
			case "2":
				System.out.println("Introduzca el numero decimal a añadir: ");
				try {
					number = Double.parseDouble(scan.nextLine());
					añadir_final(number, file);
				} catch(IOException e) {
					System.out.println("Error de E/S de datos");
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Formato introducido erroneo, el formato es XX.XX");
					System.out.println(e.getMessage());
				}
				break;
			// Case 3 para mostrar el archivo
			case "3":
				try {
					mostrarFichero(file);
				} catch (IOException e) {
					System.out.println("Error de E/S de datos.");
					System.out.println(e.getMessage());
				}
				break;
			// Case 4 para sustituir un numero por otro
			case "4":
				try {
					System.out.println("Introduzca el decimal a ser sustituido: ");
					number = Double.parseDouble(scan.nextLine());
					System.out.println("Introduzca el decimal sustituto.");
					sustituto = Double.parseDouble(scan.nextLine());
					sustituir(number, sustituto, file);
				} catch (InputMismatchException e) {
					System.out.println("Formato introducido erroneo, el formato es XX.XX");
					System.out.println(e.getMessage());
				} catch(IOException e) {
					System.out.println("Error de E/S de datos");
					System.out.println(e.getMessage());
				}
				break;
			// Case 5 para cerrar el programaz
			case "5":
				exit = true;
				break;
			default:
				System.out.println("Seleccione una opcion valida.");
			}
		}
	}

	public static void añadir_principio(double number, File file) throws IOException {
		System.out.println(number);
		String linea;
		String contenido = "";

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		// En primer lugar recorremos el arhivo linea a linea y las guardamos en la
		// string contenido
		while ((linea = br.readLine()) != null) {
			contenido += linea + "\n";
		}
		br.close();

		FileWriter fw = new FileWriter(file);
		// Despues simplemente escribimos primero el numero y luego el contenido
		fw.write(String.valueOf(number) + "\n");
		fw.write(contenido);
		fw.flush();
		fw.close();
		mostrarFichero(file);
	}

	public static void añadir_final(double number, File file) throws IOException{
		FileWriter fw = new FileWriter(file, true);
		fw.write(String.valueOf("\n" + number));
		fw.flush();
		fw.close();
	}

	public static void mostrarFichero(File file) throws IOException {
		String linea;
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		// Recorremos linea por linea y sacamos por pantalla
		while ((linea = br.readLine()) != null) {
			System.out.println(linea);
		}
		br.close();
	}
	
	public static void sustituir(double number, double sustituto, File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linea;
		String contenido = "";
		// Leemos el archivo y guardamos su contenido en un String
		while((linea = br.readLine()) != null) {
			contenido += linea + "\n";
		}
		br.close();
		// Modificamos la cadena sustituyendo el numero todas las veces que aparezca
		contenido = contenido.replaceAll(String.valueOf(number), String.valueOf(sustituto));
		
		// Escribimos en el archivo el nuevo contenido
		FileWriter fw = new FileWriter(file);
		fw.write(contenido);
		fw.flush();
		fw.close();
	}
}
