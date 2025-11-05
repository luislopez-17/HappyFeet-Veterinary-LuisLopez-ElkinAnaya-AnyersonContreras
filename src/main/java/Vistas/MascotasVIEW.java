/**
 *
 * @author usuario
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.MascotasController;
import java.util.List;
import java.util.Scanner;
import model.Mascotas;

/**
 *
 * @author usuario
 */
public class MascotasVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final MascotasController controller = new MascotasController();
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ MASCOTAS =====");
            System.out.println("1. Agregar mascota");
            System.out.println("2. Actualizar mascota");
            System.out.println("3. Eliminar mascota");
            System.out.println("4. Listar mascotas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo mascotas...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Mascota ---");
        System.out.print("ID del dueño: ");
        int duenoId = leerEntero();
        System.out.print("Nombre de la mascota: ");
        String nombre = sc.nextLine();
        System.out.print("ID de la raza: ");
        int razaId = leerEntero();
        System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
        String fechaNacimientoStr = sc.nextLine();
        System.out.print("Sexo (Macho/Hembra): ");
        String sexo = sc.nextLine();
        System.out.print("Peso actual: ");
        double pesoActual = leerDouble();
        System.out.print("Microchip: ");
        String microchip = sc.nextLine();
        System.out.print("Tatuaje: ");
        String tatuaje = sc.nextLine();
        System.out.print("URL de la foto: ");
        String urlFoto = sc.nextLine();
        System.out.print("Alergias: ");
        String alergias = sc.nextLine();
        System.out.print("Condiciones preexistentes: ");
        String condiciones = sc.nextLine();

        controller.agregar(duenoId, nombre, razaId, fechaNacimientoStr, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condiciones);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Mascota ---");
        System.out.print("ID de la mascota a actualizar: ");
        int id = leerEntero();
        System.out.print("Nuevo ID del dueño: ");
        int duenoId = leerEntero();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo ID de raza: ");
        int razaId = leerEntero();
        System.out.print("Nueva fecha de nacimiento (yyyy-mm-dd): ");
        String fechaNacimientoStr = sc.nextLine();
        System.out.print("Sexo (Macho/Hembra): ");
        String sexo = sc.nextLine().trim();
        System.out.print("Nuevo peso actual: ");
        double pesoActual = leerDouble();
        System.out.print("Nuevo microchip: ");
        String microchip = sc.nextLine();
        System.out.print("Nuevo tatuaje: ");
        String tatuaje = sc.nextLine();
        System.out.print("Nueva URL de la foto: ");
        String urlFoto = sc.nextLine();
        System.out.print("Alergias: ");
        String alergias = sc.nextLine();
        System.out.print("Condiciones preexistentes: ");
        String condiciones = sc.nextLine();
        System.out.print("¿Activo? (true/false): ");
        boolean activo = Boolean.parseBoolean(sc.nextLine());

        controller.actualizar(id, duenoId, nombre, razaId, fechaNacimientoStr, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condiciones, activo);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Mascota ---");
        System.out.print("ID de la mascota a eliminar: ");
        int id = leerEntero();
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Mascotas ---");
        List<Mascotas> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay mascotas registrados.");
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
