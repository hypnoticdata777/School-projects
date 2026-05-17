public class SistemaLogin {
    public boolean log(usuario usuario, String contrasena) {
        if (usuario.estaBloqueado()) {
            System.out.println("Acceso denegado: la cuenta esta bloqueada");
            return false;
        }

        if (usuario.autentificar(contrasena)) {
            usuario.reiniciarIntentos();
            usuario.agregarMovimiento("Acceso concedido");
            System.out.println("Acceso concedido");
            return true;
        }

        usuario.registrarIntentoFallido();
        System.out.println("Acceso denegado");

        if (usuario.estaBloqueado()) {
            System.out.println("Cuenta bloqueada por 3 intentos fallidos");
        } else {
            System.out.println("Intentos restantes: " + (3 - usuario.getIntentosFallidos()));
        }

        return false;
    }
}
