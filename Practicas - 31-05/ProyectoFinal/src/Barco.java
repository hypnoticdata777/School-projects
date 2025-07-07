public class Barco {
    private String nombre;
    private String tipo;
    private double longitud;
    private int anioFabricacion;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre del barco: " + nombre);
        System.out.println("Tipo: " + tipo);
        System.out.println("Longitud: " + longitud + " metros");
        System.out.println("Año de fabricación: " + anioFabricacion);
    }
}


