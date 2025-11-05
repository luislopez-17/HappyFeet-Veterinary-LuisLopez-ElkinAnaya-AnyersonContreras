/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import dao.ServiciosDAO;
import model.Servicios;
import utils.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class ServiciosVIEW {
    private static final Scanner sc = new Scanner(System.in);
    private static final ServiciosDAO dao = new ServiciosDAO();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE SERVICIOS ===");
            System.out.println("1. Agregar servicio");
            System.out.println("2. Listar servicios");
            System.out.println("3. Actualizar servicio");
            System.out.println("4. Eliminar servicio");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 0 -> System.out.println("Saliendo del módulo de servicios...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void agregar() {
        System.out.println("\n--- Registrar nuevo servicio ---");

        System.out.print("Nombre del servicio: ");
        String nombre = sc.nextLine();
        if (nombre.isBlank()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }

        System.out.print("Descripción (opcional): ");
        String descripcion = sc.nextLine();

        System.out.print("Categoría (opcional): ");
        String categoria = sc.nextLine();

        System.out.print("Precio base: ");
        double precioBase = leerDouble();
        if (precioBase < 0) {
            System.out.println("Error: el precio base no puede ser negativo.");
            return;
        }

        System.out.print("Duración estimada en minutos (opcional, Enter si no aplica): ");
        String duracionStr = sc.nextLine();
        int duracion = duracionStr.isBlank() ? 0 : Integer.parseInt(duracionStr);

        System.out.print("¿Activo? (true/false, por defecto true): ");
        String activoStr = sc.nextLine();
        boolean activo = activoStr.isBlank() ? true : Boolean.parseBoolean(activoStr);

        Servicios servicio = new Servicios(nombre, descripcion, categoria, precioBase, duracion, activo);

        try (Connection con = ConexionDB.conectar()) {
            dao.agregar(servicio, con);
        } catch (SQLException e) {
            System.out.println("Error al agregar servicio: " + e.getMessage());
        }
    }

    private static void listar() {
        System.out.println("\n--- Lista de servicios registrados ---");
        List<Servicios> lista = dao.listar();

        if (lista.isEmpty()) {
            System.out.println("No hay servicios registrados en la base de datos.");
        } else {
            for (Servicios s : lista) {
                System.out.println("--------------------------------------");
                System.out.println("ID: " + s.getId());
                System.out.println("Nombre: " + s.getNombre());
                System.out.println("Descripción: " + s.getDescripcion());
                System.out.println("Categoría: " + s.getCategoria());
                System.out.println("Precio base: " + s.getPrecioBase());
                System.out.println("Duración estimada (min): " + s.getDuracionEstimadaMinutos());
                System.out.println("Activo: " + s.isActivo());
            }
        }
    }

    private static void actualizar() {
        System.out.println("\n--- Actualizar servicio ---");
        System.out.print("ID del servicio a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        if (nombre.isBlank()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }

        System.out.print("Nueva descripción (opcional): ");
        String descripcion = sc.nextLine();

        System.out.print("Nueva categoría (opcional): ");
        String categoria = sc.nextLine();

        System.out.print("Nuevo precio base: ");
        double precioBase = leerDouble();
        if (precioBase < 0) {
            System.out.println("Error: el precio base no puede ser negativo.");
            return;
        }

        System.out.print("Nueva duración estimada (minutos): ");
        int duracion = leerEntero();

        System.out.print("¿Activo? (true/false): ");
        String activoStr = sc.nextLine();
        boolean activo = activoStr.isBlank() ? true : Boolean.parseBoolean(activoStr);

        Servicios servicio = new Servicios(id, nombre, descripcion, categoria, precioBase, duracion, activo);
        dao.actualizar(servicio);
    }

    private static void eliminar() {
        System.out.println("\n--- Eliminar servicio ---");
        System.out.print("ID del servicio a eliminar: ");
        int id = leerEntero();

        Servicios servicio = new Servicios(id, "", "", "", 0, 0, false);
        dao.eliminar(servicio);
    }

    // --- Métodos auxiliares para validar entrada ---

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

    private static double leerDouble() {
        while (true) {
            try {
                String input = sc.nextLine();
                if (input.isBlank()) {
                    System.out.print("Por favor, ingrese un valor numérico válido: ");
                    continue;
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Valor no válido. Ingrese un número decimal: ");
            }
        }
    }
}


