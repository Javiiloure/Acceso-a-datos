import java.util.Scanner;
import java.lang.Math;
import java.lang.NumberFormatException;

public class EjercicioPartido {
	public static void main(String[] args) {
		boolean exit = false;
		int segundo = 0;
		int minuto = 0;
		Scanner scan = new Scanner(System.in);

		inicio: while (exit != true) {
			System.out.println("Introduzca el valor del segundo:");
			try {
				segundo = Integer.parseInt(scan.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("El valor introducido no es un numero");
				continue inicio;
			}
			if (segundo >= 7200) {
				System.out.println("El partido ya ha terminado");
			} else if (segundo < 7200 && segundo >= 4500) {
				minuto = Math.round(segundo / 60) + 1;
				System.out.println("El partido esta en el minuto " + minuto);
				System.out.println("Se encuentra en la segunda parte");

			} else if (segundo < 4500 && segundo >= 2700) {
				minuto = Math.round(segundo / 60) + 1;
				System.out.println("El partido esta en el minuto " + minuto);
				System.out.println("Se encuentra en el descanso");

			} else if (segundo < 2700 && segundo > 0) {
				minuto = Math.round(segundo / 60) + 1;
				System.out.println("El partido esta en el minuto " + minuto);
				System.out.println("Se encuentra en la primera parte");
			}

			else {
				System.out.println("Segundo introducido no valido");
			}
			char select;
			while (exit != true) {
				System.out.println("Desea introducir otro segundo?(S/N)");
				select = scan.next().charAt(0);
				scan.nextLine();
				if (select == 's' || select == 'S') {
					continue inicio;
				} else if (select == 'N' || select == 'n') {
					System.out.println("Cerrando el programa");
					exit = false;
					break;
				} else System.out.println("Seleccione una opción válida");
			}
		}
		scan.close();
	}
}
