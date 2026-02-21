package Abarrotes;

// Importa clases necesarias para la interfaz gráfica y la tabla
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Main extends JFrame {  // Clase principal que extiende de JFrame para crear la ventana
    private GestorProductos gestor = new GestorProductos(); // Instancia del gestor de productos (maneja la lógica)
    private DefaultTableModel modelo; // Modelo para la tabla de productos
    private JTable tabla; // Tabla donde se mostrarán los productos
    private DefaultTableModel modeloClientes;  // Modelo para la tabla de clientes
private JTable tablaClientes;              // Tabla donde se mostrarán los clientes


    public Main() { // Constructor de la clase Main
        setTitle("Tienda Abarrotes"); // Título de la ventana
        setSize(900, 950); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Finaliza el programa al cerrar
        setResizable(false); // No permite redimensionar la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        Image icono = Toolkit.getDefaultToolkit().getImage("src/Abarrotes/tienda.png"); // Carga un ícono personalizado
        setIconImage(icono); // Establece el ícono de la ventana
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Establece el layout principal como BorderLayout

        // Se define el modelo de la tabla con columnas fijas
        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio", "Stock", "Marca", "Sabor"}, 0);
        tabla = new JTable(modelo); // Se crea la tabla con ese modelo
        tabla.setPreferredScrollableViewportSize(new Dimension(700, 300)); // Tamaño preferido
        tabla.getTableHeader().setReorderingAllowed(false); // No permite mover las columnas
        JScrollPane scrollPane = new JScrollPane(tabla); // Se pone la tabla dentro de un scroll

        // Panel superior con los campos de entrada
        JPanel panelCampos = new JPanel();
        JTextField txtNombre = new JTextField(10); // Campo de texto para el nombre
        JTextField txtPrecio = new JTextField(5); // Campo de texto para el precio
        JTextField txtStock = new JTextField(5); // Campo de texto para el stock
        JTextField txtMarca = new JTextField(10); // Campo de texto para la marca
        JTextField txtSabor = new JTextField(10); // Campo de texto para el sabor

        // Agrega los labels y campos al panel
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Precio:"));
        panelCampos.add(txtPrecio);
        panelCampos.add(new JLabel("Stock:"));
        panelCampos.add(txtStock);
        panelCampos.add(new JLabel("Marca:"));
        panelCampos.add(txtMarca);
        panelCampos.add(new JLabel("Sabor:"));
        panelCampos.add(txtSabor);


        //Panel captura clientes
        JPanel panelClientes = new JPanel();
        JTextField nomCliente = new JTextField(10);
        JTextField dirCliente = new JTextField(20);
        JTextField telCliente = new JTextField(10);
        JTextField correoCliente = new JTextField(20);

        //Agregar campos de cliente al panel
        panelClientes.add(new JLabel("Nombre cliente:"));
        panelClientes.add(nomCliente);
        panelClientes.add(new JLabel("Dirección:"));
        panelClientes.add(dirCliente);
        panelClientes.add(new JLabel("Teléfono:"));
        panelClientes.add(telCliente);
        panelClientes.add(new JLabel("Correo electrónico:"));
        panelClientes.add(correoCliente);
        // ===== Modelo y tabla de clientes =====
        modeloClientes = new DefaultTableModel(new String[]{"ID", "Nombre", "Dirección", "Teléfono", "Correo"}, 0);
        tablaClientes = new JTable(modeloClientes);
        tablaClientes.setPreferredScrollableViewportSize(new Dimension(700, 200));
        tablaClientes.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollClientes = new JScrollPane(tablaClientes);
        // ===== Botones de cliente =====
        JPanel panelBotonesClientes = new JPanel();
        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        JButton btnModificarCliente = new JButton("Modificar Cliente");
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        panelBotonesClientes.add(btnAgregarCliente);
        panelBotonesClientes.add(btnModificarCliente);
        panelBotonesClientes.add(btnEliminarCliente);


        // Panel inferior con los botones de acción
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.cyan);
        panelBotones.setOpaque(true);
        JButton botonAgregar =  new JButton("Agregar Producto"); // Botón para agregar
        JButton botonEliminar = new JButton("Eliminar Producto"); // Botón para eliminar
        JButton botonEditar = new JButton("Editar Producto"); // Botón para editar
        JButton botonEliminarTodo = new JButton("Eliminar Todo"); // Botón para eliminar todos

        // Agrega los botones al panel
        panelBotones.add(botonAgregar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonEliminarTodo);

        // Añade los paneles a la ventana principal
        add(panelCampos);
        add(panelBotones);
        add(scrollPane);

        add(panelClientes);
        add(panelBotonesClientes);
        add(scrollClientes);


        // Evento que cuando se selecciona un registro lo muestra en los campos de texto
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                // Llena los campos con los valores de la fila seleccionada
                txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                txtPrecio.setText(modelo.getValueAt(fila, 2).toString());
                txtStock.setText(modelo.getValueAt(fila, 3).toString());
                txtMarca.setText(modelo.getValueAt(fila, 4).toString());
                txtSabor.setText(modelo.getValueAt(fila, 5).toString());
            }
        });

        // Acción para agregar producto
        botonAgregar.addActionListener(e -> {
            try {
                // Lee los datos ingresados
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                int stock = Integer.parseInt(txtStock.getText());
                String marca = txtMarca.getText();
                String sabor = txtSabor.getText();

                // Valida que no estén vacíos
                if (nombre.isEmpty() || marca.isEmpty() || sabor.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.");
                    return;
                }
                if (precio < 0 ){
                    JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.");
                }

                // Crea el producto y lo añade a la lista y tabla
                Producto producto = new Producto(nombre, precio, stock, marca, sabor);
                gestor.agregarProducto(producto);
                modelo.addRow(new Object[]{
                    producto.getId(), nombre, precio, stock, marca, sabor
                });

                // Limpia los campos de texto
                txtNombre.setText("");
                txtPrecio.setText("");
                txtStock.setText("");
                txtMarca.setText("");
                txtSabor.setText("");
            } catch (Exception ex) {
                // Si hay un error en los datos, muestra un mensaje
                JOptionPane.showMessageDialog(this, "Error al agregar producto. Revisa los datos.");
            }
        });

        // Acción para editar un producto existente
        botonEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                try {
                    // Obtiene los nuevos datos
                    String nuevoNombre = txtNombre.getText();
                    double nuevoPrecio = Double.parseDouble(txtPrecio.getText());
                    int nuevoStock = Integer.parseInt(txtStock.getText());
                    String nuevaMarca = txtMarca.getText();
                    String nuevoSabor = txtSabor.getText();

                    // Valida campos
                    if (nuevoNombre.isEmpty() || nuevaMarca.isEmpty() || nuevoSabor.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.");
                        return;
                    }

                    // Modifica el producto seleccionado
                    Producto p = gestor.getProductos().get(fila);
                    p.setNombre(nuevoNombre);
                    p.setPrecio(nuevoPrecio);
                    p.setStock(nuevoStock);
                    p.setMarca(nuevaMarca);
                    p.setSabor(nuevoSabor);

                    // Actualiza los valores en la tabla
                    modelo.setValueAt(nuevoNombre, fila, 1);
                    modelo.setValueAt(nuevoPrecio, fila, 2);
                    modelo.setValueAt(nuevoStock, fila, 3);
                    modelo.setValueAt(nuevaMarca, fila, 4);
                    modelo.setValueAt(nuevoSabor, fila, 5);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al editar producto. Verifica los datos.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para editar.");
            }
        });

        // Acción para eliminar un producto seleccionado
        botonEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar producto seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Elimina de la lista y de la tabla
                    gestor.eliminarProducto(fila);
                    modelo.removeRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
            }
        });

        // Acción para eliminar todos los productos
        botonEliminarTodo.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar todos los productos?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Limpia la lista del gestor y la tabla
                gestor.getProductos().clear();
                modelo.setRowCount(0);
            }
        });
        // Evento que cuando se selecciona un cliente lo muestra en los campos de texto
