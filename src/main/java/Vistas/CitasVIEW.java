/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.CitasController;
import java.util.List;
import java.util.Scanner;
import model.Citas;

/**
 *
 * @author usuario
 */
public class CitasVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final CitasController controller = new CitasController();
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ CITAS =====");
            System.out.println("1. Agregar cita");
            System.out.println("2. Actualizar cita");
            System.out.println("3. Eliminar cita");
            System.out.println("4. Listar citas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo citas...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Cita ---");
        System.out.print("ID de la mascota: ");
        int mascotaId = leerEntero();
        System.out.print("ID del veterinario: ");
        String vetStr = sc.nextLine().trim();
        Integer veterinarioId = vetStr.isEmpty() ? 0 : Integer.parseInt(vetStr);
        System.out.print("Fecha y hora (yyyy-MM-dd HH:mm:ss): ");
        String fechaHora = sc.nextLine();
        System.out.print("Motivo: ");
        String motivo = sc.nextLine();
        System.out.print("ID del estado: ");
        int estadoId = leerEntero();
        System.out.print("Observaciones: ");
        String observaciones = sc.nextLine();

        controller.agregar(mascotaId, veterinarioId, fechaHora, motivo, estadoId, observaciones);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Cita ---");
        System.out.print("ID de la cita a actualizar: ");
        int id = leerEntero();
        System.out.print("Nuevo ID de la mascota: ");
        int mascotaId = leerEntero();
        System.out.print("Nuevo ID del veterinario: ");
        String vetStr = sc.nextLine().trim();
        Integer veterinarioId = vetStr.isEmpty() ? 0 : Integer.parseInt(vetStr);
        System.out.print("Nueva fecha y hora (yyyy-MM-dd HH:mm:ss): ");
        String fechaHora = sc.nextLine();
        System.out.print("Nuevo motivo: ");
        String motivo = sc.nextLine();
        System.out.print("Nuevo ID del estado: ");
        int estadoId = leerEntero();
        System.out.print("Nuevas observaciones: ");
        String observaciones = sc.nextLine();

        controller.actualizar(id, mascotaId, veterinarioId, fechaHora, motivo, estadoId, observaciones);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Cita ---");
        System.out.print("ID de la cita a eliminar: ");
        int id = leerEntero();
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Mascotas ---");
        List<Citas> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay citas registrados.");
        } else {
            lista.forEach(System.out::println);
        }
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
