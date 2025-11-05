/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.DuenosController;
import java.util.List;
import java.util.Scanner;
import model.Duenos;

/**
 *
 * @author usuario
 */
public class DuenosVIEW {
        private final Scanner sc = new Scanner(System.in);
    private final DuenosController controller = new DuenosController();
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ DUEÑOS =====");
            System.out.println("1. Agregar dueño");
            System.out.println("2. Actualizar dueño");
            System.out.println("3. Eliminar dueño");
            System.out.println("4. Listar dueños");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();


            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo dueños...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Dueño ---");
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Documento de identidad: ");
        String documento = sc.nextLine();
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Correo electrónico: ");
        String email = sc.nextLine();
        System.out.print("Contacto de emergencia: ");
        String contacto = sc.nextLine();
        
        controller.agregar(nombre, documento, direccion, telefono, email, contacto);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Dueño ---");
        System.out.print("ID del dueño a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo documento de identidad: ");
        String documento = sc.nextLine();
        System.out.print("Nueva dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Nuevo correo electrónico: ");
        String email = sc.nextLine();
        System.out.print("Nuevo contacto de emergencia: ");
        String contacto = sc.nextLine();
        System.out.print("¿Activo? (true/false): ");
        boolean activo = sc.nextBoolean();
        sc.nextLine();

        controller.actualizar(id, nombre, documento, direccion, telefono, email, contacto, activo);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Dueño ---");
        System.out.print("ID del dueño a eliminar: ");
        int id = leerEntero();
        
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Dueños ---");
        List<Duenos> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay dueños registrados.");
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

