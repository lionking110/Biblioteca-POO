package unlar.edu.ar.ui;

import unlar.edu.ar.model.*;
import unlar.edu.ar.service.BibliotecaService;
import java.util.*;

public class Main {
        public static Scanner teclado;
        private static BibliotecaService service;

        public static void main(String[] args) {

                // ¡CORRECCIÓN CRÍTICA! Inicializamos el servicio aquí
                service = new BibliotecaService();

                // 1. Crear 5 libros y 3 estudiantes
                service.agregarLibro(new Libro("111", "Las aventuras de Sherlock Holmes", "Sir Arthur Conan Doyle",
                                1892, true));
                service.agregarLibro(new Libro("222", "Las memorias de Sherlock Holmes", "Sir Arthur Conan Doyle", 1894,
                                true));
                service.agregarLibro(new Libro("333", "El regreso de Sherlock Holmes", "Sir Arthur Conan Doyle", 1903,
                                true));
                service.agregarLibro(new Libro("444", "Su última reverencia", "Sir Arthur Conan Doyle", 1917, true));
                service.agregarLibro(new Libro("555", "El archivo de Sherlock Holmes", "Sir Arthur Conan Doyle", 1926,
                                true));

                service.registrarEstudiante(new Estudiante("#EISI1335", "Jeremias Ismael Nieto", "Sistemas",
                                "jeremiasismael10@gmail.com.ar"));
                service.registrarEstudiante(new Estudiante("#EISI1479", "Sergio Sebastian Loreiro", "Sistemas",
                                "sergioyun155@gmail.com"));
                service.registrarEstudiante(new Estudiante("#EISI1560", "Francesco Stefano Pedrocca Nieto", "Sistemas",
                                "franestefano5@gmail.com"));

                System.out.println("--- Inicio de Pruebas de Gestion de Biblioteca ---\n");
// INICIO DEL MENU DE PRUEBAS
                teclado = new Scanner(System.in);

                int opcion = 0;
                do {
                        System.out.println("\n--- SISTEMA DE GESTIÓN BIBLIOTECARIA ---");
                        System.out.println("1. Registrar Préstamo");
                        System.out.println("2. Registrar Devolucion");
                        System.out.println("3. Buscar Libro");
                        System.out.println("4. Listar Prestamos");
                        System.out.println("5. Calcular Multa por Retraso");
                        System.out.println("6. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion = teclado.nextInt();
                        teclado.nextLine(); // Limpia el "Enter"
                        
                        switch (opcion) {
                                case 1:
                                        ejecutarRegistrarPrestamo();
                                        break;
                                case 2:
                                        ejecutarRegistroDevolucion();
                                        break;
                                case 3:
                                        ejecutarBuscarLibro();
                                        break;
                                case 4:
                                        listarPrestamos();
                                        break;
                                case 5:
                                        ejecutarCalcularMulta();
                                        break;
                                case 6:
                                        System.out.println("Saliendo del sistema...");
                                        break;
                                default:
                                        System.out.println("Opción no válida.");
                        }
                } while (opcion != 6);
        }

        private static void ejecutarRegistrarPrestamo() {
                System.out.print("Digite el ISBN: ");
                String isbn = teclado.nextLine();
                System.out.print("Digite el legajo: ");
                String legajo = teclado.nextLine();

                try {
                        service.registrarPrestamo(isbn, legajo);
                        System.out.println("Prestamo exitoso");
                } catch (Exception e) {
                        System.out.println("Mensaje de Error: " + e.getMessage());
                }
        }

        private static void ejecutarRegistroDevolucion() {
                System.out.print("Digite el ISBN: ");
                String isbn = teclado.nextLine();
                System.out.print("Digite el legajo: ");
                String legajo = teclado.nextLine();
                System.out.print("Digite los dias de retraso: ");
                int diasRetraso = teclado.nextInt();
                teclado.nextLine(); // Limpiamos buffer

                try {
                        service.registrarDevolucion(isbn, legajo, diasRetraso);
                } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                }
        }

        private static void ejecutarBuscarLibro() {
                System.out.print("Nombre del libro a buscar o parte del nombre: ");
                String libro = teclado.nextLine();

                List<Libro> encontrados = service.buscarLibrosPorTitulo(libro);
                if (encontrados.isEmpty()) {
                        System.out.println("Libro no encontrado");
                } else {
                        for (Libro lib : encontrados) {
                                System.out.println(lib.toString());
                        }
                }
        }
        
        private static void listarPrestamos() {
                System.out.print("Listar prestamos del legajo: ");
                String legajo = teclado.nextLine();

                try {
                        List<Prestamo> prestamos = service.listarPrestamosPorEstudiante(legajo);
                        if (prestamos.isEmpty()) {
                                System.out.println("El estudiante no tiene prestamos activos.");
                        } else {
                                System.out.println(prestamos);
                        }
                } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                }
        }

        private static void ejecutarCalcularMulta() {
                System.out.print("Digite dias de retraso: ");
                int diasRetraso = teclado.nextInt();
                teclado.nextLine(); // Limpiamos buffer

                System.out.print("Digite valor del libro: ");
                double valorLibro = teclado.nextDouble();
                teclado.nextLine(); // <-- ¡CORRECCIÓN! Limpiamos el buffer del double

                try {
                        // Guardamos el resultado en una variable para no llamar al método dos veces
                        double totalMulta = service.calcularMulta(diasRetraso, valorLibro);
                        System.out.println("Multa a pagar: $" + totalMulta);
                } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                }
        }
}