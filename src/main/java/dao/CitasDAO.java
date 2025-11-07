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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Citas;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class CitasDAO {
    public boolean existeMascota(Connection con, int mascotaId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM mascotas WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, mascotaId);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; // true si si existe
                }
            }
        }
        return false;
    }
    
    public boolean existeVeterinario(Connection con, int veterinarioId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM veterinarios WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, veterinarioId);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; // true si si existe
                }
            }
        }
        return false;
    }
    
    public boolean existeEstado(Connection con, int CitaestadoId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM cita_estados WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, CitaestadoId);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0; // true si si existe
                }
            }
        }
        return false;
    }
    
    public void agregar(Citas c, Connection con, int mascotaId, Integer veterinarioId, int citaestadoId) throws SQLException{
        if(!existeMascota(con, mascotaId)){
            System.out.println("El ID de la mascota no existe, no se puede crear la cita");
            return;
        }
        if (veterinarioId != null && veterinarioId > 0) {
            if (!existeVeterinario(con, veterinarioId)) {
                System.out.println("El ID del veterinario no existe, no se puede crear la cita");
                return;
            }
        }
        if(!existeEstado(con, citaestadoId)){
            System.out.println("El ID de cita estado no existe, no se puede crear la cita");
            return;
        }
        
        String sql = "INSERT INTO citas (mascota_id, veterinario_id, fecha_hora, motivo, estado_id, observaciones) VALUES (?,?,?,?,?,?)";
        try(PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, mascotaId);
            if (veterinarioId != null && veterinarioId > 0) {
                ps.setInt(2, veterinarioId);
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            ps.setTimestamp(3, c.getFechaHora());
            ps.setString(4, c.getMotivo());
            ps.setInt(5, citaestadoId);
            ps.setString(6, c.getObservaciones());
            
            int filas = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    
                    System.out.println("Cita insertada con id = " + idGenerado);
                }
            }
            System.out.println("Cita agregada. Filas afectadas: " + filas);
        }catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        } 
    }
    
    public void actualizar(Citas c,int mascotaId, Integer veterinarioId, int citaestadoId) throws SQLException{
        try (Connection con = ConexionDB.conectar()) {
            if(!existeMascota(con, mascotaId)){
                System.out.println("El ID de la mascota no existe, no se puede crear la cita");
                return;
            }
            if (veterinarioId != null && veterinarioId > 0) {
                if (!existeVeterinario(con, veterinarioId)) {
                    System.out.println("El ID del veterinario no existe, no se puede crear la cita");
                    return;
                }
            }
            if(!existeEstado(con, citaestadoId)){
                System.out.println("El ID de cita estado no existe, no se puede crear la cita");
                return;
            }
            
            String sql = "UPDATE citas SET mascota_id = ?, veterinario_id = ?, fecha_hora = ?, motivo = ?, estado_id = ?, observaciones = ? WHERE id = ?";               
                try(
                PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, mascotaId);
                if (veterinarioId != null && veterinarioId > 0) {
                    ps.setInt(2, veterinarioId);
                } else {
                    ps.setNull(2, java.sql.Types.INTEGER);
                }
                ps.setTimestamp(3, c.getFechaHora());
                ps.setString(4, c.getMotivo());
                ps.setInt(5, citaestadoId);
                ps.setString(6, c.getObservaciones());
                ps.setInt(7, c.getId());

                int filas = ps.executeUpdate();
                if(filas > 0){
                    System.out.println("Actualizacion correcta, filas afectadas = " + filas);
                }else{
                    System.out.println("No se encontró la cita con id: " + c.getId());
                }
            }catch(SQLException ex){
                System.out.println("Error SQL al actualizar raza: " + ex.getMessage());
                ex.printStackTrace();
            }
        }        
    }
    
    public void eliminar(Citas c){
        String sql = "DELETE FROM citas WHERE id = ?";
        try(Connection con = ConexionDB.conectar();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, c.getId());
            
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("Cita eliminada correctamente, filas afectadas = " + filas);
            }else{
                System.out.println("No se encontró la cita con id: " + c.getId());
            }
        }catch(SQLException ex){
            System.out.println("Error SQL al elimar la raza: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<Citas> listar(){
        List<Citas> lista = new ArrayList<>();
        
        String sql = "SELECT id, mascota_id, veterinario_id, fecha_hora, motivo, estado_id, observaciones, fecha_creacion FROM citas";
        
        try(Connection con = ConexionDB.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("id");
                int mascotaId = rs.getInt("mascota_id");
                int veterinarioId = rs.getInt("veterinario_id");
                Timestamp fechaHora = rs.getTimestamp("fecha_hora");
                String motivo = rs.getString("motivo");
                int estadoId = rs.getInt("estado_id");
                String observaciones = rs.getString("observaciones");
                Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");
                
                Citas c = new Citas(id, mascotaId, veterinarioId, fechaHora, motivo, estadoId, observaciones, fechaCreacion);
                lista.add(c);
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
    
    public boolean verificarDisponibilidad(Connection con, int veterinarioId, LocalDate fecha, LocalTime hora) throws SQLException {
        LocalTime finNueva = hora.plusMinutes(30);

        String sql = """
            SELECT fecha_hora
            FROM citas
            WHERE veterinario_id = ? AND DATE(fecha_hora) = ?
        """;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, veterinarioId);
            stmt.setDate(2, java.sql.Date.valueOf(fecha));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalTime inicioExistente = rs.getTimestamp("fecha_hora").toLocalDateTime().toLocalTime();
                    LocalTime finExistente = inicioExistente.plusMinutes(30);

                    if (hora.isBefore(finExistente) && finNueva.isAfter(inicioExistente)) {
                    System.out.printf("Ya tiene una cita de %s a %s%n", inicioExistente, finExistente);
                    return false;
                    }
                }
            }
        }
        return true;
    }
}


