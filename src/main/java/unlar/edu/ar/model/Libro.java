package unlar.edu.ar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera Getters, Setters, equals, hashCode y toString()
@AllArgsConstructor // Genera constructor con todos los atributos
@NoArgsConstructor  // Genera constructor vacío (importante para frameworks)
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anio;
    private boolean disponible;
}