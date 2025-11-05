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
import java.util.ArrayList;
import java.util.List;
import model.MovimientoInventario;
import utils.ConexionDB;
/**
 *
 * @author ELKIN
 */
public class MovimientosInventarioDAO {
    public void agregar(MovimientoInventario mi) {
        String sql = "INSERT INTO movimientos_inventario (producto_id, tipo_movimiento, cantidad, stock_anterior, stock_nuevo, motivo, referencia_consulta_id, referencia_procedimiento_id, usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, mi.getProductoId());
            ps.setString(2, mi.getTipoMovimiento());
            ps.setInt(3, mi.getCantidad());
            ps.setInt(4, mi.getStockAnterior());
            ps.setInt(5, mi.getStockNuevo());
            ps.setString(6, mi.getMotivo());
           
             if (mi.getReferenciaConsultaId()!= null) {
                ps.setInt(7, mi.getReferenciaConsultaId());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }
            
            if (mi.getReferenciaConsultaId() != null) {
                ps.setInt(8, mi.getReferenciaProcedimientoId());
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }
            
            ps.setString(9, mi.getUsuario());
            
            int filas = ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    System.out.println("Movimiento insertado con ID = " + idGenerado);
                }
            }
            
            System.out.println("Movimiento agregado. Filas afectadas = " + filas);
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar movimiento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    //Listar 
    public List <MovimientoInventario> listar (){
        List<MovimientoInventario> lista = new ArrayList<>();
        
        
        String sql = "SELECT id,producto_id, tipo_movimiento, cantidad, stock_anterior, stock_nuevo, motivo, referencia_consulta_id, referencia_procedimiento_id, usuario, fecha_movimiento FROM movimientos_inventario";
        
        try (Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while (rs.next()){
                MovimientoInventario mi = new MovimientoInventario(
                    rs.getInt("id"),
                    rs.getInt("producto_id"),
                    rs.getString("tipo_movimiento"),
                    rs.getInt("cantidad"),
                    rs.getInt("stock_anterior"),
                    rs.getInt("stock_nuevo"),
                    rs.getString("motivo"),
                    (Integer) rs.getObject("referencia_consulta_id"),
                    (Integer) rs.getObject("referencia_procedimiento_id"),
                    rs.getString("usuario"),
                    null
                );
                
                mi.setFechaMovimiento(rs.getTimestamp("fecha_movimiento"));
                lista.add(mi);
            }
            
       
            } catch (SQLException e) {
                System.out.println("Error SQL al listar movimientos: " + e.getMessage());
                e.printStackTrace();
            }

            return lista;
    }
    
     // Actualizar
    public boolean actualizar(MovimientoInventario mi) {
        String sql = "UPDATE movimientos_inventario SET producto_id=?, tipo_movimiento=?, cantidad=?, stock_anterior=?, stock_nuevo=?, motivo=?, referencia_consulta_id=?, referencia_procedimiento_id=?, usuario=? WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, mi.getProductoId());
            ps.setString(2, mi.getTipoMovimiento());
            ps.setInt(3, mi.getCantidad());
            ps.setInt(4, mi.getStockAnterior());
            ps.setInt(5, mi.getStockNuevo());
            ps.setString(6, mi.getMotivo());
            
            if (mi.getReferenciaConsultaId() != null) {
                ps.setInt(7, mi.getReferenciaConsultaId());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }
            
            if (mi.getReferenciaProcedimientoId() != null) {
                ps.setInt(8, mi.getReferenciaProcedimientoId());
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }
            
            ps.setString(9, mi.getUsuario());
            ps.setInt(10, mi.getId());
            
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualización exitosa. Filas afectadas = " + filas);
                return true;
            } else {
                System.out.println("No existe movimiento con id = " + mi.getId());
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar movimiento: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    // Eliminar
    public boolean eliminar(int id) {
        String sql = "DELETE FROM movimientos_inventario WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Eliminación exitosa. Filas afectadas = " + filas);
                return true;
            } else {
                System.out.println("No existe un movimiento con id = " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar movimiento: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
}
    
  
    


