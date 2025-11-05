/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.TransaccionesPuntos;
import model.TransaccionesPuntos.Tipo;
import utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class TransaccionesPuntosDAO {
    
    // Verificar existencia de club_mascotas por ID
    public boolean existeClub(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM club_mascotas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Verificar existencia de factura por ID
    public boolean existeFactura(Connection con, int id) throws SQLException {
        if (id <= 0) return true; // factura opcional
        String sql = "SELECT COUNT(*) FROM facturas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Agregar transacción
    public void agregar(TransaccionesPuntos t) {
        String sql = "INSERT INTO transacciones_puntos(club_mascotas_id, factura_id, puntos, tipo, fecha, descripcion, saldo_anterior, saldo_nuevo) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.conectar()) {

            if (!existeClub(con, t.getClubMascotasId())) {
                System.out.println("Error: El club de mascotas no existe.");
                return;
            }
            if (!existeFactura(con, t.getFacturaId())) {
                System.out.println("Error: La factura no existe.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, t.getClubMascotasId());
                if (t.getFacturaId() > 0) ps.setInt(2, t.getFacturaId());
                else ps.setNull(2, Types.INTEGER);
                ps.setInt(3, t.getPuntos());
                ps.setString(4, t.getTipo().name());
                ps.setTimestamp(5, t.getFecha());
                ps.setString(6, t.getDescripcion());
                ps.setInt(7, t.getSaldoAnterior());
                ps.setInt(8, t.getSaldoNuevo());

                int filas = ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) System.out.println("Transacción agregada con id = " + rs.getInt(1));
                }
                System.out.println("Filas afectadas: " + filas);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Actualizar transacción
    public void actualizar(TransaccionesPuntos t) {
        String sql = "UPDATE transacciones_puntos SET club_mascotas_id=?, factura_id=?, puntos=?, tipo=?, fecha=?, descripcion=?, saldo_anterior=?, saldo_nuevo=? WHERE id=?";

        try (Connection con = ConexionDB.conectar()) {

            if (!existeClub(con, t.getClubMascotasId())) {
                System.out.println("Error: El club de mascotas no existe.");
                return;
            }
            if (!existeFactura(con, t.getFacturaId())) {
                System.out.println("Error: La factura no existe.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, t.getClubMascotasId());
                if (t.getFacturaId() > 0) ps.setInt(2, t.getFacturaId());
                else ps.setNull(2, Types.INTEGER);
                ps.setInt(3, t.getPuntos());
                ps.setString(4, t.getTipo().name());
                ps.setTimestamp(5, t.getFecha());
                ps.setString(6, t.getDescripcion());
                ps.setInt(7, t.getSaldoAnterior());
                ps.setInt(8, t.getSaldoNuevo());
                ps.setInt(9, t.getId());

                int filas = ps.executeUpdate();
                if (filas > 0) System.out.println("Transacción actualizada correctamente.");
                else System.out.println("No se encontró la transacción con id = " + t.getId());
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Eliminar transacción
    public void eliminar(int id) {
        String sql = "DELETE FROM transacciones_puntos WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Transacción eliminada correctamente.");
            else System.out.println("No se encontró la transacción con id = " + id);
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Listar transacciones
    public List<TransaccionesPuntos> listar() {
        List<TransaccionesPuntos> lista = new ArrayList<>();
        String sql = "SELECT t.*, c.dueno_id, f.id AS factura_id " +
                     "FROM transacciones_puntos t " +
                     "LEFT JOIN club_mascotas c ON t.club_mascotas_id=c.id " +
                     "LEFT JOIN facturas f ON t.factura_id=f.id";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int clubId = rs.getInt("club_mascotas_id");
                int facturaId = rs.getInt("factura_id");
                int puntos = rs.getInt("puntos");
                Tipo tipo = Tipo.valueOf(rs.getString("tipo"));
                Timestamp fecha = rs.getTimestamp("fecha");
                String descripcion = rs.getString("descripcion");
                int saldoAnterior = rs.getInt("saldo_anterior");
                int saldoNuevo = rs.getInt("saldo_nuevo");

                TransaccionesPuntos t = new TransaccionesPuntos(id, clubId, facturaId, puntos, tipo, fecha, descripcion, saldoAnterior, saldoNuevo);

                String nombreClub = "ClubMascotas#" + clubId;
                t.setClubMascotas(nombreClub);
                if (facturaId > 0) t.setFactura("Factura#" + facturaId);

                lista.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al listar: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    // Verificar existencia por ID
    public boolean existePorId(int id) {
        String sql = "SELECT COUNT(*) FROM transacciones_puntos WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al verificar existencia: " + ex.getMessage());
        }
        return false;
    }
}
