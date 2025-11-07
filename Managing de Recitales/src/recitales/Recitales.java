package recitales;
/*conjunto de canciones que se quieren interpretar.*/

import java.util.LinkedList;

import artista.Artista;
import cancion.Cancion;

public class Recitales {
	private LinkedList<Artista> artistas;
	private LinkedList<Cancion> canciones;
	public Recitales() {
		this.artistas=new LinkedList<Artista>();
		this.canciones=new LinkedList<Cancion>();
	}
	public void darArtista(Artista artista) {
		artistas.add(artista);
	}
	public void darCancion(Cancion cancion) {
		canciones.add(cancion);
	}
}
