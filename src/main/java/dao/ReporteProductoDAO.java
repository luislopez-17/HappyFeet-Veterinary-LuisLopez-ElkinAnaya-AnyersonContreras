/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ReporteProducto;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class ReporteProductoDAO {
    public List<ReporteProducto> obtenerProductosMasVendidos(Date fechaInicio, Date fechaFin) {
        List<ReporteProducto> lista = new ArrayList<>();

        String sql = """
            SELECT i.nombre_producto AS nombre,
                   SUM(it.cantidad) AS cantidad_total,
                   SUM(it.subtotal) AS ingresos_totales
            FROM facturas f
            JOIN items_factura it ON f.id = it.factura_id
            JOIN inventario i ON it.producto_id = i.id
            WHERE it.tipo_item = 'Producto'
              AND f.fecha_emision BETWEEN ? AND ?
            GROUP BY i.nombre_producto
            ORDER BY ingresos_totales DESC
        """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad_total");
                double ingresos = rs.getDouble("ingresos_totales");
                lista.add(new ReporteProducto(nombre, cantidad, ingresos));
            }

        } catch (SQLException ex) {
            System.err.println("Error al generar reporte: " + ex.getMessage());
        }

        return lista;
    }
}
