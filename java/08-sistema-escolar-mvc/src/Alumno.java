/**
 * Clase modelo que representa a un Alumno dentro del sistema escolar.
 *
 * En la arquitectura por capas que usamos (Modelo - Repositorio - Servicio - Controlador),
 * esta clase es el MODELO: solo guarda datos, no tiene logica de negocio ni acceso a BD.
 *
 * Usamos encapsulamiento (atributos privados + getters) para proteger los datos
 * y seguir las buenas practicas de POO.
 */
public class Alumno {

    // Atributos privados del alumno
    // El id lo asigna automaticamente la base de datos (AUTO_INCREMENT), por eso
    // cuando creamos uno nuevo le ponemos 0 y MySQL le da el valor real.
    private int id;
    private String nombre;
    private String carrera;
    private int semestre;

    /**
     * Constructor principal. Se usa tanto para crear objetos nuevos (id=0)
     * como para los que ya vienen de la base de datos (id real).
     *
     * @param id        Identificador unico del alumno (0 si es nuevo, >0 si ya existe en BD)
     * @param nombre    Nombre completo del alumno
     * @param carrera   Carrera que estudia el alumno
     * @param semestre  Semestre actual que cursa el alumno
     */
    public Alumno(int id, String nombre, String carrera, int semestre) {
        this.id       = id;
        this.nombre   = nombre;
        this.carrera  = carrera;
        this.semestre = semestre;
    }

    // -------------------------------------------------------------------------
    // Getters - solo de lectura porque no necesitamos modificar los atributos
    // desde afuera despues de crear el objeto
    // -------------------------------------------------------------------------

    /** Regresa el ID del alumno tal como esta en la base de datos */
    public int getId()         { return id; }

    /** Regresa el nombre completo del alumno */
    public String getNombre()  { return nombre; }

    /** Regresa la carrera que estudia el alumno */
    public String getCarrera() { return carrera; }

    /** Regresa el semestre actual del alumno */
    public int getSemestre()   { return semestre; }

    /**
     * Representacion en texto del objeto Alumno.
     * Se usa cuando hacemos System.out.println(alumno) en el menu principal.
     *
     * @return String con todos los datos del alumno formateados
     */
    @Override
    public String toString() {
        return "ID: " + id +
               " | Nombre: "   + nombre +
               " | Carrera: "  + carrera +
               " | Semestre: " + semestre;
    }
}
