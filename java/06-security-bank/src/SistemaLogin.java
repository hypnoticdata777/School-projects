public class SistemaLogin {
    public boolean log(usuario usuario, String contraseña){
        if (usuario.autentificar(contraseña)){
            System.out.println("acceso concedido");
            return true;
        } else {
            System.out.println("Acceso Denegado");
            return false;
        }
    }

}

/*
Explicacion del codigo:

public class SistemaLogin
Declara la clase SistemaLogin, que sirve para manejar el inicio de sesion.

public boolean log(usuario usuario, String contraseña)
Declara un metodo llamado log que recibe un usuario y una contraseña.
El metodo regresa true si el acceso es correcto, o false si el acceso es incorrecto.

if (usuario.autentificar(contraseña))
Revisa si la contraseña recibida coincide con la contraseña guardada en el usuario.

System.out.println("acceso concedido");
Muestra un mensaje cuando la contraseña es correcta.

return true;
Indica que el inicio de sesion fue exitoso.

else
Se ejecuta cuando la contraseña no es correcta.

System.out.println("Acceso Denegado");
Muestra un mensaje cuando la contraseña es incorrecta.

return false;
Indica que el inicio de sesion fallo.
*/
