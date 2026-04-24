package unlar.edu.ar.ui;

// IMPORTANTE: Importar nuestras clases de los otros paquetes
import unlar.edu.ar.model.Libro;
import unlar.edu.ar.model.Estudiante;
import unlar.edu.ar.service.BibliotecaService;
import unlar.edu.ar.exception.LibroNoDisponibleException;
import unlar.edu.ar.exception.LimitePrestamosExcedidoException;
import unlar.edu.ar.exception.EstudianteNoEncontradoException;

public class Main {
    public static void main(String[] args) { // Método principal para ejecutar la aplicación
        BibliotecaService service = new BibliotecaService();

// 1. Crear 5 libros y 3 estudiantes 
        service.agregarLibro(new Libro("111", "Las aventuras de Sherlock Holmes", "Sir Arthur Conan Doyle", 1892, true));
        service.agregarLibro(new Libro("222", "Las memorias de Sherlock Holmes", "Sir Arthur Conan Doyle", 1894, true));
        service.agregarLibro(new Libro("333", "El regreso de Sherlock Holmes", "Sir Arthur Conan Doyle", 1903, true));
        service.agregarLibro(new Libro("444", "Su última reverencia", "Sir Arthur Conan Doyle", 1917, true));
        service.agregarLibro(new Libro("555", "El archivo de Sherlock Holmes", "Sir Arthur Conan Doyle", 1926, true));

        service.registrarEstudiante(new Estudiante("#EISI1335", "Jeremias Ismael Nieto", "Sistemas", "jeremiasismael10@gmail.com.ar"));
        service.registrarEstudiante(new Estudiante("#EISI1479", "Sergio Sebastian Loreiro", "Sistemas", "ana@uoc.edu"));
        service.registrarEstudiante(new Estudiante("#EISI1560", "Francesco Stefano Pedrocca Nieto", "Sistemas", "franestefano5@gmail.com"));

        System.out.println("--- Inicio de Pruebas de Gestion de Biblioteca ---\n");

// INICIO DE TESTS/PRUEBAS ______________________________________________________________________________________________
        try {
// Test 1: Prestamo exitoso 
            System.out.println("Intentando prestamo exitoso...");
            service.registrarPrestamo("111", "#EISI1335");

// Test 2: Captura LibroNoDisponibleException 
            System.out.println("\nIntentando prestar el mismo libro de nuevo...");
            service.registrarPrestamo("111", "#EISI1479"); 
        
        } catch (LibroNoDisponibleException | LimitePrestamosExcedidoException | EstudianteNoEncontradoException e) {
            System.err.println("ERROR CAPTURADO: " + e.getMessage());
        } //catch para manejar las excepciones que puedan surgir durante los préstamos

// Test 3: Calculo de multa recursiva con 15 días (Requerimiento del TP)
        System.out.println("\n--- Prueba de Recursividad (Multas) ---");
        double montoMulta = service.calcularMulta(15, 1000.0);
        System.out.println("Multa por 15 dias de retraso (sobre $1000): $" + montoMulta);

// Test 4: Busqueda parcial por titulo        
        System.out.println("\n--- Prueba de Busqueda Parcial ---");
        String busqueda = "java";
        System.out.println("Buscando: '" + busqueda + "'");
        service.buscarLibrosPorTitulo(busqueda).forEach(System.out::println); //forEach para imprimir cada libro encontrado que coincida con la búsqueda parcial en el título

// Test 5: Devolucion con calculo de multa
        System.out.println("\n--- Prueba de Devolucion ---");
        // Devolvemos el libro que prestamos al inicio (ISBN 111, Legajo #EISI1335) con 5 días de retraso
                service.registrarDevolucion("111", "#EISI1335", 5);

// Test 6: Listado de prestamos por estudiante
        System.out.println("\n--- Prueba de Listado por Estudiante ---");
        // Prestamos otro para ver el listado
        try {
            service.registrarPrestamo("222", "#EISI1479");
            System.out.println("Préstamos de Sebastian (#EISI1479):");
            service.listarPrestamosPorEstudiante("#EISI1479").forEach(System.out::println);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

         System.out.println("\n--- Fin de Pruebas ---");
    }  
// FIN DE TESTS/PRUEBAS ______________________________________________________________________________________________
}