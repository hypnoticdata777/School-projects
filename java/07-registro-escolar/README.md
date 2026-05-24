# Registro Escolar

Ejercicio Java de registro escolar con separacion por capas:

- `modelo`: clase `Alumno`
- `repository`: almacenamiento en memoria
- `service`: logica para registrar y consultar alumnos
- `controller`: puente entre la app y el servicio
- `app`: menu de consola

## Ejecutar

Desde esta carpeta:

```powershell
javac -d bin src\modelo\Alumno.java src\repository\AlumnoRepository.java src\service\AlumnoService.java src\controller\AlumnoController.java src\app\App.java
java -cp bin app.App
```
