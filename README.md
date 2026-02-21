# School Projects — Carlos Sánchez

Personal collection of school assignments from my early programming courses (5th semester, Systems Engineering). These are the first programs I wrote on my own — kept here as a record of the learning process.

---

## Structure

```
java/
├── 01-basics/            Variables, types, input/output, arithmetic
├── 02-control-flow/      Switch statements, calculators
├── 03-oop/               First object-oriented exercises (Ship, Car classes)
├── 04-agenda-contactos/  Contact manager app with CRUD menus
└── 05-store-management/  Full GUI app — product and customer management

python/
└── work_orders.py        Console-based work order tracker

databases/
├── BD_Escuela1.accdb
└── Dulceria 2.0 - Tarea Base de Datos1.accdb
```

---

## Projects

### Java

**01 — Basics**
Standalone programs covering the fundamentals: `Saludo`, `Variables`, `Conversion`, `Constantes`, `EntradaDatos`, `Operaciones`. Each file demonstrates one concept.

**02 — Control Flow**
Two calculator exercises using `switch`:
- `Calculadora/` — JOptionPane dialog-based switch demo
- `CalculadoraSwitch/` — Full arithmetic calculator with console I/O and division-by-zero handling

**03 — OOP**
First object-oriented class designs:
- `Barco.java` — Ship class with attributes and `mostrarInformacion()`
- `Coche.java` — Car class with default constructor
- `Main.java` / `App.java` / `MainCoche.java` — demo programs for each class

**04 — Agenda de Contactos**
Menu-driven contact manager using `ArrayList<Contacto>` and JOptionPane. Supports add, view, edit, and delete. Includes sample data in `contactos.txt`.

**05 — Store Management (Exam Project)**
Full Swing GUI (`JFrame` + `JTable`) with two tabs:
- **Products** — add, edit, delete, clear all; fields: name, price, stock, brand, flavor
- **Customers** — add, modify, delete; fields: name, address, phone, email

Demonstrates MVC-style separation: `Producto`, `Clientes` (data classes), `GestorProductos` (logic), `Main` (view).

---

### Python

**work_orders.py**
Console app for managing work orders. Covers lists, dictionaries, `for`/`while` loops, functions, and basic input validation. Menu options: add, list, complete, delete.

---

### Databases

Two Microsoft Access (`.accdb`) files from database coursework — a school database and a candy store inventory.

---

## Requirements

- Java: JDK 17 or higher
- Python: 3.x (no external dependencies)

**Run a Java program:**
```bash
javac Saludo.java
java Saludo
```

**Run the Python script:**
```bash
python python/work_orders.py
```

---

*Author: Carlos Sánchez — Ingeniería en Sistemas*
