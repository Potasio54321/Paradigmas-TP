package cancion;
import java.util.HashMap;

/*
 * pieza musical a interpretar en el recital. Cada canción requiere uno o más roles 
 * (por ejemplo: voz principal, guitarra eléctrica, bajo, batería, coros, teclados, etc.).
 * */
import roles.Roles;
import artista.ArtistaBase;

public class Cancion {
	private String nombre;
	private double duracionSeg;
	private HashMap<Roles, Podio<ArtistaBase>> RolesNecesarios;
	public Cancion(String nombre,double duracionSeg) {
		this.nombre=nombre;
		this.duracionSeg=duracionSeg;
		this.RolesNecesarios=new HashMap<Roles, Podio<ArtistaBase>>();
	}
	public String getNombre() {
		return nombre;
	}
	public double getDuracionSeg() {
		return duracionSeg;
	}
}
