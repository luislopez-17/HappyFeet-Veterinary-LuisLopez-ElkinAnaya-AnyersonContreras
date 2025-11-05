package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HistorialMedico;
import utils.ConexionDB;

public class HistorialDAO {

    // ====== VALIDACIONES DE EXISTENCIA ======
    private boolean existeMascota(Connection con, int mascotaId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mascotas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mascotaId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeEventoTipo(Connection con, int eventoTipoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM evento_tipos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eventoTipoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeVeterinario(Connection con, int veterinarioId) throws SQLException {
        if (veterinarioId <= 0) return false; // Puede ser nulo
        String sql = "SELECT COUNT(*) FROM veterinarios WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, veterinarioId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeConsulta(Connection con, int consultaId) throws SQLException {
        if (consultaId <= 0) return false;
        String sql = "SELECT COUNT(*) FROM consultas_medicas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, consultaId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeProcedimiento(Connection con, int procedimientoId) throws SQLException {
        if (procedimientoId <= 0) return false;
        String sql = "SELECT COUNT(*) FROM procedimientos_especiales WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, procedimientoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeHistorial(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM historial_medico WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // ====== MÉTODO AGREGAR ======
    public void agregar(HistorialMedico h, Connection con) throws SQLException {

        // Validar relaciones obligatorias
        if (!existeMascota(con, h.getMascotaId())) {
            System.out.println("El ID de la mascota no existe, no se puede insertar historial.");
            return;
        }

        if (!existeEventoTipo(con, h.getEventoTipoId())) {
            System.out.println("El ID del tipo de evento no existe, no se puede insertar historial.");
            return;
        }

        // Validar relaciones opcionales
        if (h.getVeterinarioId() > 0 && !existeVeterinario(con, h.getVeterinarioId())) {
            System.out.println("El ID del veterinario no existe, se omitirá este valor.");
            h = new HistorialMedico(
                h.getMascotaId(), h.getFechaEvento(), h.getEventoTipoId(), 
                h.getDescripcion(), h.getDiagnostico(), h.getTratamientoRecomendado(), 
                0, h.getConsultaId(), h.getProcedimientoId()
            );
        }

        if (h.getConsultaId() > 0 && !existeConsulta(con, h.getConsultaId())) {
            System.out.println("El ID de la consulta no existe, se omitirá este valor.");
            h = new HistorialMedico(
                h.getMascotaId(), h.getFechaEvento(), h.getEventoTipoId(), 
                h.getDescripcion(), h.getDiagnostico(), h.getTratamientoRecomendado(), 
                h.getVeterinarioId(), 0, h.getProcedimientoId()
            );
        }

        if (h.getProcedimientoId() > 0 && !existeProcedimiento(con, h.getProcedimientoId())) {
            System.out.println("El ID del procedimiento no existe, se omitirá este valor.");
            h = new HistorialMedico(
                h.getMascotaId(), h.getFechaEvento(), h.getEventoTipoId(), 
                h.getDescripcion(), h.getDiagnostico(), h.getTratamientoRecomendado(), 
                h.getVeterinarioId(), h.getConsultaId(), 0
            );
        }

        String sql = "INSERT INTO historial_medico (mascota_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado, veterinario_id, consulta_id, procedimiento_id) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, h.getMascotaId());
            ps.setDate(2, h.getFechaEvento());
            ps.setInt(3, h.getEventoTipoId());
            ps.setString(4, h.getDescripcion());
            ps.setString(5, h.getDiagnostico());
            ps.setString(6, h.getTratamientoRecomendado());

            if (h.getVeterinarioId() > 0) ps.setInt(7, h.getVeterinarioId());
            else ps.setNull(7, Types.INTEGER);

            if (h.getConsultaId() > 0) ps.setInt(8, h.getConsultaId());
            else ps.setNull(8, Types.INTEGER);

            if (h.getProcedimientoId() > 0) ps.setInt(9, h.getProcedimientoId());
            else ps.setNull(9, Types.INTEGER);

            int filas = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println("Historial insertado correctamente con id = " + rs.getInt(1));
                }
            }
            System.out.println("Historial agregado. Filas afectadas: " + filas);
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar historial médico: " + ex.getMessage());
        }
    }

    // ====== MÉTODO ACTUALIZAR ======
    public void actualizar(HistorialMedico h) {
        String sql = "UPDATE historial_medico SET mascota_id=?, fecha_evento=?, evento_tipo_id=?, descripcion=?, diagnostico=?, tratamiento_recomendado=?, veterinario_id=?, consulta_id=?, procedimiento_id=? WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeHistorial(con, h.getId())) {
                System.out.println("El historial no existe, no se puede actualizar.");
                return;
            }

            if (!existeMascota(con, h.getMascotaId())) {
                System.out.println("El ID de la mascota no existe.");
                return;
            }

            if (!existeEventoTipo(con, h.getEventoTipoId())) {
                System.out.println("El ID del tipo de evento no existe.");
                return;
            }

            ps.setInt(1, h.getMascotaId());
            ps.setDate(2, h.getFechaEvento());
            ps.setInt(3, h.getEventoTipoId());
            ps.setString(4, h.getDescripcion());
            ps.setString(5, h.getDiagnostico());
            ps.setString(6, h.getTratamientoRecomendado());

            if (h.getVeterinarioId() > 0 && existeVeterinario(con, h.getVeterinarioId()))
                ps.setInt(7, h.getVeterinarioId());
            else
                ps.setNull(7, Types.INTEGER);

            if (h.getConsultaId() > 0 && existeConsulta(con, h.getConsultaId()))
                ps.setInt(8, h.getConsultaId());
            else
                ps.setNull(8, Types.INTEGER);

            if (h.getProcedimientoId() > 0 && existeProcedimiento(con, h.getProcedimientoId()))
                ps.setInt(9, h.getProcedimientoId());
            else
                ps.setNull(9, Types.INTEGER);

            ps.setInt(10, h.getId());

            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Historial actualizado correctamente. Filas afectadas: " + filas);
            else
                System.out.println("No se encontró un historial con id = " + h.getId());

        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar historial médico: " + ex.getMessage());
        }
    }

    // ====== MÉTODO ELIMINAR ======
    public void eliminar(HistorialMedico h) {
        String sql = "DELETE FROM historial_medico WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeHistorial(con, h.getId())) {
                System.out.println("El historial no existe, no se puede eliminar.");
                return;
            }

            ps.setInt(1, h.getId());
            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Historial eliminado correctamente. Filas afectadas: " + filas);
            else
                System.out.println("No se encontró el historial con id = " + h.getId());

        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar historial médico: " + ex.getMessage());
        }
    }

    // ====== MÉTODO LISTAR ======
    public List<HistorialMedico> listar() {
        List<HistorialMedico> lista = new ArrayList<>();
        String sql = "SELECT id, mascota_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado, veterinario_id, consulta_id, procedimiento_id FROM historial_medico";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HistorialMedico h = new HistorialMedico(
                        rs.getInt("id"),
                        rs.getInt("mascota_id"),
                        rs.getDate("fecha_evento"),
                        rs.getInt("evento_tipo_id"),
                        rs.getString("descripcion"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento_recomendado"),
                        rs.getInt("veterinario_id"),
                        rs.getInt("consulta_id"),
                        rs.getInt("procedimiento_id")
                );
                lista.add(h);
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL al listar historiales médicos: " + ex.getMessage());
        }
        return lista;
    }
}
