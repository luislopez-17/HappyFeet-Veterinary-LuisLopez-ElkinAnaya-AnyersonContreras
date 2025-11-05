/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.ItemFacturaController;
import java.util.List;
import java.util.Scanner;
import model.ItemFactura;

/**
 *
 * @author usuario
 */
public class ItemFacturaVIEW {

    private ItemFacturaController controller;
    private Scanner scanner;

    public ItemFacturaVIEW() {
        controller = new ItemFacturaController();
        scanner = new Scanner(System.in);
    }

    public void menu() {
        int opcion;

        do {
            System.out.println("\n===== MENÚ ITEM FACTURA =====");
            System.out.println("1. Agregar Item");
            System.out.println("2. Listar Items");
            System.out.println("3. Actualizar Item");
            System.out.println("4. Eliminar Item");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregarItem();
                case 2 -> listarItems();
                case 3 -> actualizarItem();
                case 4 -> eliminarItem();
                case 0 -> System.out.println("Saliendo del menú de ítems...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    // =======================================================
    // 🔹 AGREGAR ITEM FACTURA
    // =======================================================
    private void agregarItem() {
        System.out.println("\n--- Agregar Item de Factura ---");

        System.out.print("ID de factura: ");
        int facturaId = leerEntero();

        System.out.print("Tipo de item (PRODUCTO / SERVICIO): ");
        String tipoItem = scanner.nextLine().trim();

        int productoId = 0, servicioId = 0;
        String servicioDescripcion = "";

        if (tipoItem.equalsIgnoreCase("PRODUCTO")) {
            System.out.print("ID del producto: ");
            productoId = leerEntero();
        } else if (tipoItem.equalsIgnoreCase("SERVICIO")) {
            System.out.print("ID del servicio: ");
            servicioId = leerEntero();
            System.out.print("Descripción del servicio: ");
            servicioDescripcion = scanner.nextLine().trim();
        }

        System.out.print("Cantidad: ");
        int cantidad = leerEntero();

        System.out.print("Precio unitario: ");
        double precioUnitario = leerDouble();

        controller.agregarItemFactura(facturaId, tipoItem, productoId, servicioId,
                servicioDescripcion, cantidad, precioUnitario);
    }

    // =======================================================
    // 🔹 LISTAR ITEMS FACTURA
    // =======================================================
    private void listarItems() {
        System.out.println("\n--- Lista de Ítems de Factura ---");
        List<ItemFactura> lista = controller.listarItemsFactura();

        if (lista.isEmpty()) {
            System.out.println("No hay ítems registrados.");
        } else {
            for (ItemFactura i : lista) {
                System.out.println(i);
            }
        }
    }

    // =======================================================
    // 🔹 ACTUALIZAR ITEM FACTURA
    // =======================================================
    private void actualizarItem() {
        System.out.println("\n--- Actualizar Item de Factura ---");

        System.out.print("ID del ítem a actualizar: ");
        int id = leerEntero();

        System.out.print("ID de factura: ");
        int facturaId = leerEntero();

        System.out.print("Tipo de item (PRODUCTO / SERVICIO): ");
        String tipoItem = scanner.nextLine().trim();

        int productoId = 0, servicioId = 0;
        String servicioDescripcion = "";

        if (tipoItem.equalsIgnoreCase("PRODUCTO")) {
            System.out.print("ID del producto: ");
            productoId = leerEntero();
        } else if (tipoItem.equalsIgnoreCase("SERVICIO")) {
            System.out.print("ID del servicio: ");
            servicioId = leerEntero();
            System.out.print("Descripción del servicio: ");
            servicioDescripcion = scanner.nextLine().trim();
        }

        System.out.print("Cantidad: ");
        int cantidad = leerEntero();

        System.out.print("Precio unitario: ");
        double precioUnitario = leerDouble();

        controller.actualizarItemFactura(id, facturaId, tipoItem, productoId, servicioId,
                servicioDescripcion, cantidad, precioUnitario);
    }

    // =======================================================
    // 🔹 ELIMINAR ITEM FACTURA
    // =======================================================
    private void eliminarItem() {
        System.out.println("\n--- Eliminar Item de Factura ---");

        System.out.print("ID del ítem a eliminar: ");
        int id = leerEntero();

        controller.eliminarItemFactura(id);
    }

    // =======================================================
    // 🔹 MÉTODOS AUXILIARES
    // =======================================================
    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número entero: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número decimal: ");
            }
        }
    }
}
