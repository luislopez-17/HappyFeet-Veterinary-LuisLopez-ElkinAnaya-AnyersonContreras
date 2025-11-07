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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Mascotas;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class MascotasDAO {
     public Mascotas buscarPorId(Connection con, int idMascota) throws SQLException {
        String sql = "SELECT * FROM mascotas WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idMascota);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Mascotas m = new Mascotas(
                        rs.getInt("id"),
                        rs.getInt("dueno_id"),
                        rs.getString("nombre"),
                        rs.getInt("raza_id"),
                        rs.getDate("fecha_nacimiento"),
                        Mascotas.Sexo.valueOf(rs.getString("sexo")),
                        rs.getDouble("peso_actual"),
                        rs.getString("microchip"),
                        rs.getString("tatuaje"),
                        rs.getString("url_foto"),
                        rs.getString("alergias"),
                        rs.getString("condiciones_preexistentes"),
                        rs.getTimestamp("fecha_registro"),
                        rs.getBoolean("activo")
                    );
                    return m;
                }
            }
        }
        return null; // si no se encontró la mascota
    }
    
    public boolean existeDueno(Connection con, int duenoId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM duenos WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, duenoId);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; // true si si existe
                }
            }
        }
        return false;
    }
    
    public boolean existeRaza(Connection con, int razaId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM razas WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, razaId);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; // true si si existe
                }
            }
        }
        return false;
    }
    
    public boolean existeMicrochip(Connection con, String microchip) throws SQLException{
        String sql = "SELECT COUNT(*) FROM mascotas WHERE microchip = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, microchip);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; //true si ya existe
                }
            }
        }
        return false;
    }
    
    private boolean existeMicrochipEnOtraMascota(Connection con, String microchip, int idMascotaActual) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mascotas WHERE microchip = ? AND id <> ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, microchip);
            ps.setInt(2, idMascotaActual);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public void agregar(Mascotas m, Connection con, int duenoId, int razaId, String microchip) throws SQLException{
        if(!existeDueno(con, duenoId)){
            System.out.println("El id de este dueño no existe, no se puede insertar.");
            return;
        }
        if(!existeRaza(con, razaId)){
            System.out.println("El id de esta raza no existe, no se puede insertar.");
            return;
        }
        if (microchip != null && !microchip.trim().isEmpty()) {
            if (existeMicrochip(con, microchip)) {
                System.out.println("Ya una mascota tiene este microchip, no se puede insertar mascota duplicada.");
                return;
            }
        }
        
        String sql = "INSERT INTO mascotas(dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_actual, microchip, tatuaje, url_foto, alergias, condiciones_preexistentes) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, duenoId);
            ps.setString(2, m.getNombre());
            ps.setInt(3, razaId);
            ps.setDate(4, m.getFechaNacimiento());
            ps.setString(5, m.getSexo().name());
            ps.setDouble(6, m.getPesoActual());
            if (microchip == null || microchip.trim().isEmpty()) {
                ps.setNull(7, java.sql.Types.VARCHAR);
            } else {
                ps.setString(7, microchip.trim());
            }
            ps.setString(8, m.getTatuaje());
            ps.setString(9, m.getUrlFoto());
            ps.setString(10, m.getAlergias());
            ps.setString(11, m.getCondiciones());
            
            int filas = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    
                    System.out.println("Mascota agregada correctamente con id = " + idGenerado);
                }
                System.out.println("Mascota agregada. filas afectadas " + filas);
            }catch (SQLException ex){
                System.out.println("Error sql: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    public void actualizar(Mascotas m, int duenoId, int razaId, String microchip) throws SQLException{
        try (Connection con = ConexionDB.conectar()){          
            if(!existeDueno(con, duenoId)){
                System.out.println("El id de este dueño no existe, no se puede insertar.");
                return;
            }
            if(!existeRaza(con, razaId)){
                System.out.println("El id de esta raza no existe, no se puede insertar.");
                return;
            }
            if (microchip != null && !microchip.trim().isEmpty()) {
                if (existeMicrochipEnOtraMascota(con, microchip, m.getId())) {
                    System.out.println("Ya una mascota tiene este microchip, no se puede actualizar.");
                    return;
                }
            }
            String sql = "UPDATE mascotas SET dueno_id = ?, nombre = ?, raza_id = ?, fecha_nacimiento = ?, sexo = ?, peso_actual = ?, microchip = ?, tatuaje = ?, url_foto = ?, alergias = ?, condiciones_preexistentes = ?, activo = ? WHERE id = ?";          
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, duenoId);
                ps.setString(2, m.getNombre());
                ps.setInt(3, razaId);
                ps.setTimestamp(4, m.getFechaRegistro());
                ps.setString(5, m.getSexo().name());
                ps.setDouble(6, m.getPesoActual());
                if (microchip == null || microchip.trim().isEmpty()) {
                    ps.setNull(7, java.sql.Types.VARCHAR);
                } else {
                    ps.setString(7, microchip.trim());
                }
                ps.setString(8, m.getTatuaje());
                ps.setString(9, m.getUrlFoto());
                ps.setString(10, m.getAlergias());
                ps.setString(11, m.getCondiciones());
                ps.setBoolean(12, m.getActivo());
                ps.setInt(13, m.getId());
                
                int filas = ps.executeUpdate();
                if(filas > 0){
                    System.out.println("Actualizacion correcta, filas afectadas = " + filas);
                }else{
                    System.out.println("No se encontró mascota con id: " + m.getId());
                }
            }catch(SQLException ex){
                System.out.println("Error SQL al actualizar mascotas: " + ex.getMessage());
                ex.printStackTrace();
            }   
        }
    }
    
    public void eliminar(Mascotas m){
        String sql = "DELETE FROM mascotas WHERE id = ?";
        try(Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, m.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("La eliminacion se hizo correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No hay mascota con este id = " + m.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<Mascotas> listar(){
        List<Mascotas> lista = new ArrayList<>();
        
        String sql = "SELECT id, dueno_id, nombre, raza_id, fecha_nacimiento,sexo, peso_actual, microchip, tatuaje, url_foto, alergias, condiciones_preexistentes, fecha_registro, activo FROM mascotas";
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                int duenoId = rs.getInt("dueno_id");
                String nombre = rs.getString("nombre");
                int razaId = rs.getInt("raza_id");
                Date fechaN = rs.getDate("fecha_nacimiento");
                String sexoStr = rs.getString("sexo");
                Mascotas.Sexo sexo;
                try {
                    if (sexoStr == null) {
                    sexo = Mascotas.Sexo.Macho; // Valor por defecto
                    } else {
                        // Quita espacios y usa mayúscula inicial para coincidir con el enum
                        String normalizado = sexoStr.trim().substring(0, 1).toUpperCase() + sexoStr.trim().substring(1).toLowerCase();
                        sexo = Mascotas.Sexo.valueOf(normalizado);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Valor inválido en BD para 'sexo': " + sexoStr + " → se asignará Macho por defecto.");
                    sexo = Mascotas.Sexo.Macho;
                }
                double pesoActual = rs.getDouble("peso_actual");
                String microchip = rs.getString("microchip");
                String tatuaje = rs.getString("tatuaje");
                String url = rs.getString("url_foto");
                String alergias = rs.getString("alergias");
                String condiciones = rs.getString("condiciones_preexistentes");
                Timestamp  fechaR = rs.getTimestamp("fecha_registro");
                boolean activo = rs.getBoolean("activo");
                
                Mascotas m = new Mascotas(id, duenoId, nombre, razaId, fechaN, sexo , pesoActual, microchip, tatuaje, url, alergias, condiciones, fechaR, activo);
                lista.add(m);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
}