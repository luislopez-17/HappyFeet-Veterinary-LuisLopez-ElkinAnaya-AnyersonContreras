/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ProcedimientosEspecialesDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import model.ProcedimientosEspeciales;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class ProcedimientosEspecialesController {
    private ProcedimientosEspecialesDAO procedimientosDao;
    
    public ProcedimientosEspecialesController(){
        this.procedimientosDao = new ProcedimientosEspecialesDAO();
    }
    
    public void agregarProcedimiento(int mascotaId, int veterinarioId, String tipoProcedimiento, String nombreProcedimiento,
                                     String fechaHoraStr, int duracionMinutos, String informacionPreoperatoria,
                                     String detalleProcedimiento, String complicaciones, String seguimientoPostoperatorio,
                                     Date proximoControl, String estadoStr, double costo){
        
        if (mascotaId <= 0) {
            System.out.println("El ID de la mascota es obligatorio.");
            return;
        }
        if (veterinarioId <= 0) {
            System.out.println("El ID del veterinario es obligatorio.");
            return;
        }
        if (tipoProcedimiento == null || tipoProcedimiento.trim().isEmpty()) {
            System.out.println("El tipo de procedimiento no puede estar vacío.");
            return;
        }
        if (nombreProcedimiento == null || nombreProcedimiento.trim().isEmpty()) {
            System.out.println("El nombre del procedimiento no puede estar vacío.");
            return;
        }
        if (detalleProcedimiento == null || detalleProcedimiento.trim().isEmpty()) {
            System.out.println("El detalle del procedimiento no puede estar vacío.");
            return;
        }

        Timestamp fechaHora;
        try {
            fechaHora = Timestamp.valueOf(fechaHoraStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha inválido. Use el formato: yyyy-MM-dd HH:mm:ss");
            return;
        }

        ProcedimientosEspeciales.Estado estado;
        try {
            estado = ProcedimientosEspeciales.Estado.valueOf(
                estadoStr.replace(" ", "").trim()
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Estado inválido. Se usará 'Programado' por defecto.");
            estado = ProcedimientosEspeciales.Estado.Programado;
        }

        ProcedimientosEspeciales p = new ProcedimientosEspeciales(
            mascotaId, veterinarioId, tipoProcedimiento, nombreProcedimiento, fechaHora,
            duracionMinutos, informacionPreoperatoria, detalleProcedimiento, complicaciones,
            seguimientoPostoperatorio, proximoControl, estado, costo
        );

        try (Connection con = ConexionDB.conectar()) {
            procedimientosDao.agregar(p, mascotaId, veterinarioId);
        } catch (SQLException ex) {
            System.out.println("Error al agregar procedimiento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void actualizarProcedimiento(int id, int mascotaId, int veterinarioId, String tipoProcedimiento, String nombreProcedimiento,
                                        String fechaHoraStr, int duracionMinutos, String informacionPreoperatoria,
                                        String detalleProcedimiento, String complicaciones, String seguimientoPostoperatorio,
                                        Date proximoControl, String estadoStr, double costo){
        
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        Timestamp fechaHora;
        try {
            fechaHora = Timestamp.valueOf(fechaHoraStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha inválido. Use el formato: yyyy-MM-dd HH:mm:ss");
            return;
        }

        ProcedimientosEspeciales.Estado estado;
        try {
            estado = ProcedimientosEspeciales.Estado.valueOf(
                estadoStr.replace(" ", "").trim()
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Estado inválido. Se usará 'Programado' por defecto.");
            estado = ProcedimientosEspeciales.Estado.Programado;
        }

        ProcedimientosEspeciales p = new ProcedimientosEspeciales(
            id, mascotaId, veterinarioId, tipoProcedimiento, nombreProcedimiento, fechaHora,
            duracionMinutos, informacionPreoperatoria, detalleProcedimiento, complicaciones,
            seguimientoPostoperatorio, proximoControl, estado, costo
        );

        try (Connection con = ConexionDB.conectar()) {
            procedimientosDao.actualizar(p, mascotaId, veterinarioId);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar procedimiento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarProcedimiento(int id){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        ProcedimientosEspeciales p = new ProcedimientosEspeciales(
            id, 0, 0, "", "", null, 0, "", "", "", "", null,
                ProcedimientosEspeciales.Estado.Programado, 0
        );
        procedimientosDao.eliminar(p);
    }

    public List<ProcedimientosEspeciales> listar(){
        return procedimientosDao.listar();
    }
    
    //EXAMEN 
    public void finalizarProcedimiento(int idProcedimiento) {
        try {
            procedimientosDao.actualizarEstado(idProcedimiento, ProcedimientosEspeciales.Estado.Finalizado);
        } catch (SQLException e) {
            System.err.println("Error al finalizar el procedimiento: " + e.getMessage());
        }
    }
}
    