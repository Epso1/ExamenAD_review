
import entities.Actor;
import entities.Pelicula;
import entities.PeliculaOscarizada;
import org.junit.Test;
import org.junit.jupiter.params.converter.ConvertWith;
import utilities.Utilidades;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UtilidadesTest {

    /*
    Ejercicio 3.
    Crea la clase UtilidadesTest, donde compruebes el funcionamiento del método actoresConMasDeUnOscar,
    que devuelve una lista con los nombres de los actores y actrices con más de un Oscar.
    */
    @Test
    public void actoresConMasDeUnOscarTest() {
        List<Actor> listaActores = new ArrayList<>();
        int tamanoEsperado = 2;

        Actor actor1 = new Actor("actor1", "M", 1980, List.of(new Pelicula("pelicula1", 2000), new Pelicula("pelicula2", 2001)));
        Actor actor2 = new Actor("actor2", "M", 1981, List.of(new Pelicula("pelicula3", 2002)));
        Actor actor3 = new Actor("actor3", "F", 1982, List.of(new Pelicula("pelicula4", 2003), new Pelicula("pelicula5", 2004)));

        listaActores.add(actor1);
        listaActores.add(actor2);
        listaActores.add(actor3);

        assertEquals(tamanoEsperado, Utilidades.actoresConMasDeUnOscar(listaActores).size());
    }

    @Test
    public void actoresMasJovenesEnGanarUnOscarTest() {

        List<PeliculaOscarizada> peliculas = new ArrayList<>();
        int tamanoEsperado = 2;
        String actorEsperado = "actor1";
        String actrizEsperada = "actriz1";

        PeliculaOscarizada pelicula1 = new PeliculaOscarizada(1950, 20, "actor1", "pelicula1", "H");
        PeliculaOscarizada pelicula2 = new PeliculaOscarizada(1960, 21, "actriz1", "pelicula2", "M");
        PeliculaOscarizada pelicula3 = new PeliculaOscarizada(2002, 22, "actriz2", "pelicula3", "M");
        PeliculaOscarizada pelicula4 = new PeliculaOscarizada(2003, 23, "actor2", "pelicula4", "H");

        peliculas.add(pelicula1);
        peliculas.add(pelicula2);
        peliculas.add(pelicula3);
        peliculas.add(pelicula4);

        assertEquals(tamanoEsperado, Utilidades.actoresMasJovenesEnGanarUnOscar(peliculas).size());

        //assert de String que contiene el nombre del actor y la actriz más joven en ganar un Oscar
        assertTrue(Utilidades.actoresMasJovenesEnGanarUnOscar(peliculas).get(0).contains(actorEsperado));
        assertTrue(Utilidades.actoresMasJovenesEnGanarUnOscar(peliculas).get(1).contains(actrizEsperada));

    }


}