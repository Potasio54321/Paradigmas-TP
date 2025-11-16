package main;

import java.util.HashMap;
import java.util.LinkedList;

import roles.Roles;

public class AuxiliarLeerJsonRecital {
	private String titulo;
    private LinkedList<String> rolesRequeridos;
    public AuxiliarLeerJsonRecital() {
		rolesRequeridos=new LinkedList<String>();
		titulo=null;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LinkedList<String> getRolesRequeridos() {
		return rolesRequeridos;
	}
	public void setRolesRequeridos(LinkedList<String> rolesRequeridos) {
		this.rolesRequeridos = rolesRequeridos;
	}
	public HashMap<Roles, Integer> hacerHashMapRoles() {
		HashMap<Roles, Integer> hM=new HashMap<Roles, Integer>();
		for(String s:rolesRequeridos) {
			Roles rolActual=Roles.fromString(s);
			if(hM.containsKey(rolActual)) {
				int i=hM.get(rolActual);
				hM.put(rolActual, i+1);
			}
			else
				hM.put(rolActual, 1);
		}
		return hM;
	}
    
}
