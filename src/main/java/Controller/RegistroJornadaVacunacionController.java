/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.RegistroJornadaVacunacionDAO;
import model.RegistroJornadaVacunacion;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class RegistroJornadaVacunacionController {
     private final RegistroJornadaVacunacionDAO dao = new RegistroJornadaVacunacionDAO();

    // Agregar 
    public void agregar(int jornadaId, int mascotaId, int duenoId, int vacunaId, int veterinarioId,
                        Timestamp fechaHora, String loteVacuna, Date proximaDosis, String observaciones) {

        if (jornadaId <= 0 || mascotaId <= 0 || duenoId <= 0 || vacunaId <= 0) {
            System.out.println("Error: IDs de jornada, mascota, dueño o vacuna inválidos.");
            return;
        }

        if (fechaHora == null) {
            System.out.println("Error: La fecha y hora del registro es obligatoria.");
            return;
        }

        if (loteVacuna != null && loteVacuna.isBlank()) loteVacuna = null;
        if (observaciones != null && observaciones.isBlank()) observaciones = null;

        RegistroJornadaVacunacion r = new RegistroJornadaVacunacion(jornadaId, mascotaId, duenoId, vacunaId, veterinarioId,
                fechaHora, loteVacuna, proximaDosis, observaciones);

        dao.agregar(r);
    }

    // Actualizar 
    public void actualizar(int id, int jornadaId, int mascotaId, int duenoId, int vacunaId, int veterinarioId,
                           Timestamp fechaHora, String loteVacuna, Date proximaDosis, String observaciones) {

        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }

        if (!dao.existePorId(id)) {
            System.out.println("Error: No se encontró un registro con ID = " + id);
            return;
        }

        if (jornadaId <= 0 || mascotaId <= 0 || duenoId <= 0 || vacunaId <= 0) {
            System.out.println("Error: IDs de jornada, mascota, dueño o vacuna inválidos.");
            return;
        }

        if (fechaHora == null) {
            System.out.println("Error: La fecha y hora del registro es obligatoria.");
            return;
        }

        if (loteVacuna != null && loteVacuna.isBlank()) loteVacuna = null;
        if (observaciones != null && observaciones.isBlank()) observaciones = null;

        RegistroJornadaVacunacion r = new RegistroJornadaVacunacion(id, jornadaId, mascotaId, duenoId, vacunaId, veterinarioId,
                fechaHora, loteVacuna, proximaDosis, observaciones);

        dao.actualizar(r);
    }

    // Eliminar 
    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }
        dao.eliminar(id);
    }

    // Listar   
    public List<RegistroJornadaVacunacion> listar() {
        return dao.listar();
    }
}
