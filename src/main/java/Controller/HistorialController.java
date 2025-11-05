package Controller;

import dao.HistorialDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.HistorialMedico;
import utils.ConexionDB;

public class HistorialController {
    private HistorialDAO historialDao;

    public HistorialController() {
        this.historialDao = new HistorialDAO();
    }

    // Validar formato de fecha yyyy-MM-dd
    private boolean validarFormatoFecha(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            System.out.println("Error: la fecha del evento es obligatoria.");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(fechaStr);
            return true;
        } catch (ParseException e) {
            System.out.println("Error: formato de fecha inválido. Usa el formato yyyy-MM-dd");
            return false;
        }
    }

    public void agregar(Integer mascotaId, String fechaStr, Integer eventoTipoId, String descripcion,
                        String diagnostico, String tratamiento, Integer veterinarioId,
                        Integer consultaId, Integer procedimientoId) {

        // Validaciones de campos obligatorios
        if (mascotaId == null || mascotaId <= 0) {
            System.out.println("Error: el ID de la mascota es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            System.out.println("Error: la fecha del evento es obligatoria.");
            return;
        }
        if (eventoTipoId == null || eventoTipoId <= 0) {
            System.out.println("Error: el tipo de evento es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("Error: la descripción es obligatoria.");
            return;
        }

        if (!validarFormatoFecha(fechaStr)) {
            return;
        }

        Date fechaEvento = Date.valueOf(fechaStr);

        // Crear objeto modelo
        HistorialMedico h = new HistorialMedico(mascotaId, fechaEvento, eventoTipoId, descripcion,
                diagnostico, tratamiento, 
                (veterinarioId != null && veterinarioId > 0) ? veterinarioId : 0,
                (consultaId != null && consultaId > 0) ? consultaId : 0,
                (procedimientoId != null && procedimientoId > 0) ? procedimientoId : 0);

        try (Connection con = ConexionDB.conectar()) {
            historialDao.agregar(h, con);
            System.out.println("Historial médico agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el historial médico: " + e.getMessage());
        }
    }

    public void actualizar(int id, Integer mascotaId, String fechaStr, Integer eventoTipoId, String descripcion,
                           String diagnostico, String tratamiento, Integer veterinarioId,
                           Integer consultaId, Integer procedimientoId) {

        if (id <= 0) {
            System.out.println("Error: ID inválido para actualizar.");
            return;
        }
        if (mascotaId == null || mascotaId <= 0) {
            System.out.println("Error: el ID de la mascota es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (eventoTipoId == null || eventoTipoId <= 0) {
            System.out.println("Error: el tipo de evento es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("Error: la descripción es obligatoria.");
            return;
        }

        if (!validarFormatoFecha(fechaStr)) {
            return;
        }

        Date fechaEvento = Date.valueOf(fechaStr);

        HistorialMedico h = new HistorialMedico(id, mascotaId, fechaEvento, eventoTipoId, descripcion,
                diagnostico, tratamiento,
                (veterinarioId != null && veterinarioId > 0) ? veterinarioId : 0,
                (consultaId != null && consultaId > 0) ? consultaId : 0,
                (procedimientoId != null && procedimientoId > 0) ? procedimientoId : 0);

        try {
            historialDao.actualizar(h);
            System.out.println("Historial médico actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el historial médico: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }

        HistorialMedico h = new HistorialMedico(id, 0, null, 0, "", "", "", 0, 0, 0);
        historialDao.eliminar(h);
    }

    public List<HistorialMedico> listar() {
        return historialDao.listar();
    }
}
