import javax.swing.JOptionPane;

public class Calculadora {
    public static void main(String[] args) {
        int opcion = Integer.parseInt(
            JOptionPane.showInputDialog(null, "Ingresa la opción al switch:")
        );

        switch (opcion) {
            case 1:
                JOptionPane.showMessageDialog(
                    null,
                    "BINGO!!!",
                    "FELICIDADES",
                    JOptionPane.WARNING_MESSAGE
                );
                break;

            case 2:
                JOptionPane.showMessageDialog(
                    null,
                    "Has elegido la opción 2",
                    "INFO",
                    JOptionPane.INFORMATION_MESSAGE
                );
                break;

            default:
                JOptionPane.showMessageDialog(
                    null,
                    "Opción no válida",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
                );
                break;
        }
    }
}

    

