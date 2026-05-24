package repository;

import modelo.Alumno;

import java.util.ArrayList;

public class AlumnoRepository {

    private ArrayList<Alumno> alumnos = new ArrayList<>();

    public void guardar(Alumno alumno) {
        alumnos.add(alumno);
    }

    public ArrayList<Alumno> obtenerTodos() {
        return alumnos;
    }
}
