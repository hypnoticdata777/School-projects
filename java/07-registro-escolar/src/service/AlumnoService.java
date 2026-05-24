package service;

import modelo.Alumno;
import repository.AlumnoRepository;

import java.util.ArrayList;

public class AlumnoService {

    private AlumnoRepository repository;

    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    public void registrarAlumno(int id, String nombre, String carrera, int semestre) {
        if (nombre.isEmpty()) {
            System.out.println("El nombre es obligatorio");
            return;
        }

        Alumno alumno = new Alumno(id, nombre, carrera, semestre);

        repository.guardar(alumno);

        System.out.println("Alumno registrado correctamente");
    }

    public ArrayList<Alumno> obtenerTodos() {
        return repository.obtenerTodos();
    }
}
