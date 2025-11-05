/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import Controller.RegistroJornadaVacunacionController;
import model.RegistroJornadaVacunacion;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class RegistroJornadaVacunacionVIEW {
    private final RegistroJornadaVacunacionController controller = new RegistroJornadaVacunacionController();
    private final Scanner sc = new Scanner(System.in);
    private final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Registro Jornada Vacunación ---");
            System.out.println("1. Agregar registro");
            System.out.println("2. Actualizar registro");
            System.out.println("3. Eliminar registro");
            System.out.println("4. Listar registros");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregarRegistro();
                case 2 -> actualizarRegistro();
                case 3 -> eliminarRegistro();
                case 4 -> listarRegistros();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void agregarRegistro() {
        System.out.println("\n--- Agregar Registro ---");
        int jornadaId = pedirId("ID de la jornada");
        int mascotaId = pedirId("ID de la mascota");
        int duenoId = pedirId("ID del dueño");
        int vacunaId = pedirId("ID de la vacuna");
        int veterinarioId = pedirIdOpcional("ID del veterinario (opcional, ingrese 0 si no aplica)");
        Timestamp fechaHora = pedirFechaHora("Fecha y hora (yyyy-MM-dd HH:mm:ss)");
        String loteVacuna = pedirTextoOpcional("Lote de la vacuna (opcional)");
        Date proximaDosis = pedirFechaOpcional("Próxima dosis (yyyy-MM-dd, opcional)");
        String observaciones = pedirTextoOpcional("Observaciones (opcional)");

        controller.agregar(jornadaId, mascotaId, duenoId, vacunaId, veterinarioId, fechaHora, loteVacuna, proximaDosis, observaciones);
    }

    private void actualizarRegistro() {
        System.out.println("\n--- Actualizar Registro ---");
        int id = pedirId("ID del registro a actualizar");
        int jornadaId = pedirId("ID de la jornada");
        int mascotaId = pedirId("ID de la mascota");
        int duenoId = pedirId("ID del dueño");
        int vacunaId = pedirId("ID de la vacuna");
        int veterinarioId = pedirIdOpcional("ID del veterinario (opcional, ingrese 0 si no aplica)");
        Timestamp fechaHora = pedirFechaHora("Fecha y hora (yyyy-MM-dd HH:mm:ss)");
        String loteVacuna = pedirTextoOpcional("Lote de la vacuna (opcional)");
        Date proximaDosis = pedirFechaOpcional("Próxima dosis (yyyy-MM-dd, opcional)");
        String observaciones = pedirTextoOpcional("Observaciones (opcional)");

        controller.actualizar(id, jornadaId, mascotaId, duenoId, vacunaId, veterinarioId, fechaHora, loteVacuna, proximaDosis, observaciones);
    }

    private void eliminarRegistro() {
        System.out.println("\n--- Eliminar Registro ---");
        int id = pedirId("ID del registro a eliminar");
        controller.eliminar(id);
    }

    private void listarRegistros() {
        System.out.println("\n--- Listado de Registros ---");
        List<RegistroJornadaVacunacion> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay registros disponibles.");
        } else {
            for (RegistroJornadaVacunacion r : lista) {
                System.out.println(r);
            }
        }
    }

    // --- Métodos auxiliares para leer datos ---
    private int leerEntero() {
        while (true) {
            try {
                String linea = sc.nextLine();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Debe ingresar un número válido: ");
            }
        }
    }

    private int pedirId(String mensaje) {
        int id;
        do {
            System.out.print(mensaje + ": ");
            id = leerEntero();
            if (id <= 0) {
                System.out.println("Error: El ID debe ser mayor a 0.");
            }
        } while (id <= 0);
        return id;
    }

    private int pedirIdOpcional(String mensaje) {
        System.out.print(mensaje + ": ");
        return leerEntero(); // 0 significa no aplica
    }

    private Timestamp pedirFechaHora(String mensaje) {
        Timestamp ts = null;
        do {
            System.out.print(mensaje + ": ");
            String linea = sc.nextLine();
            try {
                ts = new Timestamp(dateTimeFormat.parse(linea).getTime());
            } catch (ParseException e) {
                System.out.println("Formato inválido. Debe ser yyyy-MM-dd HH:mm:ss");
            }
        } while (ts == null);
        return ts;
    }

    private Date pedirFechaOpcional(String mensaje) {
        System.out.print(mensaje + ": ");
        String linea = sc.nextLine();
        if (linea.isBlank()) return null;
        try {
            return new Date(dateFormat.parse(linea).getTime());
        } catch (ParseException e) {
            System.out.println("Formato inválido. Se omite la fecha.");
            return null;
        }
    }

    private String pedirTextoOpcional(String mensaje) {
        System.out.print(mensaje + ": ");
        String linea = sc.nextLine();
        return linea.isBlank() ? null : linea;
    }
}
