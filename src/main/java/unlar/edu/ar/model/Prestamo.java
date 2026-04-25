package unlar.edu.ar.model;

import java.time.LocalDate; // Importamos LocalDate para manejar fechas
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera Getters, Setters, equals, hashCode y toString()
@AllArgsConstructor // Genera constructor con todos los atributos
@NoArgsConstructor  // Genera constructor vacío (importante para frameworks)
public class Prestamo {
    private Libro libro;
    private Estudiante estudiante;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    /**
     * El metodo toString() lo piden en el Trab. Prac.
     * Lombok ya lo genera en @Data, pero si quisieras personalizarlo para que sea
     * mas "informativo/agradable" en la consola, lo ideal es:
     */
    
    @Override
    public String toString() {
        return String.format("Préstamo: [Libro: %s | Estudiante: %s | Desde: %s | Hasta: %s] \n",
                libro.getTitulo(), 
                estudiante.getNombre(), 
                fechaPrestamo, 
                fechaDevolucion != null ? fechaDevolucion : "Pendiente");
    }
}