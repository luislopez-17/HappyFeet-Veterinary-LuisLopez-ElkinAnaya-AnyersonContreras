/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.RegistroJornadaVacunacion;
import utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class RegistroJornadaVacunacionDAO {
    // Verificar existencia de IDs
    public boolean existeJornada(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM jornadas_vacunacion WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean existeMascota(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mascotas WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean existeDueno(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM duenos WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean existeVacuna(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inventario WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean existeVeterinario(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM veterinarios WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Verificar si el registro existe por ID
    public boolean existePorId(int id) {
        String sql = "SELECT COUNT(*) FROM registro_jornada_vacunacion WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Agregar registro
    public void agregar(RegistroJornadaVacunacion r) {
        String sql = "INSERT INTO registro_jornada_vacunacion(jornada_id, mascota_id, dueno_id, vacuna_id, veterinario_id, fecha_hora, lote_vacuna, proxima_dosis, observaciones) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection con = ConexionDB.conectar()) {
            // Verificar existencia de IDs
            if (!existeJornada(con, r.getJornadaId())) {
                System.out.println("Error: La jornada no existe.");
                return;
            }
            if (!existeMascota(con, r.getMascotaId())) {
                System.out.println("Error: La mascota no existe.");
                return;
            }
            if (!existeDueno(con, r.getDuenoId())) {
                System.out.println("Error: El dueño no existe.");
                return;
            }
            if (!existeVacuna(con, r.getVacunaId())) {
                System.out.println("Error: La vacuna no existe.");
                return;
            }
            if (r.getVeterinarioId() > 0 && !existeVeterinario(con, r.getVeterinarioId())) {
                System.out.println("Error: El veterinario no existe.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, r.getJornadaId());
                ps.setInt(2, r.getMascotaId());
                ps.setInt(3, r.getDuenoId());
                ps.setInt(4, r.getVacunaId());
                if (r.getVeterinarioId() > 0) ps.setInt(5, r.getVeterinarioId());
                else ps.setNull(5, Types.INTEGER);
                ps.setTimestamp(6, r.getFechaHora());
                ps.setString(7, r.getLoteVacuna());
                ps.setDate(8, r.getProximaDosis());
                ps.setString(9, r.getObservaciones());

                int filas = ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) System.out.println("Registro agregado con id = " + rs.getInt(1));
                }
                System.out.println("Filas afectadas: " + filas);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Actualizar registro
    public void actualizar(RegistroJornadaVacunacion r) {
        String sql = "UPDATE registro_jornada_vacunacion SET jornada_id=?, mascota_id=?, dueno_id=?, vacuna_id=?, veterinario_id=?, fecha_hora=?, lote_vacuna=?, proxima_dosis=?, observaciones=? WHERE id=?";
        try (Connection con = ConexionDB.conectar()) {
            if (!existeJornada(con, r.getJornadaId())) {
                System.out.println("Error: La jornada no existe.");
                return;
            }
            if (!existeMascota(con, r.getMascotaId())) {
                System.out.println("Error: La mascota no existe.");
                return;
            }
            if (!existeDueno(con, r.getDuenoId())) {
                System.out.println("Error: El dueño no existe.");
                return;
            }
            if (!existeVacuna(con, r.getVacunaId())) {
                System.out.println("Error: La vacuna no existe.");
                return;
            }
            if (r.getVeterinarioId() > 0 && !existeVeterinario(con, r.getVeterinarioId())) {
                System.out.println("Error: El veterinario no existe.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, r.getJornadaId());
                ps.setInt(2, r.getMascotaId());
                ps.setInt(3, r.getDuenoId());
                ps.setInt(4, r.getVacunaId());
                if (r.getVeterinarioId() > 0) ps.setInt(5, r.getVeterinarioId());
                else ps.setNull(5, Types.INTEGER);
                ps.setTimestamp(6, r.getFechaHora());
                ps.setString(7, r.getLoteVacuna());
                ps.setDate(8, r.getProximaDosis());
                ps.setString(9, r.getObservaciones());
                ps.setInt(10, r.getId());

                int filas = ps.executeUpdate();
                if (filas > 0) System.out.println("Registro actualizado correctamente.");
                else System.out.println("No se encontró el registro con id = " + r.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Eliminar registro
    public void eliminar(int id) {
        String sql = "DELETE FROM registro_jornada_vacunacion WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Registro eliminado correctamente.");
            else System.out.println("No se encontró el registro con id = " + id);
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Listar registros
    public List<RegistroJornadaVacunacion> listar() {
        List<RegistroJornadaVacunacion> lista = new ArrayList<>();
        String sql = "SELECT r.*, j.nombre AS nombre_jornada, m.nombre AS nombre_mascota, d.nombre AS nombre_dueno, v.nombre AS nombre_vacuna, vet.nombre AS nombre_veterinario " +
                     "FROM registro_jornada_vacunacion r " +
                     "LEFT JOIN jornadas_vacunacion j ON r.jornada_id=j.id " +
                     "LEFT JOIN mascotas m ON r.mascota_id=m.id " +
                     "LEFT JOIN duenos d ON r.dueno_id=d.id " +
                     "LEFT JOIN inventario v ON r.vacuna_id=v.id " +
                     "LEFT JOIN veterinarios vet ON r.veterinario_id=vet.id";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int jornadaId = rs.getInt("jornada_id");
                int mascotaId = rs.getInt("mascota_id");
                int duenoId = rs.getInt("dueno_id");
                int vacunaId = rs.getInt("vacuna_id");
                int veterinarioId = rs.getInt("veterinario_id");
                Timestamp fechaHora = rs.getTimestamp("fecha_hora");
                String lote = rs.getString("lote_vacuna");
                Date proxima = rs.getDate("proxima_dosis");
                String obs = rs.getString("observaciones");

                RegistroJornadaVacunacion r = new RegistroJornadaVacunacion(id, jornadaId, mascotaId, duenoId, vacunaId, veterinarioId, fechaHora, lote, proxima, obs);

                r.setJornada(rs.getString("nombre_jornada"));
                r.setMascota(rs.getString("nombre_mascota"));
                r.setDueno(rs.getString("nombre_dueno"));
                r.setVacuna(rs.getString("nombre_vacuna"));
                r.setVeterinario(rs.getString("nombre_veterinario"));

                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar registros: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}
