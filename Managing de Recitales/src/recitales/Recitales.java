package recitales;
/*conjunto de canciones que se quieren interpretar.*/

import java.util.LinkedList;

import artista.ArtistaBase;
import cancion.Cancion;

public class Recitales {
	private LinkedList<ArtistaBase> artistas;
	private LinkedList<Cancion> canciones;
	public Recitales() {
		this.artistas=new LinkedList<ArtistaBase>();
		this.canciones=new LinkedList<Cancion>();
	}
	public void darArtista(ArtistaBase artista) {
		artistas.add(artista);
	}
	public void darCancion(Cancion cancion) {
		canciones.add(cancion);
	}
}
