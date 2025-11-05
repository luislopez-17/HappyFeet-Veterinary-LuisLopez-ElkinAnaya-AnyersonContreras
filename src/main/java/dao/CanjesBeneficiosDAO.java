/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.CanjesBeneficios;
import utils.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class CanjesBeneficiosDAO {
     // Métodos de existencia
    public boolean existeClubMascotas(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM club_mascotas WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean existeBeneficio(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM beneficios_club WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean existeFactura(Connection con, int id) throws SQLException {
        if (id == 0) return true; // Factura opcional
        String sql = "SELECT COUNT(*) FROM facturas WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean existePorId(int id) {
        String sql = "SELECT COUNT(*) FROM canjes_beneficios WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL existePorId: " + ex.getMessage());
            return false;
        }
    }

    // Agregar
    public void agregar(CanjesBeneficios c) {
        String sql = "INSERT INTO canjes_beneficios(club_mascotas_id, beneficio_id, fecha_canje, puntos_canjeados, estado, fecha_expiracion, factura_id) VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.conectar()) {
            if (!existeClubMascotas(con, c.getClubMascotasId())) { System.out.println("Error: Club mascotas no existe."); return; }
            if (!existeBeneficio(con, c.getBeneficioId())) { System.out.println("Error: Beneficio no existe."); return; }
            if (!existeFactura(con, c.getFacturaId())) { System.out.println("Error: Factura no existe."); return; }

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, c.getClubMascotasId());
                ps.setInt(2, c.getBeneficioId());
                ps.setTimestamp(3, new Timestamp(c.getFechaCanje().getTime()));
                ps.setInt(4, c.getPuntosCanjeados());
                ps.setString(5, c.getEstado().name());
                if (c.getFechaExpiracion() != null) ps.setDate(6, new java.sql.Date(c.getFechaExpiracion().getTime()));
                else ps.setNull(6, Types.DATE);
                if (c.getFacturaId() != 0) ps.setInt(7, c.getFacturaId());
                else ps.setNull(7, Types.INTEGER);

                int filas = ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) System.out.println("Canje agregado con id=" + rs.getInt(1));
                }
                System.out.println("Filas afectadas: " + filas);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    // Actualizar
    public void actualizar(CanjesBeneficios c) {
        String sql = "UPDATE canjes_beneficios SET club_mascotas_id=?, beneficio_id=?, fecha_canje=?, puntos_canjeados=?, estado=?, fecha_expiracion=?, factura_id=? WHERE id=?";
        try (Connection con = ConexionDB.conectar()) {
            if (!existePorId(c.getId())) { System.out.println("Error: No se encontró canje con ID=" + c.getId()); return; }
            if (!existeClubMascotas(con, c.getClubMascotasId())) { System.out.println("Error: Club mascotas no existe."); return; }
            if (!existeBeneficio(con, c.getBeneficioId())) { System.out.println("Error: Beneficio no existe."); return; }
            if (!existeFactura(con, c.getFacturaId())) { System.out.println("Error: Factura no existe."); return; }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, c.getClubMascotasId());
                ps.setInt(2, c.getBeneficioId());
                ps.setTimestamp(3, new Timestamp(c.getFechaCanje().getTime()));
                ps.setInt(4, c.getPuntosCanjeados());
                ps.setString(5, c.getEstado().name());
                if (c.getFechaExpiracion() != null) ps.setDate(6, new java.sql.Date(c.getFechaExpiracion().getTime()));
                else ps.setNull(6, Types.DATE);
                if (c.getFacturaId() != 0) ps.setInt(7, c.getFacturaId());
                else ps.setNull(7, Types.INTEGER);
                ps.setInt(8, c.getId());

                int filas = ps.executeUpdate();
                System.out.println(filas > 0 ? "Canje actualizado correctamente." : "No se encontró el canje.");
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    // Eliminar
    public void eliminar(int id) {
        String sql = "DELETE FROM canjes_beneficios WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existePorId(id)) { System.out.println("Error: No se encontró canje con ID=" + id); return; }

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Canje eliminado correctamente." : "No se encontró el canje.");
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    // Listar
    public List<CanjesBeneficios> listar() {
        List<CanjesBeneficios> lista = new ArrayList<>();
        String sql = "SELECT * FROM canjes_beneficios";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                CanjesBeneficios c = new CanjesBeneficios(
                        rs.getInt("id"),
                        rs.getInt("club_mascotas_id"),
                        rs.getInt("beneficio_id"),
                        rs.getTimestamp("fecha_canje"),
                        rs.getInt("puntos_canjeados"),
                        CanjesBeneficios.Estado.valueOf(rs.getString("estado")),
                        rs.getDate("fecha_expiracion"),
                        rs.getInt("factura_id")
                );
                lista.add(c);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}
