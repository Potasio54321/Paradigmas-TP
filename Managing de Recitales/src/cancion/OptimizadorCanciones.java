package cancion;

import artista.Artista;
import roles.Roles;
import utiles.ListaLimitada;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class OptimizadorCanciones {
	
	public static void optimizarCancion(LinkedList<Artista> artistas, Cancion cancion) {
		// TO DO : ordenarlos asi se recorre mas facil
		Set<Roles> rolesSinLlenar = new HashSet<Roles>(cancion.rolesNecesarios.keySet());
		Set<Roles> rolesLlenados = new HashSet<Roles>();
		LinkedList<Artista> artistasUsables = filtrarArtistas(artistas, cancion.rolesNecesarios.keySet());
		if (artistasUsables.isEmpty())
			return;
		while (!rolesSinLlenar.isEmpty()) {
			// Va a selecionar el rol que necesita menos artistas en el momento
			Roles rolElegido = elegirRolMinimo(rolesSinLlenar, artistasUsables);
			// Va a elegir el artista mas barato en el momento
			Artista mejorArtistaMomento = elegirMejorArtistaDelMomento(artistasUsables, rolElegido);
			// Va a aplicar el descuento Correspondiente
			aplicarDescuentos(artistasUsables, mejorArtistaMomento);
			intercambiarArtista(artistasUsables, mejorArtistaMomento, cancion.rolesNecesarios.get(rolElegido));
			if (cancion.rolesNecesarios.get(rolElegido).estaLleno())
				intercambiarRoles(rolesSinLlenar, rolElegido, rolesLlenados);
			cancion.costo+=mejorArtistaMomento.darCosto();
			mejorArtistaMomento.darCancion();
		}

	}

	private static LinkedList<Artista> filtrarArtistas(LinkedList<Artista> artistas, Set<Roles> rolesNecesarios) {
		LinkedList<Artista> artistasUsables = new LinkedList<Artista>(artistas);
		artistasUsables.removeIf(a -> !a.tieneRoles(rolesNecesarios));
		return artistasUsables;
	}

	private static Roles elegirRolMinimo(Set<Roles> rolesSinLlenar, LinkedList<Artista> artistasUsables) {
		int minOpciones = Integer.MAX_VALUE;
		Roles rolMasDificil = null;
		// Esta viendo los roles que tiene menos artistas
		for (Roles rolActual : rolesSinLlenar) {

			int opcionesActuales = contarArtistasQueTocanRol(artistasUsables, rolActual);
			if (opcionesActuales < minOpciones) {
				minOpciones = opcionesActuales;
				rolMasDificil = rolActual;
			}
		}
		return rolMasDificil;
	}

	private static int contarArtistasQueTocanRol(LinkedList<Artista> artistas, Roles rol) {
		LinkedList<Artista> artistasParaRolActual = artistasConRol(artistas, rol);
		return artistasParaRolActual.size();
	}

	private static LinkedList<Artista> artistasConRol(LinkedList<Artista> artistasUsables, Roles rolAElegir) {
		LinkedList<Artista> artistasParaRolActual = new LinkedList<Artista>(artistasUsables);
		artistasParaRolActual.removeIf(a -> !a.tieneRol(rolAElegir) && !a.puedeTocar());
		return artistasParaRolActual;
	}

	private static Artista elegirMejorArtistaDelMomento(LinkedList<Artista> artistasUsables, Roles rolAElegir) {
		double mejorCosto = Double.MAX_VALUE;
		Artista mejorArtista = null;
		LinkedList<Artista> artistasUsablesConRol = artistasConRol(artistasUsables, rolAElegir);
		for (Artista artista : artistasUsablesConRol) {
			double costo = artista.darCosto();

			if (costo < mejorCosto) {
				mejorCosto = costo;
				mejorArtista = artista;
			}
		}
		return mejorArtista;
	}

	private static void aplicarDescuentos(LinkedList<Artista> artistasUsables, Artista mejorArtistaMomento) {
		LinkedList<Artista> artistasUsablesCompañeros = artistasParticiparonConArtista(artistasUsables,
				mejorArtistaMomento);
		for (Artista artista : artistasUsablesCompañeros) {
			artista.aplicarDescuento();
		}
	}

	private static LinkedList<Artista> artistasParticiparonConArtista(LinkedList<Artista> artistasUsables, Artista artista) {
		LinkedList<Artista> artistasParaRolActual = new LinkedList<Artista>(artistasUsables);
		artistasParaRolActual.removeIf(a -> !a.participoConArtista(artista) && !a.puedeTocar());
		return artistasParaRolActual;
	}

	private static void intercambiarArtista(LinkedList<Artista> artistasUsables, Artista mejorArtistaMomento,
			ListaLimitada<Artista> listaLimitada) {
		artistasUsables.remove(mejorArtistaMomento);
		listaLimitada.insertar(mejorArtistaMomento);
	}

	private static void intercambiarRoles(Set<Roles> rolesSinLlenar, Roles rolElegido, Set<Roles> rolesLlenados) {
		rolesSinLlenar.remove(rolElegido);
		rolesLlenados.add(rolElegido);
	}
}
