/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Factura;
import utils.ConexionDB;


public class FacturaDAO {

    // === VALIDACIONES ===
    private boolean existeDueno(int duenoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM duenos WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, duenoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        }
        return false;
    }

    private boolean existeNumeroFactura(String numero) throws SQLException {
        String sql = "SELECT COUNT(*) FROM facturas WHERE numero_factura = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        }
        return false;
    }

    private boolean validarMetodoPago(String metodo) {
        return metodo == null || metodo.isEmpty() ||
               metodo.equals("Efectivo") || metodo.equals("Tarjeta") ||
               metodo.equals("Transferencia") || metodo.equals("Mixto");
    }

    private boolean validarEstado(String estado) {
        return estado == null || estado.isEmpty() ||
               estado.equals("Pendiente") || estado.equals("Pagada") ||
               estado.equals("Anulada");
    }

    private boolean validarTotales(Factura f) {
        double esperado = f.getSubtotal() + f.getImpuesto() - f.getDescuento();
        return Math.abs(f.getTotal() - esperado) < 0.01; // tolerancia pequeña por decimales
    }

    // === AGREGAR FACTURA ===
    public void agregar(Factura f) throws SQLException {
        if (f == null) {
            System.out.println("Error: la factura no puede ser nula.");
            return;
        }

        if (f.getDuenoId() <= 0 || !existeDueno(f.getDuenoId())) {
            System.out.println("Error: el dueño con ID " + f.getDuenoId() + " no existe.");
            return;
        }

        if (f.getNumeroFactura() == null || f.getNumeroFactura().trim().isEmpty()) {
            System.out.println("Error: el número de factura es obligatorio.");
            return;
        }

        if (existeNumeroFactura(f.getNumeroFactura())) {
            System.out.println("Error: ya existe una factura con ese número.");
            return;
        }

        if (f.getSubtotal() < 0 || f.getImpuesto() < 0 || f.getDescuento() < 0 || f.getTotal() < 0) {
            System.out.println("Error: los valores monetarios no pueden ser negativos.");
            return;
        }

        if (!validarTotales(f)) {
            System.out.println("Error: el total no coincide con subtotal + impuesto - descuento.");
            return;
        }

        if (!validarMetodoPago(f.getMetodoPago())) {
            System.out.println("Error: método de pago inválido.");
            return;
        }

        if (!validarEstado(f.getEstado())) {
            System.out.println("Error: estado inválido.");
            return;
        }

        String sql = "INSERT INTO facturas (dueno_id, numero_factura, fecha_emision, subtotal, impuesto, descuento, total, metodo_pago, estado, observaciones) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, f.getDuenoId());
            ps.setString(2, f.getNumeroFactura());
            ps.setTimestamp(3, new Timestamp(f.getFechaEmision().getTime()));
            ps.setDouble(4, f.getSubtotal());
            ps.setDouble(5, f.getImpuesto());
            ps.setDouble(6, f.getDescuento());
            ps.setDouble(7, f.getTotal());
            ps.setString(8, f.getMetodoPago());
            ps.setString(9, f.getEstado());
            ps.setString(10, f.getObservaciones());

            ps.executeUpdate();
            System.out.println("Factura insertada correctamente.");
        }
    }

    // === LISTAR FACTURAS ===
    public List<Factura> listar() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas ORDER BY fecha_emision DESC";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Factura f = new Factura(
                    rs.getInt("id"),
                    rs.getInt("dueno_id"),
                    rs.getString("numero_factura"),
                    rs.getTimestamp("fecha_emision"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("impuesto"),
                    rs.getDouble("descuento"),
                    rs.getDouble("total"),
                    rs.getString("metodo_pago"),
                    rs.getString("estado"),
                    rs.getString("observaciones")
                );
                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar facturas: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // === ACTUALIZAR FACTURA ===
    public void actualizar(Factura f) throws SQLException {
        if (f == null || f.getId() <= 0) {
            System.out.println("Error: ID de factura inválido.");
            return;
        }

        if (!existeDueno(f.getDuenoId())) {
            System.out.println("Error: el dueño con ID " + f.getDuenoId() + " no existe.");
            return;
        }

        if (f.getSubtotal() < 0 || f.getImpuesto() < 0 || f.getDescuento() < 0 || f.getTotal() < 0) {
            System.out.println("Error: los valores monetarios no pueden ser negativos.");
            return;
        }

        if (!validarTotales(f)) {
            System.out.println("Error: el total no coincide con subtotal + impuesto - descuento.");
            return;
        }

        if (!validarMetodoPago(f.getMetodoPago())) {
            System.out.println("Error: método de pago inválido.");
            return;
        }

        if (!validarEstado(f.getEstado())) {
            System.out.println("Error: estado inválido.");
            return;
        }

        String sql = "UPDATE facturas SET dueno_id=?, numero_factura=?, fecha_emision=?, subtotal=?, impuesto=?, descuento=?, total=?, metodo_pago=?, estado=?, observaciones=? WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, f.getDuenoId());
            ps.setString(2, f.getNumeroFactura());
            ps.setTimestamp(3, new Timestamp(f.getFechaEmision().getTime()));
            ps.setDouble(4, f.getSubtotal());
            ps.setDouble(5, f.getImpuesto());
            ps.setDouble(6, f.getDescuento());
            ps.setDouble(7, f.getTotal());
            ps.setString(8, f.getMetodoPago());
            ps.setString(9, f.getEstado());
            ps.setString(10, f.getObservaciones());
            ps.setInt(11, f.getId());

            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Factura actualizada correctamente.");
            else
                System.out.println("No se encontró factura con id = " + f.getId());

        }
    }

    // === ELIMINAR FACTURA ===
    public void eliminar(Factura f) throws SQLException {
        if (f == null || f.getId() <= 0) {
            System.out.println("Error: ID de factura inválido.");
            return;
        }

        String sql = "DELETE FROM facturas WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, f.getId());
            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Factura eliminada correctamente.");
            else
                System.out.println("No se encontró factura con id = " + f.getId());
        }
    }
}
