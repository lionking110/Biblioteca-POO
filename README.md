# Sistema de Gestión de Biblioteca Universitaria

> **Trabajo Práctico Nº 1** - Programación III | Ingeniería en Sistemas de Información

## 👤 Integrantes - Informacion Alumno

- Nieto Jeremias Ismael - #EISI1335 - 45.254.709
- Sergio Sebastian Loreiro - EISI1479 - 45.781.037
- Francesco Stefano Pedrocca Nieto - EISI1560 - 43.416.101

## 📄 Descripción del Proyecto

Desarrollo de una aplicacion de consola en Java para la gestión integral de una biblioteca, permitiendo administrar el catálogo de libros, registro de estudiantes y el flujo de prestamos/devoluciones con validaciones estrictas de negocio.

## 📍 Tecnologías Utilizadas

- **Lenguaje:** Java 21 (JDK 21)
- **Gestor de Dependencias:** Apache Maven
- **Librerías:** Project Lombok (Optimización de código)
- **IDE:** Visual Studio Code
- **Modelado:** PlantUML (Diagrama de Clases)

## 💡 Conceptos Aplicados

- **Programación Orientada a Objetos:** Encapsulamiento estricto, asociación entre clases y modularización en capas (`model`, `service`, `exception`, `ui`).
- **Java Collections Framework:**
  - `ArrayList`: Gestión eficiente del catálogo de libros.
  - `HashMap`: Registro de estudiantes con busqueda optimizada por legajo.
  - `HashSet`: Control de prestamos activos garantizando la unicidad de registros.
- **Manejo de Excepciones:** Jerarquia de excepciones personalizadas; para el control de disponibilidad, limites de préstamos y existencia de usuarios.
- **Recursividad:** Implementacion de un algoritmo recursivo para el calculo de multas por demora, analizando la pila de llamadas.

## 📂 Estructura del Proyecto

```text
src/main/java/unlar/edu/ar/
├── exception/ # Excepciones personalizadas
├── model/     # Clases de dominio (Libro, Estudiante, Prestamo)
├── service/   # Lógica de negocio y colecciones
└── ui/        # Clase Main e interfaz de usuario
```
