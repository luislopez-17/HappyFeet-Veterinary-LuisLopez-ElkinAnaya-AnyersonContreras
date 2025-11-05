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
import model.EventosTipos;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class EventoTipoDAO {
    
    public boolean existeNombre(Connection con, String nombre) throws SQLException{
        String sql = "SELECT COUNT(*) FROM evento_tipos WHERE nombre = ?";
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
    
    public void agregar(EventosTipos et,Connection con, String nombre)throws SQLException{
        if(existeNombre(con, nombre)){
            System.out.println("El nombre ya esta registrado, no se puede insertar producto tipo");
            return;
        }
        String sql = "INSERT INTO evento_tipos(nombre, descripcion) VALUES (?,?)";
        
        try(
            PreparedStatement ps = con.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, et.getNombre());
            ps.setString(2, et.getDescripcion());
            
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
    
    public void actualizar(EventosTipos et,Connection con, String nombre)throws SQLException{
        if(existeNombre(con, nombre)){
            System.out.println("El nombre ya esta registrado, no se puede actualizar Evento tipo");
            return;
        }
        String sql = "UPDATE evento_tipos SET nombre = ?, descripcion = ? WHERE id = ?";
        
        try(
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, et.getNombre());
            ps.setString(2, et.getDescripcion());
            ps.setInt(3, et.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La actualizacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No hay un evento tipo con este id = " + et.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(EventosTipos et){
        String sql = "DELETE FROM evento_tipos WHERE id = ?";
        
        try(Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, et.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La eliminacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No hay un evento tipo con este id = " + et.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    public List<EventosTipos> listar(){
        List<EventosTipos> lista = new ArrayList<>();
        
        String sql = "SELECT id, nombre, descripcion FROM evento_tipos";
        
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                
                EventosTipos pt = new EventosTipos(id, nombre, descripcion);
                lista.add(pt);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
}
