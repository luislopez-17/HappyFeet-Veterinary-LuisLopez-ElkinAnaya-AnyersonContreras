/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import dao.MascotaAdopcionDAO;
import model.MascotaAdopcion;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class MascotaAdopcionVIEW {
     private static final Scanner sc = new Scanner(System.in);
    private static final MascotaAdopcionDAO dao = new MascotaAdopcionDAO();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE MASCOTAS EN ADOPCIÓN ===");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void agregar() {
        System.out.println("\n--- Agregar nueva mascota en adopción ---");
        System.out.print("ID de la mascota: ");
        int mascotaId = leerEntero();

        System.out.print("Fecha de ingreso (AAAA-MM-DD): ");
        Date fechaIngreso = leerFecha();

        System.out.print("Motivo de ingreso: ");
        String motivoIngreso = sc.nextLine();

        System.out.print("Estado (Disponible, En Proceso, Adoptada, Retirada): ");
        String estado = sc.nextLine();

        System.out.print("Historia: ");
        String historia = sc.nextLine();

        System.out.print("Temperamento: ");
        String temperamento = sc.nextLine();

        System.out.print("Necesidades especiales: ");
        String necesidades = sc.nextLine();

        System.out.print("URL foto adicional: ");
        String foto = sc.nextLine();

        MascotaAdopcion m = new MascotaAdopcion(mascotaId, fechaIngreso, motivoIngreso,
                estado, historia, temperamento, necesidades, foto);

        dao.agregar(m);
        System.out.println("Registro agregado correctamente.");
    }

    private static void listar() {
        System.out.println("\n--- Lista de mascotas en adopción ---");
        List<MascotaAdopcion> lista = dao.listar();

        if (lista.isEmpty()) {
            System.out.println("No hay registros en la base de datos.");
        } else {
            for (MascotaAdopcion m : lista) {
                System.out.println(m);
            }
        }
    }

    private static void actualizar() {
        System.out.println("\n--- Actualizar mascota en adopción ---");
        System.out.print("Ingrese el ID del registro a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo ID de la mascota: ");
        int mascotaId = leerEntero();

        System.out.print("Nueva fecha de ingreso (AAAA-MM-DD): ");
        Date fechaIngreso = leerFecha();

        System.out.print("Nuevo motivo de ingreso: ");
        String motivoIngreso = sc.nextLine();

        System.out.print("Nuevo estado: ");
        String estado = sc.nextLine();

        System.out.print("Nueva historia: ");
        String historia = sc.nextLine();

        System.out.print("Nuevo temperamento: ");
        String temperamento = sc.nextLine();

        System.out.print("Nuevas necesidades especiales: ");
        String necesidades = sc.nextLine();

        System.out.print("Nueva URL foto adicional: ");
        String foto = sc.nextLine();

        MascotaAdopcion m = new MascotaAdopcion(id, mascotaId, fechaIngreso, motivoIngreso,
                estado, historia, temperamento, necesidades, foto);

        dao.actualizar(m);
    }

    private static void eliminar() {
        System.out.println("\n--- Eliminar mascota en adopción ---");
        System.out.print("Ingrese el ID del registro a eliminar: ");
        int id = leerEntero();

        dao.eliminar(id);
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

    private static Date leerFecha() {
        while (true) {
            try {
                String input = sc.nextLine();
                return Date.valueOf(input); // Convierte String "AAAA-MM-DD" a java.sql.Date
            } catch (IllegalArgumentException e) {
                System.out.print("Formato inválido. Ingrese fecha como AAAA-MM-DD: ");
            }
        }
    }
}
