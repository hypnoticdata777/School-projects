# Sistema Escolar - Actividad 3

## ¿Qué hace este proyecto?

Es una aplicación de consola en Java que permite gestionar la información de **alumnos** y **docentes** de una institución escolar. El usuario interactúa con un menú numerado donde puede:

- Registrar, consultar, actualizar y eliminar alumnos
- Registrar, consultar, actualizar y eliminar docentes
- **Exportar la lista de alumnos a un archivo JSON** (alumnos.json)

Los datos se guardan y leen desde una base de datos MySQL llamada `sistema_escolar`.

---

## Estructura del proyecto

```
SistemaEscolar_Actividad3/
├── src/
│   ├── App.java                 # Punto de entrada, menú principal
│   ├── Alumno.java              # Modelo de datos del alumno
│   ├── AlumnoController.java    # Controlador de alumnos
│   ├── AlumnoService.java       # Lógica y validaciones de alumnos
│   ├── AlumnoRepository.java    # Consultas SQL de alumnos
│   ├── Docente.java             # Modelo de datos del docente
│   ├── DocenteController.java   # Controlador de docentes
│   ├── DocenteService.java      # Lógica y validaciones de docentes
│   ├── DocenteRepository.java   # Consultas SQL de docentes
│   └── Conexion.java            # Conexión a la base de datos MySQL
├── lib/
│   └── mysql-connector-j-9.5.0.jar  # Driver de MySQL
└── database.sql                 # Script para crear las tablas
```

---

## Arquitectura por capas

Este proyecto usa una **arquitectura de 3 capas** (separación de responsabilidades):

```
Usuario
   ↓
App.java          → Menú de consola, captura la entrada del usuario
   ↓
Controller        → Recibe la acción y la delega al servicio
   ↓
Service           → Valida los datos antes de procesarlos
   ↓
Repository        → Ejecuta las consultas SQL en la base de datos
   ↓
Base de datos MySQL (sistema_escolar)
```

---

## Trayecto de la información — paso a paso

### Ejemplo: Registrar un alumno (opción 1)

1. **App.java** — El usuario escribe el nombre, carrera y semestre. El menú llama a:
   ```java
   alumnoController.registrarAlumno(aNombre, aCarrera, aSemestre);
   ```

2. **AlumnoController.java** — Recibe los datos y los pasa al servicio:
   ```java
   service.registrarAlumno(nombre, carrera, semestre);
   ```

3. **AlumnoService.java** — Valida que el nombre no esté vacío. Si todo está bien, crea un objeto `Alumno` y llama al repositorio:
   ```java
   repository.guardar(new Alumno(0, nombre, carrera, semestre));
   ```

4. **AlumnoRepository.java** — Ejecuta el INSERT en la base de datos:
   ```sql
   INSERT INTO alumno (nombre, carrera, semestre) VALUES (?, ?, ?)
   ```

5. **Base de datos** — El alumno queda guardado en la tabla `alumno`.

---

### Ejemplo: Exportar alumnos a JSON (opción 3)

1. **App.java** — El usuario elige la opción 3. El menú llama a:
   ```java
   alumnoController.exportarJSONArchivo();
   ```

2. **AlumnoController.java** — Delega al servicio:
   ```java
   service.exportarJSONArchivo();
   ```

3. **AlumnoService.java** — Consulta todos los alumnos, construye el texto JSON con `StringBuilder` recorriendo la lista, y escribe el resultado en un archivo:
   ```java
   FileWriter writer = new FileWriter("alumnos.json");
   writer.write(json.toString());
   ```

4. **AlumnoRepository.java** — Ejecuta el SELECT para traer todos los registros:
   ```sql
   SELECT * FROM alumno
   ```

5. **Resultado** — Se genera el archivo `alumnos.json` en la carpeta raíz del proyecto con todos los alumnos en formato JSON.

---

## Menú principal

| Opción | Acción                     |
|--------|----------------------------|
| 1      | Registrar Alumno           |
| 2      | Mostrar Alumnos            |
| 3      | Exportar Alumnos a JSON    |
| 4      | Actualizar Alumno          |
| 5      | Eliminar Alumno            |
| 6      | Registrar Docente          |
| 7      | Mostrar Docentes           |
| 8      | Actualizar Docente         |
| 9      | Eliminar Docente           |
| 10     | Salir                      |

---

## Requisitos para ejecutar

- Java 11 o superior
- MySQL corriendo en `localhost:3306`
- Base de datos `sistema_escolar` creada con el script `database.sql`
- Driver MySQL incluido en `lib/mysql-connector-j-9.5.0.jar`
