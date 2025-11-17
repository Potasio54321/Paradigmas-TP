package utiles;

import java.util.LinkedList;

public class ListaLimitada<Tipo> {
	private LinkedList<Tipo> lista;
	private int limite;

	public ListaLimitada(int cantMax) {
		if (cantMax <= 0)
			throw new IllegalArgumentException("Lista Limitada Menor o Igual a 0 cantMax");
		lista = new LinkedList<Tipo>();
		limite = cantMax;
	}

	public void insertar(Tipo algo) {
		if (lista.size() < limite)
			lista.add(algo);
	}

	public boolean estaLleno() {
		return lista.size() == limite;
	}

	public int getCantidadMaxima() {
		return limite;
	}

	public int darTamañoActual() {
		return lista.size();
	}
}
