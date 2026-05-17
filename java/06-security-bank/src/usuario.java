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
