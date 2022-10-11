import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Tarea3_ficheros {

	public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public static File archivo_clientes = new File("archivo_clientes.txt");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean exit = false;
		boolean exit2 = false;
		FileWriter fw = null;

		//
		while (exit != true) {
			try {
				// Si no existe el archivo creamos uno y le añadimos clientes de ejemplo
				if (!archivo_clientes.exists()) {
					clientes_ejemplo();
					archivo_clientes.createNewFile();
					fw = new FileWriter(archivo_clientes);
					System.out.println("Lista de clientes guardados: ");
					for (int i = 0; i < clientes.size(); i++) {
						System.out.println(clientes.get(i).toString());
						fw.write(clientes.get(i).toString() + "\n");
						fw.flush();
					}
					fw.close();
					// Si existe leemos el archivo y añadimos los clientes al ArrayList
				} else {
					leer_archivo(archivo_clientes);
					fw = new FileWriter(archivo_clientes);
					System.out.println("Lista de clientes guardados: ");
					for (int i = 0; i < clientes.size(); i++) {
						System.out.println(clientes.get(i).toString());
						fw.write(clientes.get(i).toString() + "\n");
						fw.flush();
					}
				}
			} catch (IOException e) {
				System.out.println("Error de E/S de datos.");
			}
			//Preguntamos si se desea añadir mas clientes o se cierra el programa
			while (exit2 != true) {
				String select;
				System.out.println("Desea añadir más clientes? S/N");
				select = scan.next();
				scan.nextLine();
				select = select.toUpperCase();
				System.out.println(select);
				switch(select) {
					case"S":
						try {
							nuevo_cliente(archivo_clientes);
						}catch(IOException e) {
							System.out.println("Error de E/S de datos.");
						}
							break;
					case"N":
						exit2 = true;
						exit = true;
						System.out.println("Cerrando el programa");
						break;
					default:
						System.out.println("Seleccione una opción correcta.");
				}
			}
		}
		scan.close();
	}

	public static void leer_archivo(File archivo_clientes) throws IOException {
		String nombre = "";
		String apellidos = "";
		int telefono;
		String telefono_String = "";
		String direccion = "";
		String linea;
		FileReader fr = new FileReader(archivo_clientes);
		BufferedReader br = new BufferedReader(fr);
		int i;
		while ((linea = br.readLine()) != null) {
			nombre = "";
			apellidos = "";
			telefono_String = "";
			direccion = "";
			// Obtenemos el nombre de la linea
			i = linea.lastIndexOf("Nombre=");
			i = i + 7;
			while (true) {
				if (linea.charAt(i) == ',') {
					break;
				} else {
					nombre += linea.charAt(i);
					i++;
				}
			}
			// Obtenemos los apellidos de la linea
			i = linea.lastIndexOf("Apellidos=");
			i = i + 10;
			while (true) {
				if (linea.charAt(i) == ',') {
					break;
				} else {
					apellidos += linea.charAt(i);
					i++;
				}
			}
			// Obtenemos el numero de telefono
			i = linea.lastIndexOf("Telefono=");
			i = i + 9;
			while(true) {
				if(linea.charAt(i) == ',') {
					break;
				} else {
					telefono_String += linea.charAt(i);
					i++;
				}
			}
			//Obtenemos la direccion
			i = linea.lastIndexOf("Direccion=");
			i = i + 10;
			while(true) {
				if(linea.charAt(i) == ']') {
					break;
				} else {
					direccion += linea.charAt(i);
					i++;
				}
			}
			//Transformamos el telfono a int, creamos objeto y añadimos al ArrayList
			telefono = Integer.valueOf(telefono_String);
			Cliente cliente = new Cliente(nombre, apellidos, telefono, direccion);
			clientes.add(cliente);
		}
		br.close();
	}

	public static void nuevo_cliente(File archivo_clientes) throws IOException {
		String nombre = "";
		String apellidos = "";
		int telefono;
		String direccion = "";
		FileWriter fw = new FileWriter(archivo_clientes, true);
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el nombre del cliente: ");
		nombre = scan.nextLine();
		
		System.out.println("Introduzca los apellidos del cliente: ");
		apellidos = scan.nextLine();
		
		System.out.println("Introduzca el telefono del cliente: ");
		telefono = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Introduzca la direccion del cliente: ");
		direccion = scan.nextLine();
		//scan.close();
		
		Cliente cliente = new Cliente(nombre, apellidos, telefono, direccion);
		clientes.add(cliente);
		fw.write(cliente.toString());
		fw.flush();
		fw.close();
		System.out.println("Cliente añadido correctamente."
				+ "\nLista de clientes:");
		for(int i = 0; i < clientes.size(); i++) {
			clientes.get(i).toString();
		}
	}
	

	public static void clientes_ejemplo() {
		Cliente cliente_1 = new Cliente("Javier", "Loureiro", 634616442, "Vigo Calle Zaragoza n8 1ºA");
		clientes.add(cliente_1);
		Cliente cliente_2 = new Cliente("Zacarías", "Leche del Monte", 607335304, "Madrid Avenida de Gran Vía 150 8ºC");
		clientes.add(cliente_2);
		Cliente cliente_3 = new Cliente("Elsa", "Porrico Grande", 745003268, "A Coruña Calle Ramon i Cajal 80 4ºK");
		clientes.add(cliente_3);
		Cliente cliente_4 = new Cliente("Armando", "Paredes del Gadas", 621458056, "Barcelona Plaza de España 8 12ºE");
		clientes.add(cliente_4);
		Cliente cliente_5 = new Cliente("Susana", "Horia", 774560023, "Salamanca Calle del Agua 10 5ºB");
		clientes.add(cliente_5);
	}
}