import java.util.ArrayList;
import javax.swing.*;

public class AgendaContactos {
    public static void main(String[] args) {
        ArrayList<Contacto> agenda = new ArrayList<>();
        int opcion;

        do {
            String menu = """
                --- MENÚ ---
                1. Agregar contacto
                2. Mostrar contactos
                3. Editar contacto
                4. Eliminar contacto
                5. Salir
                """;

            String input = JOptionPane.showInputDialog(null, menu + "\nElige una opción:");
            if (input == null) break;  // Usuario canceló

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String tel = JOptionPane.showInputDialog("Teléfono:");
                    String correo = JOptionPane.showInputDialog("Correo:");
                    if (nombre != null && tel != null && correo != null) {
                        agenda.add(new Contacto(nombre, tel, correo));
                        JOptionPane.showMessageDialog(null, "Contacto agregado.");
                    }
                }
                case 2 -> {
                    if (agenda.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay contactos.");
                    } else {
                        StringBuilder lista = new StringBuilder();
                        for (Contacto c : agenda) {
                            lista.append(c.mostrar()).append("\n----------------------\n");
                        }
                        JOptionPane.showMessageDialog(null, lista.toString());
                    }
                }
                case 3 -> {
                    String nombreEditar = JOptionPane.showInputDialog("Nombre del contacto a editar:");
                    boolean encontrado = false;
                    for (Contacto c : agenda) {
                        if (c.getNombre().equalsIgnoreCase(nombreEditar)) {
                            c.setNombre(JOptionPane.showInputDialog("Nuevo nombre:", c.getNombre()));
                            c.setTelefono(JOptionPane.showInputDialog("Nuevo teléfono:", c.getTelefono()));
                            c.setCorreo(JOptionPane.showInputDialog("Nuevo correo:", c.getCorreo()));
                            JOptionPane.showMessageDialog(null, "Contacto actualizado.");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        JOptionPane.showMessageDialog(null, "Contacto no encontrado.");
                    }
                }
                case 4 -> {
                    String nombreEliminar = JOptionPane.showInputDialog("Nombre del contacto a eliminar:");
                    boolean eliminado = false;
                    for (int i = 0; i < agenda.size(); i++) {
                        if (agenda.get(i).getNombre().equalsIgnoreCase(nombreEliminar)) {
                            agenda.remove(i);
                            JOptionPane.showMessageDialog(null, "Contacto eliminado.");
                            eliminado = true;
                            break;
                        }
                    }
                    if (!eliminado) {
                        JOptionPane.showMessageDialog(null, "Contacto no encontrado.");}
                    }
    case 5 -> {
        JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
        break;
    }
        default -> {
            JOptionPane.showMessageDialog(null, "Opción inválida.");
        }
    }
            } while (true);
        }
    }

    

