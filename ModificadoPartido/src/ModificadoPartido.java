import java.util.Scanner;
import java.lang.Math;
public class ModificadoPartido {
	public static void main (String [] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int partes;
		int descansos;
		int duracion_partes;
		int duracion_descansos;
		int duracion_total;
		int segundo;
		boolean exit = false;
		
		System.out.println("Introduzca el numero de partes del partido: ");
		partes = Integer.parseInt(scan.nextLine());
		descansos = partes - 1;
		
		System.out.println("Introduzca la duracion de cada parte(minutos): ");
		duracion_partes = Integer.parseInt(scan.nextLine());
		duracion_partes = duracion_partes * 60;
		
		System.out.println("Introduzca la duración de los descansos(minutos): ");
		duracion_descansos = Integer.parseInt(scan.nextLine());
		duracion_descansos = duracion_descansos * 60;
		
		duracion_total = (duracion_partes * partes) + (duracion_descansos * descansos);
		
		System.out.println("Introduzca el valor del segundo:");
		segundo = Integer.parseInt(scan.nextLine());
		
		int aux = 0; //Variante auxiliar para saber el segundo en el que se encuentrada cada una de las partes o descanso
		int aux_parte = 1; //Contador de partes
		int aux_descanso = 0; //Contador de descansos
		
		if(segundo >= duracion_total) {
			System.out.println("El partido ya ha terminado.");
		} else if (segundo < duracion_total) {
			for(int i = 0; i <= segundo; i++) {
				if((aux_parte + aux_descanso) % 2 != 0) {  //Condicion para saber si se encuentra en descanso o en transcurso
					if(i == segundo) { //Si i = segundo se termina
						System.out.println("Situacion del partido: " + aux_parte + "ª parte minuto " + Math.round(i / 60));
					} else if(aux == duracion_partes) { // Si el segundo de la parte es igual a la duracion de las partes
						aux = 0;						// primero reseteo el contador de segundos
						aux_descanso++;					// y se suma un descanso para que en la primera condicion del bucle vaya abajo
					} else {
						aux++;
					}	
				} else {								//Funcionamiento igual al analizador de partes
					if(i == segundo) {
						System.out.println("Situacion del partido: " + aux_descanso + "º descanso minuto " + Math.round(i / 60));
					} else if( aux == duracion_descansos) {
						aux = 0;
						aux_parte++;
					} else {
						aux++;
					}
				}
			}
		}
	}
}
