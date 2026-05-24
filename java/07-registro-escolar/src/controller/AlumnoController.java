package controller;

import modelo.Alumno;
import service.AlumnoService;

import java.util.ArrayList;

public class AlumnoController {

    private AlumnoService service;

    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    public void registrarAlumno(int id, String nombre, String carrera, int semestre) {
        service.registrarAlumno(id, nombre, carrera, semestre);
    }

    public ArrayList<Alumno> obtenerTodos() {
        return service.obtenerTodos();
    }
}
