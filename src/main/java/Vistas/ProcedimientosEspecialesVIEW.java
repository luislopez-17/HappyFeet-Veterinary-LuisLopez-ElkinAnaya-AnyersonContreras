/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.ProcedimientosEspecialesController;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import model.ProcedimientosEspeciales;

/**
 *
 * @author usuario
 */
public class ProcedimientosEspecialesVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final ProcedimientosEspecialesController controller = new ProcedimientosEspecialesController();
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PROCEDIMIENTOS ESPECIALES =====");
            System.out.println("1. Agregar procedimiento");
            System.out.println("2. Actualizar procedimiento");
            System.out.println("3. Eliminar procedimiento");
            System.out.println("4. Listar procedimientos");
            System.out.println("5. Finalizar procedimiento");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 5 -> finalizarProcedimiento();
                case 0 -> System.out.println("Saliendo del módulo de procedimientos...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Procedimiento Especial ---");
        System.out.print("ID de la mascota: ");
        int mascotaId = leerEntero();
        System.out.print("ID del veterinario: ");
        int veterinarioId = leerEntero();
        System.out.print("Tipo de procedimiento: ");
        String tipoProcedimiento = sc.nextLine();
        System.out.print("Nombre del procedimiento: ");
        String nombreProcedimiento = sc.nextLine();
        System.out.print("Fecha y hora (yyyy-MM-dd HH:mm:ss): ");
        String fechaHoraStr = sc.nextLine();
        System.out.print("Duración en minutos: ");
        int duracionMinutos = leerEntero();
        System.out.print("Información preoperatoria: ");
        String informacionPre = sc.nextLine();
        System.out.print("Detalle del procedimiento: ");
        String detalle = sc.nextLine();
        System.out.print("Complicaciones: ");
        String complicaciones = sc.nextLine();
        System.out.print("Seguimiento postoperatorio: ");
        String seguimiento = sc.nextLine();
        System.out.print("Próximo control (yyyy-MM-dd): ");
        String proximoControlStr = sc.nextLine();
        Date proximoControl = null;
        if (!proximoControlStr.trim().isEmpty()) {
            proximoControl = Date.valueOf(proximoControlStr);
        }
        System.out.print("Estado (Programado / EnEjecucion / Completado / Cancelado): ");
        String estadoStr = sc.nextLine();
        System.out.print("Costo: ");
        double costo = leerDouble();

        controller.agregarProcedimiento(mascotaId, veterinarioId, tipoProcedimiento, nombreProcedimiento,
                fechaHoraStr, duracionMinutos, informacionPre, detalle, complicaciones, seguimiento,
                proximoControl, estadoStr, costo);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Procedimiento Especial ---");
        System.out.print("ID del procedimiento a actualizar: ");
        int id = leerEntero();
        System.out.print("ID de la mascota: ");
        int mascotaId = leerEntero();
        System.out.print("ID del veterinario: ");
        int veterinarioId = leerEntero();
        System.out.print("Tipo de procedimiento: ");
        String tipoProcedimiento = sc.nextLine();
        System.out.print("Nombre del procedimiento: ");
        String nombreProcedimiento = sc.nextLine();
        System.out.print("Fecha y hora (yyyy-MM-dd HH:mm:ss): ");
        String fechaHoraStr = sc.nextLine();
        System.out.print("Duración en minutos: ");
        int duracionMinutos = leerEntero();
        System.out.print("Información preoperatoria: ");
        String informacionPre = sc.nextLine();
        System.out.print("Detalle del procedimiento: ");
        String detalle = sc.nextLine();
        System.out.print("Complicaciones: ");
        String complicaciones = sc.nextLine();
        System.out.print("Seguimiento postoperatorio: ");
        String seguimiento = sc.nextLine();
        System.out.print("Próximo control (yyyy-MM-dd): ");
        String proximoControlStr = sc.nextLine();
        Date proximoControl = null;
        if (!proximoControlStr.trim().isEmpty()) {
            proximoControl = Date.valueOf(proximoControlStr);
        }
        System.out.print("Estado (Programado / EnEjecucion / Completado / Cancelado): ");
        String estadoStr = sc.nextLine();
        System.out.print("Costo: ");
        double costo = leerDouble();

        controller.actualizarProcedimiento(id, mascotaId, veterinarioId, tipoProcedimiento, nombreProcedimiento,
                fechaHoraStr, duracionMinutos, informacionPre, detalle, complicaciones, seguimiento,
                proximoControl, estadoStr, costo);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Procedimiento Especial ---");
        System.out.print("ID del procedimiento a eliminar: ");
        int id = leerEntero();
        controller.eliminarProcedimiento(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Procedimientos Especiales ---");
        List<ProcedimientosEspeciales> lista = controller.listar();
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay procedimientos registrados.");
        } else {
            lista.forEach(System.out::println);
        }
    }
    
    private void finalizarProcedimiento() {
        System.out.println("==== FINALIZAR PROCEDIMIENTO ====");
        System.out.print("Ingrese el ID del procedimiento: ");
        int id = sc.nextInt();
        controller.finalizarProcedimiento(id);
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número decimal válido: ");
            }
        }
    }
}
