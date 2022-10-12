import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;

public class Repaso_Ficheros {
	static Scanner scan = new Scanner(System.in);

	// Ejercicio 1
	public static void main1(String[] args) {

		// Los ficheros con los datas se guardaran en formato usuario_X.txt, siendo X
		// un numero incrementable en base a los archivos que existan.
		String select;
		String nombre;
		String apellidos;
		String ciudad;
		int numero_archivo;
		boolean exit = false;

		// Declaramos un bucle para el menu principal y una etiqueta inicio para volver
		// en caso de excepcion
		inicio: while (exit != true) {
			System.out.println("Seleccione una opcion: \n1/Crear archivo \n2/Mostrar archivo \n3/Salir del programa");
			select = scan.next();
			scan.nextLine();
			switch (select) {
			case "1":
				// En caso de crear archivo, se solicitan los datos por pantalla y se pasan como
				// argumentos al metodo crearFichero().
				System.out.println("Introduzca el nombre de usuario: ");
				nombre = scan.nextLine();
				System.out.println("Introduzca los apellidos: ");
				apellidos = scan.nextLine();
				System.out.println("Introduzca la ciudad de nacimiento: ");
				ciudad = scan.nextLine();
				try {
					crearFichero(nombre, apellidos, ciudad);
					System.out.println("Archivo creado correctamente.");
				} catch (IOException e) {
					System.out.println("Error de E/S de datos.\n" + e.getMessage());
					continue inicio;
				}
				break;
			case "2":
				// En caso de mostrar archivo se solicita un numero y se pasa como argumento al
				// metodo mostrarFichero()
				try {
					System.out.println("Introduzca el numero del archivo a mostrar: ");
					numero_archivo = Integer.parseInt(scan.nextLine());
					mostrarFichero(numero_archivo);
				} catch (NumberFormatException e) {
					System.out.println("Error en el formato de datos\n" + e.getMessage());
					continue inicio;
				} catch (FileNotFoundException e) {
					System.out.println("Archivo no encontrado " + e.getMessage());
					continue inicio;
				} catch (IOException e) {
					System.out.println("Error de E/S de datos " + e.getMessage());
					continue inicio;
				}
				break;
			case "3":
				exit = true;
				break;
			default:
			}

		}
	}

	public static void crearFichero(String nombre, String apellidos, String ciudad) throws IOException {
		// Declaramos una string que sera el nombre que llevara el archivo y un contador
		// para los archivos ya creados.
		String nombre_archivo = "usuario_1.txt";
		int cont = 1;
		File file = null;
		// Bucle infinito que suma una unidad al contador cada vez que se encuentra un
		// archivo creado y lo anhade al nombre del archivo a crear
		while (true) {
			file = new File(nombre_archivo);
			// Una vez que el contador supera el numero de archivos creados, se crea un
			// nuevo archivo.
			if (!file.exists()) {
				file.createNewFile();
				break;
			} else {
				cont++;
				nombre_archivo = "usuario_" + String.valueOf(cont) + ".txt";
			}
		}
		// Una vez que salimos del bucle que escriben en el archivo recien creadolos
		// datos que llegan como agumentos.
		FileWriter fw = new FileWriter(file);
		fw.write(nombre + "\n");
		fw.write(apellidos + "\n");
		fw.write(ciudad + "\n");
		fw.flush();
		fw.close();
	}

	public static void mostrarFichero(int numero_archivo) throws FileNotFoundException, IOException {

		File file = new File("usuario_" + String.valueOf(numero_archivo) + ".txt");
		// Se comprueba si el numero de archivo existe en el sistema.
		if (!file.exists()) {
			System.out.println("El numero de archivo introducido no existe en el sistema.");
		} else {
			// Si existe se muestra por pantalla su contenido linea por linea
			FileReader fr = new FileReader(file);
			String linea;
			BufferedReader br = new BufferedReader(fr);
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			br.close();
		}
	}

