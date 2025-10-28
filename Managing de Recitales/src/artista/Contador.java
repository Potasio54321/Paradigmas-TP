package artista;

public class Contador {
	private int numActual;
	private int numMax;
	public Contador(int numMax) {
		this.numMax=numMax;
		this.numActual=0;
	}
	public boolean contar() {
		if(numActual<numMax)
			numActual++;
		return numActual==numMax;
	}
	public int getNumActual() {
		return numActual;
	}
}