tablaClientes.getSelectionModel().addListSelectionListener(e -> {
    int fila = tablaClientes.getSelectedRow();
    if (fila >= 0) {
        nomCliente.setText(modeloClientes.getValueAt(fila, 1).toString());
        dirCliente.setText(modeloClientes.getValueAt(fila, 2).toString());
        telCliente.setText(modeloClientes.getValueAt(fila, 3).toString());
        correoCliente.setText(modeloClientes.getValueAt(fila, 4).toString());
    }
});

// Acción para agregar cliente
btnAgregarCliente.addActionListener(e -> {
    String nombre = nomCliente.getText();
    String direccion = dirCliente.getText();
    String telefono = telCliente.getText();
    String correo = correoCliente.getText();

    if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.");
        return;
    }

    int id = modeloClientes.getRowCount() + 1;
    modeloClientes.addRow(new Object[]{id, nombre, direccion, telefono, correo});

    // Limpiar campos
    nomCliente.setText("");
    dirCliente.setText("");
    telCliente.setText("");
    correoCliente.setText("");
});

// Acción para modificar cliente
btnModificarCliente.addActionListener(e -> {
    int fila = tablaClientes.getSelectedRow();
    if (fila >= 0) {
        String nombre = nomCliente.getText();
        String direccion = dirCliente.getText();
        String telefono = telCliente.getText();
        String correo = correoCliente.getText();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.");
            return;
        }

        modeloClientes.setValueAt(nombre, fila, 1);
        modeloClientes.setValueAt(direccion, fila, 2);
        modeloClientes.setValueAt(telefono, fila, 3);
        modeloClientes.setValueAt(correo, fila, 4);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un cliente para modificar.");
    }
});

// Acción para eliminar cliente
btnEliminarCliente.addActionListener(e -> {
    int fila = tablaClientes.getSelectedRow();
    if (fila >= 0) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar cliente seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            modeloClientes.removeRow(fila);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar.");
    }
});

    }
    public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
    new Main().setVisible(true);
JOptionPane.showMessageDialog(null, "¡Sistema cargado exitosamente!");





});

    }

}
  
    

