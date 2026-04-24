package unlar.edu.ar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    private String legajo;
    private String nombre;
    private String carrera;
    private String email;
}   