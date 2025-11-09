package recitales;
/*conjunto de canciones que se quieren interpretar.*/
import java.util.LinkedList;

import artista.Artista;
import cancion.Cancion;

public class Recitales {
	private LinkedList<Artista> artistas;
	private LinkedList<Cancion> canciones;

	public Recitales() {
		this.canciones = new LinkedList<Cancion>();
		this.artistas = new LinkedList<Artista>();
	}
	public void optimizarCancion(int index) {
		Cancion cancionAOptimizar=canciones.get(index);		
		cancionAOptimizar.optimizarCancion(artistas);
	}

	public void optimizarRecital() {
		for(Cancion cancion:canciones) {
			cancion.optimizarCancion(artistas);
		}
	}
	public String listarEstadoCanciones() {
		StringBuilder resultado=new StringBuilder();
		resultado.append("Nombre\tCosto\tCantidad Necesaria Roles\t");
		for(Cancion cancion:canciones) {
			resultado.append(cancion+(cancion.cantidadRolesNecesarios()==0?"\tTerminada":"\tSinTerminar"));
		}
		return resultado.toString();
	}

	public void darArtista(Artista artista) {
		artistas.add(artista);
	}

	public void darCancion(Cancion cancion) {
		canciones.add(cancion);
	}
}
