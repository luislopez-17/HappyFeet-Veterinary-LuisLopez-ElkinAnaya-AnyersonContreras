/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import Controller.JornadaVacunacionController;
import model.JornadasVacunaciones;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class JornadaVacunacionVIEW {
    private static final Scanner sc = new Scanner(System.in);
    private final JornadaVacunacionController controller = new JornadaVacunacionController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ JORNADAS DE VACUNACIÓN ===");
            System.out.println("1. Agregar jornada");
            System.out.println("2. Actualizar jornada");
            System.out.println("3. Eliminar jornada");
            System.out.println("4. Listar jornadas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();
            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del menú...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- AGREGAR JORNADA ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        Date fecha = leerFecha("Fecha (yyyy-mm-dd): ");

        Time horaInicio = leerHora("Hora inicio (hh:mm:ss) o dejar vacío: ");
        Time horaFin = leerHora("Hora fin (hh:mm:ss) o dejar vacío: ");

        System.out.print("Ubicación (opcional): ");
        String ubicacion = sc.nextLine();

        System.out.print("Descripción (opcional): ");
        String descripcion = sc.nextLine();

        System.out.print("Capacidad máxima: ");
        int capacidad = leerEntero();

        System.out.println("Estado: 1-Planificada 2-En_Curso 3-Finalizada 4-Cancelada");
        int est = leerEntero();
        JornadasVacunaciones.Estado estado = switch (est) {
            case 1 -> JornadasVacunaciones.Estado.Planificada;
            case 2 -> JornadasVacunaciones.Estado.En_Curso;
            case 3 -> JornadasVacunaciones.Estado.Finalizada;
            case 4 -> JornadasVacunaciones.Estado.Cancelada;
            default -> JornadasVacunaciones.Estado.Planificada;
        };

        controller.agregar(nombre, fecha, horaInicio, horaFin, ubicacion, descripcion, capacidad, estado);
    }

    private void actualizar() {
        System.out.println("\n--- ACTUALIZAR JORNADA ---");
        
        // Pedir ID primero y validar existencia
        System.out.print("ID de la jornada a actualizar: ");
        int id = leerEntero();

        if (!controller.existeId(id)) {
            System.out.println("Error: No se encontró una jornada con ID = " + id);
            return; 
        }
        
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        Date fecha = leerFecha("Fecha (yyyy-mm-dd): ");

        Time horaInicio = leerHora("Hora inicio (hh:mm:ss) o dejar vacío: ");
        Time horaFin = leerHora("Hora fin (hh:mm:ss) o dejar vacío: ");

        System.out.print("Ubicación (opcional): ");
        String ubicacion = sc.nextLine();

        System.out.print("Descripción (opcional): ");
        String descripcion = sc.nextLine();

        System.out.print("Capacidad máxima: ");
        int capacidad = leerEntero();

        System.out.println("Estado: 1-Planificada 2-En_Curso 3-Finalizada 4-Cancelada");
        int est = leerEntero();
        JornadasVacunaciones.Estado estado = switch (est) {
            case 1 -> JornadasVacunaciones.Estado.Planificada;
            case 2 -> JornadasVacunaciones.Estado.En_Curso;
            case 3 -> JornadasVacunaciones.Estado.Finalizada;
            case 4 -> JornadasVacunaciones.Estado.Cancelada;
            default -> JornadasVacunaciones.Estado.Planificada;
        };

        controller.actualizar(id, nombre, fecha, horaInicio, horaFin, ubicacion, descripcion, capacidad, estado);
    }

    private void eliminar() {
        System.out.println("\n--- ELIMINAR JORNADA ---");
        System.out.print("ID de la jornada a eliminar: ");
        int id = leerEntero();
        controller.eliminar(id);
    }

    private void listar() {
        System.out.println("\n--- LISTA DE JORNADAS ---");
        List<JornadasVacunaciones> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay jornadas registradas.");
        } else {
            for (JornadasVacunaciones j : lista) {
                System.out.println(j);
            }
        }
    }

    // Métodos auxiliares para lectura segura

    private int leerEntero() {
        while (true) {
            try {
                String linea = sc.nextLine();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }

    private Date leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String linea = sc.nextLine();
                return Date.valueOf(linea);
            } catch (IllegalArgumentException e) {
                System.out.println("Fecha inválida. Formato esperado yyyy-mm-dd. Intente nuevamente.");
            }
        }
    }

    private Time leerHora(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine();
            if (linea.isBlank()) return null;
            try {
                return Time.valueOf(linea);
            } catch (IllegalArgumentException e) {
                System.out.println("Hora inválida. Formato esperado hh:mm:ss. Intente nuevamente.");
            }
        }
    }
}
