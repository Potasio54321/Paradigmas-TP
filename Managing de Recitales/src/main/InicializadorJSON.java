package main;
import artista.Artista;
import cancion.Cancion;
import java.io.File;
import java.util.HashSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class InicializadorJSON {
    public static boolean inicializarDatos(HashSet<Artista> a, HashSet<Cancion> c, String caminoArtistas, String caminoCanciones) throws Exception {
        try {
            /// Por qué no vigilo si se cierra o no? Porque File no es FileInputStream.
            File artistasJson = new File(caminoArtistas);
            File cancionesJson = new File(caminoCanciones);

            a.addAll(inicializarArtistas(artistasJson));
            c.addAll(iniciaCanciones(cancionesJson));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return true;
    }

    public static HashSet<Artista> inicializarArtistas(File archivo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HashSet<Artista> artistas = mapper.readValue(archivo, new TypeReference<>() {});

        return artistas;
    }

    public static HashSet<Cancion> iniciaCanciones(File archivo) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	HashSet<Cancion> cancions=new HashSet<Cancion>();
    	HashSet<AuxiliarLeerJsonRecital> aux=mapper.readValue(archivo, new TypeReference<>() {});
        for(AuxiliarLeerJsonRecital a: aux) {
        	Cancion c=new Cancion(a.getTitulo());
        	c.setRolesNecesarios(a.hacerHashMapRoles());
        	cancions.add(c);
        }
        
        return cancions;
    }
}
