import java.util.Scanner;

public class EntradaDatos {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("¿Cuál es tu nombre?: ");
        String nombre = entrada.nextLine();

        System.out.print("¿Cuántos años tienes?: ");
        int edad = entrada.nextInt();

        System.out.print("¿Cuál es tu estatura (en metros)?: ");
        double estatura = entrada.nextDouble();

        System.out.println("\n--- Datos Ingresados ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Estatura: " + estatura + " m");

        entrada.close();
    }
}

