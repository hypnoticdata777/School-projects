import java.util.Scanner;

/**
 * Clase principal del Sistema Escolar.
 *
 * Aqui arranca la aplicacion. Esta clase es responsable de:
 *  1. Crear las instancias de cada capa (Repository -> Service -> Controller)
 *  2. Mostrar el menu en consola
 *  3. Leer la opcion del usuario y llamar al controlador correspondiente
 *
 * La arquitectura que seguimos es de 4 capas:
 *   App (UI) -> Controller -> Service -> Repository -> Base de datos
 *
 * Cada capa solo habla con la que tiene inmediatamente abajo, lo que hace
 * el codigo mas organizado y facil de mantener.
 */
public class App {

    public static void main(String[] args) {

        // Scanner para capturar lo que escribe el usuario en consola
        Scanner sc = new Scanner(System.in);

        // -----------------------------------------------------------------------
        // Inicializacion de capas para ALUMNOS
        // Cada capa recibe la del nivel inferior por constructor (inyeccion de dependencias)
        // -----------------------------------------------------------------------
        AlumnoRepository alumnoRepository = new AlumnoRepository();
        AlumnoService    alumnoService    = new AlumnoService(alumnoRepository);
        AlumnoController alumnoController = new AlumnoController(alumnoService);

        // -----------------------------------------------------------------------
        // Inicializacion de capas para DOCENTES
        // Mismo patron que alumnos pero para la entidad Docente
        // -----------------------------------------------------------------------
        DocenteRepository docenteRepository = new DocenteRepository();
        DocenteService    docenteService    = new DocenteService(docenteRepository);
        DocenteController docenteController = new DocenteController(docenteService);

        int opcion;

        // El menu se repite en un do-while para que siempre se muestre al menos una vez
        // y continue hasta que el usuario elija la opcion de salir (11)
        do {
            // Mostramos el menu separado por secciones para mayor claridad
            System.out.println("\n===== SISTEMA ESCOLAR =====");
            System.out.println("--- ALUMNOS ---");
            System.out.println("1. Registrar Alumno");
            System.out.println("2. Mostrar Alumnos");
            System.out.println("3. Exportar Alumnos a JSON");
            System.out.println("4. Actualizar Alumno");
            System.out.println("5. Eliminar Alumno");
            System.out.println("--- DOCENTES ---");
            System.out.println("6. Registrar Docente");
            System.out.println("7. Mostrar Docentes");
            System.out.println("8. Actualizar Docente");
            System.out.println("9. Eliminar Docente");
            System.out.println("10. Exportar Docentes a JSON");
            System.out.println("---------------------------");
            System.out.println("11. Salir");
            System.out.print("Opcion: ");

            opcion = sc.nextInt();
            sc.nextLine(); // consumimos el salto de linea que queda en el buffer despues de nextInt()

            switch (opcion) {

                // ---------------------------------------------------------------
                // OPCIONES DE ALUMNOS
                // ---------------------------------------------------------------

                case 1:
                    // Pedimos los datos del alumno antes de llamar al controlador
                    System.out.print("Nombre: ");
                    String aNombre = sc.nextLine();
                    System.out.print("Carrera: ");
                    String aCarrera = sc.nextLine();
                    System.out.print("Semestre: ");
                    int aSemestre = sc.nextInt();
                    alumnoController.registrarAlumno(aNombre, aCarrera, aSemestre);
                    break;

                case 2:
                    // obtenerTodos() regresa un ArrayList; toString() de Alumno formatea cada linea
                    System.out.println("\n--- LISTA DE ALUMNOS ---");
                    for (Alumno a : alumnoController.obtenerTodos()) {
                        System.out.println(a);
                    }
                    break;

                case 3:
                    // Genera el archivo alumnos.json en el directorio de ejecucion
                    alumnoController.exportarJSONArchivo();
                    break;

                case 4:
                    System.out.print("ID del alumno a actualizar: ");
                    int aIdUpd = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String aNombreUpd = sc.nextLine();
                    System.out.print("Nueva carrera: ");
                    String aCarreraUpd = sc.nextLine();
                    System.out.print("Nuevo semestre: ");
                    int aSemestreUpd = sc.nextInt();
                    alumnoController.actualizarAlumno(aIdUpd, aNombreUpd, aCarreraUpd, aSemestreUpd);
                    break;

                case 5:
                    System.out.print("ID del alumno a eliminar: ");
                    int aIdDel = sc.nextInt();
                    alumnoController.eliminarAlumno(aIdDel);
                    break;

                // ---------------------------------------------------------------
                // OPCIONES DE DOCENTES
                // ---------------------------------------------------------------

                case 6:
                    // Pedimos los datos del docente; salario como double para soportar centavos
                    System.out.print("Nombre: ");
                    String dNombre = sc.nextLine();
                    System.out.print("Materia: ");
                    String dMateria = sc.nextLine();
                    System.out.print("Departamento: ");
                    String dDepartamento = sc.nextLine();
                    System.out.print("Salario: ");
                    double dSalario = sc.nextDouble();
                    docenteController.registrarDocente(dNombre, dMateria, dDepartamento, dSalario);
                    break;

                case 7:
                    // toString() de Docente formatea cada registro para mostrarlo en consola
                    System.out.println("\n--- LISTA DE DOCENTES ---");
                    for (Docente d : docenteController.obtenerTodos()) {
                        System.out.println(d);
                    }
                    break;

                case 8:
                    System.out.print("ID del docente a actualizar: ");
                    int dIdUpd = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String dNombreUpd = sc.nextLine();
                    System.out.print("Nueva materia: ");
                    String dMateriaUpd = sc.nextLine();
                    System.out.print("Nuevo departamento: ");
                    String dDepartamentoUpd = sc.nextLine();
                    System.out.print("Nuevo salario: ");
                    double dSalarioUpd = sc.nextDouble();
                    docenteController.actualizarDocente(dIdUpd, dNombreUpd, dMateriaUpd, dDepartamentoUpd, dSalarioUpd);
                    break;

                case 9:
                    System.out.print("ID del docente a eliminar: ");
                    int dIdDel = sc.nextInt();
                    docenteController.eliminarDocente(dIdDel);
                    break;

                case 10:
                    // Genera el archivo docentes.json en el directorio de ejecucion
                    docenteController.exportarJSONArchivo();
                    break;

                case 11:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida. Intenta de nuevo.");
            }

        } while (opcion != 11); // el bucle termina cuando el usuario elige salir

        sc.close(); // cerramos el Scanner para liberar el recurso
    }
}
