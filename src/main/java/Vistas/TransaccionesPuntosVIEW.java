/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import Controller.TransaccionesPuntosController;
import model.TransaccionesPuntos;
import model.TransaccionesPuntos.Tipo;

import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class TransaccionesPuntosVIEW {
    
    private final Scanner sc = new Scanner(System.in);
    private final TransaccionesPuntosController controller = new TransaccionesPuntosController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Transacciones de Puntos ---");
            System.out.println("1. Agregar transacción");
            System.out.println("2. Actualizar transacción");
            System.out.println("3. Eliminar transacción");
            System.out.println("4. Listar transacciones");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Transacción ---");
        int clubId = leerId("Club Mascotas ID: ");
        int facturaId = leerIdOpcional("Factura ID (0 si no aplica): ");
        int puntos = leerEnteroPositivo("Puntos: ");
        Tipo tipo = leerTipo();
        Timestamp fecha = leerTimestamp("Fecha y hora (YYYY-MM-DD HH:MM:SS): ");
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        int saldoAnterior = leerEnteroPositivo("Saldo anterior: ");
        int saldoNuevo = leerEnteroPositivo("Saldo nuevo: ");

        controller.agregar(clubId, facturaId, puntos, tipo, fecha, descripcion, saldoAnterior, saldoNuevo);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Transacción ---");
        int id = leerId("ID de la transacción a actualizar: ");
        int clubId = leerId("Club Mascotas ID: ");
        int facturaId = leerIdOpcional("Factura ID (0 si no aplica): ");
        int puntos = leerEnteroPositivo("Puntos: ");
        Tipo tipo = leerTipo();
        Timestamp fecha = leerTimestamp("Fecha y hora (YYYY-MM-DD HH:MM:SS): ");
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        int saldoAnterior = leerEnteroPositivo("Saldo anterior: ");
        int saldoNuevo = leerEnteroPositivo("Saldo nuevo: ");

        controller.actualizar(id, clubId, facturaId, puntos, tipo, fecha, descripcion, saldoAnterior, saldoNuevo);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Transacción ---");
        int id = leerId("ID de la transacción a eliminar: ");
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Transacciones de Puntos ---");
        List<TransaccionesPuntos> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
        } else {
            for (TransaccionesPuntos t : lista) {
                System.out.println(t);
            }
        }
    }

    // ---------- MÉTODOS AUXILIARES ----------

    private int leerEntero() {
        while (true) {
            try {
                int num = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                return num;
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Intente de nuevo: ");
                sc.nextLine();
            }
        }
    }

    private int leerEnteroPositivo(String mensaje) {
        int valor;
        do {
            System.out.print(mensaje);
            valor = leerEntero();
            if (valor < 0) System.out.println("Debe ser un número positivo.");
        } while (valor < 0);
        return valor;
    }

    private int leerId(String mensaje) {
        int valor;
        do {
            System.out.print(mensaje);
            valor = leerEntero();
            if (valor <= 0) System.out.println("ID inválido, debe ser mayor a 0.");
        } while (valor <= 0);
        return valor;
    }

    private int leerIdOpcional(String mensaje) {
        System.out.print(mensaje);
        int valor = leerEntero();
        return (valor <= 0) ? 0 : valor;
    }

    private Timestamp leerTimestamp(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String input = sc.nextLine();
                return Timestamp.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Formato inválido. Use YYYY-MM-DD HH:MM:SS");
            }
        }
    }

    private Tipo leerTipo() {
        while (true) {
            System.out.println("Tipos disponibles: Ganados, Canjeados, Expirados, Ajuste");
            System.out.print("Seleccione tipo: ");
            String input = sc.nextLine().trim();
            try {
                return Tipo.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido. Intente de nuevo.");
            }
        }
    }
}
