import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Capa de SERVICIO para la entidad Docente.
 *
 * Aqui van todas las validaciones y reglas de negocio del docente antes de
 * que los datos lleguen al repositorio. Esta capa hace que el controlador
 * no tenga que preocuparse por validar, y el repositorio no tenga que saber
 * si los datos son correctos o no.
 *
 * Reglas de negocio aplicadas:
 *  - El nombre del docente no puede estar vacio
 *  - La materia no puede estar vacia
 *  - El salario debe ser mayor a 0
 */
public class DocenteService {

    // El servicio depende del repositorio para persistir los datos
    private DocenteRepository repository;

    /**
     * Constructor con inyeccion de dependencias.
     * Recibir el repositorio por constructor (en lugar de crearlo adentro)
     * nos permite intercambiarlo facilmente si cambiamos de base de datos.
     *
     * @param repository instancia del repositorio de docentes
     */
    public DocenteService(DocenteRepository repository) {
        this.repository = repository;
    }

    /**
     * Valida los datos del docente y, si pasan todas las reglas, lo registra en BD.
     *
     * @param nombre       nombre completo del docente (obligatorio)
     * @param materia      materia que imparte (obligatoria)
     * @param departamento departamento al que pertenece (puede ir vacio)
     * @param salario      salario mensual (debe ser mayor a 0)
     */
    public void registrarDocente(String nombre, String materia, String departamento, double salario) {
        // Validacion 1: el nombre es el dato principal, no puede estar vacio
        if (nombre.isEmpty()) {
            System.out.println("El nombre del docente es obligatorio");
            return;
        }
        // Validacion 2: necesitamos saber que materia imparte
        if (materia.isEmpty()) {
            System.out.println("La materia es obligatoria");
            return;
        }
        // Validacion 3: un salario de 0 o negativo no tiene sentido
        if (salario <= 0) {
            System.out.println("El salario debe ser mayor a cero");
            return;
        }
        // Si todas las validaciones pasaron, guardamos con id=0 (MySQL lo asigna)
        repository.guardar(new Docente(0, nombre, materia, departamento, salario));
        System.out.println("Docente registrado correctamente.");
    }

    /**
     * Obtiene todos los docentes almacenados en la base de datos.
     *
     * @return ArrayList con todos los docentes; lista vacia si no hay ninguno
     */
    public ArrayList<Docente> obtenerTodos() {
        return repository.obtenerTodos();
    }

    /**
     * Valida los nuevos datos y actualiza el docente con el ID indicado.
     *
     * Se aplican las mismas reglas de negocio que al registrar para
     * mantener la consistencia de los datos en BD.
     *
     * @param id           id del docente a actualizar
     * @param nombre       nuevo nombre (obligatorio)
     * @param materia      nueva materia (obligatoria)
     * @param departamento nuevo departamento
     * @param salario      nuevo salario (debe ser mayor a 0)
     */
    public void actualizarDocente(int id, String nombre, String materia, String departamento, double salario) {
        if (nombre.isEmpty()) {
            System.out.println("El nombre del docente es obligatorio");
            return;
        }
        if (materia.isEmpty()) {
            System.out.println("La materia es obligatoria");
            return;
        }
        if (salario <= 0) {
            System.out.println("El salario debe ser mayor a cero");
            return;
        }
        repository.actualizar(new Docente(id, nombre, materia, departamento, salario));
    }

    /**
     * Elimina el docente con el ID indicado de la base de datos.
     *
     * @param id identificador del docente a eliminar
     */
    public void eliminarDocente(int id) {
        repository.eliminar(id);
    }

    /**
     * Exporta todos los docentes de la base de datos a un archivo "docentes.json".
     *
     * El JSON se construye manualmente con StringBuilder (sin librerias externas).
     * El archivo se crea en el directorio de ejecucion del programa.
     *
     * Formato del archivo resultante:
     * [
     *   {
     *     "id": 1,
     *     "nombre": "Maria Lopez",
     *     "materia": "Programacion",
     *     "departamento": "Informatica",
     *     "salario": 15000.0
     *   },
     *   ...
     * ]
     */
    public void exportarJSONArchivo() {
        ArrayList<Docente> docentes = repository.obtenerTodos();

        // StringBuilder evita crear multiples objetos String en el ciclo (mas eficiente)
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < docentes.size(); i++) {
            Docente docente = docentes.get(i);

            json.append("  {\n");

            json.append("    \"id\": ")
                .append(docente.getId())
                .append(",\n");

            json.append("    \"nombre\": \"")
                .append(docente.getNombre())
                .append("\",\n");

            json.append("    \"materia\": \"")
                .append(docente.getMateria())
                .append("\",\n");

            json.append("    \"departamento\": \"")
                .append(docente.getDepartamento())
                .append("\",\n");

            // El salario no lleva comillas porque es un numero en JSON (no un string)
            json.append("    \"salario\": ")
                .append(docente.getSalario())
                .append("\n");

            json.append("  }");

            // La coma solo va si hay mas elementos despues de este
            if (i < docentes.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        // Escribimos el contenido al archivo docentes.json
        try {
            FileWriter writer = new FileWriter("docentes.json");
            writer.write(json.toString());
            writer.close();
            System.out.println("Archivo docentes.json exportado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar el archivo: " + e.getMessage());
        }
    }
}