	// Ejercicio 2
	public static void main2(String[] args) {
		// Declaramos el array de numeros y se lo pasamos como argumentos al metodo
		// llenarArray
		String select;
		String nombre_fichero = null;
		boolean exit = false;
		int[] numeros = new int[300];
		numeros = llenarArray(numeros);
		// Iniciamos un bucle para el menu principal y una etiqueta inicio para volver
		// en caso de excepcion
		inicio: while (exit != true) {
			System.out.println("Seleccione una opción: \n1/Volcado de array a fichero\n2/Mostrar fichero"
					+ "\n3/Salir del programa");
			select = scan.nextLine();
			switch (select) {
			case "1":
				// Si se selecciona volcar array se pasa como parametro el array al metodo
				// copiarArray()
				try {
					copiarArray(numeros);
				} catch (IOException e) {
					System.out.println("Error de E/S de datos " + e.getMessage());
					continue inicio;
				}
				break;
			case "2":
				System.out.println("Introduzca el nombre del fichero: ");
				nombre_fichero = scan.nextLine() + ".txt";
				try {
					mostrarFichero(nombre_fichero);
				} catch (FileNotFoundException e) {
					System.out.println("Archivo no encontrado " + e.getMessage());
					continue inicio;
				} catch (IOException e) {
					System.out.println("Error de E/S de datos " + e.getMessage());
					continue inicio;
				}
				break;
			case "3":
				exit = true;
				break;
			default:
				System.out.println("Seleccione una opcion valida");
			}
		}
	}

	public static int[] llenarArray(int[] numeros) {
		// Este metodo genera numeros aleatorios entre 0 y 999 hasta llenar el array,
		// despues lo devuelve
		int aux;
		Random rn = new Random();
		for (int i = 0; i < numeros.length; i++) {
			aux = rn.nextInt(1000);
			numeros[i] = aux;
		}
		return numeros;
	}

	public static void copiarArray(int[] numeros) throws IOException {

		String nombre_fichero;
		File file = null;

		// Iniciamos un bucle infinito para pedir el nombre del fichero
		while (true) {
			System.out.println("Introduzca el nombre del fichero: ");
			nombre_fichero = scan.nextLine() + ".txt";
			file = new File(nombre_fichero);
			// Si el nombre introducido existe en el sistema vuelve al inicio,
			// en caso contrario salimos del bucle
			if (file.exists()) {
				System.out.println("El archivo ya existe en el sistema.");
			} else {
				break;
			}
		}
		FileWriter fw = new FileWriter(file);
		// Declaramos un contador y un bucle for hasta la longitud del array y un
		// contador,
		int cont = 0;
		for (int i = 0; i < numeros.length; i++) {
			// En caso de que el contador llegue a 100 se salimos del bucle
			if (cont == 100)
				break;
			// Comprobamos con la variable del bucle si la posicion correspondiente en el
			// array
			// tiene un valor par y en caso afirmativo la guardamos en el archivo.
			if ((numeros[i] % 2) == 0) {
				fw.write(String.valueOf(numeros[i]) + "\n");
				fw.flush();
				cont++;
			}
		}
		fw.close();
	}

	public static void mostrarFichero(String nombre_fichero) throws IOException, FileNotFoundException {
		File file = new File(nombre_fichero);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linea = "";
		System.out.println("Archivo: " + nombre_fichero);

		// Iniciamos un bucle que se cierra cuando el BufferedReader termina de leer el
		// archivo
		while ((linea = br.readLine()) != null) {
			System.out.println(linea);
		}
		br.close();
	}

	// Ejercicio3
	public static void main3(String[] args) {
		File file = null;
		String nombre = "Ejercicio3.txt";
		String contenido = "Esto es una prueba";
		String contenido_final = "";
		int caracter;
		// System.out.println("Introduzca el nombre del archivo:");
		// nombre = scan.nextLine();
		// System.out.println("Introduzca el conenido del archivo:");
		// contenido = scan.nextLine();
		file = new File(nombre);

		try {
			FileWriter fw = new FileWriter(file);
			FileReader fr = new FileReader(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			fw.write(contenido);
			fw.close();
			fw.flush();
			// Iniciamos un bucle que se cierra cuando se termine de leer el archivo
			while ((caracter = fr.read()) != -1) {
				// Si el caracter leido no es un espacio se agrega a la cadena final.
				if ((char) caracter != ' ') {
					contenido_final += (char) caracter;
				}
			}
			System.out.println(contenido_final);
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado." + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S de datos." + e.getMessage());
		}
	}

