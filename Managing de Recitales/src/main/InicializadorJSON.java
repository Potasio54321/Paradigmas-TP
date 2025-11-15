package main;
import artista.Artista;
import cancion.Cancion;
import java.io.File;
import java.util.HashSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class InicializadorJSON {
    public static boolean inicializarDatos(HashSet<Artista> a, HashSet<Cancion> c) {
        try {
            /// Por qué no vigilo si se cierra o no? Porque File no es FileInputStream.
            File artistasJson = new File("json/ARTISTAS.json");
            File cancionesJson = new File("json/CANCIONES.json");

            a.addAll(inicializarArtistas(artistasJson));
            c.addAll(iniciaCanciones(cancionesJson));
            return true;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static HashSet<Artista> inicializarArtistas(File archivo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HashSet<Artista> artistas = mapper.readValue(archivo, new TypeReference<>() {});

        return artistas;
    }

    public static HashSet<Cancion> iniciaCanciones(File archivo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HashSet<Cancion> cancions = mapper.readValue(archivo, new TypeReference<>() {});

        return cancions;
    }
}
