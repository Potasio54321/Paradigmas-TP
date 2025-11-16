package roles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
	BATERISTA(1, "Baterista"), GUITARRISTA(2, "Guitarrista"), PIANISTA(4, "Pianista"),
	VOZ_PRINCIPAL(8, "Voz Principal"), DJ(16, "DJ"), CORO(32, "Coro"), BAJISTA(64, "Bajista"),
	GUITARRA_ELECTRICA(128, "Guitarra Eléctrica");

	private final String nombreRol;
	private final int flag;

	Roles(int flag, String nombreRol) {
		this.flag = flag;
		this.nombreRol = nombreRol;
	}

	/// Jackson usa esto para LEER del JSON
	/// getCampo, setCampo son para clases, para Enums es esto
	@JsonCreator
	public static Roles fromString(String text) {
		if (text == null)
			return null;
		for (Roles rol : Roles.values()) {
			if (rol.nombreRol.equalsIgnoreCase(text.trim())) {
				return rol;
			}
		}
		throw new IllegalArgumentException("Rol desconocido: '" + text);
	}

	/// Jackson, te odio
	@JsonValue
	public String getNombreRol() {
		return nombreRol;
	}

	public int getFlag() {
		return flag;
	}

	@Override
	public String toString() {
		return nombreRol;
	}
}
