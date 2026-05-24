package modelo;

public class Alumno {

    private int id;
    private String nombre;
    private String carrera;
    private int semestre;

    public Alumno(int id, String nombre, String carrera, int semestre) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.semestre = semestre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public int getSemestre() {
        return semestre;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Nombre: " + nombre + " Carrera: " + carrera + " Semestre: " + semestre;
    }
}
