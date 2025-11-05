/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import dao.MovimientosInventarioDAO;
import model.MovimientoInventario;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class MovimientosInventarioVIEW {
    private static final Scanner sc = new Scanner(System.in);
    private static final MovimientosInventarioDAO dao = new MovimientosInventarioDAO();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE MOVIMIENTOS DE INVENTARIO ===");
            System.out.println("1. Agregar movimiento");
            System.out.println("2. Listar movimientos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> listar();
                case 0 -> System.out.println("Saliendo del módulo de movimientos...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void agregar() {
        System.out.println("\n--- Registrar nuevo movimiento ---");

        System.out.print("ID del producto: ");
        int productoId = leerEntero();

        System.out.print("Tipo de movimiento (Entrada / Salida / Ajuste / Vencimiento): ");
        String tipo = sc.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = leerEntero();

        System.out.print("Stock anterior: ");
        int stockAnterior = leerEntero();

        System.out.print("Stock nuevo: ");
        int stockNuevo = leerEntero();

        System.out.print("Motivo: ");
        String motivo = sc.nextLine();

        System.out.print("ID de consulta (opcional, presione Enter si no aplica): ");
        String refConsultaStr = sc.nextLine();
        Integer refConsulta = refConsultaStr.isBlank() ? null : Integer.parseInt(refConsultaStr);

        System.out.print("ID de procedimiento (opcional, presione Enter si no aplica): ");
        String refProcStr = sc.nextLine();
        Integer refProcedimiento = refProcStr.isBlank() ? null : Integer.parseInt(refProcStr);

        System.out.print("Usuario responsable: ");
        String usuario = sc.nextLine();

        Date fechaActual = new Date();
        Timestamp fechaTimestamp = new Timestamp(fechaActual.getTime());

        MovimientoInventario mi = new MovimientoInventario(
                productoId, tipo, cantidad, stockAnterior, stockNuevo,
                motivo, refConsulta, refProcedimiento, usuario, fechaTimestamp
        );

        dao.agregar(mi);
        System.out.println("Movimiento registrado correctamente.");
    }

    private static void listar() {
        System.out.println("\n--- Lista de movimientos registrados ---");
        List<MovimientoInventario> lista = dao.listar();

        if (lista.isEmpty()) {
            System.out.println("No hay movimientos registrados en la base de datos.");
        } else {
            for (MovimientoInventario mi : lista) {
                System.out.println("--------------------------------------");
                System.out.println("ID: " + mi.getId());
                System.out.println("Producto ID: " + mi.getProductoId());
                System.out.println("Tipo: " + mi.getTipoMovimiento());
                System.out.println("Cantidad: " + mi.getCantidad());
                System.out.println("Stock anterior: " + mi.getStockAnterior());
                System.out.println("Stock nuevo: " + mi.getStockNuevo());
                System.out.println("Motivo: " + mi.getMotivo());
                System.out.println("Consulta ref: " + mi.getReferenciaConsultaId());
                System.out.println("Procedimiento ref: " + mi.getReferenciaProcedimientoId());
                System.out.println("Usuario: " + mi.getUsuario());
                System.out.println("Fecha movimiento: " + mi.getFechaMovimiento());
            }
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                String input = sc.nextLine();
                if (input.isBlank()) {
                    System.out.print("Por favor, ingrese un número válido: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Valor no válido. Ingrese un número: ");
            }
        }
    }
}