	// Ejercicio4
	public static void main(String[] args) {
		int intentos = 6;
		String palabra = "";
		String[] lista_palabras = new String[] { "Casa", "Arbol", "Caracol", "Reloj", "Ordenador" };
		File file = new File("palabras.txt");
		
		try {
			// Llamamos al metodo crearFicheroPalabras pasando como argumento el array con palabras
			// para guardarlas en un archivo
			crearFicheroPalabras(file, lista_palabras);
			// Guardamos en la string palabra un valor aleatorio conseguido del archivo
			palabra = conseguirPalabra(file);
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado en el sistema." + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S de datos." + e.getMessage());
		}
		
		String letras_ocultas = "";
		char letra;
		boolean exit = false;
		boolean encontrada = false;
		int x = 0;
		
		// Creamos una cadena de _ con la misma longitud que la palabra 
		int longitud = palabra.length();
		for (int i = 1; i <= longitud; i++) {
			letras_ocultas += '_';
		}
		StringBuilder str = new StringBuilder(letras_ocultas);
		// Bucle infinito para empezar a jugar
		while (true) {
			//Interfaz principal
			System.out.println("Adivina la palabra(" + intentos + " intentos):\n" + str);
			System.out.println("Introduzca una letra: ");
			letra = scan.nextLine().charAt(0);

			exit = false;
			encontrada = false;
			x = -1;
			// Bucle para analizar si la letra se encuentra en la palabra
			while (exit != true) {
				// Iniciamos un contador para saber la posicion en la que nos encontramos
				x++;
				// Si el contador supera la longitud de la palabra salimos del bucle
				if (x == longitud)break;
				// Si la letra coincide con en la posicion analizada se sustituye la _ 
				// por la letra
				if(x == 0) letra = Character.toUpperCase(letra);
				if(x != 0) letra = Character.toLowerCase(letra);
				if (palabra.charAt(x) == letra) {
					str.setCharAt(x, letra);
					encontrada = true;
				}
				if(x == 0) letra = Character.toLowerCase(letra);
				if(x != 0) letra = Character.toLowerCase(letra);
			}
			if (encontrada == true) {
				System.out.println("Letra encontrada!");
			} else {
				System.out.println("Letra no encontrada -1 intento :(");
				intentos--;
			}
			
			//Condicionales para saber si el juego ha terminado
			if (intentos == 0) {
				System.out.println("Te quedan 0 intentos. Has perdido");
				break;
			}
			if (!str.toString().contains("_")) {
				System.out.println("Has ganado el juego!\n" + palabra);
				break;
			}
		}

	}

	public static void crearFicheroPalabras(File file, String[] lista_palabras) throws IOException {
		FileWriter fw = new FileWriter(file);
		for (int i = 0; i < lista_palabras.length; i++) {
			fw.write(lista_palabras[i] + "\n");
			fw.flush();
		}
		fw.close();
	}

	public static String conseguirPalabra(File file) throws IOException, FileNotFoundException {
		String palabra = "";
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		int cont = 0;
		String linea;
		
		// Contamos cuantas lineas tiene el archivo
		while ((linea = br.readLine()) != null) {
			cont++;
		}
		// Reiniciamos el buffer
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		
		// Declaramos un numero aleatorio entre 1 y las lineas del archivo
		Random rn = new Random();
		int aux = rn.nextInt(1, cont);
		
		// Recorremos el archivo hasta la linea aleatoria y guardamos el valor
		for (int i = 0; i < cont; i++) {
			if (i == aux) {
				palabra = br.readLine();
			}
			br.readLine();
		}
		fr.close();
		br.close();
		return palabra;
	}
}
