public class SistemaBanco {

    public void transferir(usuario origen, usuario destino, double monto) {
        System.out.println("\nIniciando transferencia...");

        if (monto <= 0) {
            System.out.println("Monto invalido");
            origen.agregarMovimiento("Transferencia rechazada por monto invalido: $" + monto);
            return;
        }

        if (origen.retirar(monto)) {
            destino.depositar(monto);

            origen.agregarMovimiento("Transferencia enviada a " + destino.getNombre() + ": $" + monto);
            destino.agregarMovimiento("Transferencia recibida de " + origen.getNombre() + ": $" + monto);

            System.out.println("Transferencia realizada");
            System.out.println(origen.getNombre() + " transfirio $" + monto + " a " + destino.getNombre());
        } else {
            System.out.println("No se pudo realizar");
            origen.agregarMovimiento("Transferencia fallida a " + destino.getNombre() + ": $" + monto);
        }
    }
}
