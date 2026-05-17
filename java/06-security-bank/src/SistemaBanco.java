public class SistemaBanco {

    public void transferir(usuario origen,
                           usuario destino,
                           double monto) {

        System.out.println("\nIniciando transferencia...");

        // VALIDACIÓN DE INTEGRIDAD
        if(monto <= 0) {

            System.out.println("Monto inválido");
            return;
        }

        if(origen.retirar(monto)) {

            destino.depositar(monto);

            System.out.println("Transferencia realizada");

            System.out.println(
                    origen.getNombre()
                    + " transfirió $"
                    + monto
                    + " a "
                    + destino.getNombre()
            );

        } else {

            System.out.println("No se pudo realizar");
        }
    }
}
