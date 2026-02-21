public class Coche {
    private int rueda;
    private String color;
    private double peso;
    private boolean volante;
    private int puertas;

    // Constructor vacío
    public Coche() {
        rueda = 4;
        color = "Azul";
        peso = 520.66;
        volante = true;
        puertas = 4;
    }

    // Setters
    public void setRueda(int rueda) {
        this.rueda = rueda;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setVolante(boolean volante) {
        this.volante = volante;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    // Método para mostrar información
    public String mostrarInformacion() {
        return "Ruedas: " + rueda +
               "\nColor: " + color +
               "\nPeso: " + peso + " kg" +
               "\n¿Tiene volante?: " + (volante ? "Sí" : "No") +
               "\nPuertas: " + puertas;
    }
}

