package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jpl7.Atom;
import org.jpl7.PrologException;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import artista.Artista;
import cancion.Cancion;
import roles.Roles;

public class Prologando {
	public static int Prolog(HashSet<Artista> artistas, HashSet<Cancion> canciones) {
		try {
			for (Artista a : artistas) {
				crearHechoProlog(artistaProlog(a));
			}
			for (Cancion c : canciones) {
				crearHechoProlog(cancionProlog(c));
			}
			String regla_cubre_base = "cubre_base(Rol) :- " + "artista_base(_, Roles, _, _), " + "member(Rol, Roles)";
			crearReglaProlog(regla_cubre_base);
			String regla_rol_recital = "rol_recital(Rol) :- " + "cancion(_, RolesCancion), "
					+ "member(Rol, RolesCancion)";
			crearReglaProlog(regla_rol_recital);
			String regla_rol_faltante = "rol_faltante(Rol) :- " + "rol_recital(Rol), " + "\\+cubre_base(Rol)";
			crearReglaProlog(regla_rol_faltante);
			String regla_min_artistas_externos = "min_artistas_externos(Min) :- findall(Rol, rol_faltante(Rol), RolesFaltantes), list_to_set(RolesFaltantes,RolesUnicos), length(RolesUnicos, Min)";
			crearReglaProlog(regla_min_artistas_externos);

			// === CONSULTAR ===
			Query consulta = new Query("min_artistas_externos(Min)");
			if (consulta.hasSolution()) {
				Map<String, Term> solucion = consulta.oneSolution();
				Term termMin = solucion.get("Min");
				if (termMin.isInteger()) {
					int resultado = termMin.intValue();
					System.out.println("¡MÍNIMO DE MERCENARIOS: " + resultado + "!");
					return resultado;
				}
			}
			System.out.println("No se pudo calcular.");
			return -1;
		} catch (Exception e) {
			System.err.println("¡PROLOG SE ROMPIÓ!" + e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}

	private static void crearHechoProlog(String hechoString) {
		Query assertQuery = new Query("assertz(" + hechoString + ")");
		assertQuery.hasSolution();

	}

	private static void crearReglaProlog(String reglaString) {
		Query regla = new Query("assertz((" + reglaString + "))");
		regla.hasSolution();
	}

	public static String artistaProlog(Artista a) {
		// artista_base(Nombre, [RolesHistoricos], [BandasHistoricas], MaxCanciones)
		StringBuilder res = new StringBuilder();
		res.append("artista_base('");
		res.append(a.getNombreArtista());
		res.append("', ");
		HashSet<Roles> rh = a.getRolesHistorico();
		res.append(rolesAProlog(rh));
		res.append(", ");
		HashSet<String> bh = a.getBandasHistorico();
		res.append(stringsAProlog(bh));
		res.append(", ");
		res.append(a.getCantidadCanciones().getNumMax());
		res.append(")");
		return res.toString();
	}

	public static String cancionProlog(Cancion c) {
		// cancion(Nombre, [RolesNecesarios])
		StringBuilder res = new StringBuilder();
		res.append("cancion('");
		res.append(c.getNombre());
		res.append("', ");
		res.append(rolesAProlog(c.getRolesNecesarios()));
		res.append(")");
		return res.toString();
	}

	public static String rolesAProlog(HashSet<Roles> roles) {
		if (roles == null || roles.isEmpty()) {
			return "[]";
		}

		return roles.stream().map(rol -> "'" + rol.getNombreRol() + "'").collect(Collectors.joining(", ", "[", "]"));
	}

	public static String stringsAProlog(HashSet<String> bandas) {
		if (bandas == null || bandas.isEmpty()) {
			return "[]";
		}

		return bandas.stream().map(b -> "'" + b + "'").collect(Collectors.joining(", ", "[", "]"));
	}

}
