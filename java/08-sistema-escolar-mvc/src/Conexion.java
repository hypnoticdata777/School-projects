import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase utilitaria para gestionar la conexion a la base de datos MySQL.
 *
 * Usamos el patron de metodo estatico para no tener que crear instancias de esta clase.
 * Cada repositorio llama a Conexion.conectar() cuando necesita ejecutar una consulta.
 *
 * IMPORTANTE: Para que funcione, XAMPP debe estar encendido con el servicio MySQL activo,
 * y la base de datos "sistema_escolar" debe existir (ver database.sql).
 *
 * Usamos JDBC (Java Database Connectivity) con el driver de MySQL que esta en la
 * carpeta lib/mysql-connector-j-9.5.0.jar
 */
public class Conexion {

    // Cadena de conexion JDBC: protocolo + host + puerto + nombre de la BD
    private static final String URL      = "jdbc:mysql://localhost:3306/sistema_escolar";

    // Usuario de MySQL (por defecto en XAMPP es root sin contrasena)
    private static final String USER     = "root";
    private static final String PASSWORD = "";

    /**
     * Abre y regresa una conexion activa a la base de datos.
     *
     * Los repositorios usan esta conexion dentro de un try-with-resources para
     * que se cierre automaticamente al terminar, evitando fugas de recursos.
     *
     * @return objeto Connection listo para usar, o null si fallo la conexion
     */
    public static Connection conectar() {
        try {
            // DriverManager busca el driver apropiado segun la URL y regresa la conexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}
