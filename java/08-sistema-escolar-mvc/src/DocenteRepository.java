import java.sql.*;
import java.util.ArrayList;

/**
 * Capa de REPOSITORIO para la entidad Docente.
 *
 * Igual que AlumnoRepository, esta clase es la unica que accede directamente
 * a la tabla "docente" en MySQL. Todas las consultas SQL del docente viven aqui.
 *
 * El uso de PreparedStatement es obligatorio cuando los valores vienen del usuario
 * para evitar ataques de SQL Injection.
 */
public class DocenteRepository {

    /**
     * Inserta un nuevo docente en la tabla "docente" de la base de datos.
     *
     * No insertamos el id porque la columna tiene AUTO_INCREMENT en MySQL
     * y se genera sola con cada INSERT.
     *
     * @param docente objeto Docente con los datos a guardar
     */
    public void guardar(Docente docente) {
        String sql = "INSERT INTO docente (nombre, materia, departamento, salario) VALUES (?, ?, ?, ?)";

        // try-with-resources garantiza que la conexion se cierre aunque ocurra una excepcion
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Posicion 1, 2, 3, 4 corresponden a los cuatro ? del INSERT
            ps.setString(1, docente.getNombre());
            ps.setString(2, docente.getMateria());
            ps.setString(3, docente.getDepartamento());
            ps.setDouble(4, docente.getSalario());

            ps.executeUpdate(); // ejecuta el INSERT y confirma en BD
        } catch (Exception e) {
            System.out.println("Error al guardar docente: " + e.getMessage());
        }
    }

    /**
     * Regresa una lista con todos los docentes que hay en la base de datos.
     *
     * Se recorre el ResultSet fila por fila y se construye un objeto Docente
     * por cada registro encontrado.
     *
     * @return ArrayList de Docente; vacia si no hay registros en la tabla
     */
    public ArrayList<Docente> obtenerTodos() {
        ArrayList<Docente> lista = new ArrayList<>();
        String sql = "SELECT * FROM docente";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // Cada llamada a rs.next() posiciona el cursor en la siguiente fila
            while (rs.next()) {
                lista.add(new Docente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("materia"),
                    rs.getString("departamento"),
                    rs.getDouble("salario")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener docentes: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza todos los campos de un docente existente buscandolo por su ID.
     *
     * Si el ID no existe en la BD, executeUpdate() regresa 0 filas afectadas
     * y se muestra un mensaje al usuario.
     *
     * @param docente objeto Docente con el id del registro a editar y los nuevos valores
     */
    public void actualizar(Docente docente) {
        String sql = "UPDATE docente SET nombre=?, materia=?, departamento=?, salario=? WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, docente.getNombre());
            ps.setString(2, docente.getMateria());
            ps.setString(3, docente.getDepartamento());
            ps.setDouble(4, docente.getSalario());
            ps.setInt(5, docente.getId()); // el id es el filtro del WHERE, va al final

            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Docente actualizado." : "No se encontro el ID.");
        } catch (Exception e) {
            System.out.println("Error al actualizar docente: " + e.getMessage());
        }
    }

    /**
     * Elimina permanentemente el registro de un docente segun su ID.
     *
     * @param id identificador del docente que se desea eliminar de la BD
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM docente WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Docente eliminado." : "No se encontro el ID.");
        } catch (Exception e) {
            System.out.println("Error al eliminar docente: " + e.getMessage());
        }
    }
}
