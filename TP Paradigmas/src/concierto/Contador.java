package utiles;

public class Contador {
	private int numActual;
	private int numMax;
	/// De nuevo, para el Jackson
    public Contador() {
        this.numActual = 0;
    }

    public Contador(int numMax) {
        this.numMax = numMax;
        this.numActual = 0;
    }

    ///  Getters y Setters para el Jackson 5
    public void setNumMax(int numMax) {
        this.numMax = numMax;
    }

    public int getNumActual() {
        return numActual;
    }

    public int getNumMax() {
        return numMax;
    }

    public void contar() {
        if (numActual < numMax)
            numActual++;
    }

    public boolean estaMaximo() {
        return numActual == numMax;
    }
}
