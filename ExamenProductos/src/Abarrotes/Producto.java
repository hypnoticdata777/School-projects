package Abarrotes;

public class Producto {
    // Contador estático para generar IDs autoincrementables (compartido por todas las instancias)
    private static int contadorId = 1;
    
    // Atributos de un producto
    private int id;           // Identificador único del producto
    private String nombre;    // Nombre del producto
    private double precio;    // Precio del producto
    private int stock;        // Cantidad disponible en inventario
    private String marca;     // Marca del producto
    private String sabor;     // Sabor del producto (si aplica)

    // Constructor que inicializa un nuevo producto con sus propiedades básicas
    public Producto(String nombre, double precio, int stock, String marca, String sabor) {
        this.id = contadorId++;  // Asigna un ID único y autoincrementa el contador
        this.nombre = nombre;    // Establece el nombre
        this.precio = precio;    // Establece el precio
        this.stock = stock;      // Establece el stock inicial
        this.marca = marca;      // Establece la marca
        this.sabor = sabor;      // Establece el sabor
    }


    // Métodos GETTERS (para obtener los valores de los atributos)
    public int getId() { return id; }                   // Devuelve el ID
    public String getNombre() { return nombre; }        // Devuelve el nombre
    public double getPrecio() { return precio; }        // Devuelve el precio
    public int getStock() { return stock; }             // Devuelve el stock
    public String getMarca() { return marca; }          // Devuelve la marca
    public String getSabor() { return sabor; }          // Devuelve el sabor

    // Métodos SETTERS (para modificar los valores de los atributos)
    public void setNombre(String nombre) { this.nombre = nombre; }       // Actualiza el nombre
    public void setPrecio(double precio) { this.precio = precio; }      // Actualiza el precio
    public void setStock(int stock) { this.stock = stock; }              // Actualiza el stock
    public void setMarca(String marca) { this.marca = marca; }           // Actualiza la marca
    public void setSabor(String sabor) { this.sabor = sabor; }           // Actualiza el sabor
}