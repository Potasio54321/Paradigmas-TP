package recitales;
import java.util.HashSet;
/*conjunto de canciones que se quieren interpretar.*/
import java.util.LinkedList;

import artista.Artista;
import cancion.Cancion;
import roles.Roles;

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
		for(Artista artista:artistas) {
			artista.quitarDescuento();
		}
	}

	public void optimizarRecital() {
		for(Cancion cancion:canciones) {
			cancion.optimizarCancion(artistas);
			for(Artista artista:artistas) {
				artista.quitarDescuento();
			}
		}
	}
	public String listarEstadoCanciones() {
		StringBuilder resultado=new StringBuilder();
		String lineaInicial=String.format("%-45s Costo Cantidad Necesaria Roles Estado\n", "Nombre");
		resultado.append(lineaInicial);
		
		for(Cancion cancion:canciones) {
			String lineaActual=String.format("%-45s %5.2f %-24d %s\n",cancion.getNombre(),cancion.darCostoTotal(),cancion.cantidadRolesNecesarios(),(cancion.estaTerminada()?"Terminada":"SinTerminar"));
			resultado.append(lineaActual);
		}
		return resultado.toString();
	}

	public void darArtista(HashSet<Artista> artistas2) {
		artistas.addAll(artistas2);
	}

	public void darCancion(HashSet<Cancion> canciones2) {
		canciones.addAll(canciones2);
	}
	public String listarArtistasContratados() {
	    StringBuilder sb = new StringBuilder();
	    for (Artista artista : artistas) {
	    	sb.append(artista+"\n");
	    }
	    return sb.toString();
	}
	public String listadoCancionSimpleConIndex() {
		StringBuilder resultado=new StringBuilder();
		int i=1;
		String lineaInicial=String.format("Numero Cancion Nombre Cancion\n");
		resultado.append(lineaInicial);
		
		for(Cancion cancion:canciones) {
			String lineaActual=String.format("%-14d %s\n",i,cancion.getNombre());
			resultado.append(lineaActual);
			i+=1;
		}
		return resultado.toString();
	}
	public int darCantidadArtistas() {
		return artistas.size();
	}
	public int darCantidadCanciones() {
		return canciones.size();
	}
	public Artista obtenerArtista(int index) {
		return artistas.get(index);
	}
	public String listadoArtistasSimpleConIndex() {
		StringBuilder resultado=new StringBuilder();
		int i=1;
		String lineaInicial=String.format("Numero Artista %-30s %-10s Costo Nuevo\n","Nombre Artista","Costo");
		resultado.append(lineaInicial);
		
		for(Artista artista:artistas) {
			String lineaActual=String.format("%-14d %-30s %-10.2f %.2f\n",i,artista.getNombreArtista(),artista.darCosto(),artista.darCosto()*1.5);
			resultado.append(lineaActual);
			i+=1;
		}
		return resultado.toString();
	}
	public boolean entrenarArtista(int opcionSelecionada, Roles rolElegido) {
		return artistas.get(opcionSelecionada).entrenar(rolElegido);
	}
	public String listarRolesFaltantesCantidadParaCancion(int opcionSelecionada) {
		Cancion cancion=canciones.get(opcionSelecionada);	
		return cancion.darRolesFaltantes();
	}
	public String listarRolesFaltantesCantidadParaRecital() {
		StringBuilder resultado=new StringBuilder();
		for(Cancion cancion:canciones) {
			resultado.append(cancion.darRolesFaltantes());
		}
		return resultado.toString();
	}
	public LinkedList<Artista> getArtistas() {
		return new LinkedList<Artista>(this.artistas);
	}
	public LinkedList<Cancion> getCanciones() {
		return new LinkedList<Cancion>(this.canciones);
	}
}
