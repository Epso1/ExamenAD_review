package entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Actor implements Serializable {
    @JsonProperty("name")
    private String nombre;
    @JsonProperty("sex")
    private String sexo;
    @JsonProperty("yearOfBirth")
    private int anyoNacimiento;
    @JsonProperty("movies")
    private List<Pelicula> peliculas;

    public Actor(String nombre, String sexo, int anyoNacimiento, List<Pelicula> peliculas){
        this.nombre = nombre;
        this.sexo = sexo;
        this.anyoNacimiento = anyoNacimiento;
        this.peliculas = peliculas;
    }

    @Override
    public String toString(){
        return "[nombre=" + nombre +
                ", sexo=" + sexo +
                ", añoNacimiento=" + anyoNacimiento +
                ", peliculas =" + peliculas.toString() + "]";
    }
}