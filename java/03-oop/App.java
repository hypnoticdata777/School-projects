public class App {
    public static void main(String[] args) {
        Barco miBarco = new Barco();
        miBarco.setNombre("Titanic");
        miBarco.setTipo("Pasajero");
        miBarco.setLongitud(269.0);
        miBarco.setAnioFabricacion(1912);

        miBarco.mostrarInformacion();
    }
}
