package utiles;
/*
 * Cada artista candidato tiene un costo de contratación. 
 * Si algún artista base comparte historial de haber pertenecido 
 * a la misma banda histórica con el artista candidato, el costo del
 * candidato se reduce a la mitad (50%). Si se comparte con más de un base,
 * sigue siendo 50% (no acumulativo).*/
public class Costo {
	private double costo;
	private boolean tieneDescuento;
	private final static double PORCENTAJE_DESCUENTO=0.5;//50%
	public Costo(double costo) {
		this.costo=costo;
		tieneDescuento=false;
	}
	public double getCosto() {
		if(tieneDescuento)
			return costo*PORCENTAJE_DESCUENTO;
		return costo;
	}
	public boolean getTieneDescuento() {
		return tieneDescuento;
	}
	public void aplicarDescuento() {
		if(!tieneDescuento) {
			tieneDescuento=true;
		}
	}
	public void quitarDescuento() {
		if(tieneDescuento) {
			tieneDescuento=false;
		}
	}
}
