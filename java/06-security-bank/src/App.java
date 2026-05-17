public class App {

    public static void main(String[] args) {
        usuario admin = new usuario(
                "Daniel",
                "Admin123!",
                "ADMIN",
                10000,
                2000
        );

        usuario cliente = new usuario(
                "Carlos",
                "Carlos123!",
                "CLIENTE",
                3000,
                1500
        );

        usuario vio = new usuario(
                "VIO",
                "Vio2026!",
                "CLIENTE",
                5000,
                1000
        );

        SistemaLogin login = new SistemaLogin();
        SistemaBanco banco = new SistemaBanco();

        mostrarTitulo("VALIDACION DE CONTRASENA SEGURA");
        System.out.println("1234 es segura? " + (usuario.esContrasenaSegura("1234") ? "Si" : "No"));
        System.out.println("Admin123! es segura? " + (usuario.esContrasenaSegura("Admin123!") ? "Si" : "No"));

        mostrarTitulo("BLOQUEO TRAS 3 INTENTOS");
        login.log(vio, "error1");
        login.log(vio, "error2");
        login.log(vio, "error3");
        login.log(vio, "Vio2026!");

        mostrarTitulo("ACCESO ADMINISTRADOR");
        boolean acceso = login.log(admin, "Admin123!");

        if (acceso && admin.esAdmin()) {
            System.out.println("Acceso administrador concedido");
        }

        mostrarTitulo("OPERACIONES BANCARIAS");
        System.out.println("Intento de retiro mayor al limite:");
        admin.retirar(2500);

        banco.transferir(admin, cliente, 1500);

        mostrarTitulo("INTERFAZ SENCILLA TIPO FLEX");
        mostrarPanelFlex(admin, cliente, vio);

        mostrarTitulo("HISTORIAL DE MOVIMIENTOS");
        admin.mostrarHistorial();
        cliente.mostrarHistorial();
        vio.mostrarHistorial();
    }

    private static void mostrarTitulo(String titulo) {
        System.out.println("\n========================================");
        System.out.println(titulo);
        System.out.println("========================================");
    }

    private static void mostrarPanelFlex(usuario usuario1, usuario usuario2, usuario usuario3) {
        System.out.println("+----------------------+----------------------+----------------------+");
        System.out.printf("| %-20s | %-20s | %-20s |%n", usuario1.getNombre(), usuario2.getNombre(), usuario3.getNombre());
        System.out.println("+----------------------+----------------------+----------------------+");
        System.out.printf("| Rol: %-15s | Rol: %-15s | Rol: %-15s |%n",
                usuario1.getRol(), usuario2.getRol(), usuario3.getRol());
        System.out.printf("| Saldo: $%-12.2f | Saldo: $%-12.2f | Saldo: $%-12.2f |%n",
                usuario1.getSaldo(), usuario2.getSaldo(), usuario3.getSaldo());
        System.out.printf("| Limite: $%-11.2f | Limite: $%-11.2f | Limite: $%-11.2f |%n",
                usuario1.getLimiteRetiro(), usuario2.getLimiteRetiro(), usuario3.getLimiteRetiro());
        System.out.printf("| Bloqueado: %-10s | Bloqueado: %-10s | Bloqueado: %-10s |%n",
                usuario1.estaBloqueado() ? "Si" : "No",
                usuario2.estaBloqueado() ? "Si" : "No",
                usuario3.estaBloqueado() ? "Si" : "No");
        System.out.println("+----------------------+----------------------+----------------------+");
    }
}

/*
EXPLICACION PARA PRINCIPIANTES - App.java

Esta es la pagina principal del programa. En Java, el metodo main es donde empieza
todo cuando damos ejecutar.

Que hace esta clase:
- Crea tres usuarios: Daniel, Carlos y VIO.
- Crea un objeto de SistemaLogin para probar el inicio de sesion.
- Crea un objeto de SistemaBanco para hacer transferencias.
- Muestra pruebas de contrasena segura.
- Simula 3 intentos fallidos con el usuario VIO para bloquear su cuenta.
- Prueba el acceso del administrador Daniel.
- Prueba un retiro que supera el limite.
- Hace una transferencia de Daniel a Carlos.
- Imprime una interfaz sencilla en consola, parecida a tarjetas en columnas.
- Muestra el historial de movimientos de cada usuario.

Con que otras clases trabaja:
- Trabaja con usuario.java porque necesita crear usuarios y consultar su saldo,
  rol, limite, bloqueo e historial.
- Trabaja con SistemaLogin.java porque usa login.log(...) para validar accesos.
- Trabaja con SistemaBanco.java porque usa banco.transferir(...) para mover dinero.

Idea principal:
App.java no guarda las reglas importantes. Solo organiza y ejecuta el ejemplo.
Las reglas de usuario estan en usuario.java, las reglas de login en SistemaLogin.java
y las reglas bancarias en SistemaBanco.java.
*/
