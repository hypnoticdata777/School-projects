import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el primer número:");
        double num1 = scanner.nextDouble();

        System.out.println("Ingrese el segundo número:");
        double num2 = scanner.nextDouble();

        System.out.println("\nSeleccione una operación:");
        System.out.println("+ para Suma");
        System.out.println("- para Resta");
        System.out.println("* para Multiplicación");
        System.out.println("/ para División");
        System.out.println("% para Residuo");

        char operacion = scanner.next().charAt(0); // ← CORRECTO aquí

        switch (operacion) {
            case '+':
                System.out.println("Resultado: " + (num1 + num2));
                break;
            case '-':
                System.out.println("Resultado: " + (num1 - num2));
                break;
            case '*':
                System.out.println("Resultado: " + (num1 * num2));
                break;
            case '/':
                if (num2 != 0) {
                    System.out.println("Resultado: " + (num1 / num2));
                } else {
                    System.out.println("Error: división entre cero.");
                }
                break;
            case '%':
                if (num2 != 0) {
                    System.out.println("Resultado: " + (num1 % num2));
                } else {
                    System.out.println("Error: no se puede sacar residuo de cero.");
                }
                break;
            default:
                System.out.println("Operación no válida.");
        }

        scanner.close(); // ← importante cerrar el scanner
    }
}
