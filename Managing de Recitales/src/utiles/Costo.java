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
	private final static double PORCENTAJE_DESCUENTO = 0.5;// 50%
	// Constructor vacío para Jackson

	public Costo() {
		this.tieneDescuento = false;
	}

	public Costo(double costo) {
		if (costo < 0)
			throw new IllegalArgumentException("Costo Menor A 0 costo");
		this.costo = costo;
		tieneDescuento = false;
	}

	// Setter para "costo" del JSON
	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getCosto() {
		return tieneDescuento ? costo * PORCENTAJE_DESCUENTO : costo;
	}

	public boolean getTieneDescuento() {
		return tieneDescuento;
	}

	public void aplicarDescuento() {
		if (!tieneDescuento) {
			tieneDescuento = true;
		}
	}

	public void quitarDescuento() {
		if (tieneDescuento) {
			tieneDescuento = false;
		}
	}
}
