import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Capa de SERVICIO para la entidad Alumno.
 *
 * En la arquitectura por capas, el servicio es el intermediario entre el controlador
 * y el repositorio. Su responsabilidad es aplicar las REGLAS DE NEGOCIO antes de
 * mandar a guardar o modificar datos:
 *  - Validar que el nombre no este vacio
 *  - Validar que el semestre sea coherente
 *  - etc.
 *
 * El controlador llama al servicio, el servicio valida y llama al repositorio.
 * Asi cada capa tiene una sola responsabilidad (principio SRP).
 */
public class AlumnoService {

    // Dependencia hacia la capa de repositorio (inyectada por constructor)
    private AlumnoRepository repository;

    /**
     * Constructor que recibe el repositorio que va a usar este servicio.
     * Usamos inyeccion de dependencias para que sea mas facil de testear y mantener.
     *
     * @param repository instancia del repositorio de alumnos
     */
    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    /**
     * Valida los datos del alumno y, si todo esta correcto, lo manda a guardar.
     *
     * Regla de negocio: el nombre no puede estar vacio porque es el dato
     * principal que identifica al alumno en los reportes.
     *
     * @param nombre   nombre completo del alumno (no puede estar vacio)
     * @param carrera  carrera que estudia
     * @param semestre semestre actual que cursa
     */
    public void registrarAlumno(String nombre, String carrera, int semestre) {
        if (nombre.isEmpty()) {
            System.out.println("El nombre es obligatorio");
            return; // cortamos la ejecucion si la validacion falla
        }
        // Creamos el objeto con id=0 porque MySQL asigna el id real con AUTO_INCREMENT
        repository.guardar(new Alumno(0, nombre, carrera, semestre));
        System.out.println("Alumno registrado correctamente.");
    }

    /**
     * Obtiene la lista completa de alumnos desde la base de datos.
     *
     * @return ArrayList con todos los alumnos registrados
     */
    public ArrayList<Alumno> obtenerTodos() {
        return repository.obtenerTodos();
    }

    /**
     * Valida los nuevos datos y actualiza el registro del alumno con el ID indicado.
     *
     * @param id       id del alumno que se quiere actualizar
     * @param nombre   nuevo nombre (no puede estar vacio)
     * @param carrera  nueva carrera
     * @param semestre nuevo semestre
     */
    public void actualizarAlumno(int id, String nombre, String carrera, int semestre) {
        if (nombre.isEmpty()) {
            System.out.println("El nombre es obligatorio");
            return;
        }
        repository.actualizar(new Alumno(id, nombre, carrera, semestre));
    }

    /**
     * Elimina el alumno con el ID indicado.
     *
     * @param id identificador del alumno a eliminar
     */
    public void eliminarAlumno(int id) {
        repository.eliminar(id);
    }

    /**
     * Exporta todos los alumnos de la base de datos a un archivo llamado "alumnos.json".
     *
     * Construimos el JSON manualmente con StringBuilder porque no usamos librerias externas
     * (como Gson o Jackson). El archivo se genera en el directorio desde donde se ejecuta el programa.
     *
     * Formato del archivo resultante:
     * [
     *   {
     *     "id": 1,
     *     "nombre": "Juan Perez",
     *     "carrera": "Sistemas",
     *     "semestre": 8
     *   },
     *   ...
     * ]
     */
    public void exportarJSONArchivo() {
        ArrayList<Alumno> alumnos = repository.obtenerTodos();

        // StringBuilder es mas eficiente que concatenar Strings en un ciclo
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < alumnos.size(); i++) {
            Alumno alumno = alumnos.get(i);

            json.append("  {\n");

            json.append("    \"id\": ")
                .append(alumno.getId())
                .append(",\n");

            json.append("    \"nombre\": \"")
                .append(alumno.getNombre())
                .append("\",\n");

            json.append("    \"carrera\": \"")
                .append(alumno.getCarrera())
                .append("\",\n");

            json.append("    \"semestre\": ")
                .append(alumno.getSemestre())
                .append("\n");

            json.append("  }");

            // Solo ponemos coma si NO es el ultimo elemento del arreglo JSON
            if (i < alumnos.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        // Escribimos el resultado al archivo usando FileWriter
        try {
            FileWriter writer = new FileWriter("alumnos.json");
            writer.write(json.toString());
            writer.close();
            System.out.println("Archivo alumnos.json exportado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar el archivo: " + e.getMessage());
        }
    }
}
