/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.FacturaController;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Factura;


/**
 *
 * @author ELKIN
 */
public class FacturaVIEW {

    private FacturaController facturaController;
    private Scanner sc;

    public FacturaVIEW() {
        facturaController = new FacturaController();
        sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE FACTURAS ===");
            System.out.println("1. Agregar factura");
            System.out.println("2. Listar facturas");
            System.out.println("3. Actualizar factura");
            System.out.println("4. Eliminar factura");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregarFactura();
                case 2 -> listarFacturas();
                case 3 -> actualizarFactura();
                case 4 -> eliminarFactura();
                case 0 -> System.out.println("Saliendo del módulo de facturas...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }

    // === AGREGAR ===
    private void agregarFactura() {
        System.out.println("\n--- Registrar nueva factura ---");

        System.out.print("ID del dueño: ");
        int duenoId = leerEntero();

        System.out.print("Número de factura: ");
        String numeroFactura = sc.nextLine();

        System.out.print("Subtotal: ");
        double subtotal = leerDouble();

        System.out.print("Impuesto: ");
        double impuesto = leerDouble();

        System.out.print("Descuento: ");
        double descuento = leerDouble();

        // 💡 Total se calcula automáticamente, solo se muestra el resultado
        double totalCalculado = subtotal + impuesto - descuento;
        if (totalCalculado < 0) totalCalculado = 0;
        System.out.println("Total calculado automáticamente: " + totalCalculado);

        System.out.print("Método de pago (Efectivo/Tarjeta/Transferencia/Mixto): ");
        String metodoPago = sc.nextLine();

        System.out.print("Estado (Pendiente/Pagada/Anulada): ");
        String estado = sc.nextLine();

        System.out.print("Observaciones (opcional): ");
        String observaciones = sc.nextLine();

        facturaController.agregarFactura(duenoId, numeroFactura, new Date(),
                subtotal, impuesto, descuento, metodoPago, estado, observaciones);
    }

    // === LISTAR ===
    private void listarFacturas() {
        System.out.println("\n--- Lista de facturas registradas ---");
        List<Factura> lista = facturaController.listarFacturas();

        if (lista.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            for (Factura f : lista) {
                System.out.println("--------------------------------------------");
                System.out.println("ID: " + f.getId());
                System.out.println("Dueño ID: " + f.getDuenoId());
                System.out.println("Número de factura: " + f.getNumeroFactura());
                System.out.println("Fecha emisión: " + f.getFechaEmision());
                System.out.println("Subtotal: " + f.getSubtotal());
                System.out.println("Impuesto: " + f.getImpuesto());
                System.out.println("Descuento: " + f.getDescuento());
                System.out.println("Total: " + f.getTotal());
                System.out.println("Método de pago: " + f.getMetodoPago());
                System.out.println("Estado: " + f.getEstado());
                System.out.println("Observaciones: " + f.getObservaciones());
            }
        }
    }

    // === ACTUALIZAR ===
    private void actualizarFactura() {
        System.out.println("\n--- Actualizar factura ---");

        System.out.print("ID de la factura a actualizar: ");
        int id = leerEntero();

        System.out.print("ID del dueño: ");
        int duenoId = leerEntero();

        System.out.print("Número de factura: ");
        String numeroFactura = sc.nextLine();

        System.out.print("Subtotal: ");
        double subtotal = leerDouble();

        System.out.print("Impuesto: ");
        double impuesto = leerDouble();

        System.out.print("Descuento: ");
        double descuento = leerDouble();

        double totalCalculado = subtotal + impuesto - descuento;
        if (totalCalculado < 0) totalCalculado = 0;
        System.out.println("Total recalculado automáticamente: " + totalCalculado);

        System.out.print("Método de pago (Efectivo/Tarjeta/Transferencia/Mixto): ");
        String metodoPago = sc.nextLine();

        System.out.print("Estado (Pendiente/Pagada/Anulada): ");
        String estado = sc.nextLine();

        System.out.print("Observaciones (opcional): ");
        String observaciones = sc.nextLine();

        facturaController.actualizarFactura(id, duenoId, numeroFactura, new Date(),
                subtotal, impuesto, descuento, metodoPago, estado, observaciones);
    }

    // === ELIMINAR ===
    private void eliminarFactura() {
        System.out.println("\n--- Eliminar factura ---");
        System.out.print("ID de la factura a eliminar: ");
        int id = leerEntero();

        facturaController.eliminarFactura(id);
    }

    // === MÉTODOS AUXILIARES ===
    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número entero válido: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un valor numérico válido: ");
            }
        }
    }
}
