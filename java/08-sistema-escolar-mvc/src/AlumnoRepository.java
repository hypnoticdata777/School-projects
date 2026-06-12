import java.sql.*;
import java.util.ArrayList;

/**
 * Capa de REPOSITORIO para la entidad Alumno.
 *
 * Esta clase es la unica que habla directamente con la base de datos.
 * Sigue el patron Repository: centraliza todas las consultas SQL en un solo lugar
 * para que el resto de las capas no tengan que preocuparse por como se guardan los datos.
 *
 * Usamos PreparedStatement en lugar de Statement normal porque:
 *  - Previene inyeccion SQL (los ? se escapan automaticamente)
 *  - Es mas eficiente cuando la consulta se ejecuta varias veces
 */
public class AlumnoRepository {

    /**
     * Inserta un nuevo alumno en la tabla "alumno" de la base de datos.
     *
     * El id del objeto puede ser 0 porque MySQL lo genera automaticamente
     * con AUTO_INCREMENT al hacer el INSERT.
     *
     * @param alumno objeto Alumno con los datos a guardar (el id se ignora)
     */
    public void guardar(Alumno alumno) {
        // Los ? son marcadores de posicion, se reemplazan de forma segura con setString/setInt
        String sql = "INSERT INTO alumno (nombre, carrera, semestre) VALUES (?, ?, ?)";

        // try-with-resources: cierra automaticamente la conexion y el PreparedStatement al terminar
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Asignamos cada valor al ? correspondiente por posicion (empieza en 1, no en 0)
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getCarrera());
            ps.setInt(3, alumno.getSemestre());

            ps.executeUpdate(); // ejecuta el INSERT en la BD
        } catch (Exception e) {
            System.out.println("Error al guardar alumno: " + e.getMessage());
        }
    }

    /**
     * Consulta y regresa todos los alumnos almacenados en la base de datos.
     *
     * Recorremos el ResultSet fila por fila y construimos un objeto Alumno
     * por cada registro para devolverlos en una lista.
     *
     * @return ArrayList con todos los alumnos; si no hay registros, regresa lista vacia
     */
    public ArrayList<Alumno> obtenerTodos() {
        ArrayList<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumno";

        // Usamos Statement simple porque esta consulta no lleva parametros externos
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // rs.next() avanza al siguiente registro; regresa false cuando ya no hay mas
            while (rs.next()) {
                lista.add(new Alumno(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("carrera"),
                    rs.getInt("semestre")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener alumnos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza los datos de un alumno existente usando su ID como clave de busqueda.
     *
     * executeUpdate() regresa el numero de filas afectadas; si es 0 significa que
     * no existe ningun alumno con ese ID en la base de datos.
     *
     * @param alumno objeto Alumno con el id del registro a modificar y los nuevos valores
     */
    public void actualizar(Alumno alumno) {
        String sql = "UPDATE alumno SET nombre=?, carrera=?, semestre=? WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getCarrera());
            ps.setInt(3, alumno.getSemestre());
            ps.setInt(4, alumno.getId()); // el id va al final porque es el filtro del WHERE

            int filas = ps.executeUpdate();
            // Operador ternario para mostrar el resultado sin un if-else completo
            System.out.println(filas > 0 ? "Alumno actualizado." : "No se encontro el ID.");
        } catch (Exception e) {
            System.out.println("Error al actualizar alumno: " + e.getMessage());
        }
    }

    /**
     * Elimina el registro de un alumno de la base de datos segun su ID.
     *
     * @param id identificador del alumno que se quiere borrar
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM alumno WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Alumno eliminado." : "No se encontro el ID.");
        } catch (Exception e) {
            System.out.println("Error al eliminar alumno: " + e.getMessage());
        }
    }
}
