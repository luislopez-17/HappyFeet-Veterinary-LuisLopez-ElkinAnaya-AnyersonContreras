/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author usuario
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ConsultaMedica;
import utils.ConexionDB;


//Comentario para cambios
public class ConsultaMedicasDAO {

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

    private boolean existeCita(Connection con, int citaId) throws SQLException {
        if (citaId <= 0) return false; // puede ser null
        String sql = "SELECT COUNT(*) FROM citas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, citaId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeConsulta(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM consultas_medicas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public void agregar(ConsultaMedica c, Connection con) throws SQLException {
        if (!existeMascota(con, c.getIdMascotas())) {
            System.out.println("El ID de la mascota no existe, no se puede insertar consulta");
            return;
        }

        if (!existeVeterinario(con, c.getIdVeterinario())) {
            System.out.println("El ID del veterinario no existe, no se puede insertar consulta");
            return;
        }

        if (c.getIdCita() > 0 && !existeCita(con, c.getIdCita())) {
            System.out.println("El ID de la cita no existe, no se puede asignar");
            return;
        }

        String sql = "INSERT INTO consultas_medicas (mascota_id, veterinario_id, cita_id, fecha_hora, motivo, sintomas, diagnostico, recomendaciones, observaciones, peso_registrado, temperatura) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getIdMascotas());
            ps.setInt(2, c.getIdVeterinario());
            if (c.getIdCita() > 0) {
                ps.setInt(3, c.getIdCita());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setTimestamp(4, c.getFechaHora());
            ps.setString(5, c.getMotivo());
            ps.setString(6, c.getSintomas());
            ps.setString(7, c.getDiagnostico());
            ps.setString(8, c.getRecomendaciones());
            ps.setString(9, c.getObservaciones());
            ps.setDouble(10, c.getPesoRegistrado());
            ps.setDouble(11, c.getTemperatura());

            int filas = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println("Consulta insertada correctamente con id = " + rs.getInt(1));
                }
            }
            System.out.println("Consulta agregada. Filas afectadas: " + filas);
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar consulta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void actualizar(ConsultaMedica c) {
        String sql = "UPDATE consultas_medicas SET mascota_id=?, veterinario_id=?, cita_id=?, fecha_hora=?, motivo=?, sintomas=?, diagnostico=?, recomendaciones=?, observaciones=?, peso_registrado=?, temperatura=? WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Validaciones
            if (!existeMascota(con, c.getIdMascotas())) {
                System.out.println("El ID de la mascota no existe, no se puede actualizar consulta");
                return;
            }

            if (!existeVeterinario(con, c.getIdVeterinario())) {
                System.out.println("El ID del veterinario no existe, no se puede actualizar consulta");
                return;
            }

            if (c.getIdCita() > 0 && !existeCita(con, c.getIdCita())) {
                System.out.println("El ID de la cita no existe, no se puede actualizar consulta");
                return;
            }

            ps.setInt(1, c.getIdMascotas());
            ps.setInt(2, c.getIdVeterinario());
            if (c.getIdCita() > 0) {
                ps.setInt(3, c.getIdCita());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setTimestamp(4, c.getFechaHora());
            ps.setString(5, c.getMotivo());
            ps.setString(6, c.getSintomas());
            ps.setString(7, c.getDiagnostico());
            ps.setString(8, c.getRecomendaciones());
            ps.setString(9, c.getObservaciones());
            ps.setDouble(10, c.getPesoRegistrado());
            ps.setDouble(11, c.getTemperatura());
            ps.setInt(12, c.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualización exitosa. Filas afectadas: " + filas);
            } else {
                System.out.println("No se encontró una consulta con id = " + c.getId());
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar consulta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminar(ConsultaMedica c) {
        String sql = "DELETE FROM consultas_medicas WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeConsulta(con, c.getId())) {
                System.out.println("La consulta no existe, no se puede eliminar");
                return;
            }

            ps.setInt(1, c.getId());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("La consulta se eliminó correctamente. Filas afectadas: " + filas);
            } else {
                System.out.println("No se encontró la consulta con id = " + c.getId());
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar consulta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<ConsultaMedica> listar() {
        List<ConsultaMedica> lista = new ArrayList<>();
        String sql = "SELECT id, mascota_id, veterinario_id, cita_id, fecha_hora, motivo, sintomas, diagnostico, recomendaciones, observaciones, peso_registrado, temperatura FROM consultas_medicas";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ConsultaMedica c = new ConsultaMedica(
                        rs.getInt("id"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id"),
                        rs.getInt("cita_id"),
                        rs.getTimestamp("fecha_hora"),
                        rs.getString("motivo"),
                        rs.getString("sintomas"),
                        rs.getString("diagnostico"),
                        rs.getString("recomendaciones"),
                        rs.getString("observaciones"),
                        rs.getDouble("peso_registrado"),
                        rs.getDouble("temperatura")
                );
                lista.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL al listar consultas: " + ex.getMessage());
            ex.printStackTrace();
        }

        return lista;
    }
    
    public List<ConsultaMedica> obtenerHistorialPesoPorMascota(Connection con, int mascotaId) throws SQLException {
        List<ConsultaMedica> lista = new ArrayList<>();

        String sql = """
            SELECT fecha_hora, peso_registrado
            FROM consultas_medicas
            WHERE mascota_id = ? AND peso_registrado IS NOT NULL
            ORDER BY fecha_hora ASC
        """;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, mascotaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ConsultaMedica c = new ConsultaMedica(
                        0, // id
                        mascotaId,
                        0, // idVeterinario
                        0, // idCita
                        rs.getTimestamp("fecha_hora"),
                        null, // motivo
                        null, // sintomas
                        null, // diagnostico
                        null, // recomendaciones
                        null, // observaciones
                        rs.getDouble("peso_registrado"),
                        0.0 // temperatura
                    );
                    lista.add(c);
                }
            }
        }
        return lista;
    }
}

