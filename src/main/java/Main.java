import entities.Actor;
import entities.PeliculaOscarizada;
import jdk.jshell.execution.Util;
import utilities.Utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ejercicio 1.
        // Almacenar en una lista de objetos PeliculasOscarizadas la información obtenida de los 2 ficheros .csv

        Path oscarFemale = Path.of(".", "src", "main", "resources", "oscar_age_female.csv");
        Path oscarMale = Path.of(".", "src", "main", "resources", "oscar_age_male.csv");

        List<PeliculaOscarizada> peliculasOscarizadas = Utilidades.leerPeliculasOscarizadasCsv(oscarFemale, oscarMale);


//        System.out.println("Lista de películas oscarizadas:");
//        peliculasOscarizadas.forEach(pelicula -> System.out.println(pelicula.toString()));

        // Ejercicio 2.
        //Dada la información de los ficheros .csv facilitados, crea un fichero llamado actores.json, en el que se
        //almacenarán todos los actores y actrices que hayan ganado algún oscar, así como las películas con las que lo
        //han ganado.
        //Este fichero actores.json deberá guardarse en un directorio llamado salida, el cual deberás crear dentro de
        //resources.
        List<Actor> actoresYActrices = Utilidades.convertirPeliculasOscarizadasEnActores(peliculasOscarizadas);
        System.out.println("Lista de actores y actrices:");
        actoresYActrices.forEach(actor -> System.out.println(actor.toString()));
        Path salida = Path.of(".", "src", "main", "resources", "salida");
        if (!Files.exists(salida)) {
            try {
                Files.createDirectory(salida);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path file = Path.of(salida.toString(), "actores.json");
        Utilidades.escribirActoresEnJson(file, actoresYActrices);



        //Ejercicio Extra
        // Crea el método actoresMasJovenesEnGanarUnOscar, que devuelve una lista de Strings con el actor
        // y la actriz más jóvenes en ganar un Oscar.

        System.out.println("Lista de actores y actrices más jóvenes en ganar un Oscar:");
        Utilidades.actoresMasJovenesEnGanarUnOscar(peliculasOscarizadas).forEach(System.out::println);


        System.out.println("Mostrando actores de archivo JSON");
        Utilidades.leerActoresJson(file).forEach(System.out::println);

    }
}
