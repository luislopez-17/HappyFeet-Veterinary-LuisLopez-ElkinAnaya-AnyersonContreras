/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import Controller.ClubMascotasController;
import model.ClubMascotas;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class ClubMascotasVIEW {
     private static final Scanner sc = new Scanner(System.in);
    private final ClubMascotasController controller = new ClubMascotasController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Club de Mascotas ---");
            System.out.println("1. Agregar club");
            System.out.println("2. Actualizar club");
            System.out.println("3. Eliminar club");
            System.out.println("4. Listar clubes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Error: Debe ingresar un número. Intente de nuevo: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarClub();
                case 2 -> actualizarClub();
                case 3 -> eliminarClub();
                case 4 -> listarClubes();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 0);
    }

    private void agregarClub() {
        System.out.println("\n--- Agregar Club de Mascotas ---");
        int duenoId = leerEntero("ID del dueño");
        int puntosAcum = leerEnteroOpcional("Puntos acumulados");
        int puntosCanj = leerEnteroOpcional("Puntos canjeados");
        int puntosDisp = leerEnteroOpcional("Puntos disponibles");

        System.out.print("Nivel (Bronce por defecto): ");
        String nivel = sc.nextLine().strip();
        if (nivel.isBlank()) nivel = "Bronce";

        Date fechaInscripcion = leerFecha("Fecha de inscripción (yyyy-mm-dd)");
        boolean activo = true;

        controller.agregar(duenoId, puntosAcum, puntosCanj, puntosDisp, nivel, fechaInscripcion, activo);
    }

    private void actualizarClub() {
        System.out.println("\n--- Actualizar Club de Mascotas ---");
        int id = leerEntero("ID del club a actualizar");

        if (!controller.existePorId(id)) {
            System.out.println("Error: No se encontró un club con ID = " + id);
            return;
        }

        int duenoId = leerEntero("ID del dueño");
        int puntosAcum = leerEnteroOpcional("Puntos acumulados");
        int puntosCanj = leerEnteroOpcional("Puntos canjeados");
        int puntosDisp = leerEnteroOpcional("Puntos disponibles");

        System.out.print("Nivel (Bronce por defecto): ");
        String nivel = sc.nextLine().strip();
        if (nivel.isBlank()) nivel = "Bronce";

        Date fechaInscripcion = leerFecha("Fecha de inscripción (yyyy-mm-dd)");
        boolean activo = leerBoolean("Activo? (true/false)");

        controller.actualizar(id, duenoId, puntosAcum, puntosCanj, puntosDisp, nivel, fechaInscripcion, activo);
    }

    private void eliminarClub() {
        System.out.println("\n--- Eliminar Club de Mascotas ---");
        int id = leerEntero("ID del club a eliminar");
        controller.eliminar(id);
    }

    private void listarClubes() {
        System.out.println("\n--- Lista de Clubes ---");
        List<ClubMascotas> lista = controller.listar();
        if (lista.isEmpty()) System.out.println("No hay clubes registrados.");
        else lista.forEach(System.out::println);
    }

    private int leerEntero(String mensaje) {
        int valor;
        do {
            System.out.print(mensaje + ": ");
            while (!sc.hasNextInt()) {
                System.out.print("Error: Debe ingresar un número válido. Intente de nuevo: ");
                sc.next();
            }
            valor = sc.nextInt();
            sc.nextLine();
            if (valor <= 0) System.out.println("Error: Debe ser mayor a 0.");
        } while (valor <= 0);
        return valor;
    }

    private int leerEnteroOpcional(String mensaje) {
        System.out.print(mensaje + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Error: Debe ingresar un número. Intente de nuevo: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor < 0 ? 0 : valor;
    }

    private Date leerFecha(String mensaje) {
        Date fecha = null;
        while (fecha == null) {
            System.out.print(mensaje + ": ");
            String input = sc.nextLine().strip();
            try {
                fecha = Date.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Fecha inválida. Formato correcto yyyy-mm-dd");
            }
        }
        return fecha;
    }

    private boolean leerBoolean(String mensaje) {
        Boolean valor = null;
        while (valor == null) {
            System.out.print(mensaje + ": ");
            String input = sc.nextLine().strip().toLowerCase();
            if (input.equals("true") || input.equals("t")) valor = true;
            else if (input.equals("false") || input.equals("f")) valor = false;
            else System.out.println("Error: Debe ingresar true o false.");
        }
        return valor;
    }
}
