package Abarrotes;

public class Clientes {

    private String nombreCliente;//nombreLibro
    private String direccion;//noPaginas
    private int telefono;//autor
    private String correoElectronico;//tipo

    public Clientes (String nombreCliente, String direccion,
                    int telefono, String correoElectronico) {
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }
    //Getters
    public String getNombreCliente() { return nombreCliente;}
    public String getDireccion() { return direccion;}
    public int getTelefono() { return telefono;}
    public String getCorreoElectronico() { return correoElectronico;}

    //Setters

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;}
    public void setDireccion(String direccion) {
        this.direccion = direccion;}
    public void setTelefono(int telefono) {
        this.telefono = telefono;}
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;}
                    
}
