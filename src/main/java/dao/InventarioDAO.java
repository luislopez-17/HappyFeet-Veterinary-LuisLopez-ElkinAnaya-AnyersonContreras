/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Inventario;
import utils.ConexionDB;

public class InventarioDAO {

    private boolean existeProductoTipo(Connection con, int tipoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM producto_tipos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tipoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeProveedor(Connection con, int proveedorId) throws SQLException {
        if (proveedorId <= 0) return false; // Puede ser null
        String sql = "SELECT COUNT(*) FROM proveedores WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, proveedorId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeProducto(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inventario WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public void agregar(Inventario i) {
        String sql = """
            INSERT INTO inventario (
                nombre_producto, producto_tipo_id, descripcion, fabricante, proveedor_id,
                lote, cantidad_stock, stock_minimo, unidad_medida, fecha_vencimiento,
                precio_compra, precio_venta, requiere_receta
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (!existeProductoTipo(con, i.getProductoTipoId())) {
                System.out.println("El tipo de producto no existe, no se puede registrar en inventario.");
                return;
            }

            if (i.getProveedorId() > 0 && !existeProveedor(con, i.getProveedorId())) {
                System.out.println("El proveedor no existe, no se puede asignar al producto.");
                return;
            }

            ps.setString(1, i.getNombreProducto());
            ps.setInt(2, i.getProductoTipoId());

            if (i.getDescripcion() != null && !i.getDescripcion().isEmpty())
                ps.setString(3, i.getDescripcion());
            else ps.setNull(3, Types.VARCHAR);

            if (i.getFabricante() != null && !i.getFabricante().isEmpty())
                ps.setString(4, i.getFabricante());
            else ps.setNull(4, Types.VARCHAR);

            if (i.getProveedorId() > 0)
                ps.setInt(5, i.getProveedorId());
            else ps.setNull(5, Types.INTEGER);

            if (i.getLote() != null && !i.getLote().isEmpty())
                ps.setString(6, i.getLote());
            else ps.setNull(6, Types.VARCHAR);

            ps.setInt(7, i.getCantidadStock());
            ps.setInt(8, i.getStockMinimo());
            ps.setString(9, i.getUnidadMedida() != null ? i.getUnidadMedida() : "unidad");

            if (i.getFechaVencimiento() != null)
                ps.setDate(10, i.getFechaVencimiento());
            else ps.setNull(10, Types.DATE);

            if (i.getPrecioCompra() > 0)
                ps.setDouble(11, i.getPrecioCompra());
            else ps.setNull(11, Types.DECIMAL);

            ps.setDouble(12, i.getPrecioVenta());
            ps.setBoolean(13, i.isRequiereReceta());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Producto agregado con ID = " + rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error SQL al agregar producto: " + e.getMessage());
        }
    }

    public void actualizar(Inventario i) {
        String sql = """
            UPDATE inventario
            SET nombre_producto=?, producto_tipo_id=?, descripcion=?, fabricante=?, proveedor_id=?,
                lote=?, cantidad_stock=?, stock_minimo=?, unidad_medida=?, fecha_vencimiento=?,
                precio_compra=?, precio_venta=?, requiere_receta=?, activo=?
            WHERE id=?
        """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeProducto(con, i.getId())) {
                System.out.println("El producto no existe, no se puede actualizar.");
                return;
            }

            if (!existeProductoTipo(con, i.getProductoTipoId())) {
                System.out.println("El tipo de producto no existe, no se puede actualizar.");
                return;
            }

            if (i.getProveedorId() > 0 && !existeProveedor(con, i.getProveedorId())) {
                System.out.println("El proveedor no existe, no se puede actualizar.");
                return;
            }

            ps.setString(1, i.getNombreProducto());
            ps.setInt(2, i.getProductoTipoId());
            ps.setString(3, i.getDescripcion());
            ps.setString(4, i.getFabricante());
            if (i.getProveedorId() > 0)
                ps.setInt(5, i.getProveedorId());
            else ps.setNull(5, Types.INTEGER);

            ps.setString(6, i.getLote());
            ps.setInt(7, i.getCantidadStock());
            ps.setInt(8, i.getStockMinimo());
            ps.setString(9, i.getUnidadMedida());
            ps.setDate(10, i.getFechaVencimiento());
            ps.setDouble(11, i.getPrecioCompra());
            ps.setDouble(12, i.getPrecioVenta());
            ps.setBoolean(13, i.isRequiereReceta());
            ps.setBoolean(14, i.isActivo());
            ps.setInt(15, i.getId());

            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Producto actualizado correctamente." : "No se encontró el producto.");

        } catch (SQLException e) {
            System.out.println("Error SQL al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminar(Inventario i) {
        String sql = "DELETE FROM inventario WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeProducto(con, i.getId())) {
                System.out.println("El producto no existe, no se puede eliminar.");
                return;
            }

            ps.setInt(1, i.getId());
            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Producto eliminado correctamente." : "No se encontró el producto.");

        } catch (SQLException e) {
            System.out.println("Error SQL al eliminar producto: " + e.getMessage());
        }
    }

    public List<Inventario> listar() {
        List<Inventario> lista = new ArrayList<>();
        String sql = """
            SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante,
                   proveedor_id, lote, cantidad_stock, stock_minimo, unidad_medida,
                   fecha_vencimiento, precio_compra, precio_venta, requiere_receta,
                   activo, fecha_registro
            FROM inventario
        """;

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Inventario i = new Inventario(
                    rs.getInt("id"),
                    rs.getString("nombre_producto"),
                    rs.getInt("producto_tipo_id"),
                    rs.getString("descripcion"),
                    rs.getString("fabricante"),
                    rs.getInt("proveedor_id"),
                    rs.getString("lote"),
                    rs.getInt("cantidad_stock"),
                    rs.getInt("stock_minimo"),
                    rs.getString("unidad_medida"),
                    rs.getDate("fecha_vencimiento"),
                    rs.getDouble("precio_compra"),
                    rs.getDouble("precio_venta"),
                    rs.getBoolean("requiere_receta"),
                    rs.getBoolean("activo"),
                    rs.getTimestamp("fecha_registro")
                );
                lista.add(i);
            }

        } catch (SQLException e) {
            System.out.println("Error SQL al listar inventario: " + e.getMessage());
        }
        return lista;
    }
}

