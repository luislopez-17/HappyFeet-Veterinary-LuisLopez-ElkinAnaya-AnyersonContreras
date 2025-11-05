/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.MascotaAdopcion;
import utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ELKIN
 */
public class MascotaAdopcionDAO {
    
    // Verificar si existe la mascota
    private boolean existeMascota(int mascotaId) {
        String sql = "SELECT id FROM mascotas WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mascotaId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al verificar existencia de mascota: " + e.getMessage());
            return false;
        }
    }

    // AGREGAR MASCOTA EN ADOPCIÓN
    public void agregar(MascotaAdopcion m) {
        if (!existeMascota(m.getMascotaId())) {
            System.out.println("Error: La mascota con ID " + m.getMascotaId() + " no existe.");
            return;
        }

        String sql = "INSERT INTO mascotas_adopcion (mascota_id, fecha_ingreso, motivo_ingreso, estado, historia, temperamento, necesidades_especiales, foto_adicional_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getMascotaId());
            ps.setDate(2, m.getFechaIngreso()); // java.sql.Date
            ps.setString(3, m.getMotivoIngreso());
            ps.setString(4, m.getEstado());
            ps.setString(5, m.getHistoria());
            ps.setString(6, m.getTemperamento());
            ps.setString(7, m.getNecesidadesEspeciales());
            ps.setString(8, m.getFotoAdicionalUrl());

            ps.executeUpdate();
            System.out.println("Mascota en adopción agregada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar mascota en adopción: " + e.getMessage());
        }
    }

    // LISTAR MASCOTAS EN ADOPCIÓN
    public List<MascotaAdopcion> listar() {
        List<MascotaAdopcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas_adopcion";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                MascotaAdopcion m = new MascotaAdopcion(
                        rs.getInt("id"),
                        rs.getInt("mascota_id"),
                        rs.getDate("fecha_ingreso"),
                        rs.getString("motivo_ingreso"),
                        rs.getString("estado"),
                        rs.getString("historia"),
                        rs.getString("temperamento"),
                        rs.getString("necesidades_especiales"),
                        rs.getString("foto_adicional_url")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mascotas en adopción: " + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR ESTADO
    public void actualizar(MascotaAdopcion m) {
        if (m.getId() <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        String sql = "UPDATE mascotas_adopcion SET estado=? WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getEstado());
            ps.setInt(2, m.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Mascota en adopción actualizada correctamente.");
            else System.out.println("No se encontró una mascota con ese ID.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar mascota en adopción: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(int id) {
    String sql = "DELETE FROM mascotas_adopcion WHERE id = ?";
    try (Connection con = ConexionDB.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        int filas = ps.executeUpdate();
        if (filas > 0) System.out.println("Mascota eliminada correctamente.");
        else System.out.println("No se encontró la mascota con ese ID.");
    } catch (SQLException e) {
        System.out.println("Error al eliminar mascota: " + e.getMessage());
    }
}

}
   

