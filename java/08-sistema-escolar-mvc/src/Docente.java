/**
 * Clase modelo que representa a un Docente dentro del sistema escolar.
 *
 * Al igual que Alumno, esta clase forma parte de la capa MODELO en la arquitectura
 * por capas. Solo tiene atributos y getters, sin logica ni conexion a BD.
 *
 * El salario se maneja como double porque puede tener centavos (ej. 15000.50).
 */
public class Docente {

    // Atributos privados del docente
    // id = 0 cuando es nuevo (la BD lo asigna con AUTO_INCREMENT)
    private int id;
    private String nombre;
    private String materia;
    private String departamento;
    private double salario;

    /**
     * Constructor del docente. Se usa para crear objetos nuevos y tambien
     * para mapear los registros que regresan de la base de datos.
     *
     * @param id            Identificador unico (0 si es nuevo, real si viene de BD)
     * @param nombre        Nombre completo del docente
     * @param materia       Materia que imparte el docente
     * @param departamento  Departamento al que pertenece el docente
     * @param salario       Salario mensual del docente (debe ser mayor a 0)
     */
    public Docente(int id, String nombre, String materia, String departamento, double salario) {
        this.id           = id;
        this.nombre       = nombre;
        this.materia      = materia;
        this.departamento = departamento;
        this.salario      = salario;
    }

    // -------------------------------------------------------------------------
    // Getters - acceso de solo lectura a los atributos del docente
    // -------------------------------------------------------------------------

    /** Regresa el ID del docente en la base de datos */
    public int getId()              { return id; }

    /** Regresa el nombre completo del docente */
    public String getNombre()       { return nombre; }

    /** Regresa la materia que imparte */
    public String getMateria()      { return materia; }

    /** Regresa el departamento al que pertenece */
    public String getDepartamento() { return departamento; }

    /** Regresa el salario mensual del docente */
    public double getSalario()      { return salario; }

    /**
     * Representacion en texto del objeto Docente.
     * Se invoca automaticamente cuando hacemos System.out.println(docente).
     *
     * @return String con todos los datos del docente formateados para consola
     */
    @Override
    public String toString() {
        return "ID: " + id +
               " | Nombre: "       + nombre +
               " | Materia: "      + materia +
               " | Departamento: " + departamento +
               " | Salario: $"     + salario;
    }
}
