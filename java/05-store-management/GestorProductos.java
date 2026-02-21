package Abarrotes;

import java.util.Vector;

public class GestorProductos {
    // Vector que almacena todos los productos
    private Vector<Producto> productos = new Vector<>();

    // Método para agregar un nuevo producto a la lista
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // Método para eliminar un producto según su índice en el Vector
    public void eliminarProducto(int index) {
        productos.remove(index);
    }

    // Método para editar todas las propiedades de un producto existente
    // Recibe el índice del producto y los nuevos valores para sus atributos
    public void editarProductoCompleto(int index, String nombre, double precio, int stock, String marca, String sabor) {
        Producto p = productos.get(index);  // Obtiene el producto a editar
        p.setNombre(nombre);               // Actualiza el nombre
        p.setPrecio(precio);               // Actualiza el precio
        p.setStock(stock);                 // Actualiza el stock
        p.setMarca(marca);                 // Actualiza la marca
        p.setSabor(sabor);                // Actualiza el sabor
    }

    // Método para eliminar todos los productos (limpia el Vector)
    public void eliminarTodos() {
        productos.clear();
    }

    // Método que devuelve el Vector con todos los productos
    public Vector<Producto> getProductos() {
        return productos;
    }
}

