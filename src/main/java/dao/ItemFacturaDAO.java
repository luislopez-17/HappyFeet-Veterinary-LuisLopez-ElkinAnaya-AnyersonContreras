/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ItemFactura;
import utils.ConexionDB;

/**
 *
 * @author ELKIN
 */
public class ItemFacturaDAO {

    // ==========================
    // 🔹 VALIDACIONES AUXILIARES
    // ==========================

    private boolean existeFactura(int facturaId) throws SQLException {
        String sql = "SELECT id FROM facturas WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, facturaId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    private boolean existeProducto(int productoId) throws SQLException {
        String sql = "SELECT id FROM inventario WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    private boolean existeServicio(int servicioId) throws SQLException {
        String sql = "SELECT id FROM servicios WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, servicioId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    // ==================================
    // 🔹 AGREGAR ITEM FACTURA (CREATE)
    // ==================================
    public void agregar(ItemFactura i) {
        if (i.getTipoItem() == null || i.getTipoItem().isBlank()) {
            System.out.println("El tipo de ítem no puede estar vacío.");
            return;
        }

        if (i.getCantidad() <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }

        if (i.getPrecioUnitario() < 0) {
            System.out.println("El precio unitario no puede ser negativo.");
            return;
        }

        // Calcular subtotal automáticamente
        double subtotalCalculado = i.getCantidad() * i.getPrecioUnitario();

        try (Connection con = ConexionDB.conectar()) {
            // Validar existencia de la factura
            if (!existeFactura(i.getFacturaId())) {
                System.out.println("Error: la factura con ID " + i.getFacturaId() + " no existe.");
                return;
            }

            // Validar coherencia tipo_item - producto_id / servicio_id
            if (i.getTipoItem().equalsIgnoreCase("Producto")) {
                if (i.getProductoId() == 0 || i.getServicioId() != 0) {
                    System.out.println("Error: si el tipo es 'Producto', debe tener producto_id válido y servicio_id nulo.");
                    return;
                }
                if (!existeProducto(i.getProductoId())) {
                    System.out.println("Error: el producto con ID " + i.getProductoId() + " no existe.");
                    return;
                }
            } else if (i.getTipoItem().equalsIgnoreCase("Servicio")) {
                if (i.getServicioId() == 0 || i.getProductoId() != 0) {
                    System.out.println("Error: si el tipo es 'Servicio', debe tener servicio_id válido y producto_id nulo.");
                    return;
                }
                if (!existeServicio(i.getServicioId())) {
                    System.out.println("Error: el servicio con ID " + i.getServicioId() + " no existe.");
                    return;
                }
                if (i.getServicioDescripcion() == null || i.getServicioDescripcion().isBlank()) {
                    System.out.println("Debe especificar una descripción para el servicio.");
                    return;
                }
            } else {
                System.out.println("Error: tipo de ítem no válido. Debe ser 'Producto' o 'Servicio'.");
                return;
            }

            String sql = """
                    INSERT INTO items_factura 
                    (factura_id, tipo_item, producto_id, servicio_id, servicio_descripcion, cantidad, precio_unitario, subtotal)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, i.getFacturaId());
                ps.setString(2, i.getTipoItem());
                ps.setObject(3, i.getProductoId() == 0 ? null : i.getProductoId(), Types.INTEGER);
                ps.setObject(4, i.getServicioId() == 0 ? null : i.getServicioId(), Types.INTEGER);
                ps.setString(5, i.getServicioDescripcion());
                ps.setInt(6, i.getCantidad());
                ps.setDouble(7, i.getPrecioUnitario());
                ps.setDouble(8, subtotalCalculado);

                ps.executeUpdate();
                System.out.println("Ítem de factura agregado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar ítem de factura: " + e.getMessage());
        }
    }

    // ==================================
    // 🔹 ACTUALIZAR ITEM FACTURA (UPDATE)
    // ==================================
    public void actualizar(ItemFactura i) {
        if (i.getId() <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        if (i.getCantidad() <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }

        if (i.getPrecioUnitario() < 0) {
            System.out.println("El precio unitario no puede ser negativo.");
            return;
        }

        double subtotalCalculado = i.getCantidad() * i.getPrecioUnitario();

        try (Connection con = ConexionDB.conectar()) {
            if (!existeFactura(i.getFacturaId())) {
                System.out.println("Error: la factura con ID " + i.getFacturaId() + " no existe.");
                return;
            }

            String sql = """
                    UPDATE items_factura 
                    SET factura_id=?, tipo_item=?, producto_id=?, servicio_id=?, 
                        servicio_descripcion=?, cantidad=?, precio_unitario=?, subtotal=? 
                    WHERE id=?
                    """;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, i.getFacturaId());
                ps.setString(2, i.getTipoItem());
                ps.setObject(3, i.getProductoId() == 0 ? null : i.getProductoId(), Types.INTEGER);
                ps.setObject(4, i.getServicioId() == 0 ? null : i.getServicioId(), Types.INTEGER);
                ps.setString(5, i.getServicioDescripcion());
                ps.setInt(6, i.getCantidad());
                ps.setDouble(7, i.getPrecioUnitario());
                ps.setDouble(8, subtotalCalculado);
                ps.setInt(9, i.getId());

                int filas = ps.executeUpdate();
                if (filas > 0) System.out.println("Ítem actualizado correctamente.");
                else System.out.println("No se encontró un ítem con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar ítem de factura: " + e.getMessage());
        }
    }

    // ==================================
    // 🔹 ELIMINAR ITEM FACTURA (DELETE)
    // ==================================
    public void eliminar(ItemFactura i) {
        if (i.getId() <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement("DELETE FROM items_factura WHERE id=?")) {
            ps.setInt(1, i.getId());
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Ítem eliminado correctamente.");
            else System.out.println("No se encontró ítem con ese ID.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar ítem de factura: " + e.getMessage());
        }
    }

    // ==================================
    // 🔹 LISTAR ITEMS (READ)
    // ==================================
    public List<ItemFactura> listar() {
        List<ItemFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM items_factura";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ItemFactura i = new ItemFactura(
                        rs.getInt("id"),
                        rs.getInt("factura_id"),
                        rs.getString("tipo_item"),
                        rs.getInt("producto_id"),
                        rs.getInt("servicio_id"),
                        rs.getString("servicio_descripcion"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal")
                );
                lista.add(i);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar ítems de factura: " + e.getMessage());
        }
        return lista;
    }
}
