package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entities.Actor;
import entities.Pelicula;
import entities.PeliculaOscarizada;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Utilidades {



    // 1. leerPeliculasOscarizadasCsv: lee un fichero CSV y devuelve una lista de objetos PeliculaOscarizada.
    //                                 Debe tener en cuenta también el parámetro sexo para filtrar por sexo.

    public static List<PeliculaOscarizada> leerPeliculasOscarizadasCsv(Path rutacsvFemale, Path rutacsvMale){
        List<PeliculaOscarizada> peliculasOscarizadas = new ArrayList<>();

        try(Stream<String> contenidoFicheroFemale = Files.lines(rutacsvFemale);
            Stream<String> contenidoFicheroMale = Files.lines(rutacsvMale)){

            List<List<String>> contenidoFicheroListaFemale = contenidoFicheroFemale
                    .map(linea -> Arrays.asList(linea.split(";")))
                    .skip(1)
                    .toList();
            List<List<String>> contenidoFicheroListaMale = contenidoFicheroMale
                    .map(linea -> Arrays.asList(linea.split(";")))
                    .skip(1)
                    .toList();

            contenidoFicheroListaFemale.forEach(linea -> {
                peliculasOscarizadas.add(new PeliculaOscarizada(Integer.parseInt(linea.get(1)), Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), "M"));
            });
            contenidoFicheroListaMale.forEach(linea -> {
                peliculasOscarizadas.add(new PeliculaOscarizada(Integer.parseInt(linea.get(1)), Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), "H"));
            });

            return peliculasOscarizadas;
        }catch(IOException e){
            e.printStackTrace(System.out);
        }
        return peliculasOscarizadas;
    }
    // 2. convertirPeliculasOscarizadasEnActores: dada una lista de PeliculasOscarizadas, devuelve una lista de objetos Actor
    //                                 en la que estarán incluidos todos los actores y actrices.
    public static List<Actor> convertirPeliculasOscarizadasEnActores(List<PeliculaOscarizada> peliculasOscarizadas){
        List<Actor> actores = new ArrayList<>();

        peliculasOscarizadas.forEach(pelicula -> {
            if(!actores.contains(pelicula.getActor())){
                actores.add(new Actor(pelicula.getActor(), pelicula.getSexo(), (pelicula.getAnyo()-pelicula.getEdad()),
                        peliculasOscarizadas.stream().filter(p -> p.getActor().equals(pelicula.getActor())).map(p -> new Pelicula(p.getPelicula(), p.getAnyo())).toList()));
            }
        });

        return actores;
    }

    // 3. escribirActoresEnJson: escribe en un fichero JSON la lista de actores/actrices en el formato solicitado.
    public static void escribirActoresEnJson(Path rutaJson, List<Actor> actores) {
        try {
            ObjectMapper objetMapper = new ObjectMapper();
            objetMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objetMapper.writeValue(rutaJson.toFile(), actores);

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    // 4. actoresConMasdeUnOscar: devuelve una lista de Strings con los nombres de los actores/actrices que hayan ganado más de un Oscar.
    public static List<String> actoresConMasDeUnOscar(List<Actor> actores){
        List<String> actoresConMasDeUnOscar = new ArrayList<>();

        actores.forEach(actor -> {
            if(actor.getPeliculas().size() > 1){
                actoresConMasDeUnOscar.add(actor.getNombre());
            }
        });

        return actoresConMasDeUnOscar;
    }

    // 5. actoresMasJovenesEnGanarUnOscar: devuelve una lista de Strings con el actor y la actriz más jóvenes en ganar un Oscar.
    public static List<String> actoresMasJovenesEnGanarUnOscar(List<PeliculaOscarizada> peliculas){
        String actorMasJoven = "";
        String actrizMasJoven = "";

        actrizMasJoven = peliculas.stream()
                .filter(pelicula -> pelicula.getSexo().equals("M"))
                .sorted((p1, p2) -> p1.getEdad() - p2.getEdad())
                .limit(1)
                .map(pelicula -> pelicula.getActor()
                ).toList().get(0);

        actorMasJoven = peliculas.stream()
                .filter(pelicula -> pelicula.getSexo().equals("H"))
                .sorted((p1, p2) -> p1.getEdad() - p2.getEdad())
                .limit(1)
                .map(pelicula -> pelicula.getActor()
                ).toList().get(0);

        return List.of("Actor más joven: " + actorMasJoven, "Actriz más joven:" + actrizMasJoven);
    }


public static List<Actor> leerActoresJson(Path rutaJson){
    List<Actor> actores = new ArrayList<>();

    try{
        ObjectMapper objectMapper = new ObjectMapper();
        actores = objectMapper.readValue(rutaJson.toFile(), new TypeReference<List<Actor>>(){});

        return actores;
    }catch(IOException e){
        e.printStackTrace(System.out);
    }
    return actores;
}

}