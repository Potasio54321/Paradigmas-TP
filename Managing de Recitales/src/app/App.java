package app;

import java.util.Scanner;

import grafico.Graficos;

public class App {
	// Atributos
	// Scanner
	// Recital
	public static void main(String[] args) {
		// Inicializar
		Scanner entrada = new Scanner(System.in);
		int opcionElegida;
		Graficos.mostrarBienvenida();
		esperar(entrada);
		// Hacer Acciones
		do {
			ClearConsole();
			opcionElegida = entrada.nextInt();
			System.out.println("Eligiste :" + opcionElegida);
			hacerAccion(opcionElegida);
		} while (opcionElegida != 9);
		/// Cierre
		entrada.close();
	}

	public static void ClearConsole() {
		// Esta horrible esto pero es lo unico que funciona
		System.out.println(
				"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	private static void esperar(Scanner entrada) {
		entrada.nextLine();
	}

	private static void hacerAccion(int opcionElegida) {
		switch (opcionElegida) {
		case 1:
			System.out.println("Estoy Contando Roles para 1 Cancion");
			break;
		case 2:
			System.out.println("Estoy Contando Roles para el Recital");
			break;
		case 3:
			System.out.println("Estoy Contratando Artistas para 1 cancion");
			break;
		case 4:
			System.out.println("Estoy Contratando Artistas para el Recital");
			break;
		case 5:
			System.out.println("Estoy Entrenando Artistas");
			break;
		case 6:
			System.out.println("Estoy Listando Artistas");
			System.out.println(recital.listarArtistasContratados());
			break;
		case 7:
			System.out.println("Estoy Listando Canciones");
			break;
		case 8:
			System.out.println("Estoy Prolog");
			break;
		case 9:
			System.out.println("Saliendo");
			break;
		default:
			System.out.println("Intente de nuevo");
			break;
		}
	}
}
