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

/*
EXPLICACION PARA PRINCIPIANTES - SistemaBanco.java

Esta clase se encarga de las operaciones de dinero entre usuarios.

Que hace esta clase:
- Tiene el metodo transferir(...).
- Recibe un usuario origen, un usuario destino y un monto.
- Revisa que el monto sea mayor que cero.
- Intenta retirar el dinero del usuario origen.
- Si el retiro funciona, deposita ese dinero al usuario destino.
- Guarda movimientos en el historial de los dos usuarios.
- Si algo falla, muestra un mensaje y guarda el intento fallido.

Con que otras clases trabaja:
- Trabaja con usuario.java porque usa retirar(...), depositar(...),
  agregarMovimiento(...) y getNombre().
- App.java usa esta clase cuando llama banco.transferir(admin, cliente, 1500).

Idea principal:
SistemaBanco.java contiene la logica bancaria. No valida contrasenas, porque eso
le toca a SistemaLogin.java. Su trabajo es mover dinero de forma controlada.
*/
