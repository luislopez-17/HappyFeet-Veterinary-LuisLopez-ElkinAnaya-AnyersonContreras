/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.ClubMascotas;
import utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class ClubMascotasDAO {
    
    // Verificar si existe el club por ID
    public boolean existePorId(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM club_mascotas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Verificar si existe un dueño
    public boolean existeDueno(Connection con, int duenoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM duenos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, duenoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Agregar club
    public void agregar(ClubMascotas c) {
        String sql = "INSERT INTO club_mascotas(dueno_id, puntos_acumulados, puntos_canjeados, puntos_disponibles, nivel, fecha_inscripcion, activo) VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.conectar()) {
            if (!existeDueno(con, c.getDuenoId())) {
                System.out.println("Error: El dueño no existe.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, c.getDuenoId());
                ps.setInt(2, c.getPuntosAcumulados());
                ps.setInt(3, c.getPuntosCanjeados());
                ps.setInt(4, c.getPuntosDisponibles());
                ps.setString(5, c.getNivel());
                ps.setDate(6, c.getFechaInscripcion());
                ps.setBoolean(7, c.isActivo());

                int filas = ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Club agregado con id = " + rs.getInt(1));
                    }
                }
                System.out.println("Filas afectadas: " + filas);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar club: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Actualizar club
    public void actualizar(ClubMascotas c) {
        String sql = "UPDATE club_mascotas SET dueno_id=?, puntos_acumulados=?, puntos_canjeados=?, puntos_disponibles=?, nivel=?, fecha_inscripcion=?, activo=? WHERE id=?";

        try (Connection con = ConexionDB.conectar()) {
            if (!existePorId(con, c.getId())) {
                System.out.println("Error: No se encontró un club con ID = " + c.getId());
                return;
            }
            if (!existeDueno(con, c.getDuenoId())) {
                System.out.println("Error: El dueño no existe.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, c.getDuenoId());
                ps.setInt(2, c.getPuntosAcumulados());
                ps.setInt(3, c.getPuntosCanjeados());
                ps.setInt(4, c.getPuntosDisponibles());
                ps.setString(5, c.getNivel());
                ps.setDate(6, c.getFechaInscripcion());
                ps.setBoolean(7, c.isActivo());
                ps.setInt(8, c.getId());

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    System.out.println("Club actualizado correctamente, filas afectadas: " + filas);
                } else {
                    System.out.println("No se encontró el club con id = " + c.getId());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar club: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Eliminar club
    public void eliminar(int id) {
        String sql = "DELETE FROM club_mascotas WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Club eliminado correctamente, filas afectadas: " + filas);
            } else {
                System.out.println("No se encontró el club con id = " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar club: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Listar clubes
    public List<ClubMascotas> listar() {
        List<ClubMascotas> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.dueno_id, c.puntos_acumulados, c.puntos_canjeados, c.puntos_disponibles, c.nivel, c.fecha_inscripcion, c.fecha_ultima_actualizacion, c.activo, d.nombre AS nombre_dueno FROM club_mascotas c LEFT JOIN duenos d ON c.dueno_id = d.id";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int duenoId = rs.getInt("dueno_id");
                int puntosAcum = rs.getInt("puntos_acumulados");
                int puntosCanj = rs.getInt("puntos_canjeados");
                int puntosDisp = rs.getInt("puntos_disponibles");
                String nivel = rs.getString("nivel");
                Date fechaInscripcion = rs.getDate("fecha_inscripcion");
                Timestamp fechaUltAct = rs.getTimestamp("fecha_ultima_actualizacion");
                boolean activo = rs.getBoolean("activo");
                String nombreDueno = rs.getString("nombre_dueno");

                ClubMascotas c = new ClubMascotas(id, duenoId, puntosAcum, puntosCanj, puntosDisp,
                        nivel, fechaInscripcion, fechaUltAct, activo);
                if (nombreDueno != null && !nombreDueno.isBlank()) {
                    c.setDueno(nombreDueno);
                } else {
                    c.setDueno("Dueño #" + duenoId);
                }

                lista.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL al listar clubes: " + ex.getMessage());
            ex.printStackTrace();
        }

        return lista;
    }
}
