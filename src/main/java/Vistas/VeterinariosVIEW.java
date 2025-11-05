/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.VeterinarioController;
import java.util.Scanner;
import java.util.List;
import model.Veterinarios;

/**
 *
 * @author usuario
 */
public class VeterinariosVIEW{
    private final Scanner sc = new Scanner(System.in);
    private final VeterinarioController controller = new VeterinarioController();
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ VETERINARIOS =====");
            System.out.println("1. Agregar veterinario");
            System.out.println("2. Actualizar veterinario");
            System.out.println("3. Eliminar veterinario");
            System.out.println("4. Listar veterinarios");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo veterinarios...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Veterinario ---");
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Documento de identidad: ");
        String documento = sc.nextLine();
        System.out.print("Licencia profesional: ");
        String licencia = sc.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Correo electrónico: ");
        String email = sc.nextLine();
        System.out.print("Fecha de contratación (yyyy-MM-dd o vacío): ");
        String fecha = sc.nextLine();
        
        controller.agregar(nombre, documento, licencia, especialidad, telefono, email, fecha);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Veterinario ---");
        System.out.print("ID del veterinario a actualizar: ");
        int id = leerEntero();
        
        System.out.print("Nuevo nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo documento de identidad: ");
        String documento = sc.nextLine();
        System.out.print("Nueva licencia profesional: ");
        String licencia = sc.nextLine();
        System.out.print("Nueva especialidad: ");
        String especialidad = sc.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Nuevo correo electrónico: ");
        String email = sc.nextLine();
        System.out.print("Nueva fecha de contratación (yyyy-MM-dd o vacío): ");
        String fecha = sc.nextLine();
        System.out.print("¿Activo? (true/false): ");
        boolean activo = sc.nextBoolean();
        sc.nextLine();

        controller.actualizar(id, nombre, documento, licencia, especialidad, telefono, email, fecha, activo);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Veterinario ---");
        System.out.print("ID del veterinario a eliminar: ");
        int id = leerEntero();
        
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Veterinarios ---");
        List<Veterinarios> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
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

