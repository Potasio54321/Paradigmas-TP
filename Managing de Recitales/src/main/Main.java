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
	public static void main(String[] args) throws Exception {
		// Inicializar
		try {
			Scanner entrada = new Scanner(System.in);
			Recitales recital = new Recitales();
			int opcionElegida;
			Graficos.mostrarBienvenida();
			esperar();
			// Verificacion todo OK
			if (!inicializarRecital(recital, args)) {
				Graficos.mostrarSalida();
				entrada.close();
				return;
			}
			// Hacer Acciones
			do {
				clearConsole();
				Graficos.mostrarElegirOpcion();
				opcionElegida = leerInt(entrada);
				Graficos.mostrarOpcionElegida(opcionElegida);
				hacerAccion(opcionElegida, recital, entrada);
			} while (opcionElegida != 9);
			/// Solo pasa si se toca 9
			entrada.close();
			esperar();
		} catch (Exception e) {
			throw e;
		}
	}

	private static int leerInt(Scanner entrada) {
		System.out.print("Ingrese un número: ");

		while (!entrada.hasNextInt()) {
			System.out.println("Entrada inválida. Debe ingresar un número.");
			entrada.next(); // descarta lo incorrecto
			System.out.print("Ingrese un número: ");
		}

		return entrada.nextInt();
	}

	private static boolean inicializarRecital(Recitales recital, String[] args) throws Exception {
		HashSet<Artista> artistas = new HashSet<>();
		HashSet<Cancion> canciones = new HashSet<>();

		if (!InicializadorJSON.inicializarDatos(artistas, canciones, args[0], args[1])) {
			return false;
		}
		recital.darArtista(artistas);
		recital.darCancion(canciones);
		return true;
	}

	public static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			
		}
	}

	private static void esperar() {
		try {
			Thread.sleep(1000); //Pausa por 1 segundo
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private static void hacerAccion(int opcionElegida, Recitales recital, Scanner entrada) {
		int opcionSelecionada;
		int prologEstado;
		Roles rolElegido = null;
		switch (opcionElegida) {
		case 1:
			opcionSelecionada = elegirIndexCancionSimpleContar(recital, entrada);
			System.out.println(recital.listarRolesFaltantesCantidadParaCancion(opcionSelecionada - 1));
			break;
		case 2:
			Graficos.mostrarContarPorRecital();
			System.out.println(recital.listarRolesFaltantesCantidadParaRecital());
			break;
		case 3:
			opcionSelecionada = elegirIndexCancionSimpleOptimizar(recital, entrada);
			recital.optimizarCancion(opcionSelecionada - 1);
			break;
		case 4:
			Graficos.mostrarContratadoRecital();
			recital.optimizarRecital();
			break;
		case 5:
			do {
				opcionSelecionada = ElegirIndexArtistaSelecionar(recital, entrada);
				rolElegido = elegirRolArtistaNoTiene(opcionSelecionada, recital, entrada);
			} while (rolElegido == null);
			recital.entrenarArtista(opcionSelecionada - 1, rolElegido);
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
			Graficos.mostrarProlog();
			prologEstado=Prologando.Prolog(recital.getArtistas(), recital.getCanciones());
			if(prologEstado!=Prologando.FALLIDO) {
				System.out.println("Se necesitan entrenar "+prologEstado +" Artistas");
			}
			break;
		case 9:
			Graficos.mostrarSalida();
			break;
		default:
			Graficos.mostrarIntenteloDeNuevo();
			break;
		}
		clearConsole();
	}

	private static int elegirIndexCancionSimpleOptimizar(Recitales recital, Scanner entrada) {
		int opcionSelecionada;
		do {
			clearConsole();
			Graficos.mostrarContarPorCancion();
			// ListarOpciones
			System.out.println(recital.listadoCancionSimpleConIndex());
			// Selecionar
			System.out.println("Elija una opcion");
			opcionSelecionada = leerInt(entrada);
		} while (opcionSelecionada < 1 || opcionSelecionada > recital.darCantidadCanciones());
		return opcionSelecionada;
	}

	private static int elegirIndexCancionSimpleContar(Recitales recital, Scanner entrada) {
		int opcionSelecionada;
		do {
			clearConsole();
			Graficos.mostrarContarPorCancion();
			// ListarOpciones
			System.out.println(recital.listadoCancionSimpleConIndex());
			// Selecionar
			System.out.println("Elija una opcion");
			opcionSelecionada = leerInt(entrada);
		} while (opcionSelecionada < 1 || opcionSelecionada > recital.darCantidadCanciones());
		return opcionSelecionada;
	}

	private static Roles elegirRolArtistaNoTiene(int opcionSelecionada, Recitales recital, Scanner entrada) {
		Artista a = recital.obtenerArtista(opcionSelecionada - 1);
		List<Roles> rolesArtista = new ArrayList<>(Arrays.asList(Roles.values()));
		rolesArtista.retainAll(a.getRolesHistorico());
		// SelecionarRol
		if (rolesArtista.size() != Roles.values().length)
			return elegirRolNoTenido(rolesArtista, entrada);
		System.out.println("Selecione a otro artista, este tiene todos los Roles");
		return null;
	}

	private static int ElegirIndexArtistaSelecionar(Recitales recital, Scanner entrada) {
		int opcionSelecionada;
		do {
			clearConsole();
			Graficos.mostrarEntrenadoArtista();
			// ListarOpciones Costo CostoNuevo
			System.out.println(recital.listadoArtistasSimpleConIndex());
			// Selecionar
			System.out.println("Elija una opcion");
			opcionSelecionada = leerInt(entrada);
		} while (opcionSelecionada < 1 || opcionSelecionada > recital.darCantidadArtistas());
		return opcionSelecionada;
	}

	private static Roles elegirRolNoTenido(List<Roles> rolesArtista, Scanner entrada) {
		int indexElegido;
		List<Roles> rolesTotales = new ArrayList<>(Arrays.asList(Roles.values()));
		rolesTotales.removeIf(r -> rolesArtista.contains(r));
		do {
			System.out.println("Elija un Rol");
			for (int i = 0; i < rolesTotales.size(); i++) {
				System.out.println("Rol Numero " + (i + 1) + " " + rolesTotales.get(i));
			}
			indexElegido = leerInt(entrada);
		} while (indexElegido < 0 || indexElegido > rolesTotales.size());
		return rolesTotales.get(indexElegido - 1);
	}
}
