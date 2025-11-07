/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author usuario
 */

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.ProcedimientosEspeciales;
import utils.ConexionDB;

public class ProcedimientosEspecialesDAO {

    private boolean existeMascota(Connection con, int mascotaId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mascotas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mascotaId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeVeterinario(Connection con, int veterinarioId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM veterinarios WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, veterinarioId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public void agregar(ProcedimientosEspeciales p, int mascotaId, int veterinarioId) throws SQLException {
        try (Connection con = ConexionDB.conectar()) {
            if (!existeMascota(con, mascotaId)) {
                System.out.println("El ID de la mascota no existe, no se puede registrar el procedimiento.");
                return;
            }
            if (!existeVeterinario(con, veterinarioId)) {
                System.out.println("El ID del veterinario no existe, no se puede registrar el procedimiento.");
                return;
            }

            String sql = """
                INSERT INTO procedimientos_especiales 
                (mascota_id, veterinario_id, tipo_procedimiento, nombre_procedimiento, fecha_hora,
                 duracion_estimada_minutos, informacion_preoperatoria, detalle_procedimiento,
                 complicaciones, seguimiento_postoperatorio, proximo_control, estado, costo_procedimiento)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, mascotaId);
                ps.setInt(2, veterinarioId);
                ps.setString(3, p.getTipoProcedimiento());
                ps.setString(4, p.getNombreProcedimiento());
                ps.setTimestamp(5, p.getFechaHora());
                ps.setObject(6, p.getDuracionMinutos(), java.sql.Types.INTEGER);
                ps.setString(7, p.getInformacionPreoperatoria());
                ps.setString(8, p.getDetalleProcedimientos());
                ps.setString(9, p.getComplicaciones());
                ps.setString(10, p.getSeguimiento());
                ps.setDate(11, p.getProximoControl());
                ps.setString(12, p.getEstado() != null ? p.getEstado().name().replace("_", " ") : "Programado"); // enum con default
                ps.setDouble(13, p.getCosto());

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            System.out.println("Procedimiento registrado con ID: " + rs.getInt(1));
                        }
                    }
                }
                System.out.println("Inserción exitosa. Filas afectadas: " + filas);
            }
        }
    }

    public void actualizar(ProcedimientosEspeciales p, int mascotaId, int veterinarioId) throws SQLException {
        try (Connection con = ConexionDB.conectar()) {
            if (!existeMascota(con, mascotaId)) {
                System.out.println("El ID de la mascota no existe, no se puede actualizar.");
                return;
            }
            if (!existeVeterinario(con, veterinarioId)) {
                System.out.println("El ID del veterinario no existe, no se puede actualizar.");
                return;
            }

            String sql = """
                UPDATE procedimientos_especiales
                SET mascota_id=?, veterinario_id=?, tipo_procedimiento=?, nombre_procedimiento=?, fecha_hora=?,
                    duracion_estimada_minutos=?, informacion_preoperatoria=?, detalle_procedimiento=?,
                    complicaciones=?, seguimiento_postoperatorio=?, proximo_control=?, estado=?, costo_procedimiento=?
                WHERE id=?
            """;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, mascotaId);
                ps.setInt(2, veterinarioId);
                ps.setString(3, p.getTipoProcedimiento());
                ps.setString(4, p.getNombreProcedimiento());
                ps.setTimestamp(5, p.getFechaHora());
                ps.setObject(6, p.getDuracionMinutos(), java.sql.Types.INTEGER);
                ps.setString(7, p.getInformacionPreoperatoria());
                ps.setString(8, p.getDetalleProcedimientos());
                ps.setString(9, p.getComplicaciones());
                ps.setString(10, p.getSeguimiento());
                ps.setDate(11, p.getProximoControl());
                ps.setString(12, p.getEstado().name().replace("_", " "));
                ps.setDouble(13, p.getCosto());
                ps.setInt(14, p.getId());

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    System.out.println("Procedimiento actualizado correctamente.");
                } else {
                    System.out.println("No se encontró un procedimiento con ID = " + p.getId());
                }
            }
        }
    }

    
    public void eliminar(ProcedimientosEspeciales p) {
        String sql = "DELETE FROM procedimientos_especiales WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Procedimiento eliminado correctamente.");
            } else {
                System.out.println("No se encontró el procedimiento con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar procedimiento: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<ProcedimientosEspeciales> listar() {
    List<ProcedimientosEspeciales> lista = new ArrayList<>();
    String sql = "SELECT * FROM procedimientos_especiales";
    
    try (Connection con = ConexionDB.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            ProcedimientosEspeciales.Estado estado = ProcedimientosEspeciales.Estado.valueOf(
                rs.getString("estado").replace(" ", "")
            );
            
            ProcedimientosEspeciales p = new ProcedimientosEspeciales(
                rs.getInt("id"),
                rs.getInt("mascota_id"),
                rs.getInt("veterinario_id"),
                rs.getString("tipo_procedimiento"),
                rs.getString("nombre_procedimiento"),
                rs.getTimestamp("fecha_hora"),
                rs.getInt("duracion_estimada_minutos"),
                rs.getString("informacion_preoperatoria"),
                rs.getString("detalle_procedimiento"),
                rs.getString("complicaciones"),
                rs.getString("seguimiento_postoperatorio"),
                rs.getDate("proximo_control"),
                estado,
                rs.getDouble("costo_procedimiento")
            );
            lista.add(p);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar procedimientos: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}
    
    //EXAMEN
    public void actualizarEstado(int idProcedimiento, ProcedimientosEspeciales.Estado nuevoEstado) throws SQLException {
        String sql = "UPDATE procedimientos_especiales SET estado = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, nuevoEstado.name());
            stmt.setInt(2, idProcedimiento);
            int filas = stmt.executeUpdate();
            
            if (filas > 0) {
                System.out.println("Estado del procedimiento actualizado a: " + nuevoEstado);
                // Luego de actualizar, verificamos si debe notificarse
                verificarProximoControl(con, idProcedimiento);
            } else {
                System.out.println("No se encontró un procedimiento con ese ID.");
            }
        }
    }

    // === Verificar si tiene proximo control y estado finalizado ===
    private void verificarProximoControl(Connection con, int idProcedimiento) throws SQLException {
        String sql = """
            SELECT p.mascota_id, p.proximo_control, m.dueno_id
            FROM procedimientos_especiales p
            JOIN mascotas m ON p.mascota_id = m.id
            WHERE p.id = ? AND p.estado = 'Finalizado' AND p.proximo_control IS NOT NULL
        """;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProcedimiento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idMascota = rs.getInt("mascota_id");
                int idDueno = rs.getInt("dueno_id");
                Date fechaControl = rs.getDate("proximo_control");

                // Mensaje en consola
                System.out.println("\n*** RECORDATORIO DE CONTROL PRÓXIMO ***");
                System.out.println("Mascota ID: " + idMascota);
                System.out.println("Dueño ID: " + idDueno);
                System.out.println("Fecha sugerida de control: " + fechaControl);
                System.out.println("Tarea: Contactar al dueño para agendar.\n");

                // Guardar también en archivo log
                registrarEnArchivo(idMascota, idDueno, fechaControl);
            }
        }
    }

    // === Escribir en archivo ===
    private void registrarEnArchivo(int idMascota, int idDueno, Date fechaControl) {
        try (FileWriter fw = new FileWriter("agenda_controles.log", true)) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaActual = LocalDateTime.now().format(formato);

            String linea = String.format("[%s] | TAREA_PENDIENTE | Mascota_ID=%d | Dueño_ID=%d | Fecha_Control_Sugerida=%s%n",
                    fechaActual, idMascota, idDueno, fechaControl.toString());

            fw.write(linea);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo log: " + e.getMessage());
        }
    }

}
