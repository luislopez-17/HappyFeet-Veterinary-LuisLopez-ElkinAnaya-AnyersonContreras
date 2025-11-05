/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import Controller.AdopcionController;
import model.Adopcion;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class AdopcionVIEW {
    private final AdopcionController controller = new AdopcionController();
    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 5) {
            System.out.println("\n===== MENU ADOPCIONES =====");
            System.out.println("1. Agregar adopción");
            System.out.println("2. Modificar adopción");
            System.out.println("3. Eliminar adopción");
            System.out.println("4. Listar adopciones");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    agregarAdopcion();
                    break;
                case 2:
                    modificarAdopcion();
                    break;
                case 3:
                    eliminarAdopcion();
                    break;
                case 4:
                    listarAdopciones();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private void agregarAdopcion() {
        System.out.println("\n--- AGREGAR ADOPCIÓN ---");

        int mascotaId = leerEntero("ID de la mascota: ");
        int duenoId = leerEntero("ID del dueño: ");
        Date fechaAdopcion = leerFecha("Fecha de adopción (YYYY-MM-DD): ");
        System.out.print("Contrato (opcional): ");
        String contrato = sc.nextLine();
        System.out.print("Condiciones especiales (opcional): ");
        String condiciones = sc.nextLine();
        boolean seguimiento = leerBoolean("¿Requiere seguimiento? (true/false): ");
        Date fechaPrimer = leerFecha("Fecha primer seguimiento (opcional, YYYY-MM-DD, enter para vacío): ");

        controller.agregar(mascotaId, duenoId, fechaAdopcion, contrato, condiciones, seguimiento, fechaPrimer);
    }

    private void modificarAdopcion() {
        System.out.println("\n--- MODIFICAR ADOPCIÓN ---");

        int id = leerEntero("ID de la adopción a modificar: ");
        int mascotaId = leerEntero("Nuevo ID de la mascota: ");
        int duenoId = leerEntero("Nuevo ID del dueño: ");
        Date fechaAdopcion = leerFecha("Nueva fecha de adopción (YYYY-MM-DD): ");
        System.out.print("Nuevo contrato (opcional): ");
        String contrato = sc.nextLine();
        System.out.print("Nuevas condiciones especiales (opcional): ");
        String condiciones = sc.nextLine();
        boolean seguimiento = leerBoolean("¿Requiere seguimiento? (true/false): ");
        Date fechaPrimer = leerFecha("Nueva fecha primer seguimiento (opcional, YYYY-MM-DD, enter para vacío): ");

        controller.modificar(id, mascotaId, duenoId, fechaAdopcion, contrato, condiciones, seguimiento, fechaPrimer);
    }

    private void eliminarAdopcion() {
        System.out.println("\n--- ELIMINAR ADOPCIÓN ---");
        int id = leerEntero("ID de la adopción a eliminar: ");
        controller.eliminar(id);
    }

    private void listarAdopciones() {
        System.out.println("\n--- LISTA DE ADOPCIONES ---");
        List<Adopcion> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay adopciones registradas.");
        } else {
            for (Adopcion a : lista) {
                System.out.println(a);
            }
        }
    }

    // MÉTODOS AUXILIARES 
    private int leerEntero(String mensaje) {
        int valor = -1;
        while (valor < 0) {
            try {
                System.out.print(mensaje);
                String entrada = sc.nextLine();
                valor = Integer.parseInt(entrada);
                if (valor < 0) System.out.println("Debe ser un número positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
        return valor;
    }

    private boolean leerBoolean(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim().toLowerCase();
            if (entrada.equals("true")) return true;
            if (entrada.equals("false")) return false;
            System.out.println("Ingrese 'true' o 'false'.");
        }
    }

    private Date leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            if (entrada.isBlank()) return null; // opcional
            try {
                return Date.valueOf(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de fecha inválido. Use YYYY-MM-DD.");
            }
        }
    }
}
