package roles;

public enum Roles {
	BATERISTA("Baterista"), GUITARRISTA("Guitarrista"), PIANISTA("Pianista");

	private String nombreRol;

	private Roles(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombreRol;
	}
}	
