package Vistas;

import Controller.HistorialController;
import java.util.List;
import java.util.Scanner;
import model.HistorialMedico;

public class HistorialVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final HistorialController controller = new HistorialController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ HISTORIAL MÉDICO =====");
            System.out.println("1. Agregar registro");
            System.out.println("2. Actualizar registro");
            System.out.println("3. Eliminar registro");
            System.out.println("4. Listar registros");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo de historial médico...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Historial Médico ---");
        System.out.print("ID de la mascota (obligatorio): ");
        int mascotaId = leerEntero();

        System.out.print("Fecha del evento (yyyy-MM-dd, obligatorio): ");
        String fechaEvento = sc.nextLine();

        System.out.print("ID del tipo de evento (obligatorio): ");
        int eventoTipoId = leerEntero();

        System.out.print("Descripción (obligatoria): ");
        String descripcion = sc.nextLine();

        System.out.print("Diagnóstico (puede dejar vacío): ");
        String diagnostico = sc.nextLine();

        System.out.print("Tratamiento recomendado (puede dejar vacío): ");
        String tratamiento = sc.nextLine();

        System.out.print("ID del veterinario (Enter si no aplica): ");
        String vetStr = sc.nextLine().trim();
        Integer veterinarioId = vetStr.isEmpty() ? null : Integer.parseInt(vetStr);

        System.out.print("ID de la consulta médica (Enter si no aplica): ");
        String consultaStr = sc.nextLine().trim();
        Integer consultaId = consultaStr.isEmpty() ? null : Integer.parseInt(consultaStr);

        System.out.print("ID del procedimiento especial (Enter si no aplica): ");
        String procStr = sc.nextLine().trim();
        Integer procedimientoId = procStr.isEmpty() ? null : Integer.parseInt(procStr);

        controller.agregar(mascotaId, fechaEvento, eventoTipoId, descripcion, diagnostico, tratamiento, veterinarioId, consultaId, procedimientoId);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Historial Médico ---");
        System.out.print("ID del historial a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo ID de la mascota (obligatorio): ");
        int mascotaId = leerEntero();

        System.out.print("Nueva fecha del evento (yyyy-MM-dd, obligatorio): ");
        String fechaEvento = sc.nextLine();

        System.out.print("Nuevo ID del tipo de evento (obligatorio): ");
        int eventoTipoId = leerEntero();

        System.out.print("Nueva descripción (obligatoria): ");
        String descripcion = sc.nextLine();

        System.out.print("Nuevo diagnóstico (puede dejar vacío): ");
        String diagnostico = sc.nextLine();

        System.out.print("Nuevo tratamiento recomendado (puede dejar vacío): ");
        String tratamiento = sc.nextLine();

        System.out.print("Nuevo ID del veterinario (Enter si no aplica): ");
        String vetStr = sc.nextLine().trim();
        Integer veterinarioId = vetStr.isEmpty() ? null : Integer.parseInt(vetStr);

        System.out.print("Nuevo ID de la consulta médica (Enter si no aplica): ");
        String consultaStr = sc.nextLine().trim();
        Integer consultaId = consultaStr.isEmpty() ? null : Integer.parseInt(consultaStr);

        System.out.print("Nuevo ID del procedimiento especial (Enter si no aplica): ");
        String procStr = sc.nextLine().trim();
        Integer procedimientoId = procStr.isEmpty() ? null : Integer.parseInt(procStr);

        controller.actualizar(id, mascotaId, fechaEvento, eventoTipoId, descripcion, diagnostico, tratamiento, veterinarioId, consultaId, procedimientoId);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Historial Médico ---");
        System.out.print("ID del historial a eliminar: ");
        int id = leerEntero();
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Registros de Historial Médico ---");
        List<HistorialMedico> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay registros de historial médico.");
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
