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

/*
EXPLICACION PARA PRINCIPIANTES - SistemaLogin.java

Esta clase se encarga del inicio de sesion.

Que hace esta clase:
- Recibe un usuario y una contrasena.
- Primero revisa si la cuenta ya esta bloqueada.
- Si no esta bloqueada, compara la contrasena escrita con la contrasena guardada.
- Si la contrasena es correcta, deja entrar y reinicia los intentos fallidos.
- Si la contrasena es incorrecta, suma un intento fallido.
- Si llega a 3 intentos fallidos, bloquea la cuenta.

Con que otras clases trabaja:
- Trabaja con usuario.java porque necesita preguntarle si esta bloqueado, si la
  contrasena es correcta y cuantos intentos fallidos tiene.
- App.java usa esta clase cuando llama login.log(...).

Idea principal:
SistemaLogin.java no guarda usuarios propios. Solo valida el acceso usando los
datos y metodos que ya existen dentro de cada objeto usuario.
*/
