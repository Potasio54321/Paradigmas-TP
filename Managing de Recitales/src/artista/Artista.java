package artista;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import roles.Roles;
import utiles.Contador;
import utiles.Costo;
/*
 * músico o técnico que puede desempeñar un rol por canción, de varios roles. 
 * Cada artista tiene: nombre, lista de roles que ha ocupado históricamente, 
 * histórico de bandas/colaboraciones (lista de nombres de bandas con las que tocó),
 * un costo por contratación por canción, y la cantidad máxima de canciones dispuesto
 * a tocar en un mismo recital (si es artista contratado). Un artista solo puede ser 
 * asignado a roles que haya desempeñado históricamente.
 */
public class Artista {
	private String nombreArtista;
	private HashSet<String> bandasHistorico;
	private HashSet<Roles> rolesHistorico;
	private Costo costo;//Esto maneja los costos.
	private Contador cantidadCanciones;//Esto maneja la cantidad de canciones
	public Artista(String nombreArtista,int cantMaximaCanciones,float costo) {
		this.nombreArtista = nombreArtista;
		this.bandasHistorico = new HashSet<String>();
		this.rolesHistorico = new HashSet<Roles>();
		this.cantidadCanciones=new Contador(cantMaximaCanciones);
		this.costo=new Costo(costo);
	}

	@Override
	public String toString() {
		StringBuilder representacion = new StringBuilder(this.nombreArtista);

		representacion.append("\nRoles Historicos:");
		if (!rolesHistorico.isEmpty()) {
			for (Roles rol : rolesHistorico)
				representacion.append("\n" + rol);
		} else
			representacion.append("\nNo tiene roles");

		representacion.append("\nBandas Historico:");
		if (!bandasHistorico.isEmpty()) {
			for (String banda : bandasHistorico)
				representacion.append("\n" + banda);
		} else
			representacion.append("\nNo participo con ninguna banda");

		return representacion.toString();
	}
	///Relacionado con la bandas
	public void setBandasHistorico(String... bandas) {
		for (String banda : bandas)
			this.bandasHistorico.add(banda);
	}

	public boolean participoConArtista(Artista otro) {
		boolean noHayElementosEnComun=Collections.disjoint(bandasHistorico, otro.bandasHistorico);
		return !noHayElementosEnComun;
	}
	///Relacionadas la Cantidad de canciones
	public void darCancion() {
		cantidadCanciones.contar();
	}
	public boolean puedeTocar() {
		return !cantidadCanciones.estaMaximo();
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombreArtista);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(nombreArtista, other.nombreArtista);
	}

	///Relacionadas Al Rol
	public void setRolesHistorico(Roles... roles) {
		for (Roles rol : roles)
			this.rolesHistorico.add(rol);
	}
	public boolean tieneRol(Roles rol) {
		return rolesHistorico.contains(rol);
	}
	public boolean tieneRoles(Set<Roles> roles) {
		boolean noHayElementosEnComun=Collections.disjoint(rolesHistorico, roles);
		return !noHayElementosEnComun;
	}
	///Relacionadas Al Coste
	public double darCosto() {
		return this.costo.getCosto();
	}
	public void aplicarDescuento() {
		this.costo.aplicarDescuento();
	}
	public void quitarDescuento() {
		this.costo.quitarDescuento();;
	}
}
