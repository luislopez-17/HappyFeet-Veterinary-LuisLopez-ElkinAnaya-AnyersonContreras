/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import controller.InventarioController;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;


public class InventarioVIEW {

    private final Scanner sc = new Scanner(System.in);
    private final InventarioController controller = new InventarioController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ INVENTARIO =====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Actualizar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Listar productos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo inventario...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Producto ---");
        System.out.print("Nombre del producto (obligatorio): ");
        String nombreProducto = sc.nextLine();

        System.out.print("ID del tipo de producto (obligatorio): ");
        int tipoProductoId = leerEntero();

        System.out.print("Descripción (opcional): ");
        String descripcion = sc.nextLine();

        System.out.print("Fabricante (opcional): ");
        String fabricante = sc.nextLine();

        System.out.print("ID del proveedor (Enter si no aplica): ");
        String provStr = sc.nextLine().trim();
        int proveedorId = provStr.isEmpty() ? 0 : Integer.parseInt(provStr);

        System.out.print("Lote (opcional): ");
        String lote = sc.nextLine();

        System.out.print("Cantidad en stock (default 0): ");
        String cantStr = sc.nextLine().trim();
        int cantidadStock = cantStr.isEmpty() ? 0 : Integer.parseInt(cantStr);

        System.out.print("Stock mínimo (default 0): ");
        String minStr = sc.nextLine().trim();
        int stockMinimo = minStr.isEmpty() ? 0 : Integer.parseInt(minStr);

        System.out.print("Unidad de medida (default 'unidad'): ");
        String unidadMedida = sc.nextLine().trim();
        if (unidadMedida.isEmpty()) {
            unidadMedida = "unidad";
        }

        Date fechaVencimiento = null;
        System.out.print("Fecha de vencimiento (yyyy-MM-dd, Enter si no aplica): ");
        String fechaStr = sc.nextLine().trim();
        if (!fechaStr.isEmpty()) {
            try {
                fechaVencimiento = Date.valueOf(fechaStr);
                if (!fechaVencimiento.toLocalDate().isAfter(LocalDate.now())) {
                    System.out.println("Error: la fecha de vencimiento debe ser posterior a la actual.");
                    return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: formato de fecha inválido. Usa yyyy-MM-dd.");
                return;
            }
        }

        System.out.print("Precio de compra (Enter si no aplica): ");
        String compraStr = sc.nextLine().trim();
        double precioCompra = compraStr.isEmpty() ? 0 : Double.parseDouble(compraStr);

        System.out.print("Precio de venta (obligatorio): ");
        double precioVenta = leerDouble();

        System.out.print("¿Requiere receta? (s/n): ");
        boolean requiereReceta = sc.nextLine().trim().equalsIgnoreCase("s");

        controller.agregarInventario(nombreProducto, tipoProductoId, descripcion, fabricante, proveedorId,
                lote, cantidadStock, stockMinimo, unidadMedida, fechaVencimiento,
                precioCompra, precioVenta, requiereReceta);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Producto ---");
        System.out.print("ID del producto a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre del producto (obligatorio): ");
        String nombreProducto = sc.nextLine();

        System.out.print("Nuevo ID del tipo de producto: ");
        int tipoProductoId = leerEntero();

        System.out.print("Nueva descripción: ");
        String descripcion = sc.nextLine();

        System.out.print("Nuevo fabricante: ");
        String fabricante = sc.nextLine();

        System.out.print("Nuevo ID del proveedor (Enter si no aplica): ");
        String provStr = sc.nextLine().trim();
        int proveedorId = provStr.isEmpty() ? 0 : Integer.parseInt(provStr);

        System.out.print("Nuevo lote: ");
        String lote = sc.nextLine();

        System.out.print("Nueva cantidad en stock: ");
        int cantidadStock = leerEntero();

        System.out.print("Nuevo stock mínimo: ");
        int stockMinimo = leerEntero();

        System.out.print("Nueva unidad de medida: ");
        String unidadMedida = sc.nextLine().trim();
        if (unidadMedida.isEmpty()) {
            unidadMedida = "unidad";
        }

        Date fechaVencimiento = null;
        System.out.print("Nueva fecha de vencimiento (yyyy-MM-dd, Enter si no aplica): ");
        String fechaStr = sc.nextLine().trim();
        if (!fechaStr.isEmpty()) {
            try {
                fechaVencimiento = Date.valueOf(fechaStr);
                if (!fechaVencimiento.toLocalDate().isAfter(LocalDate.now())) {
                    System.out.println("Error: la fecha de vencimiento debe ser posterior a la actual.");
                    return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: formato de fecha inválido. Usa yyyy-MM-dd.");
                return;
            }
        }

        System.out.print("Nuevo precio de compra: ");
        String compraStr = sc.nextLine().trim();
        double precioCompra = compraStr.isEmpty() ? 0 : Double.parseDouble(compraStr);

        System.out.print("Nuevo precio de venta: ");
        double precioVenta = leerDouble();

        System.out.print("¿Requiere receta? (s/n): ");
        boolean requiereReceta = sc.nextLine().trim().equalsIgnoreCase("s");

        System.out.print("¿Activo? (s/n): ");
        boolean activo = sc.nextLine().trim().equalsIgnoreCase("s");

        controller.actualizarInventario(id, nombreProducto, tipoProductoId, descripcion, fabricante,
                proveedorId, lote, cantidadStock, stockMinimo, unidadMedida, fechaVencimiento,
                precioCompra, precioVenta, requiereReceta, activo);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Producto ---");
        System.out.print("ID del producto a eliminar: ");
        int id = leerEntero();
        controller.eliminarInventario(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Productos en Inventario ---");
        controller.listarInventario();
    }

    private int leerEntero() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número decimal válido: ");
            }
        }
    }
}

