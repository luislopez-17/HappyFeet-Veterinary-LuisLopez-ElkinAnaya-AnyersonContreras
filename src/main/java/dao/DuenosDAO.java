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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Duenos;
import utils.ConexionDB;


/**
 *
 * @author usuario
 */
public class DuenosDAO {
    public boolean existedocumento(Connection con, String documento) throws SQLException{
        String sql = "SELECT COUNT(*) FROM duenos WHERE documento_identidad = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, documento);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; //true si ya existe
                }
            }
        }
        return false;
    }
    
    public boolean existeEmail(Connection con, String email) throws SQLException{
        String sql = "SELECT COUNT(*) FROM duenos WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; // true si ya existe
                }
            }
        }
        return false;
    }
    
    public void agregar(Duenos d, Connection con, String email, String documento) throws SQLException{
        if(existedocumento(con, documento)){
            System.out.println("El documento ya esta registrado, no se puede insertar dueño");
            return;
        }
        if(existeEmail(con, email)){
            System.out.println("El email ya esta registrado, no se puede insertar dueño");
            return;
        }
        
        String sql = "INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia) VALUES (?,?,?,?,?,?)" ;
        
        try(PreparedStatement ps = con.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, d.getNombreCompleto());
            ps.setString(2, documento);
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, email);
            ps.setString(6, d.getContactoEmergencia());
            
            int filas = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    
                    System.out.println("Dueno insertado correctamente con id = " + idGenerado);                    
                }
            }
            System.out.println("Dueno agregado. Filas afectadas " + filas);
        } catch (SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizar(Duenos d){
        String sql = "UPDATE duenos SET nombre_completo = ?, documento_identidad = ?, direccion = ?, telefono = ?, email = ?, contacto_emergencia = ?, activo = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // validacion si existe otro dueño con el mismo email o documento (excepto el actual)
            String checkSql = "SELECT COUNT(*) FROM duenos WHERE (email = ? OR documento_identidad = ?) AND id <> ?";
            try (PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                checkPs.setString(1, d.getEmail());
                checkPs.setString(2, d.getDocumento());
                checkPs.setInt(3, d.getID());
                try (ResultSet rs = checkPs.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        System.out.println("El correo o documento ya pertenece a otro dueño. No se puede actualizar.");
                        return;
                    }
                }
            }

            ps.setString(1, d.getNombreCompleto());
            ps.setString(2, d.getDocumento());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getContactoEmergencia());
            ps.setBoolean(7, d.getActivo());
            ps.setInt(8, d.getID());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualización exitosa. Filas afectadas: " + filas);
            } else {
                System.out.println("No se encontró un dueño con id = " + d.getID());
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(Duenos d){
        String sql = "DELETE FROM duenos WHERE id = ?";
        
        try(Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, d.getID());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La eliminacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No hay un dueño con este id = " + d.getID());
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<Duenos> listar(){
        List<Duenos> lista = new ArrayList<>();
        
        String sql = "SELECT id, nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia, fecha_registro, activo FROM duenos";
        
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_completo");
                String documento = rs.getString("documento_identidad");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String contacto = rs.getString("contacto_emergencia");
                Timestamp fechaRegistro = rs.getTimestamp("fecha_registro");
                boolean activo = rs.getBoolean("activo");
                
                Duenos d = new Duenos(id, nombre, documento, direccion, telefono, email, contacto,fechaRegistro,activo);
                lista.add(d);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
}

