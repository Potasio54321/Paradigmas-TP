package cancion;


/*
 * pieza musical a interpretar en el recital. Cada canción requiere uno o más roles 
 * (por ejemplo: voz principal, guitarra eléctrica, bajo, batería, coros, teclados, etc.).*/
import roles.Roles;
import utiles.ListaLimitada;
import artista.Artista;
import java.util.HashMap;
import java.util.LinkedList;

public class Cancion {
	private String nombre;
	private boolean fueOptimizada;
	protected double costo;
	protected HashMap<Roles, ListaLimitada<Artista>> rolesNecesarios;
	
	public Cancion(String nombre) {
		this.nombre = nombre;
		this.costo=0;
		fueOptimizada=false;
		this.rolesNecesarios = new HashMap<Roles, ListaLimitada<Artista>>();
	}
	@Override
		public String toString() {
			return this.nombre+" "+costo+" "+cantidadRolesNecesarios();
		}
	public void setRolesNecesarios(Roles rol, int cant) {
		ListaLimitada<Artista> nuevoPodio = new ListaLimitada<Artista>(cant);
		rolesNecesarios.put(rol,nuevoPodio);
	}
	public void setRolesNecesarios(HashMap<Roles, Integer> roles) {
		for(Roles r:roles.keySet()) {
			ListaLimitada<Artista> nuevaLista = new ListaLimitada<Artista>(roles.get(r));
			rolesNecesarios.put(r,nuevaLista);
		}
		
	}
	public void optimizarCancion(LinkedList<Artista> artistas) {
		if(!fueOptimizada)
			OptimizadorCanciones.optimizarCancion(artistas,this);
	}
	public int cantidadRolesNecesarios() {
		int acum = 0;
		for (ListaLimitada<Artista> podio : rolesNecesarios.values()) {
			acum += podio.getCantidadMaxima();
		}
		return acum;
	}
	public double darCostoTotal() {
		return costo;
	}
	public String getNombre() {
		return nombre;
	}
	public boolean estaTerminada() {
		for (ListaLimitada<Artista> podio : rolesNecesarios.values()) {
			if(!podio.estaLleno())
				return false;
		}
		return true;
	}

}
