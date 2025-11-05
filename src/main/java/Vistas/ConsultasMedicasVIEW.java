/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

/**
 *
 * @author usuario
 */
import Controller.ConsultasMedicasController;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import model.ConsultaMedica;

/**
 *
 * @author usuario
 */
public class ConsultasMedicasVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final ConsultasMedicasController controller = new ConsultasMedicasController();
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ CONSULTAS MÉDICAS =====");
            System.out.println("1. Agregar consulta");
            System.out.println("2. Actualizar consulta");
            System.out.println("3. Eliminar consulta");
            System.out.println("4. Listar consultas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo consultas médicas...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- Agregar Consulta Médica ---");
        System.out.print("ID de la mascota: ");
        int idMascota = leerEntero();
        System.out.print("ID del veterinario: ");
        int idVeterinario = leerEntero();
        System.out.print("ID de la cita (opcional, presione Enter para omitir): ");
        String citaStr = sc.nextLine().trim();
        Integer idCita = citaStr.isEmpty() ? null : Integer.parseInt(citaStr);
        System.out.print("Fecha y hora (yyyy-MM-dd HH:mm:ss): ");
        String fechaStr = sc.nextLine();
        System.out.print("Motivo: ");
        String motivo = sc.nextLine();
        System.out.print("Síntomas: ");
        String sintomas = sc.nextLine();
        System.out.print("Diagnóstico: ");
        String diagnostico = sc.nextLine();
        System.out.print("Recomendaciones: ");
        String recomendaciones = sc.nextLine();
        System.out.print("Observaciones: ");
        String observaciones = sc.nextLine();
        System.out.print("Peso registrado (kg): ");
        double peso = leerDouble();
        System.out.print("Temperatura (°C): ");
        double temperatura = leerDouble();

        Timestamp fechaHora = Timestamp.valueOf(fechaStr);

        controller.agregar(idMascota, idVeterinario, idCita, fechaStr, motivo, sintomas, diagnostico, recomendaciones, observaciones, peso, temperatura);
    }

    private void actualizar() {
        System.out.println("\n--- Actualizar Consulta Médica ---");
        System.out.print("ID de la consulta a actualizar: ");
        int id = leerEntero();
        System.out.print("Nuevo ID de la mascota: ");
        int idMascota = leerEntero();
        System.out.print("Nuevo ID del veterinario: ");
        int idVeterinario = leerEntero();
        System.out.print("Nuevo ID de la cita (opcional, Enter para omitir): ");
        String citaStr = sc.nextLine().trim();
        Integer idCita = citaStr.isEmpty() ? null : Integer.parseInt(citaStr);
        System.out.print("Nueva fecha y hora (yyyy-MM-dd HH:mm:ss): ");
        String fechaStr = sc.nextLine();
        System.out.print("Nuevo motivo: ");
        String motivo = sc.nextLine();
        System.out.print("Nuevos síntomas: ");
        String sintomas = sc.nextLine();
        System.out.print("Nuevo diagnóstico: ");
        String diagnostico = sc.nextLine();
        System.out.print("Nuevas recomendaciones: ");
        String recomendaciones = sc.nextLine();
        System.out.print("Nuevas observaciones: ");
        String observaciones = sc.nextLine();
        System.out.print("Nuevo peso registrado (kg): ");
        double peso = leerDouble();
        System.out.print("Nueva temperatura (°C): ");
        double temperatura = leerDouble();

        Timestamp fechaHora = Timestamp.valueOf(fechaStr);

        controller.actualizar(id, idMascota, idVeterinario, idCita, fechaStr, motivo, sintomas, diagnostico, recomendaciones, observaciones, peso, temperatura);
    }

    private void eliminar() {
        System.out.println("\n--- Eliminar Consulta Médica ---");
        System.out.print("ID de la consulta a eliminar: ");
        int id = leerEntero();
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Consultas Médicas ---");
        List<ConsultaMedica> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay consultas médicas registradas.");
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
