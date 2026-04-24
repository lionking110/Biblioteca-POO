package unlar.edu.ar.ui;

// IMPORTANTE: Importar nuestras clases de los otros paquetes
import unlar.edu.ar.model.Libro;
import unlar.edu.ar.model.Estudiante;
import unlar.edu.ar.service.BibliotecaService;
import unlar.edu.ar.exception.LibroNoDisponibleException;
import unlar.edu.ar.exception.LimitePrestamosExcedidoException;
import unlar.edu.ar.exception.EstudianteNoEncontradoException;

// Importar la herramienta de fechas de Java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BibliotecaService service = new BibliotecaService();

// 1. Crear 5 libros y 3 estudiantes 
        service.agregarLibro(new Libro("111", "Las aventuras de Sherlock Holmes", "Sir Arthur Conan Doyle", 1892, true));
        service.agregarLibro(new Libro("222", "Clean Code", "Robert Martin", 2008, true));
        service.agregarLibro(new Libro("333", "Design Patterns", "GoF", 1994, true));
        service.agregarLibro(new Libro("444", "Effective Java", "Joshua Bloch", 2018, true));
        service.agregarLibro(new Libro("555", "Spring in Action", "Craig Walls", 2022, true));

        service.registrarEstudiante(new Estudiante("101", "Juan Perez", "Sistemas", "juan@uoc.edu"));
        service.registrarEstudiante(new Estudiante("102", "Ana Garcia", "Sistemas", "ana@uoc.edu"));
        service.registrarEstudiante(new Estudiante("103", "Luis Torres", "Industrial", "luis@uoc.edu"));

        System.out.println("--- Inicio de Pruebas de Gestión de Biblioteca ---\n");

        try {
// Test 1: Préstamo exitoso
            System.out.println("Intentando préstamo exitoso...");
            service.registrarPrestamo("111", "101");

// Test 2: Captura LibroNoDisponibleException
            System.out.println("\nIntentando prestar el mismo libro de nuevo...");
            service.registrarPrestamo("111", "102"); 

        } catch (LibroNoDisponibleException | LimitePrestamosExcedidoException | EstudianteNoEncontradoException e) {
            System.err.println("ERROR CAPTURADO: " + e.getMessage());
        }

// Test 3: Cálculo de multa recursiva con 15 días (Requerimiento del TP)
        System.out.println("\n--- Prueba de Recursividad (Multas) ---");
        double montoMulta = service.calcularMulta(15, 1000.0);
        System.out.println("Multa por 15 días de retraso (sobre $1000): $" + montoMulta);

        // ... (Debajo de tus pruebas anteriores)

// Test 4: Búsqueda parcial por título        
        System.out.println("\n--- Prueba de Búsqueda Parcial ---");
        String busqueda = "java";
        System.out.println("Buscando: '" + busqueda + "'");
        service.buscarLibrosPorTitulo(busqueda).forEach(System.out::println);

// Test 5: Devolución con cálculo de multa
        System.out.println("\n--- Prueba de Devolución ---");
        // Devolvemos el libro que prestamos al inicio (ISBN 111, Legajo 101) con 5 días de retraso
        service.registrarDevolucion("111", "101", 5);

// Test 6: Listado de préstamos por estudiante
        System.out.println("\n--- Prueba de Listado por Estudiante ---");
        // Prestamos otro para ver el listado
        try {
            service.registrarPrestamo("222", "102");
            System.out.println("Préstamos de Ana (102):");
            service.listarPrestamosPorEstudiante("102").forEach(System.out::println);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

         System.out.println("\n--- Fin de Pruebas ---");
    }
}