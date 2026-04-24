package unlar.edu.ar.service;

// Importamos las herramientas de Java (Collections)
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

// Importamos nuestras propias clases y excepciones
import unlar.edu.ar.model.*;
import unlar.edu.ar.exception.*;

// Importamos herramientas de tiempo
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BibliotecaService {
    // Definición de colecciones según la consigna
    private List<Libro> catalogoLibros;
    private Map<String, Estudiante> registroEstudiantes;
    private Set<Prestamo> prestamosActivos;

    public BibliotecaService() {
        this.catalogoLibros = new ArrayList<>();
        this.registroEstudiantes = new HashMap<>(); //
        this.prestamosActivos = new HashSet<>();
    }

    // Métodos básicos de registro
    public void agregarLibro(Libro libro) {
        catalogoLibros.add(libro);
    }

    public void registrarEstudiante(Estudiante estudiante) {
        registroEstudiantes.put(estudiante.getLegajo(), estudiante);
    }

    public void registrarPrestamo(String isbn, String legajo) 
        throws LibroNoDisponibleException, LimitePrestamosExcedidoException, EstudianteNoEncontradoException {
    
    // 1. Buscar el libro
    Libro libro = catalogoLibros.stream()
            .filter(l -> l.getIsbn().equals(isbn) && l.isDisponible())
            .findFirst()
            .orElseThrow(() -> new LibroNoDisponibleException("El libro no está disponible o no existe."));

    // 2. Buscar el estudiante
    Estudiante estudiante = registroEstudiantes.get(legajo);
    if (estudiante == null) {
        throw new EstudianteNoEncontradoException("Estudiante con legajo " + legajo + " no encontrado.");
    }

    // 3. Validar límite (Contar préstamos actuales del estudiante)
    long cantidadPrestamos = prestamosActivos.stream()
            .filter(p -> p.getEstudiante().getLegajo().equals(legajo))
            .count();

    if (cantidadPrestamos >= 3) {
        throw new LimitePrestamosExcedidoException("El estudiante ya tiene 3 libros en su poder.");
    }

    // 4. Registrar el préstamo
    libro.setDisponible(false);
    Prestamo nuevoPrestamo = new Prestamo(libro, estudiante, LocalDate.now(), null);
    prestamosActivos.add(nuevoPrestamo);
    
    System.out.println("Préstamo registrado con éxito: " + libro.getTitulo());
    }


    /**
     * Método recursivo para calcular la multa.
     * @param diasRetraso cantidad de días excedidos
     * @param valorLibro precio base para el cálculo (ej. un valor fijo de 1000 para este TP)
     * @return el monto total de la multa
     */
    public double calcularMulta(int diasRetraso, double valorLibro) {
        // Caso Base 1: No hay retraso o ya procesamos todos los días
        if (diasRetraso <= 0) {
            return 0;
        }
        
        // Caso Base 2: Máximo de 30 días según la consigna
        if (diasRetraso > 30) {
            return calcularMulta(30, valorLibro);
        }

        // Caso Recursivo: 1% del valor por el día actual + el resto de los días
        double multaDiaActual = valorLibro * 0.01;
        return multaDiaActual + calcularMulta(diasRetraso - 1, valorLibro);
    }

// --- NUEVOS MÉTODOS ---

    /**
     * Búsqueda parcial por título (case-insensitive)
     * Requerimiento 2.4
     */
    public List<Libro> buscarLibrosPorTitulo(String query) {
        return catalogoLibros.stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Registrar devolución y calcular multa si corresponde
     * Requerimiento 2.4
     */
    public void registrarDevolucion(String isbn, String legajo, int diasRetraso) {
        // Buscamos el préstamo en el HashSet
        Optional<Prestamo> prestamoEncontrado = prestamosActivos.stream()
                .filter(p -> p.getLibro().getIsbn().equals(isbn) && 
                             p.getEstudiante().getLegajo().equals(legajo))
                .findFirst();

        if (prestamoEncontrado.isPresent()) {
            Prestamo p = prestamoEncontrado.get();
            
            // 1. Calcular multa si hay retraso (Usando recursividad)
            if (diasRetraso > 0) {
                double multa = calcularMulta(diasRetraso, 1000.0); // Usamos 1000 como valor base
                System.out.println("Devolución con retraso. Multa a pagar: $" + multa);
            }

            // 2. Liberar el libro
            p.getLibro().setDisponible(true);
            
            // 3. Quitar de préstamos activos
            prestamosActivos.remove(p);
            System.out.println("Libro '" + p.getLibro().getTitulo() + "' devuelto con éxito.");
        } else {
            System.out.println("No se encontró un registro de ese préstamo.");
        }
    }

    /**
     * Listar préstamos por estudiante específico
     * Requerimiento 2.4
     */
    public List<Prestamo> listarPrestamosPorEstudiante(String legajo) {
        return prestamosActivos.stream()
                .filter(p -> p.getEstudiante().getLegajo().equals(legajo))
                .collect(Collectors.toList());
    }

}