package artista;

import java.util.HashSet;
import roles.Roles;
/*
 * músico o técnico que puede desempeñar un rol por canción, de varios roles. 
 * Cada artista tiene: nombre, lista de roles que ha ocupado históricamente, 
 * histórico de bandas/colaboraciones (lista de nombres de bandas con las que tocó),
 * un costo por contratación por canción, y la cantidad máxima de canciones dispuesto
 * a tocar en un mismo recital (si es artista contratado). Un artista solo puede ser 
 * asignado a roles que haya desempeñado históricamente.
 */
public class ArtistaBase {
	private String nombreArtista;
	private HashSet<String> bandasHistorico;
	private HashSet<Roles> rolesHistorico;
	private Costo costo;//Esto maneja los costos.
	private Contador cantidadCanciones;//Esto maneja la cantidad de canciones
	public ArtistaBase(String nombreArtista,int cantMaximaCanciones,float costo) {
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

	public void setBandasHistorico(String... bandas) {
		for (String banda : bandas)
			this.bandasHistorico.add(banda);
	}

	public void setRolesHistorico(Roles... roles) {
		for (Roles rol : roles)
			this.rolesHistorico.add(rol);
	}

	public boolean participoEnBanda(String banda) {
		return bandasHistorico.contains(banda);
	}

	public boolean tieneRol(Roles rol) {
		return rolesHistorico.contains(rol);
	}
	public double darCostoActual() {
		return this.costo.getCosto()*this.cantidadCanciones.getNumActual();
	}
}
