package app;

import controller.AlumnoController;
import modelo.Alumno;
import repository.AlumnoRepository;
import service.AlumnoService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AlumnoRepository repository = new AlumnoRepository();
        AlumnoService service = new AlumnoService(repository);
        AlumnoController controller = new AlumnoController(service);

        int opcion;

        do {
            System.out.println("\nREGISTRO ESCOLAR");
            System.out.println("1. Registrar Alumno");
            System.out.println("2. Mostrar Alumnos");
            System.out.println("3. Salir");

            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Carrera: ");
                    String carrera = sc.nextLine();

                    System.out.print("Semestre: ");
                    int semestre = sc.nextInt();
                    sc.nextLine();

                    controller.registrarAlumno(id, nombre, carrera, semestre);
                    break;

                case 2:
                    System.out.println("\n===== ALUMNOS =====");

                    for (Alumno alumno : controller.obtenerTodos()) {
                        System.out.println(alumno);
                    }
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 3);

        sc.close();
    }
}
