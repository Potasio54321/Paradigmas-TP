package main;
import java.util.HashSet;
import java.util.Scanner;
import artista.Artista;
import cancion.Cancion;
import grafico.Graficos;

public class Main {
    /// Globales, estarán encendidas 24/7
    public static HashSet<Artista> artistas = new HashSet<>();
    public static HashSet<Cancion> canciones = new HashSet<>();

    public static void main(String[] args) {
        if (!InicializadorJSON.inicializarDatos(artistas, canciones)) {
            System.out.println("Saliendo...");
            return;
        }

        // Inicializar
        Scanner entrada = new Scanner(System.in);
        int opcionElegida;
        Graficos.mostrarBienvenida();

        // Hacer Acciones
        do {
            ClearConsole();
            opcionElegida = entrada.nextInt();
            System.out.println("Eligiste :" + opcionElegida);
            hacerAccion(opcionElegida);
        } while (opcionElegida != 9);
        /// Cierre
        entrada.close();
    }


    public static void ClearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    private static void esperar(Scanner entrada) {
        entrada.nextLine();
    }

    private static void hacerAccion(int opcionElegida) {
        switch (opcionElegida) {
            case 1:
                System.out.println("Estoy Contando Roles para 1 Cancion");
                break;
            case 2:
                System.out.println("Estoy Contando Roles para el Recital");
                break;
            case 3:
                System.out.println("Estoy Contratando Artistas para 1 cancion");
                break;
            case 4:
                System.out.println("Estoy Contratando Artistas para el Recital");
                break;
            case 5:
                System.out.println("Estoy Entrenando Artistas");
                break;
            case 6:
                System.out.println("Estoy Listando Artistas");
                break;
            case 7:
                System.out.println("Estoy Listando Canciones");
                break;
            case 8:
                System.out.println("Estoy Prolog");
                break;
            case 9:
                System.out.println("Saliendo");
                break;
            default:
                System.out.println("Intente de nuevo");
                break;
        }
    }
}
