package artista;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	private static final double AUMENTO_ENTRENAMIENTO = 1.5;
	@JsonProperty("nombre")
	private String nombreArtista;
	@JsonProperty("bandas")
	private HashSet<String> bandasHistorico;
	@JsonProperty("roles")
	private HashSet<Roles> rolesHistorico;
	@JsonProperty("maxCanciones")
	private Contador cantidadCanciones;
	@JsonProperty("costo")
	private Costo costo;

	/// Usado por Jackson
	public Artista() {
		this.bandasHistorico = new HashSet<>();
		this.rolesHistorico = new HashSet<>();
	}

	public Artista(String nombreArtista, int cantMaximaCanciones, float costo) {
		if (nombreArtista == "")
			throw new IllegalArgumentException("Artista nombre Artista Vacio");
		this.nombreArtista = nombreArtista;
		this.bandasHistorico = new HashSet<String>();
		this.rolesHistorico = new HashSet<Roles>();
		this.cantidadCanciones = new Contador(cantMaximaCanciones);
		this.costo = new Costo(costo);
	}

	/// Getters y Setters 100% requeridos para Jackson
	public String getNombreArtista() {
		return nombreArtista;
	}

	public void setNombreArtista(String nombreArtista) {
		if (nombreArtista == "")
			throw new IllegalArgumentException("Artista nombre Artista Vacio");
		this.nombreArtista = nombreArtista;
	}

	public HashSet<String> getBandasHistorico() {
		return bandasHistorico;
	}

	public void setBandasHistorico(HashSet<String> bandasHistorico) {
		this.bandasHistorico = bandasHistorico;
	}

	public HashSet<Roles> getRolesHistorico() {
		return rolesHistorico;
	}

	public void setRolesHistorico(HashSet<Roles> rolesHistorico) {
		this.rolesHistorico = rolesHistorico;
	}

	public Costo getCosto() {
		return costo;
	}

	public void setCosto(Costo costo) {
		this.costo = costo;
	}

	public Contador getCantidadCanciones() {
		return cantidadCanciones;
	}

	public void setCantidadCanciones(Contador cantidadCanciones) {
		this.cantidadCanciones = cantidadCanciones;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Artista artista = (Artista) o;
		return Objects.equals(this.nombreArtista, artista.nombreArtista);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.nombreArtista);
	}

	@Override
	public String toString() {
		StringBuilder representacion = new StringBuilder();
		representacion.append("El nombre del artista es: " + this.nombreArtista);
		representacion.append("\nPuede Tocar " + this.cantidadCanciones.getNumActual() + "/"
				+ this.cantidadCanciones.getNumMax() + " Canciones");
		representacion.append("\nSu costo por cancion es: " + this.costo.getCosto());
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

	/// Relacionado con la bandas
	@JsonIgnore
	public void setBandasHistorico(String... bandas) {
		for (String banda : bandas)
			this.bandasHistorico.add(banda);
	}

	public boolean participoConArtista(Artista otro) {
		boolean noHayElementosEnComun = Collections.disjoint(bandasHistorico, otro.bandasHistorico);
		return !noHayElementosEnComun;
	}

	/// Relacionadas la Cantidad de canciones
	public void darCancion() {
		cantidadCanciones.contar();
	}

	public boolean puedeTocar() {
		return !cantidadCanciones.estaMaximo();
	}

	/// Relacionadas Al Rol
	@JsonIgnore
	public void setRolesHistorico(Roles... roles) {
		for (Roles rol : roles)
			this.rolesHistorico.add(rol);
	}

	public boolean tieneRol(Roles rol) {
		return rolesHistorico.contains(rol);
	}

	public boolean tieneRoles(Set<Roles> roles) {
		boolean noHayElementosEnComun = Collections.disjoint(rolesHistorico, roles);
		return !noHayElementosEnComun;
	}

	public boolean entrenar(Roles nuevoRol) {
		// No entrenar si ya tiene el rol o No entrenar si ya fue contratado en alguna
		// canción
		if (rolesHistorico.contains(nuevoRol) || cantidadCanciones.getNumActual() > 0)
			return false;

		// Aumentar costo
		double nuevoCosto = this.costo.getCosto() * AUMENTO_ENTRENAMIENTO;
		this.costo = new Costo(nuevoCosto);

		// Agregar nuevo rol
		this.rolesHistorico.add(nuevoRol);
		return true;
	}

	/// Relacionadas Al Coste
	public double darCosto() {
		return this.costo.getCosto();
	}

	public void aplicarDescuento() {
		this.costo.aplicarDescuento();
	}

	public void quitarDescuento() {
		this.costo.quitarDescuento();
	}
}
