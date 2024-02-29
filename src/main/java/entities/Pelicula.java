package entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula implements Serializable {
    @JsonProperty("title")
    private String titulo;
    @JsonProperty("year")
    private int anyo;

    @Override
    public String toString(){
        return "[titulo=" + titulo +
                ", año=" + anyo  + "]";
    }
}