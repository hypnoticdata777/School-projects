public class usuario {
    private String nombre;
    private String contraseña;
    private String rol;
    private double  saldo;

    public usuario(String nombre, String contraseña, String rol, double saldo) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
        this.saldo = saldo;

    }
    public boolean autentificar(String cont){
        return contraseña.equals(cont);
    }
    public boolean esAdmin(){
        return rol.equalsIgnoreCase("admin");
    }
    public String getNombre(){
        return nombre;
    }
    public boolean retirar (double monto){
        if(monto <= 0) {
            System.out.println("monto invalido");
            return false;
        }
        if(monto > saldo) {
            System.out.println("saldo insuficiente");
            return false;
        }
        saldo -= monto;
        return true;
    }
    public void depositar(double monto){
        if (monto <=0){
            System.out.println("monto invalido");
            return;
        }
        saldo += monto;
    }
    public void mostrarInformacion(){
        System.out.println("Usuario: "+ nombre);
        System.out.println("Rol: "+ rol);
        System.out.println("Saldo "+ saldo);
    }

}

/*
Explicacion del codigo:

public class usuario
Declara la clase usuario, que representa a un usuario del sistema.

private String nombre;
Guarda el nombre del usuario.

private String contraseña;
Guarda la contraseña del usuario.

private String rol;
Guarda el rol del usuario, por ejemplo "admin".

private double saldo;
Guarda el saldo disponible del usuario.

public usuario(String nombre, String contraseña, String rol, double saldo)
Es el constructor. Sirve para crear un usuario con nombre, contraseña, rol y saldo.

this.nombre = nombre;
Guarda en el atributo nombre el valor que llega al constructor.

this.contraseña = contraseña;
Guarda en el atributo contraseña el valor que llega al constructor.

this.rol = rol;
Guarda en el atributo rol el valor que llega al constructor.

this.saldo = saldo;
Guarda en el atributo saldo el valor que llega al constructor.

public boolean autentificar(String cont)
Metodo que revisa si la contraseña escrita es correcta.

return contraseña.equals(cont);
Compara la contraseña guardada con la contraseña recibida.

public boolean esAdmin()
Metodo que revisa si el usuario tiene rol de administrador.

return rol.equalsIgnoreCase("admin");
Compara el rol con "admin" sin importar mayusculas o minusculas.

public boolean retirar(double monto)
Metodo que revisa si se puede retirar una cantidad de dinero.

if(monto <= 0)
Revisa si el monto es menor o igual a cero.

System.out.println("monto invalido");
Muestra un mensaje si el monto no es valido.

return false;
Indica que no se pudo hacer el retiro.

if(monto > saldo)
Revisa si el monto es mayor que el saldo disponible.

System.out.println("saldo insuficiente");
Muestra un mensaje si no hay suficiente saldo.

return true;
Indica que el retiro si se puede realizar.
*/
