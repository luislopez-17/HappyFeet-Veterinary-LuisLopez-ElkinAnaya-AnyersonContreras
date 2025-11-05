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
import model.Especies;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class EspecieDAO {
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
    
    public void agregar(Especies e, Connection con, String nombre)throws SQLException{
        if(existeNombre(con, nombre)){
            System.out.println("El nombre ya esta registrado, no se puede insertar especie");
            return;
        }
        String sql = "INSERT INTO especies(nombre, descripcion) VALUES  (?,?)";
        
        
        try(PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDescripcion());
            
            int filas = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    
                    System.out.println("Especie insertada con id = " + idGenerado);
                }
            }
            System.out.println("Especie agregada. Filas afectadas: " + filas);
        }catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        } 
    }
    
   
    public void actualizar(Especies e,Connection con, String nombre) throws SQLException{
        if(existeNombre(con, nombre)){
            System.out.println("El nombre ya esta registrado, no se puede actualizar especie");
            return;
        }

        String sql = "UPDATE especies SET nombre = ?, descripcion = ? WHERE id = ?";
        
        try(
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDescripcion());
            ps.setInt(3, e.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("Actualizacion correcta, filas afectadas = " + filas);
            }else{
                System.out.println("No se encontró la especie con id: " + e.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error SQL al actualizar especie: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(Especies e){
        String sql = "DELETE FROM especies WHERE id = ?";
        try(Connection con =  ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, e.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
               System.out.println("Especie eliminada correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No se encontró la especie con id: " + e.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error SQL al eliminar especie: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<Especies> listar(){
        List<Especies> lista = new ArrayList<>();
        
        String sql = "SELECT id, nombre, descripcion FROM especies";
        
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                
                Especies e = new Especies(id, nombre, descripcion);
                lista.add(e);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
    
}
