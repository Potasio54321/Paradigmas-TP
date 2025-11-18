package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Main;

class TesteoProyecto {
	@Test
	void testNombresArchivoNoExiste() {
		assertThrows(Exception.class, () -> Main.main(new String[] { "", "" }));
	}

	@Test
	void testNombresCancionVacio() {
		Exception e = assertThrows(Exception.class,
				() -> Main.main(new String[] { "json/ARTISTAS.json", "json/RECITALConNombresVacio.json" }));
		assertEquals("Cancion nombre Vacio", e.getMessage());
	}

	@Test
	void testNombresArtistaVacio() {
		assertThrows(Exception.class,
				() -> Main.main(new String[] { "json/ARTISTASConNombresVacio.json", "json/RECITAL.json" }));
	}

	@Test
	void testSinArtistas() {
		assertThrows(Exception.class,
				() -> Main.main(new String[] { "json/ARTISTASVacio.json", "json/RECITALVacio.json" }));
	}

	@Test
	void testSinCanciones() {
		assertThrows(Exception.class, () -> Main.main(new String[] { "json/ARTISTAS.json", "json/RECITALVacio.json" }));
	}

}
