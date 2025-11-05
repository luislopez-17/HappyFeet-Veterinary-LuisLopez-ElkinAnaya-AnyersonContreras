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
import model.CitaEstados;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class CitaEstadosDAO {
    public boolean existeNombre(Connection con, String nombre) throws SQLException{
        String sql = "SELECT COUNT(*) FROM cita_estados WHERE nombre = ?";
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
    
    public void agregar(CitaEstados ce, Connection con, String nombre) throws SQLException{
        if(existeNombre(con, nombre)){
            System.out.println("El nombre ya esta registrado, no se puede insertar cita estado");
            return;
        }
        String sql = "INSERT INTO cita_estados(nombre, descripcion) VALUES (?,?)";
        
        try(             
            PreparedStatement ps = con.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, ce.getNombre());
            ps.setString(2, ce.getDescripcion());
            
            int filas = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    
                    System.out.println("Cita estados insertado con id = " + idGenerado);                    
                }
            }
            System.out.println("Cita estados agregado. Filas afectadas " + filas);
        } catch (SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizar(CitaEstados ce, Connection con, String nombre) throws SQLException{
        if(existeNombre(con, nombre)){
            System.out.println("El nombre ya esta registrado, no se puede actualizar cita estado");
            return;
        }
        String sql = "UPDATE cita_estados SET nombre = ?, descripcion = ? WHERE id = ?";
        
        try(
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, ce.getNombre());
            ps.setString(2, ce.getDescripcion());
            ps.setInt(3, ce.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La actualizacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No hay una cita estados con este id = " + ce.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(CitaEstados ce){
        String sql = "DELETE FROM cita_estados WHERE id = ?";
        
        try(Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, ce.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La eliminacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No hay un cita estados con este id = " + ce.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    public List<CitaEstados> listar(){
        List<CitaEstados> lista = new ArrayList<>();
        
        String sql = "SELECT id, nombre, descripcion FROM cita_estados";
        
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                
                CitaEstados ce = new CitaEstados(id, nombre, descripcion);
                lista.add(ce);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
}
