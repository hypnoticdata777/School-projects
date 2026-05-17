public class App {

    public static void main(String[] args) {

        usuario admin = new usuario(
                "Daniel",
                "1234",
                "ADMIN",
                10000
        );

        usuario cliente = new usuario(
                "Carlos",
                "abcd",
                "CLIENTE",
                3000
        );

        SistemaLogin login = new SistemaLogin();

        // CONFIDENCIALIDAD
        boolean acceso = login.log(admin, "1234");

        if(acceso) {

            admin.mostrarInformacion();

            // CONTROL DE ACCESO
            if(admin.esAdmin()) {

                System.out.println(
                        "\nAcceso administrador concedido"
                );
            }
        }

        SistemaBanco banco = new SistemaBanco();

        // INTEGRIDAD
        banco.transferir(admin, cliente, 1500);

        System.out.println("\n=== ESTADO FINAL ===");

        admin.mostrarInformacion();

        System.out.println();

        cliente.mostrarInformacion();
    }
}
