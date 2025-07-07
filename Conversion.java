public class Conversion {
    public static void main(String[] args) {
        double precioOriginal = 199.99;
        int precioEntero = (int) precioOriginal; // conversión explícita

        int edad = 31;
        double edadDecimal = edad; // conversión implícita

        System.out.println("Precio original (double): " + precioOriginal);
        System.out.println("Precio entero (int): " + precioEntero);
        System.out.println("Edad (int): " + edad);
        System.out.println("Edad como decimal (double): " + edadDecimal);
    }
}

