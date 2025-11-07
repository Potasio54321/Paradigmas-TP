package utiles;

public class Contador {
	private int numActual;
	private int numMax;
	public Contador(int numMax) {
		this.numMax=numMax;
		this.numActual=0;
	}
	public void contar() {
		if(numActual<numMax)
			numActual++;
	}
	public int getNumActual() {
		return numActual;
	}
	public boolean estaMaximo() {
		return numActual==numMax;
	}
}
