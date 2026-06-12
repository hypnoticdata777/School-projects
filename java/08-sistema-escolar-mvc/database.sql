-- Ejecuta este script en phpMyAdmin (XAMPP)
-- Menu: SQL > pegar esto > Continuar

CREATE DATABASE IF NOT EXISTS sistema_escolar;
USE sistema_escolar;

CREATE TABLE IF NOT EXISTS alumno (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    carrera   VARCHAR(100),
    semestre  INT
);

CREATE TABLE IF NOT EXISTS docente (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    materia      VARCHAR(100),
    departamento VARCHAR(100),
    salario      DOUBLE
);
