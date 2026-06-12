import java.util.ArrayList;

/**
 * Capa de CONTROLADOR para la entidad Docente.
 *
 * El controlador conecta el menu de usuario (App.java) con la logica de negocio
 * (DocenteService). Recibe las acciones del menu y las delega al servicio
 * sin validar ni tocar la base de datos directamente.
 *
 * Esto nos permite cambiar la interfaz (por ejemplo pasar de consola a GUI)
 * sin tener que reescribir la logica de negocio ni las consultas SQL.
 */
public class DocenteController {

    // El controlador solo conoce al servicio, no al repositorio
    private DocenteService service;

    /**
     * Constructor que recibe el servicio de docentes por inyeccion de dependencias.
     *
     * @param service instancia del servicio de docentes
     */
    public DocenteController(DocenteService service) {
        this.service = service;
    }

    /**
     * Delega al servicio el registro de un nuevo docente con los datos capturados en el menu.
     *
     * @param nombre       nombre del docente
     * @param materia      materia que imparte
     * @param departamento departamento al que pertenece
     * @param salario      salario mensual del docente
     */
    public void registrarDocente(String nombre, String materia, String departamento, double salario) {
        service.registrarDocente(nombre, materia, departamento, salario);
    }

    /**
     * Pide al servicio la lista de todos los docentes para mostrarla en consola.
     *
     * @return ArrayList con todos los docentes en la base de datos
     */
    public ArrayList<Docente> obtenerTodos() {
        return service.obtenerTodos();
    }

    /**
     * Delega al servicio la actualizacion del docente con el ID dado.
     *
     * @param id           id del docente a actualizar
     * @param nombre       nuevo nombre
     * @param materia      nueva materia
     * @param departamento nuevo departamento
     * @param salario      nuevo salario
     */
    public void actualizarDocente(int id, String nombre, String materia, String departamento, double salario) {
        service.actualizarDocente(id, nombre, materia, departamento, salario);
    }

    /**
     * Delega al servicio la eliminacion del docente con el ID dado.
     *
     * @param id identificador del docente que se quiere eliminar
     */
    public void eliminarDocente(int id) {
        service.eliminarDocente(id);
    }

    /**
     * Solicita al servicio que exporte todos los docentes al archivo docentes.json.
     * Es el equivalente directo de exportarJSONArchivo() de AlumnoController.
     */
    public void exportarJSONArchivo() {
        service.exportarJSONArchivo();
    }
}
