import java.util.ArrayList;
import java.util.List;

public class usuario {
    private String nombre;
    private String contrasena;
    private String rol;
    private double saldo;
    private double limiteRetiro;
    private int intentosFallidos;
    private boolean bloqueado;
    private List<String> historialMovimientos;

    public usuario(String nombre, String contrasena, String rol, double saldo, double limiteRetiro) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol = rol;
        this.saldo = saldo;
        this.limiteRetiro = limiteRetiro;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.historialMovimientos = new ArrayList<>();

        if (esContrasenaSegura(contrasena)) {
            agregarMovimiento("Cuenta creada con contrasena segura");
        } else {
            agregarMovimiento("Cuenta creada con contrasena insegura");
        }
    }

    public boolean autentificar(String cont) {
        return contrasena.equals(cont);
    }

    public boolean esAdmin() {
        return rol.equalsIgnoreCase("admin");
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteRetiro() {
        return limiteRetiro;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public boolean estaBloqueado() {
        return bloqueado;
    }

    public void registrarIntentoFallido() {
        intentosFallidos++;
        agregarMovimiento("Intento fallido de acceso #" + intentosFallidos);

        if (intentosFallidos >= 3) {
            bloqueado = true;
            agregarMovimiento("Cuenta bloqueada por 3 intentos fallidos");
        }
    }

    public void reiniciarIntentos() {
        intentosFallidos = 0;
    }

    public boolean retirar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto invalido");
            agregarMovimiento("Retiro rechazado por monto invalido: $" + monto);
            return false;
        }

        if (monto > limiteRetiro) {
            System.out.println("Retiro rechazado: supera el limite de $" + limiteRetiro);
            agregarMovimiento("Retiro rechazado por limite: $" + monto);
            return false;
        }

        if (monto > saldo) {
            System.out.println("Saldo insuficiente");
            agregarMovimiento("Retiro rechazado por saldo insuficiente: $" + monto);
            return false;
        }

        saldo -= monto;
        agregarMovimiento("Retiro realizado: $" + monto);
        return true;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto invalido");
            agregarMovimiento("Deposito rechazado por monto invalido: $" + monto);
            return;
        }

        saldo += monto;
        agregarMovimiento("Deposito recibido: $" + monto);
    }

    public void agregarMovimiento(String movimiento) {
        historialMovimientos.add(movimiento);
    }

    public void mostrarHistorial() {
        System.out.println("\nHistorial de " + nombre + ":");

        if (historialMovimientos.isEmpty()) {
            System.out.println("- Sin movimientos");
            return;
        }

        for (String movimiento : historialMovimientos) {
            System.out.println("- " + movimiento);
        }
    }

    public void mostrarInformacion() {
        System.out.println("Usuario: " + nombre);
        System.out.println("Rol: " + rol);
        System.out.println("Saldo: $" + saldo);
        System.out.println("Limite de retiro: $" + limiteRetiro);
        System.out.println("Intentos fallidos: " + intentosFallidos);
        System.out.println("Bloqueado: " + (bloqueado ? "Si" : "No"));
    }

    public static boolean esContrasenaSegura(String contrasena) {
        if (contrasena == null || contrasena.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspecial = false;

        for (int i = 0; i < contrasena.length(); i++) {
            char caracter = contrasena.charAt(i);

            if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            } else if (Character.isLowerCase(caracter)) {
                tieneMinuscula = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumero = true;
            } else {
                tieneEspecial = true;
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneEspecial;
    }
}

/*
EXPLICACION PARA PRINCIPIANTES - usuario.java

Esta clase representa a una persona que usa el banco.

Que datos guarda:
- nombre: el nombre del usuario.
- contrasena: la clave que se usa para iniciar sesion.
- rol: puede ser ADMIN o CLIENTE.
- saldo: el dinero disponible.
- limiteRetiro: cantidad maxima que puede retirar en una sola operacion.
- intentosFallidos: cuenta cuantas veces fallo al iniciar sesion.
- bloqueado: indica si la cuenta ya no puede entrar por fallar 3 veces.
- historialMovimientos: lista donde se guardan retiros, depositos, accesos y errores.

Que hace esta clase:
- Revisa si la contrasena escrita es correcta con autentificar(...).
- Revisa si el usuario es administrador con esAdmin().
- Permite retirar dinero, pero valida monto, saldo y limite de retiro.
- Permite depositar dinero.
- Guarda movimientos en el historial.
- Bloquea al usuario cuando llega a 3 intentos fallidos.
- Valida si una contrasena es segura.

Con que otras clases trabaja:
- App.java crea objetos de esta clase para Daniel, Carlos y VIO.
- SistemaLogin.java usa esta clase para revisar contrasenas, registrar intentos
  fallidos y bloquear cuentas.
- SistemaBanco.java usa esta clase para retirar al usuario origen, depositar al
  usuario destino y guardar movimientos.

Idea principal:
usuario.java es como la ficha o cuenta bancaria de cada persona. Aqui estan sus
datos y las reglas que afectan directamente a esa cuenta.
*/
