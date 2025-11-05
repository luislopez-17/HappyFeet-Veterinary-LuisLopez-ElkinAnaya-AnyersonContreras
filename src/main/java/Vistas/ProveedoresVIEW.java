/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.ProveedoresController;
import java.util.List;
import java.util.Scanner;
import model.Proveedores;

public class ProveedoresVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final ProveedoresController controller = new ProveedoresController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PROVEEDORES =====");
            System.out.println("1. Agregar proveedor");
            System.out.println("2. Actualizar proveedor");
            System.out.println("3. Eliminar proveedor");
            System.out.println("4. Listar proveedores");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo proveedores...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Proveedor ---");
        System.out.print("Nombre de la empresa (obligatorio): ");
        String nombreEmpresa = sc.nextLine();
        System.out.print("Contacto: ");
        String contacto = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Sitio web: ");
        String sitioWeb = sc.nextLine();

        controller.agregar(nombreEmpresa, contacto, telefono, email, direccion, sitioWeb);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Proveedor ---");
        System.out.print("ID del proveedor a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre de la empresa (obligatorio): ");
        String nombreEmpresa = sc.nextLine();
        System.out.print("Nuevo contacto: ");
        String contacto = sc.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Nuevo email: ");
        String email = sc.nextLine();
        System.out.print("Nueva dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Nuevo sitio web: ");
        String sitioWeb = sc.nextLine();
        System.out.print("¿Activo? (true/false): ");
        boolean activo = Boolean.parseBoolean(sc.nextLine().trim());

        controller.actualizar(id, nombreEmpresa, contacto, telefono, email, direccion, sitioWeb, activo);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Proveedor ---");
        System.out.print("ID del proveedor a eliminar: ");
        int id = leerEntero();
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Proveedores ---");
        List<Proveedores> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
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
}
