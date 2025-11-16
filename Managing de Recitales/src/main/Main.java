package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import artista.Artista;
import cancion.Cancion;
import grafico.Graficos;
import recitales.Recitales;
import roles.Roles;

public class Main {
	public static void main(String[] args) {
		// Inicializar
		Scanner entrada = new Scanner(System.in);
		Recitales recital = new Recitales();
		int opcionElegida;
		Graficos.mostrarBienvenida();
		esperar();
		// Verificacion todo OK
		if (!inicializarRecital(recital)) {
			Graficos.mostrarSalida();
			entrada.close();
			return;
		}
		// Hacer Acciones
		do {
			ClearConsole();
			Graficos.mostrarElegirOpcion();
			opcionElegida = entrada.nextInt();
			Graficos.mostrarOpcionElegida(opcionElegida);
			hacerAccion(opcionElegida, recital, entrada);
		} while (opcionElegida != 9);
		/// Solo pasa si se toca 9
		entrada.close();
		esperar();
	}

	private static boolean inicializarRecital(Recitales recital) {
		HashSet<Artista> artistas = new HashSet<>();
		HashSet<Cancion> canciones = new HashSet<>();
		if (!InicializadorJSON.inicializarDatos(artistas, canciones)) {
			return false;
		}
		recital.darArtista(artistas);
		recital.darCancion(canciones);
		return true;
	}

	public static void ClearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}

	private static void esperar() {
		try {
			Thread.sleep(1000); // Pauses the current thread for 1 second (1000 milliseconds)
		} catch (InterruptedException e) {
			// Handle the interruption if another thread interrupts the sleeping thread
			Thread.currentThread().interrupt(); // Re-interrupt the current thread
		}
	}

	private static void hacerAccion(int opcionElegida, Recitales recital, Scanner entrada) {
		int opcionSelecionada;
		Roles rolElegido = null;
		switch (opcionElegida) {
		case 1:
			Graficos.mostrarContarPorCancion();
			break;
		case 2:
			Graficos.mostrarContarPorRecital();
			break;
		case 3:
			do {
				ClearConsole();
				Graficos.mostrarContratadoCancion();
				// ListarOpciones
				System.out.println(recital.listadoCancionSimpleConIndex());
				// Selecionar
				System.out.println("Elija una opcion");
				opcionSelecionada = entrada.nextInt();
			} while (opcionSelecionada < 1 || opcionSelecionada > recital.darCantidadCanciones());
			recital.optimizarCancion(opcionSelecionada - 1);
			break;
		case 4:
			Graficos.mostrarContratadoRecital();
			recital.optimizarRecital();
			break;
		case 5:
			Graficos.mostrarEntrenadoArtista();
			do {
				do {
					ClearConsole();
					Graficos.mostrarContratadoCancion();
					// ListarOpciones Costo CostoNuevo
					System.out.println(recital.listadoArtistasSimpleConIndex());
					// Selcionar
					System.out.println("Elija una opcion");
					opcionSelecionada = entrada.nextInt();
				} while (opcionSelecionada < 1 || opcionSelecionada > recital.darCantidadArtistas());
				Artista a = recital.obtenerArtista(opcionSelecionada - 1);
				List<Roles> rolesArtista = new ArrayList<>(Arrays.asList(Roles.values()));
				rolesArtista.retainAll(a.getRolesHistorico());
				// SelecionarRol
				if (rolesArtista.size() != Roles.values().length) {
					rolElegido = elegirRolNoTenido(rolesArtista, entrada);
				} else {
					System.out.println("Selecione a otro artista, este tiene todos los Roles");
					rolElegido = null;
				}
			} while (rolElegido == null);
			recital.entrenarArtista(opcionSelecionada-1, rolElegido);
			break;
		case 6:
			Graficos.mostrarArtistas();
			System.out.println(recital.listarArtistasContratados());
			break;
		case 7:
			Graficos.mostrarCanciones();
			System.out.println(recital.listarEstadoCanciones());
			break;
		case 8:
			System.out.println("Estoy Prolog");
			break;
		case 9:
			Graficos.mostrarSalida();
			break;
		default:
			Graficos.mostrarIntenteloDeNuevo();
			break;
		}
		ClearConsole();
	}

	private static Roles elegirRolNoTenido(List<Roles> rolesArtista, Scanner entrada) {
		int indexElegido;
		List<Roles> rolesTotales = new ArrayList<>(Arrays.asList(Roles.values()));
		rolesTotales.removeIf(r -> rolesArtista.contains(r));
		do {
			System.out.println("Elija un Rol");
			for (int i = 0; i < rolesTotales.size(); i++) {
				System.out.println("Rol Numero " + (i+1) + " " + rolesTotales.get(i));
			}
			indexElegido = entrada.nextInt();
		} while (indexElegido < 0 || indexElegido > rolesTotales.size());
		return rolesTotales.get(indexElegido - 1);
	}
}
