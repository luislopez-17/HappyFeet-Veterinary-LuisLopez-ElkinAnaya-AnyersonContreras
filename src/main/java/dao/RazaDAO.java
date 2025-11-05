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
import model.Razas;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class RazaDAO {
    public boolean existeEspecie(Connection con, int especieId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM especies WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, especieId);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; // true si si existe
                }
            }
        }
        return false;
    }
    
    public void agregar(Razas r, Connection con, int especieId) throws SQLException{       
        if (!existeEspecie(con, especieId)) {
        System.out.println("La especie con ID " + especieId + " no existe. No se puede insertar la raza.");
        return;
    }
        String sql = "INSERT INTO razas(especie_id, nombre, caracteristicas) VALUES  (?,?,?)";
        
        try(PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, especieId);
            ps.setString(2, r.getNombre());
            ps.setString(3, r.getCaracteristicas());
            
            int filas = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    
                    System.out.println("Raza insertada con id = " + idGenerado);
                }
            }
            System.out.println("Raza agregada. Filas afectadas: " + filas);
        }catch (SQLException ex) {
            if (ex.getErrorCode() == 1452) { //Captura error en las llaves foraneos
            System.out.println("la especie no existe o la relación no es válida.");
        } else {
            System.out.println("Error SQL: " + ex.getMessage());
        }
        ex.printStackTrace();
        } 
    }
    
    public void actualizar(Razas r, int especieId) throws SQLException {
        try (Connection con = ConexionDB.conectar()) {
        // Validar si la especie existe
            if (!existeEspecie(con, especieId)) {
                System.out.println("La especie con ID " + especieId + " no existe. No se puede actualizar la raza.");
                return;
            }
            String sql = "UPDATE razas SET especie_id = ?, nombre = ?, caracteristicas = ? WHERE id = ?";
            try(
                PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, especieId);
                ps.setString(2, r.getNombre());
                ps.setString(3, r.getCaracteristicas());
                ps.setInt(4, r.getId());

                int filas = ps.executeUpdate();
                if(filas > 0){
                    System.out.println("Actualizacion correcta, filas afectadas = " + filas);
                }else{
                    System.out.println("No se encontró la raza con id: " + r.getId());
                }
            }catch(SQLException ex){
                System.out.println("Error SQL al actualizar raza: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    public void eliminar(Razas r){
        String sql = "DELETE FROM razas WHERE id = ?";
        try(Connection con = ConexionDB.conectar();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, r.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("Raza eliminada correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No se encontró la raza con id: " + r.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error SQL al elimar la raza: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<Razas> listar(){
        List<Razas> lista = new ArrayList<>();
        
        String sql = "SELECT id, especie_id , nombre, caracteristicas FROM razas";
        
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                int especieID = rs.getInt("especie_id");
                String nombre = rs.getString("nombre");
                String caracteristicas = rs.getString("caracteristicas");
                
                Razas r = new Razas(id, especieID , nombre, caracteristicas);
                lista.add(r);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
}
