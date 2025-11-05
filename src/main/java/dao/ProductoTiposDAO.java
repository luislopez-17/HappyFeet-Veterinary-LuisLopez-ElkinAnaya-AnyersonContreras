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
import model.ProductoTipos;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class ProductoTiposDAO {
    public boolean existeNombre(Connection con, String nombre) throws SQLException{
        String sql = "SELECT COUNT(*) FROM producto_tipos WHERE nombre = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, nombre);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; //true si ya existe
                }
            }
        }
        return false;
    }
    
    public void agregar(ProductoTipos pt,Connection con, String nombre)throws SQLException{
        if(existeNombre(con, nombre)){
            System.out.println("El nombre ya esta registrado, no se puede insertar producto tipo");
            return;
        }
        String sql = "INSERT INTO producto_tipos(nombre, descripcion) VALUES (?,?)";
        
        try(
            PreparedStatement ps = con.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, pt.getNombre());
            ps.setString(2, pt.getDescripcion());
            
            int filas = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    
                    System.out.println("Producto tipo insertado con id = " + idGenerado);                    
                }
            }
            System.out.println("Producto Tipo agregado. Filas afectadas " + filas);
        } catch (SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizar(ProductoTipos pt,Connection con, String nombre)throws SQLException{
        if(existeNombre(con, nombre)){
            System.out.println("El nombre ya esta registrado, no se puede actualizar producto tipo");
            return;
        }
        String sql = "UPDATE producto_tipos SET nombre = ?, = descripcion = ? WHERE id = ?";
        
        try(
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, pt.getNombre());
            ps.setString(2, pt.getDescripcion());
            ps.setInt(3, pt.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La actualizacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No se encontro el producto tipo con id = " + pt.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error al actualizar " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(ProductoTipos pt){
        String sql = "DELETE FROM producto_tipos WHERE id = ?";
        
        try(Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, pt.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La eliminacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No se encontro el producto tipo con id = " + pt.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error al eliminar " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<ProductoTipos> listar(){
        List<ProductoTipos> lista = new ArrayList<>();
        
        String sql = "SELECT id, nombre, descripcion FROM producto_tipos";
        
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                
                ProductoTipos pt = new ProductoTipos(id, nombre, descripcion);
                lista.add(pt);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
}
