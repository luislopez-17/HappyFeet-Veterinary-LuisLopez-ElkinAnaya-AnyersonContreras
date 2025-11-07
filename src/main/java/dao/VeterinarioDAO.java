/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Veterinarios;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class VeterinarioDAO {
    
    public boolean existeId(Connection con, int idVeterinario) throws SQLException {
    String sql = "SELECT COUNT(*) FROM veterinarios WHERE id = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, idVeterinario);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    }
    return false;
}
    public boolean existedocumento(Connection con, String documento) throws SQLException{
        String sql = "SELECT COUNT(*) FROM veterinarios WHERE documento_identidad = ?";
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
    
    public boolean existelicencia(Connection con, String licencia) throws SQLException{
        String sql = "SELECT COUNT(*) FROM veterinarios WHERE licencia_profesional = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, licencia);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; //true si ya existe
                }
            }
        }
        return false;
    }
    
    public boolean existeEmail(Connection con, String email) throws SQLException{
        String sql = "SELECT COUNT(*) FROM veterinarios WHERE email = ?";
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
    
    public void agregar(Veterinarios v, Connection con, String email, String documento, String licencia) throws SQLException{
        if(existedocumento(con, documento)){
            System.out.println("El documento ya esta registrado, no se puede insertar dueño");
            return;
        }
        if (email != null && !email.trim().isEmpty()) {
            if (existeEmail(con, email)) {
                System.out.println("Ya un veterinario tiene este email, no se puede insertar veterinario");
                return;
            }
        }
        if(existelicencia(con, licencia)){
            System.out.println("La licencia ya esta registrada");
        }
        
        String sql = "INSERT INTO veterinarios (nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion) VALUES (?,?,?,?,?,?,?)" ;
        
        try(PreparedStatement ps = con.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, v.getNombre());
            ps.setString(2, documento);
            ps.setString(3, licencia);
            ps.setString(4, v.getEspecialidad());
            ps.setString(5, v.getTelefono());
            if (email == null || email.trim().isEmpty()) {
                ps.setNull(6, java.sql.Types.VARCHAR);
            } else {
                ps.setString(6, email.trim());
            }
            ps.setDate(7, v.getFechaContratacion());
            
            int filas = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    
                    System.out.println("Veterinario insertado correctamente con id = " + idGenerado);                    
                }
            }
            System.out.println("Veterinario agregado. Filas afectadas " + filas);
        } catch (SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizar(Veterinarios v){
        String sql = "UPDATE veterinarios SET nombre_completo = ?, documento_identidad = ?, licencia_profesional = ? , especialidad = ?, telefono = ?, email = ?, fecha_contratacion = ?, activo = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // validacion si existe otro veterinario con el mismo email o documento (excepto el actual)
            String checkSql = "SELECT COUNT(*) FROM veterinarios WHERE (documento_identidad = ? OR licencia_profesional = ? OR email = ?) AND id <> ?";
            try (PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                checkPs.setString(1, v.getDocumento());
                checkPs.setString(2, v.getLicencia());
                // Si el email es null o vacío, se pone NULL para evitar comparación errónea
                if (v.getEmail() == null || v.getEmail().trim().isEmpty()) {
                    checkPs.setNull(3, java.sql.Types.VARCHAR);
                } else {
                    checkPs.setString(3, v.getEmail().trim());
                }
                checkPs.setInt(4, v.getId());
            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("El documento, licencia o correo ya pertenece a otro veterinario. No se puede actualizar.");
                    return;
                }
            }
        }
            ps.setString(1, v.getNombre());
            ps.setString(2, v.getDocumento());
            ps.setString(3, v.getLicencia());
            ps.setString(4, v.getEspecialidad());
            ps.setString(5, v.getTelefono());
            if (v.getEmail() == null || v.getEmail().trim().isEmpty()) {
                ps.setNull(6, java.sql.Types.VARCHAR);
            } else {
                ps.setString(6, v.getEmail().trim());
            }
            ps.setDate(7, v.getFechaContratacion());
            ps.setBoolean(8, v.getActivo());
            ps.setInt(9, v.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualización exitosa. Filas afectadas: " + filas);
            } else {
                System.out.println("No se encontró un dueño con id = " + v.getId());
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(Veterinarios v){
        String sql = "DELETE FROM veterinarios WHERE id = ?";
        
        try(Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, v.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La eliminacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No hay un veterinario con este id = " + v.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<Veterinarios> listar(){
        List<Veterinarios> lista = new ArrayList<>();
        
        String sql = "SELECT id, nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion, activo FROM veterinarios";
        
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_completo");
                String documento = rs.getString("documento_identidad");
                String licencia = rs.getString("licencia_profesional");
                String especialidad = rs.getString("especialidad");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                Date fechaC = rs.getDate("fecha_contratacion");
                boolean activo = rs.getBoolean("activo");
                
                Veterinarios v = new Veterinarios(id, nombre, documento, licencia, especialidad, telefono, email, fechaC, activo);
                lista.add(v);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
}
