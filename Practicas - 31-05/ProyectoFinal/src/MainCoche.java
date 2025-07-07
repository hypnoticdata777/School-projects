import javax.swing.JOptionPane;

public class MainCoche {
    public static void main(String[] args) {
        Coche c = new Coche();

        int rueda = Integer.parseInt(JOptionPane.showInputDialog("Número de ruedas:"));
        String color = JOptionPane.showInputDialog("Color del coche:");
        double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso en kg:"));
        int puertas = Integer.parseInt(JOptionPane.showInputDialog("Número de puertas:"));

        // Volante: Sí/No → boolean
        String volanteInput = JOptionPane.showInputDialog("¿Tiene volante? (sí/no):").toLowerCase();
        boolean volante = volanteInput.equals("sí") || volanteInput.equals("si");

        c.setRueda(rueda);
        c.setColor(color);
        c.setPeso(peso);
        c.setPuertas(puertas);
        c.setVolante(volante);

        JOptionPane.showMessageDialog(null, c.mostrarInformacion());
    }
}
