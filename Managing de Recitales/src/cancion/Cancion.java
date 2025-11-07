package cancion;


/*
 * pieza musical a interpretar en el recital. Cada canción requiere uno o más roles 
 * (por ejemplo: voz principal, guitarra eléctrica, bajo, batería, coros, teclados, etc.).*/
import roles.Roles;
import utiles.ListaLimitada;
import artista.Artista;
import java.util.HashMap;

public class Cancion {
	private String nombre;
	protected double costo;
	protected HashMap<Roles, ListaLimitada<Artista>> rolesNecesarios;

	public Cancion(String nombre) {
		this.nombre = nombre;
		this.costo=0;
		this.rolesNecesarios = new HashMap<Roles, ListaLimitada<Artista>>();
	}

	public void setRolesNecesarios(Roles rol, int cant) {
		ListaLimitada<Artista> nuevoPodio = new ListaLimitada<Artista>(cant);
		rolesNecesarios.put(rol,nuevoPodio);
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

}
