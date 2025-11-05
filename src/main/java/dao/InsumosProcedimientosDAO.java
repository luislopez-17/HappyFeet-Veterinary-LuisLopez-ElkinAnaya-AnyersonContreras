package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.InsumosProcedimientos;
import utils.ConexionDB;

public class InsumosProcedimientosDAO {

    // ====== VALIDACIONES DE EXISTENCIA ======
    private boolean existeProcedimiento(Connection con, int procedimientoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM procedimientos_especiales WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, procedimientoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeProducto(Connection con, int productoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inventario WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean existeInsumoProcedimiento(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM insumos_procedimientos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // ====== MÉTODO AGREGAR ======
    public void agregar(InsumosProcedimientos i) {
        String sql = "INSERT INTO insumos_procedimientos (procedimiento_id, producto_id, cantidad_usada, observaciones) VALUES (?,?,?,?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // === VALIDACIONES ===
            if (!existeProcedimiento(con, i.getProcedimientoId())) {
                System.out.println("El ID del procedimiento no existe, no se puede agregar el insumo.");
                return;
            }

            if (!existeProducto(con, i.getProductoId())) {
                System.out.println("El ID del producto no existe, no se puede agregar el insumo.");
                return;
            }

            if (i.getCantidadUsada() <= 0) {
                System.out.println("La cantidad usada debe ser mayor que 0.");
                return;
            }

            // === ASIGNACIÓN ===
            ps.setInt(1, i.getProcedimientoId());
            ps.setInt(2, i.getProductoId());
            ps.setInt(3, i.getCantidadUsada());
            ps.setString(4, i.getObservaciones());

            int filas = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println("Insumo agregado correctamente con ID = " + rs.getInt(1));
                }
            }
            System.out.println("Filas afectadas: " + filas);

        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar insumo_procedimiento: " + ex.getMessage());
        }
    }

    // ====== MÉTODO ACTUALIZAR ======
    public void actualizar(InsumosProcedimientos i) {
        String sql = "UPDATE insumos_procedimientos SET procedimiento_id=?, producto_id=?, cantidad_usada=?, observaciones=? WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // === VALIDACIONES ===
            if (!existeInsumoProcedimiento(con, i.getId())) {
                System.out.println("El insumo no existe, no se puede actualizar.");
                return;
            }

            if (!existeProcedimiento(con, i.getProcedimientoId())) {
                System.out.println("El ID del procedimiento no existe, no se puede actualizar.");
                return;
            }

            if (!existeProducto(con, i.getProductoId())) {
                System.out.println("El ID del producto no existe, no se puede actualizar.");
                return;
            }

            if (i.getCantidadUsada() <= 0) {
                System.out.println("La cantidad usada debe ser mayor que 0.");
                return;
            }

            // === ASIGNACIÓN ===
            ps.setInt(1, i.getProcedimientoId());
            ps.setInt(2, i.getProductoId());
            ps.setInt(3, i.getCantidadUsada());
            ps.setString(4, i.getObservaciones());
            ps.setInt(5, i.getId());

            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Insumo actualizado correctamente. Filas afectadas: " + filas);
            else
                System.out.println("No se encontró un insumo con id = " + i.getId());

        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar insumo_procedimiento: " + ex.getMessage());
        }
    }

    // ====== MÉTODO ELIMINAR ======
    public void eliminar(InsumosProcedimientos i) {
        String sql = "DELETE FROM insumos_procedimientos WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeInsumoProcedimiento(con, i.getId())) {
                System.out.println("El insumo no existe, no se puede eliminar.");
                return;
            }

            ps.setInt(1, i.getId());
            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Insumo eliminado correctamente. Filas afectadas: " + filas);
            else
                System.out.println("No se encontró un insumo con id = " + i.getId());

        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar insumo_procedimiento: " + ex.getMessage());
        }
    }

    // ====== MÉTODO LISTAR ======
    public List<InsumosProcedimientos> listar() {
    List<InsumosProcedimientos> lista = new ArrayList<>();

    String sql = """
        SELECT ip.id, 
               ip.procedimiento_id, 
               ip.producto_id, 
               ip.cantidad_usada, 
               ip.observaciones,
               p.nombre_procedimiento AS procedimiento, 
               inv.nombre_producto AS producto
        FROM insumos_procedimientos ip
        JOIN procedimientos_especiales p ON ip.procedimiento_id = p.id
        JOIN inventario inv ON ip.producto_id = inv.id
        ORDER BY ip.id ASC
    """;

    try (Connection con = ConexionDB.conectar();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            int id = rs.getInt("id");
            int procedimientoId = rs.getInt("procedimiento_id");
            int productoId = rs.getInt("producto_id");
            int cantidadUsada = rs.getInt("cantidad_usada");
            String observaciones = rs.getString("observaciones");

            // Campos adicionales (nombres descriptivos)
            String procedimiento = rs.getString("procedimiento");
            String producto = rs.getString("producto");

            // Crear objeto modelo
            InsumosProcedimientos insumo = new InsumosProcedimientos(id, procedimientoId, productoId, cantidadUsada, observaciones);
            insumo.setProcedimiento(procedimiento);
            insumo.setProducto(producto);

            lista.add(insumo);
        }

        if (lista.isEmpty()) {
            System.out.println("No hay registros de insumos de procedimientos.");
        }

    } catch (SQLException ex) {
        System.out.println("Error SQL al listar insumos_procedimientos: " + ex.getMessage());
        ex.printStackTrace();
    }

    return lista;
}

}
