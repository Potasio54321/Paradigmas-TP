package cancion;

import java.util.LinkedList;

public class Podio<Tipo extends Comparable<Tipo>> {
	private LinkedList<Tipo> podio;
	private int cantidadMaxima;
	public Podio(int cantMax) {
		podio=new LinkedList<Tipo>();
		cantidadMaxima=cantMax;
	}
	public void insertar(Tipo otro) {
		if(podio.getFirst().compareTo(otro)>0) {
			if(podio.size()==cantidadMaxima)
				podio.remove(cantidadMaxima-1);
			podio.add(otro);
		}
	}
}	
