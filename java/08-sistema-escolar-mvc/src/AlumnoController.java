import java.util.ArrayList;

/**
 * Capa de CONTROLADOR para la entidad Alumno.
 *
 * En la arquitectura por capas, el controlador actua como puente entre
 * la interfaz de usuario (el menu en App.java) y la logica de negocio (AlumnoService).
 *
 * Su unica responsabilidad es recibir la accion del usuario y delegarla al servicio.
 * NO valida datos ni accede a la base de datos directamente; eso lo hacen
 * el servicio y el repositorio respectivamente.
 */
public class AlumnoController {

    // Referencia al servicio que contiene la logica de negocio
    private AlumnoService service;

    /**
     * Constructor que recibe el servicio por inyeccion de dependencias.
     *
     * @param service instancia del servicio de alumnos
     */
    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    /**
     * Solicita al servicio que registre un nuevo alumno con los datos proporcionados.
     *
     * @param nombre   nombre del alumno ingresado por el usuario
     * @param carrera  carrera ingresada por el usuario
     * @param semestre semestre ingresado por el usuario
     */
    public void registrarAlumno(String nombre, String carrera, int semestre) {
        service.registrarAlumno(nombre, carrera, semestre);
    }

    /**
     * Solicita al servicio la lista completa de alumnos para mostrarla en el menu.
     *
     * @return ArrayList con todos los alumnos registrados en la base de datos
     */
    public ArrayList<Alumno> obtenerTodos() {
        return service.obtenerTodos();
    }

    /**
     * Solicita al servicio que actualice los datos del alumno con el ID indicado.
     *
     * @param id       id del alumno a modificar
     * @param nombre   nuevo nombre
     * @param carrera  nueva carrera
     * @param semestre nuevo semestre
     */
    public void actualizarAlumno(int id, String nombre, String carrera, int semestre) {
        service.actualizarAlumno(id, nombre, carrera, semestre);
    }

    /**
     * Solicita al servicio que elimine el alumno con el ID indicado.
     *
     * @param id identificador del alumno que se desea eliminar
     */
    public void eliminarAlumno(int id) {
        service.eliminarAlumno(id);
    }

    /**
     * Solicita al servicio que exporte todos los alumnos al archivo alumnos.json.
     * No recibe parametros porque el servicio sabe como obtener los datos.
     */
    public void exportarJSONArchivo() {
        service.exportarJSONArchivo();
    }
}
