/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.PreescripcionController;
import model.Preescripcion;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author ELKIN
 */
public class PreescripcionVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final PreescripcionController controller = new PreescripcionController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ DE PRESCRIPCIONES ===");
            System.out.println("1. Agregar prescripción");
            System.out.println("2. Listar prescripciones");
            System.out.println("3. Actualizar prescripción");
            System.out.println("4. Eliminar prescripción");
            System.out.println("5. Buscar por ID");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> controller.listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 5 -> buscar();
                case 0 -> System.out.println("Saliendo del módulo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
    System.out.println("\n--- Nueva Prescripción ---");

    int consultaId = 0;
    System.out.print("¿Tiene consulta asociada? (s/n): ");
    String opcion = sc.nextLine().trim().toLowerCase();
    if (opcion.equals("s")) {
        System.out.print("ID de la consulta: ");
        try {
            consultaId = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada no válida, se omitirá la consulta.");
            consultaId = 0;
        }
    }

    System.out.print("ID del procedimiento (Enter si no aplica): ");
    int procedimientoId = 0;
    String procInput = sc.nextLine().trim();
    if (!procInput.isEmpty()) {
        try {
            procedimientoId = Integer.parseInt(procInput);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada no válida, se omitirá el procedimiento.");
            procedimientoId = 0;
        }
    }

    System.out.print("ID del producto: ");
    int productoId = Integer.parseInt(sc.nextLine().trim());

    System.out.print("Cantidad: ");
    int cantidad = Integer.parseInt(sc.nextLine().trim());

    System.out.print("Dosis: ");
    String dosis = sc.nextLine().trim();

    System.out.print("Frecuencia: ");
    String frecuencia = sc.nextLine().trim();

    System.out.print("Duración (días): ");
    int duracion = Integer.parseInt(sc.nextLine().trim());

    System.out.print("Instrucciones: ");
    String instrucciones = sc.nextLine().trim();

    // Se crea el objeto con la fecha actual
    Preescripcion p = new Preescripcion(
            consultaId,
            procedimientoId,
            productoId,
            cantidad,
            dosis,
            frecuencia,
            duracion,
            instrucciones,
            new Timestamp(System.currentTimeMillis())
    );

    controller.agregar(p);
}


    private void actualizar() {
        System.out.print("\nIngrese el ID de la prescripción a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Preescripcion existente = controller.buscarPorId(id);
        if (existente == null) {
            System.out.println("No existe una prescripción con ese ID.");
            return;
        }

        System.out.print("Nueva dosis: ");
        String dosis = sc.nextLine();
        System.out.print("Nueva frecuencia: ");
        String frecuencia = sc.nextLine();
        System.out.print("Nueva duración (días): ");
        int duracion = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevas instrucciones: ");
        String instrucciones = sc.nextLine();

        Preescripcion actualizada = new Preescripcion(
                id,
                existente.getConsultaId(),
                existente.getProcedimientoId(),
                existente.getProductoId(),
                existente.getCantidad(),
                dosis,
                frecuencia,
                duracion,
                instrucciones,
                new Timestamp(System.currentTimeMillis())
        );

        controller.actualizar(id, id, duracion, duracion, id, dosis, frecuencia, duracion, instrucciones);
    }

    private void eliminar() {
        System.out.print("\nIngrese el ID de la prescripción a eliminar: ");
        int id = sc.nextInt();
        controller.eliminar(id);
    }

    private void buscar() {
        System.out.print("\nIngrese el ID de la prescripción: ");
        int id = sc.nextInt();
        Preescripcion p = controller.buscarPorId(id);
        if (p == null) {
            System.out.println("No existe una prescripción con ese ID.");
        } else {
            System.out.println(p);
        }
    }
}

