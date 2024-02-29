package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaOscarizada {

    private int anyo;
    private int edad;
    private String actor;
    private String pelicula;
    private String sexo;

    @Override
    public String toString() {
        if (sexo.equals("M")) {
            return "[pelicula= " + pelicula  +
                    ", año=" + anyo +
                    ", actriz=" + actor +
                    ", edad=" + edad +
                    ", sexo=" + sexo + "]";
        }
        else {
            return "[pelicula= " + pelicula  +
                    ", año=" + anyo +
                    ", actor=" + actor +
                    ", edad=" + edad +
                    ", sexo=" + sexo + "]";
        }
    }

}