/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.VeterinarioDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import model.Veterinarios;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class VeterinarioController {
    private VeterinarioDAO veterinarioDao;
    
    public VeterinarioController(){
        this.veterinarioDao = new VeterinarioDAO();
    }
    
    public void agregar(String nombreCompleto, String documento, String licenciaProfesional, String especialidad,String telefono, String email,String fechacontratacionStr){
        if(nombreCompleto == null || nombreCompleto.trim().isBlank()){
            System.out.println("El nombre es obligatorio");
            return;
        }
        
        if(documento == null || documento.trim().isBlank()){
            System.out.println("El documento es obligatorio");
            return;
        }
        
        Date fechaContratacion = null;
        if (fechacontratacionStr != null && !fechacontratacionStr.trim().isEmpty()) {
            try {
                fechaContratacion = Date.valueOf(fechacontratacionStr.trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de fecha inválido. Use yyyy-MM-dd o deje vacío.");
                return;
            }
        }
        
        Veterinarios v = new Veterinarios(nombreCompleto, documento, licenciaProfesional, especialidad, telefono, email, fechaContratacion, true);
         try (Connection con = ConexionDB.conectar()) {
            veterinarioDao.agregar(v, con, email, documento, email);
        } catch (SQLException ex) {
            System.out.println("Error al agregar veterinario: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizar(int id, String nombreCompleto, String documento, String licenciaProfesional, String especialidad,String telefono, String email,String fechacontratacionStr, boolean activo){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        if(nombreCompleto == null || nombreCompleto.trim().isBlank()){
            System.out.println("El nombre es obligatorio");
            return;
        }
        
        if(documento == null || documento.trim().isBlank()){
            System.out.println("El documento es obligatorio");
            return;
        }
        
        Date fechaContratacion = null;
        if (fechacontratacionStr != null && !fechacontratacionStr.trim().isEmpty()) {
            try {
                fechaContratacion = Date.valueOf(fechacontratacionStr.trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de fecha inválido. Use yyyy-MM-dd o deje vacío.");
                return;
            }
        }
        
        Veterinarios v = new Veterinarios(id, nombreCompleto, documento, licenciaProfesional, especialidad, telefono, email, fechaContratacion, activo);
        try (Connection con = ConexionDB.conectar()) {
            veterinarioDao.actualizar(v);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar veterinario: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(int id){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        //se crea una especie "temporal" solo con el id
        Veterinarios v = new Veterinarios(id, "", "", "", "", "", "", null , true);
        veterinarioDao.eliminar(v);
    }
    
    public List<Veterinarios> listar(){
        return veterinarioDao.listar();
    }
}
