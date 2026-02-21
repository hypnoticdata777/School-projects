# work_orders.py
# Mini sistema de órdenes de trabajo (consola)

# 1️ ---- Estructura de datos ----
work_orders = []        # ← lista donde guardaremos cada orden como dict

# 2 ---- Funciones ----
def mostrar_menu():
    print("\n--- MENÚ PRINCIPAL ---")
    print("1. Agregar orden de trabajo")
    print("2. Listar órdenes")
    print("3. Completar orden")
    print("4. Eliminar orden")
    print("5. Salir")

def crear_orden():
    titulo = input("Título breve de la orden: ").strip()
    costo  = float(input("Costo estimado (USD): "))
    orden  = {"titulo": titulo, "costo": costo, "completada": False}
    work_orders.append(orden)
    print(" Orden creada.")

def listar_ordenes():
    if not work_orders:
        print("No hay órdenes aún.")
        return
    print("\nID | Título                         | Costo | Estado")
    print("-"*55)
    for idx, orden in enumerate(work_orders, start=1):      # ← FOR
        estado = "✔" if orden["completada"] else "Pendiente"
        print(f"{idx:2} | {orden['titulo'][:28]:28} | "
              f"${orden['costo']:6.2f} | {estado}")

def completar_orden():
    listar_ordenes()
    if not work_orders:
        return
    i = int(input("ID a marcar como completada: "))
    if 1 <= i <= len(work_orders):                          # ← CONDICIÓN
        work_orders[i-1]["completada"] = True
        print(" Orden marcada completada.")
    else:
        print("ID inválido.")

def eliminar_orden():
    listar_ordenes()
    if not work_orders:
        return
    i = int(input("ID a eliminar: "))
    if 1 <= i <= len(work_orders):
        work_orders.pop(i-1)
        print("  Orden eliminada.")
    else:
        print("ID inválido.")

# 3️ ---- Bucle principal ----
while True:                                                 # ← WHILE
    mostrar_menu()
    opcion = input("Elige opción (1-5): ").strip()

    if opcion == "1":                                       # ← IF / ELIF / ELSE
        crear_orden()
    elif opcion == "2":
        listar_ordenes()
    elif opcion == "3":
        completar_orden()
    elif opcion == "4":
        eliminar_orden()
    elif opcion == "5":
        print("Saliendo... ¡Nos vemos, bro!")
        break
    else:
        print(" Opción no válida, intenta de nuevo.")
